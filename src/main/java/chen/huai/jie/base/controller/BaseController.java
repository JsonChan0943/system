package chen.huai.jie.base.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import chen.huai.jie.base.system.ConstantFile;
import chen.huai.jie.base.utils.DateFormatUtils;
import chen.huai.jie.base.utils.RedisUtils;
import chen.huai.jie.system.entity.LogEntity;
import chen.huai.jie.system.entity.UserEntity;
import chen.huai.jie.system.service.LogService;

import com.alibaba.fastjson.JSON;

/**
 * controller的基类
 * 
 * @author Administrator
 * 
 */
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected static final String ROWS = "rows";
	protected static final String TOTAL = "total";

	@Resource
	private LogService logServiceImpl;

	/**
	 * 如果有异常，页面跳转到特定页
	 * 
	 * @param req
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	public String execute(HttpServletRequest req, Exception ex) {
		ex.printStackTrace();
		StackTraceElement[] st = ex.getStackTrace();
		req.setAttribute("errTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		req.setAttribute("errInfo", st[0]);
		req.setAttribute("ex", ex);
		logger.info(ex.getMessage());
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		ex.printStackTrace(new PrintWriter(buf, true));
		String expMessage = buf.toString();
		req.setAttribute("exceptionMsg", expMessage);
		try {
			if (buf != null) {
				buf.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "common/error";
	}

	/**
	 * 把Object对象用Json方式写回页面
	 * 
	 * @param object
	 */
	protected void writeJson(Object object, HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			logger.info("输出Json字符串：" + json);
			response.setContentType("text/html;charset=utf-8");
			writer = response.getWriter();
			writer.write(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();// 用完IO流必须要关闭，放在finally里保证无论程序执行是否正确都能关闭IO流
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	protected void download(String fileName, HttpServletResponse response) throws IOException {
		String fileFolder = System.getProperty("java.io.tmpdir");
		File file = new File(fileFolder + fileName);
		OutputStream os = response.getOutputStream();
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			response.setContentType("application/octet-stream; charset=utf-8");
			os.write(FileUtils.readFileToByteArray(file));
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	/**
	 * 系统日志记录
	 * 
	 * @param request
	 * @param subject
	 *            日志主题
	 * @param content
	 *            日志内容
	 */
	protected void addLog(HttpServletRequest request, String subject, String content) {
		LogEntity entity = new LogEntity();
		entity.setId(UUID.randomUUID().toString());
		entity.setSubject(subject);
		entity.setContent(content);
		HttpSession session = request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute(ConstantFile.SESSION_USER_BEAN);
		String operator = userEntity.getUser_name();
		entity.setOperator(operator);
		entity.setCreate_time(new Date());
		logServiceImpl.addLog(entity);
	}

	/**
	 * 把值存入缓存
	 * 
	 * @param key
	 * @param value
	 */
	protected void setAttributeToSession(String key, String value) {
		RedisUtils.setString(key, value);
	}

	/**
	 * 从缓存里获取值
	 * 
	 * @param key
	 * @return
	 */
	protected String getAttributeFromSession(String key) {
		return RedisUtils.getString(key);
	}
}
