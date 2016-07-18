package chen.huai.jie.system.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chen.huai.jie.base.controller.BaseController;
import chen.huai.jie.base.utils.BeanUtils;
import chen.huai.jie.base.utils.DateFormatUtils;
import chen.huai.jie.base.utils.ExportExcelUtil;
import chen.huai.jie.system.bean.export.LogBeanForExport;
import chen.huai.jie.system.entity.LogEntity;
import chen.huai.jie.system.pager.LogPager;
import chen.huai.jie.system.service.LogService;

/**
 * 系统日志管理
 * 
 * @author chenhuaijie
 * 
 */
@Controller
@RequestMapping("/system/log")
public class SysLogController extends BaseController {
	@Resource
	private LogService logServiceImpl;

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index() {
		return "system/log/SystemLogIndex";
	}

	/**
	 * 获取数据
	 * 
	 * @param pager
	 * @param response
	 */
	@RequestMapping("/list.html")
	public void list(LogPager pager, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<LogEntity> list = logServiceImpl.findLogByPage(pager);
		Long total = logServiceImpl.countLogCnt(pager);
		map.put(ROWS, list);
		map.put(TOTAL, total);
		writeJson(map, response);
	}

	/**
	 * 导出用户列表
	 * 
	 * @param pager
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportLogList.html")
	public void exportUserList(LogPager pager, HttpServletResponse response) throws IOException {
		List<LogEntity> list = logServiceImpl.findLogByPage(pager);
		List<LogBeanForExport> logBeanForExportList = new ArrayList<LogBeanForExport>();
		for (LogEntity logEntity : list) {
			LogBeanForExport logBeanForExport = new LogBeanForExport();
			try {
				BeanUtils.copyBean2Bean(logBeanForExport, logEntity);
				logBeanForExport.setCreate_time(DateFormatUtils.format(logEntity.getCreate_time(),
						"yyyy-MM-dd HH:mm:ss"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			logBeanForExportList.add(logBeanForExport);
		}
		String fileFolder = System.getProperty("java.io.tmpdir");
		String fileName = "SystemLogList.xlsx";
		String filePath = fileFolder + fileName;
		String pattern = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		String sheetTitle = "系统日志列表";
		String[] strings = { "主题", "内容", "操作人", "记录时间" };
		List<String[]> headerList = new ArrayList<String[]>();
		headerList.add(strings);
		ExportExcelUtil<LogBeanForExport> excelUtil = new ExportExcelUtil<LogBeanForExport>();
		Workbook wk = new SXSSFWorkbook();
		Workbook workbook = excelUtil.doExportExcel1(wk, sheetTitle, headerList, logBeanForExportList, pattern, 0,
				false);
		OutputStream out = null;
		out = new FileOutputStream(filePath);
		workbook.write(out);
		out.flush();
		out.close();
		writeJson(fileName, response);
	}

	/**
	 * 下载文件
	 * 
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/downLoadFile.html")
	public void downLoadFile(String fileName, HttpServletResponse response) throws IOException {
		download(fileName, response);
	}
}
