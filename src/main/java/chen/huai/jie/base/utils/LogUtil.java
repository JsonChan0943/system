package chen.huai.jie.base.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 * @author Administrator
 *
 */
public class LogUtil {
	private static Logger log = LoggerFactory.getLogger(LogUtil.class);  
	
	/**
	 * 记录异常日志到日志文件
	 * @param e
	 */
	public static void log(Exception e){
		StackTraceElement[] st = e.getStackTrace();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		log.info("报错时间："+sdf.format(now));
		log.info("异常类型："+e);
		log.info("\n异常信息："+st[0]);
		log.error("错误堆栈信息",e);
	}
}
