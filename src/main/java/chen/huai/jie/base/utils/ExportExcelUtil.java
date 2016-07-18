package chen.huai.jie.base.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/***
 * 导出Excel文件
 * 
 * 适用范围：导出的数据list中对象（可以包含list属性，即主记录下可以包括数量不定的子记录list） 主记录居左对齐，只有第一行有表头
 * 子记录居左缩进对齐，每个主记录下子记录第一行有表头
 * 
 * 数据量很大的时候，需要分sheet导入
 * 
 * 使用反射机制
 * 
 * @author cj
 * 
 */
public class ExportExcelUtil<T> {

	// log
	private static Log log = LogFactory.getLog(ExportExcelUtil.class);

	/***
	 * 导出excel文件
	 * 
	 * 没有数据条数限制 -- 适用excel2007及以上版本
	 * 
	 * 可以分sheet写入，防止内存溢出
	 * 
	 * 得到Workbook
	 * 
	 * sheetTitle : excel文件sheet名称 headerList : excel文件中数据表头名称，以String[]组装
	 * dataset : 写入excel文件的数据集 pattern : 针对日期时间的格式定义 cellnums : 减少多少列 isIndex :
	 * 每行是否需要序号
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Workbook doExportExcel1(Workbook workbook, String sheetTitle, List<String[]> headerList, Collection<T> dataset, String pattern,
			int cellnums, boolean isIndex) {
		// 生成一个sheet
		Sheet sheet = workbook.createSheet(sheetTitle);
		// 设置表格默认列宽16个字符
		sheet.setDefaultColumnWidth(16);

		// //主记录样式
		// //生成一个样式 - 表头使用
		CellStyle styleForTitle = workbook.createCellStyle();
		// 设置这个样式
		styleForTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中对齐
		// styleForTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		// styleForTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN); //左边框
		// styleForTitle.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边框
		// styleForTitle.setBorderRight(HSSFCellStyle.BORDER_THIN); //右边框
		// 字体
		Font fontForTitle = workbook.createFont();
		fontForTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 粗体
		fontForTitle.setFontHeightInPoints((short) 10); // 字号
		fontForTitle.setFontName("宋体"); // 字体
		styleForTitle.setFont(fontForTitle);

		// //生成一个样式 - 内容使用 - 字符串
		CellStyle styleForStr = workbook.createCellStyle();
		// 设置这个样式
		styleForStr.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中对齐
		// styleForStr.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		// styleForStr.setBorderLeft(HSSFCellStyle.BORDER_THIN); //左边框
		// styleForStr.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边框
		// styleForStr.setBorderRight(HSSFCellStyle.BORDER_THIN); //右边框
		// 字体
		Font fontForStr = workbook.createFont();
		fontForStr.setFontHeightInPoints((short) 10); // 字号
		fontForStr.setFontName("宋体"); // 字体
		styleForStr.setFont(fontForStr);

		// //生成一个样式 - 内容使用 - 数字
		CellStyle styleForNum = workbook.createCellStyle();
		// 设置这个样式
		styleForNum.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 右对齐
		// styleForNum.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		// styleForNum.setBorderLeft(HSSFCellStyle.BORDER_THIN); //左边框
		// styleForNum.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边框
		// styleForNum.setBorderRight(HSSFCellStyle.BORDER_THIN); //右边框
		// styleForNum.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
		// //整数部分三位逗号隔开，小数显示实际位数（最多4位，最少2位）
		// 字体
		Font fontForNum = workbook.createFont();
		fontForNum.setFontHeightInPoints((short) 10); // 字号
		fontForNum.setFontName("宋体"); // 字体
		styleForNum.setFont(fontForNum);

		// //子记录样式
		// 生成样式 - 子记录背景颜色 - 用于区分主记录
		short aa = IndexedColors.GREY_25_PERCENT.getIndex();
		short bb = CellStyle.SOLID_FOREGROUND;

		// 生成一个样式 - 表头使用
		CellStyle lstyleForTitle = workbook.createCellStyle();
		// 设置这个样式
		lstyleForTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中对齐
		lstyleForTitle.setFillForegroundColor(aa);
		lstyleForTitle.setFillPattern(bb);
		// 字体
		Font lfontForTitle = workbook.createFont();
		lfontForTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 粗体
		lfontForTitle.setFontHeightInPoints((short) 10); // 字号
		lfontForTitle.setFontName("宋体"); // 字体
		lstyleForTitle.setFont(lfontForTitle);

		// //生成一个样式 - 内容使用 - 字符串
		CellStyle lstyleForStr = workbook.createCellStyle();
		// 设置这个样式
		lstyleForStr.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中对齐
		lstyleForStr.setFillForegroundColor(aa);
		lstyleForStr.setFillPattern(bb);
		// 字体
		Font lfontForStr = workbook.createFont();
		lfontForStr.setFontHeightInPoints((short) 10); // 字号
		lfontForStr.setFontName("宋体"); // 字体
		lstyleForStr.setFont(lfontForStr);

		// //生成一个样式 - 内容使用 - 数字
		CellStyle lstyleForNum = workbook.createCellStyle();
		// 设置这个样式
		lstyleForNum.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 右对齐
		lstyleForNum.setFillForegroundColor(aa);
		lstyleForNum.setFillPattern(bb);
		// 字体
		Font lfontForNum = workbook.createFont();
		lfontForNum.setFontHeightInPoints((short) 10); // 字号
		lfontForNum.setFontName("宋体"); // 字体
		lstyleForNum.setFont(lfontForNum);

		// 产生表头部分 -- 主记录表头 只要第一行产生
		Row row = sheet.createRow(0);
		String[] header1 = {};
		if (headerList != null && headerList.size() > 0) {
			header1 = headerList.get(0);
		}
		for (short i = 0; i < header1.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(styleForTitle); // 设置样式
			HSSFRichTextString text = new HSSFRichTextString(header1[i]);
			cell.setCellValue(text);
		}

		// 遍历数据集合，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			int flag = 0;// 记录当前属性中第几个list
			// 创建下一行
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length - cellnums; i++) {
				Cell cell = row.createCell(i);
				// 添加序号
				if (isIndex) {
					if (i == (short) 0) {
						cell.setCellValue(index);
					}
				}
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof Integer) {
						int intValue = (Integer) value;
						cell.setCellValue(intValue);
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof Short) {
						cell.setCellValue((Short) value);
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof Float) {
						float fValue = (Float) value;
						cell.setCellValue(fValue);
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof Double) {
						double dValue = (Double) value;
						cell.setCellValue(dValue);
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof Long) {
						long longValue = (Long) value;
						cell.setCellValue(longValue);
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof BigDecimal) {
						BigDecimal longValue = (BigDecimal) value;
						cell.setCellValue(longValue.doubleValue());
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
						cell.setCellValue(textValue);
						cell.setCellStyle(styleForStr); // 设置样式
					} else if (value instanceof List) {
						flag++;
						List<T> list = (List<T>) value;
						String[] header2 = {};
						if (list != null && list.size() > 0) {
							// 写入子记录表头
							index++;
							Row lrow = sheet.createRow(index);
							header2 = (String[]) headerList.get(flag);
							for (short ii = 0; ii < header2.length; ii++) {
								Cell lcell = lrow.createCell(ii + 1);
								lcell.setCellStyle(lstyleForTitle); // 设置样式
								HSSFRichTextString text = new HSSFRichTextString(header2[ii]);
								lcell.setCellValue(text);
							}
							// 遍历子记录集，产生数据行
							for (int j = 0; j < list.size(); j++) {
								index++;
								Row llrow = sheet.createRow(index);
								T lt = (T) list.get(j);
								// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
								Field[] lfields = lt.getClass().getDeclaredFields();
								for (short jj = 0; jj < lfields.length - cellnums; jj++) {
									Cell llcell = llrow.createCell(jj + 1);
									Field lfield = lfields[jj];
									String lfieldName = lfield.getName();
									String lgetMethodName = "get" + lfieldName.substring(0, 1).toUpperCase() + lfieldName.substring(1);
									try {
										Class ltCls = lt.getClass();
										Method lgetMethod = ltCls.getMethod(lgetMethodName, new Class[] {});
										Object lvalue = lgetMethod.invoke(lt, new Object[] {});
										// 判断值的类型后进行强制类型转换
										String ltextValue = null;
										if (lvalue instanceof Integer) {
											int intValue = (Integer) lvalue;
											llcell.setCellValue(intValue);
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof Short) {
											llcell.setCellValue((Short) lvalue);
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof Float) {
											float fValue = (Float) lvalue;
											llcell.setCellValue(fValue);
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof Double) {
											double dValue = (Double) lvalue;
											llcell.setCellValue(dValue);
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof Long) {
											long longValue = (Long) lvalue;
											llcell.setCellValue(longValue);
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof BigDecimal) {
											BigDecimal longValue = (BigDecimal) lvalue;
											llcell.setCellValue(longValue.doubleValue());
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof Date) {
											Date date = (Date) lvalue;
											SimpleDateFormat sdf = new SimpleDateFormat(pattern);
											ltextValue = sdf.format(date);
											llcell.setCellValue(ltextValue);
											llcell.setCellStyle(lstyleForStr); // 设置样式
										} else {
											// 其它数据类型都当作字符串简单处理
											Object obj_value = (lvalue == null) ? "" : lvalue;
											ltextValue = obj_value.toString();
											llcell.setCellValue(ltextValue);
											llcell.setCellStyle(lstyleForStr); // 设置样式
										}
									} catch (SecurityException e) {
										log.error(e.getStackTrace()[0].toString());
									} catch (NoSuchMethodException e) {
										log.error(e.getStackTrace()[0].toString());
									} catch (IllegalArgumentException e) {
										log.error(e.getStackTrace()[0].toString());
									} catch (IllegalAccessException e) {
										log.error(e.getStackTrace()[0].toString());
									} catch (InvocationTargetException e) {
										log.error(e.getStackTrace()[0].toString());
									} finally {
										// 清理资源

									}
								}
							}
						}
					} else {
						// 其它数据类型都当作字符串简单处理
						Object obj_value = (value == null) ? "" : value;
						textValue = obj_value.toString();
						cell.setCellValue(textValue);
						cell.setCellStyle(styleForStr); // 设置样式
					}
				} catch (Exception e) {
					log.error(e.getStackTrace()[0].toString());
				} finally {
					// 清理资源

				}
			}
		}
		it = null;
		return workbook;
	}

	/***
	 * 导出excel文件
	 * 
	 * 数据量有限制 -- 不能超过65537条记录
	 * 
	 * 适合小数据量的导出
	 * 
	 * 得到InputStream
	 * 
	 * sheetTitle : excel文件sheet名称 headerList : excel文件中数据表头名称，以String[]组装
	 * dataset : 写入excel文件的数据集 pattern : 针对日期时间的格式定义 cellnums : 减少多少列 isIndex :
	 * 每行是否需要序号
	 */
	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public InputStream doExportExcel2(String sheetTitle, List<String[]> headerList, Collection<T> dataset, String pattern, int cellnums,
			boolean isIndex) {
		// 声明一个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格，sheet
		HSSFSheet sheet = workbook.createSheet(sheetTitle);
		// 设置表格默认列宽16个字符
		sheet.setDefaultColumnWidth(16);

		// //生成一个样式 - 表头使用
		CellStyle styleForTitle = workbook.createCellStyle();
		// 设置这个样式
		styleForTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中对齐
		// styleForTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		// styleForTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN); //左边框
		// styleForTitle.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边框
		// styleForTitle.setBorderRight(HSSFCellStyle.BORDER_THIN); //右边框
		// 字体
		Font fontForTitle = workbook.createFont();
		fontForTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 粗体
		fontForTitle.setFontHeightInPoints((short) 10); // 字号
		fontForTitle.setFontName("宋体"); // 字体
		styleForTitle.setFont(fontForTitle);

		// 生成一个样式 - 内容使用 - 字符串
		CellStyle styleForStr = workbook.createCellStyle();
		// 设置这个样式
		styleForStr.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中对齐
		// styleForStr.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		// styleForStr.setBorderLeft(HSSFCellStyle.BORDER_THIN); //左边框
		// styleForStr.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边框
		// styleForStr.setBorderRight(HSSFCellStyle.BORDER_THIN); //右边框
		// 字体
		Font fontForStr = workbook.createFont();
		fontForStr.setFontHeightInPoints((short) 10); // 字号
		fontForStr.setFontName("宋体"); // 字体
		styleForStr.setFont(fontForStr);

		// //生成一个样式 - 内容使用 - 数字
		CellStyle styleForNum = workbook.createCellStyle();
		// 设置这个样式
		styleForNum.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 右对齐
		// styleForNum.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		// styleForNum.setBorderLeft(HSSFCellStyle.BORDER_THIN); //左边框
		// styleForNum.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边框
		// styleForNum.setBorderRight(HSSFCellStyle.BORDER_THIN); //右边框
		// styleForNum.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
		// //整数部分三位逗号隔开，小数显示实际位数（最多4位，最少2位）
		// 字体
		Font fontForNum = workbook.createFont();
		fontForNum.setFontHeightInPoints((short) 10); // 字号
		fontForNum.setFontName("宋体"); // 字体
		styleForNum.setFont(fontForNum);

		// //子记录样式
		// 生成样式 - 子记录背景颜色 - 用于区分主记录
		short aa = IndexedColors.RED.getIndex();
		short bb = CellStyle.SOLID_FOREGROUND;

		// //生成一个样式 - 内容使用 - 字符串
		CellStyle lstyleForStr = workbook.createCellStyle();
		// 设置这个样式
		lstyleForStr.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中对齐
		lstyleForStr.setFillForegroundColor(aa);
		lstyleForStr.setFillPattern(bb);
		// 字体
		Font lfontForStr = workbook.createFont();
		lfontForStr.setFontHeightInPoints((short) 10); // 字号
		lfontForStr.setFontName("宋体"); // 字体
		lstyleForStr.setFont(lfontForStr);

		// //生成一个样式 - 内容使用 - 数字
		CellStyle lstyleForNum = workbook.createCellStyle();
		// 设置这个样式
		lstyleForNum.setAlignment(HSSFCellStyle.ALIGN_RIGHT); // 右对齐
		lstyleForNum.setFillForegroundColor(aa);
		lstyleForNum.setFillPattern(bb);
		// 字体
		Font lfontForNum = workbook.createFont();
		lfontForNum.setFontHeightInPoints((short) 10); // 字号
		lfontForNum.setFontName("宋体"); // 字体
		lstyleForNum.setFont(lfontForNum);

		// 产生表头部分 -- 主记录表头 只要第一行产生
		HSSFRow row = sheet.createRow(0);
		String[] header1 = {};
		if (headerList != null && headerList.size() > 0) {
			header1 = headerList.get(0);
		}
		for (short i = 0; i < header1.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(styleForTitle); // 设置样式
			HSSFRichTextString text = new HSSFRichTextString(header1[i]);
			cell.setCellValue(text);
		}

		// 遍历数据集合，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			int flag = 0;// 记录当前属性中第几个list
			// 创建下一行
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length - cellnums; i++) {
				HSSFCell cell = row.createCell(i);
				// 添加序号
				if (isIndex) {
					if (i == (short) 0) {
						cell.setCellValue(index);
					}
				}
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof Integer) {
						int intValue = (Integer) value;
						cell.setCellValue(intValue);
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof Short) {
						cell.setCellValue((Short) value);
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof Float) {
						float fValue = (Float) value;
						cell.setCellValue(fValue);
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof Double) {
						double dValue = (Double) value;
						cell.setCellValue(dValue);
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof Long) {
						long longValue = (Long) value;
						cell.setCellValue(longValue);
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof BigDecimal) {
						BigDecimal longValue = (BigDecimal) value;
						cell.setCellValue(longValue.doubleValue());
						cell.setCellStyle(styleForNum); // 设置样式
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
						cell.setCellValue(textValue);
						cell.setCellStyle(styleForStr); // 设置样式
					} else if (value instanceof List) {
						flag++;
						List<T> list = (List<T>) value;
						String[] header2 = {};
						if (list != null && list.size() > 0) {
							// 写入子记录表头
							index++;
							HSSFRow lrow = sheet.createRow(index);
							header2 = (String[]) headerList.get(flag);
							for (short ii = 0; ii < header2.length; ii++) {
								HSSFCell lcell = lrow.createCell(ii + 1);
								lcell.setCellStyle(styleForTitle); // 设置样式
								HSSFRichTextString text = new HSSFRichTextString(header2[ii]);
								lcell.setCellValue(text);
							}
							// 遍历子记录集，产生数据行
							for (int j = 0; j < list.size(); j++) {
								index++;
								HSSFRow llrow = sheet.createRow(index);
								T lt = (T) list.get(j);
								// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
								Field[] lfields = lt.getClass().getDeclaredFields();
								for (short jj = 0; jj < lfields.length - cellnums; jj++) {
									HSSFCell llcell = llrow.createCell(jj + 1);
									Field lfield = lfields[jj];
									String lfieldName = lfield.getName();
									String lgetMethodName = "get" + lfieldName.substring(0, 1).toUpperCase() + lfieldName.substring(1);
									try {
										Class ltCls = lt.getClass();
										Method lgetMethod = ltCls.getMethod(lgetMethodName, new Class[] {});
										Object lvalue = lgetMethod.invoke(lt, new Object[] {});
										// 判断值的类型后进行强制类型转换
										String ltextValue = null;
										if (lvalue instanceof Integer) {
											int intValue = (Integer) lvalue;
											llcell.setCellValue(intValue);
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof Short) {
											llcell.setCellValue((Short) lvalue);
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof Float) {
											float fValue = (Float) lvalue;
											llcell.setCellValue(fValue);
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof Double) {
											double dValue = (Double) lvalue;
											llcell.setCellValue(dValue);
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof Long) {
											long longValue = (Long) lvalue;
											llcell.setCellValue(longValue);
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof BigDecimal) {
											BigDecimal longValue = (BigDecimal) lvalue;
											llcell.setCellValue(longValue.doubleValue());
											llcell.setCellStyle(lstyleForNum); // 设置样式
										} else if (lvalue instanceof Date) {
											Date date = (Date) lvalue;
											SimpleDateFormat sdf = new SimpleDateFormat(pattern);
											ltextValue = sdf.format(date);
											llcell.setCellValue(ltextValue);
											llcell.setCellStyle(lstyleForStr); // 设置样式
										} else {
											// 其它数据类型都当作字符串简单处理
											Object obj_value = (lvalue == null) ? "" : lvalue;
											ltextValue = obj_value.toString();
											llcell.setCellValue(ltextValue);
											llcell.setCellStyle(lstyleForStr); // 设置样式
										}
									} catch (SecurityException e) {
										log.error(e.getStackTrace()[0].toString());
									} catch (NoSuchMethodException e) {
										log.error(e.getStackTrace()[0].toString());
									} catch (IllegalArgumentException e) {
										log.error(e.getStackTrace()[0].toString());
									} catch (IllegalAccessException e) {
										log.error(e.getStackTrace()[0].toString());
									} catch (InvocationTargetException e) {
										log.error(e.getStackTrace()[0].toString());
									} finally {
										// 清理资源
									}
								}
							}
						}
					} else {
						// 其它数据类型都当作字符串简单处理
						Object obj_value = (value == null) ? "" : value;
						textValue = obj_value.toString();
						cell.setCellValue(textValue);
						cell.setCellStyle(styleForStr); // 设置样式
					}
				} catch (Exception e) {
					log.error(e.getStackTrace()[0].toString());
				} finally {
					// 清理资源

				}
			}
		}
		it = null;
		// 以流的方式
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			workbook.write(out);
		} catch (IOException e) {
			log.error(e.getStackTrace()[0].toString());
		}
		byte[] content = out.toByteArray();
		InputStream in = new ByteArrayInputStream(content);
		return in;
	}
	
	/***
	 * 导出excel文件
	 * 
	 * 数据量有限制 -- 不能超过65537条记录
	 * 
	 * 适合小数据量的导出
	 * 
	 * 得到InputStream
	 * 
	 * sheetTitle : excel文件sheet名称
	 * headerList : excel文件中数据表头名称，以String[]组装
	 * dataset : 写入excel文件的数据集
	 * pattern : 针对日期时间的格式定义
	 * cellnums : 减少多少列
	 * isIndex : 每行是否需要序号
	 */
	public HSSFWorkbook doExportExcel2(HSSFWorkbook workbook, String sheetTitle, String[] header1, Collection<T> dataset, String pattern, int cellnums, boolean isIndex){
		//生成一个表格，sheet
		HSSFSheet sheet = workbook.createSheet(sheetTitle);
		//设置表格默认列宽16个字符
		sheet.setDefaultColumnWidth(16);
		
	////生成一个样式 - 表头使用
		CellStyle styleForTitle = workbook.createCellStyle();
		//设置这个样式
		styleForTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中对齐
//		styleForTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
//		styleForTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN); //左边框
//		styleForTitle.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边框
//		styleForTitle.setBorderRight(HSSFCellStyle.BORDER_THIN); //右边框
		//字体
		Font fontForTitle = workbook.createFont();
		fontForTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //粗体
		fontForTitle.setFontHeightInPoints((short)10); //字号
		fontForTitle.setFontName("宋体"); //字体
		styleForTitle.setFont(fontForTitle);
		
		////生成一个样式 - 内容使用 - 字符串
		CellStyle styleForStr = workbook.createCellStyle();
		//设置这个样式
		styleForStr.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中对齐
//		styleForStr.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
//		styleForStr.setBorderLeft(HSSFCellStyle.BORDER_THIN); //左边框
//		styleForStr.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边框
//		styleForStr.setBorderRight(HSSFCellStyle.BORDER_THIN); //右边框
		//字体
		Font fontForStr = workbook.createFont();
		fontForStr.setFontHeightInPoints((short)10); //字号
		fontForStr.setFontName("宋体"); //字体
		styleForStr.setFont(fontForStr);
		
		////生成一个样式 - 内容使用 - 数字
		CellStyle styleForNum = workbook.createCellStyle();
		//设置这个样式
		styleForNum.setAlignment(HSSFCellStyle.ALIGN_RIGHT); //右对齐
//		styleForNum.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
//		styleForNum.setBorderLeft(HSSFCellStyle.BORDER_THIN); //左边框
//		styleForNum.setBorderTop(HSSFCellStyle.BORDER_THIN); //上边框
//		styleForNum.setBorderRight(HSSFCellStyle.BORDER_THIN); //右边框
//		styleForNum.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00")); //整数部分三位逗号隔开，小数显示实际位数（最多4位，最少2位）
		//字体
		Font fontForNum = workbook.createFont();
		fontForNum.setFontHeightInPoints((short)10); //字号
		fontForNum.setFontName("宋体"); //字体
		styleForNum.setFont(fontForNum);
		
		//产生表头部分 -- 主记录表头  只要第一行产生
		HSSFRow row = sheet.createRow(0);
		for(short i = 0; i < header1.length; i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(styleForTitle); //设置样式
			HSSFRichTextString text = new HSSFRichTextString(header1[i]);
			cell.setCellValue(text);
		}
		
		//遍历数据集合，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()){
			int flag = 0;//记录当前属性中第几个list
			//创建下一行
			index++;
			row = sheet.createRow(index);
			T t = (T)it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (short i = 0; i < fields.length - cellnums; i++) {
				HSSFCell cell = row.createCell(i);
				// 添加序号
				if (isIndex) {
					if (i == (short)0) {
						cell.setCellValue(index);
					}
				}
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof Integer) {
						int intValue = (Integer) value;
						cell.setCellValue(intValue);
						cell.setCellStyle(styleForNum); //设置样式
					} else if (value instanceof Short) {
						cell.setCellValue((Short) value);
						cell.setCellStyle(styleForNum); //设置样式
					} else if (value instanceof Float) {
						float fValue = (Float) value;
						cell.setCellValue(fValue);
						cell.setCellStyle(styleForNum); //设置样式
					} else if (value instanceof Double) {
						double dValue = (Double) value;
						cell.setCellValue(dValue);
						cell.setCellStyle(styleForNum); //设置样式
					} else if (value instanceof BigDecimal) {
						double dValue = ((BigDecimal) value).doubleValue();
						cell.setCellValue(dValue);
						cell.setCellStyle(styleForNum); //设置样式
					} else if (value instanceof Long) {
						long longValue = (Long) value;
						cell.setCellValue(longValue);
						
						HSSFCellStyle cellStyle = workbook.createCellStyle();
						cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						cellStyle.setFillForegroundColor(HSSFColor.BLACK.index);
						cell.setCellStyle(cellStyle); // 黑色背景
						//cell.setCellStyle(styleForNum); //设置样式
					} else if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat(pattern);
						textValue = sdf.format(date);
						cell.setCellValue(textValue);
						cell.setCellStyle(styleForStr); //设置样式
					}  else {
						// 其它数据类型都当作字符串简单处理
						Object obj_value = (value == null) ? "" : value;
						textValue = obj_value.toString();
						cell.setCellValue(textValue);
						cell.setCellStyle(styleForStr); //设置样式
					}
				} catch (Exception e) {
					log.error(e.getStackTrace()[0].toString());
				} finally {
					//清理资源
					
				}
			}
		}
		it = null;
		return workbook;
	}
	
	/*
	 public void exportXLSFile4SendHistory()  {
		String beginTime = Struts2Utils.getParameter("beginTime");
		String endTime = Struts2Utils.getParameter("endTime");
		String sendPhone = Struts2Utils.getParameter("sendPhone");
		String sendUserName = Struts2Utils.getParameter("sendUserName"); // 发送用户
		
		String[] colName = {"发送时间","发送内容","发送用户","成功号码数"}; // 表头
		String exName = "SendHistoryList"+DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") + ".xls";
		
		String res = "success";
		ExportExcelUtil export = new ExportExcelUtil();
		HSSFWorkbook writeWB = new HSSFWorkbook();  
		
		PagedQueryResult paged = coalSMessageService.findPagedCoalSMessageHistoryByAdmin(1, 50000, beginTime, endTime, sendPhone, sendUserName);
		int pagecount = (paged.getCount() % 50000) == 0 ? paged.getCount() / 50000 : paged.getCount() / 50000 + 1;
		for(int i=1;i<=pagecount;i++){
			int pagesize = 50000;
			if(i==pagecount && (paged.getCount() % 50000) != 0){
				pagesize = paged.getCount() % 50000;
			}
			List dataList = coalSMessageService.findPagedCoalSMessageHistoryByAdmin((i-1)*pagesize, pagesize, beginTime, endTime, sendPhone, sendUserName).getResults();
			List exportList = new ArrayList();
			for(int j = 0; j < dataList.size(); j++){
				CoalSMessageHistory history  = (CoalSMessageHistory)dataList.get(j);
				
				SendHistoryExportBean exportBean = new SendHistoryExportBean();
				exportBean.setSendTime(DateFormatUtils.format(history.getSendTime(), "yyyy-MM-dd HH:mm:ss"));
				exportBean.setSendContent(history.getMsgContent());
				exportBean.setSendUserName(history.getCreateUserName());
				exportBean.setSuccessNums(history.getSuccessNums());
				
				exportList.add(exportBean);
			}
			if(dataList.size()>0){
				writeWB = export.doExportExcel2(writeWB, "sheet"+i+"", colName, exportList, "yyyy-MM-dd", 0, false);
			}
			exportList = null;
			dataList = null;
		}
		
		if(pagecount>0){
			res += "|"+DateFormatUtils.format(new Date(), "yyyy-MM-dd")+"|"+exName;
			String fileFolder = ApplicationConfig.UPLOAD_ROOT_PATH + ApplicationConfig.UPLOAD_SEND_HISTORY_TEMP_PATH;
			File f = new File(fileFolder);
			if(!f.exists()){
				f.mkdirs();
			}
			try {
				FileOutputStream fos = new FileOutputStream(fileFolder + "/" + exName);
				writeWB.write(fos);
				fos.close();
			} catch (IOException e) {
				logger.debug(e.getStackTrace()[0].toString());
			}
		}else{
			res = "nodata";
		}
		
		Struts2Utils.renderText(res);
	} 
	 */

}
