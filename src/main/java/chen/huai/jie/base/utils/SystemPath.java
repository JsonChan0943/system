package chen.huai.jie.base.utils;

/**
 * Class SystemPath 
 * 
 * @author jacksonzzy
 * @version $Revision:0.1,$Date: 2015年8月25日$
 * 
 *          Description: 得到当前应用的系统路径
 * 
 * 
 *          History: // 历史修改记录
 * 
 *          <author> <time> <version > <desc>
 * 
 */
public class SystemPath {

	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst(
				"WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		
		if(resultPath.indexOf("windows")<0) {
			resultPath = separator + resultPath;
		}
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("os.name"));
		System.out.println(getSysPath());
		System.out.println(System.getProperty("java.io.tmpdir"));
		System.out.println(getSeparator());
		System.out.println(getClassPath());
	}
}
