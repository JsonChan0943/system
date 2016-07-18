package chen.huai.jie.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/***
 * 导出CSV文件 适用范围：导出的数据list中对象（可以包含list属性，即主记录下可以包括数量不定的子记录list） 主记录居左对齐，只有第一行有表头
 * 子记录居左缩进对齐，每个主记录下子记录第一行有表头
 * 
 * 支持分批写入，同一个文件路径下同一个文件进行叠加写入
 * 
 * 使用反射机制
 * 
 * @author jacksonzzy
 * 
 */
public class ExportCsvUtil<T> {
	// log
	private static Log log = LogFactory.getLog(ExportCsvUtil.class);

	/***
	 * list(可能嵌套数量不定的list)数据写入csv文件
	 * 
	 * fileFolder : 写入csv文件所在的文件夹 filePath : 写入csv文件的完整路径 headerList :
	 * 写入csv文件记录的标题列名 contentList : 写入csv文件记录集合
	 */
	public void exportListToCsv(String fileFolder, String filePath, List headerList, List contentList) {
		FileOutputStream fis = null;
		OutputStreamWriter osw = null;
		File f = null;
		try {
			f = new File(fileFolder);
			// 文件夹不存在，先创建文件夹
			if (!f.exists()) {
				f.mkdirs();
				f.setReadable(true);
				f.setWritable(true);
				}
			fis = new FileOutputStream(filePath, true);
			osw = new OutputStreamWriter(fis, "GBK");
			// 循环数据list，写入数据
			for (int i = 0; i < contentList.size(); i++) {
				// 记录当前属性中第几个list
				int flag = 0;
				// 记录需要写入文件的
				StringBuffer str = new StringBuffer();
				// 第一行写入表头
				if (i == 0&&headerList!=null&&headerList.size()>0) {
					str.append(headerList.get(0));
					str.append("\r\n");
				}
				T t = (T) contentList.get(i);
				Field[] fields = t.getClass().getDeclaredFields();
				// 循环写入字段值
				for (int ii = 0; ii < fields.length; ii++) {
					Field field = fields[ii];
					String fieldName = field.getName();
					String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 当属性为list时
					if (value instanceof List) {
						flag++;
						List<T> list = (List<T>) value;
						if (list != null && list.size() > 0) {
							// 先删除上一行记录的最后一个逗号
							str.deleteCharAt(str.length() - 1);
							// 子记录写入表头
							str.append("\r\n");
							str.append(",");
							str.append(headerList.get(flag));
							str.append(this.getExportStr(list));
						}
					} else {// 不是list时，直接写入文件
						str.append(value == null ? "" : value);
						str.append(",");
					}
				}
				str.deleteCharAt(str.length() - 1);
				str.append("\r\n");
				osw.write(str.toString());
				osw.flush();
			}
			osw.close();
		} catch (Exception e) {
			log.error(e.getStackTrace()[0].toString());
		}
	}

	/***
	 * 处理list 得到需要写入文件的String
	 */
	private StringBuffer getExportStr(List<T> list) {
		// 记录需要写入文件的
		StringBuffer str = new StringBuffer("\r\n");
		try {
			for (int a = 0; a < list.size(); a++) {
				str.append(",");
				T t = (T) list.get(a);
				Field[] fields = t.getClass().getDeclaredFields();
				// 循环写入字段值
				for (int aa = 0; aa < fields.length; aa++) {
					Field field = fields[aa];
					String fieldName = field.getName();
					String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					str.append(value == null ? "" : value);
					str.append(",");
				}
				// 最后一条记录不用换行，去除逗号
				if (a < list.size() - 1) {
					str.deleteCharAt(str.length() - 1);
					str.append("\r\n");
				}
			}
		} catch (Exception e) {
			log.error(e.getStackTrace()[0].toString());
		}
		return str;
	}

	 /***
	 * 测试类
	 
	 public static void main(String[] args){
	 //子记录一
	 List list1 = new ArrayList();
	 B aa1 = new B(11L, "子记录11", "1111");
	 B aa2 = new B(12L, "子记录12", "1122");
	 list1.add(aa1);
	 list1.add(aa2);
	 //子记录二
	 List list2 = new ArrayList();
	 B bb1 = new B(21L, "子记录21", "2211");
	 B bb2 = new B(22L, "子记录22", "2222");
	 B bb3 = new B(23L, "子记录23", "2233");
	 list2.add(bb1);
	 list2.add(bb2);
	 list2.add(bb3);
	 //组装数据
	 A a1 = new A(1L, "主记录1", "", list1, list2);
	 A a2 = new A(2L, "主记录2", "", list1, list2);
	 List contentList = new ArrayList();
	 contentList.add(a1);
	 contentList.add(a2);
	 //标题
	 List headerList = new ArrayList();
	 String s = "主记录ID,主记录名称";
	 String ss1 = "子记录一ID,子记录一名称,子记录一描述";
	 String ss2 = "子记录二ID,子记录二名称,子记录二描述";
	 headerList.add(s);
	 headerList.add(ss1);
	 headerList.add(ss2);
	 ExportCsvUtil<A> exportCsv = new ExportCsvUtil<A>();
	 exportCsv.exportListToCsv("D:/", "D:/test.csv", headerList, contentList);
	 }*/
}
