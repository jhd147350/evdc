package evdc.vianet.emailticket.task;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 修改自:将HSSF替换为XSSF 体积更小
 * http://blog.csdn.net/zhousenshan/article/details/52578666 TODO note
 */
public class ExportExcelUtil {
	/**
	 * 
	 * @param response
	 *            请求
	 * @param fileName
	 *            文件名 如："学生表"
	 * @param excelHeader
	 *            excel表头数组，存放"姓名#name"格式字符串，"姓名"为excel标题行， "name"为对象字段名
	 * @param dataList
	 *            数据集合，需与表头数组中的字段名一致，并且符合javabean规范
	 * @return 返回一个XSSFWorkbook
	 * @throws Exception
	 */
	public static <T> XSSFWorkbook export(HttpServletResponse response, String fileName, String[] excelHeader,
			Collection<T> dataList) throws Exception {
		// 设置请求
		response.setContentType("application/application/vnd.ms-excel");
		response.setHeader("Content-disposition",
				"attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
		// 创建一个Workbook，对应一个Excel文件
		XSSFWorkbook wb = new XSSFWorkbook();

		// 设置标题样式
		XSSFCellStyle titleStyle = wb.createCellStyle();

		// 设置字体样式
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 15);// 字体高度
		titleFont.setFontName("黑体");// 字体样式
		titleStyle.setFont(titleFont);
		// 在Workbook中添加一个sheet,对应Excel文件中的sheet
		XSSFSheet sheet = wb.createSheet(fileName);
		// 标题数组
		String[] titleArray = new String[excelHeader.length];
		// 字段名数组
		String[] fieldArray = new String[excelHeader.length];
		for (int i = 0; i < excelHeader.length; i++) {
			String[] tempArray = excelHeader[i].split("#");// 临时数组 分割#
			titleArray[i] = tempArray[0];
			fieldArray[i] = tempArray[1];
		}
		// 在sheet中添加标题行
		XSSFRow row = sheet.createRow((int) 0);// 行数从0开始
		XSSFCell sequenceCell = row.createCell(0);// cell列 从0开始 第一列添加序号
		sequenceCell.setCellValue("序号");
		sequenceCell.setCellStyle(titleStyle);
		sheet.autoSizeColumn(0);// 自动设置宽度
		// 为标题行赋值
		for (int i = 0; i < titleArray.length; i++) {
			XSSFCell titleCell = row.createCell(i + 1);// 0号位被序号占用，所以需+1
			titleCell.setCellValue(titleArray[i]);
			titleCell.setCellStyle(titleStyle);
			sheet.autoSizeColumn(i + 1);// 0号位被序号占用，所以需+1
		}
		// 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
		XSSFCellStyle dataStyle = wb.createCellStyle();

		// 设置数据字体
		Font dataFont = wb.createFont();
		dataFont.setFontHeightInPoints((short) 12);// 字体高度
		dataFont.setFontName("宋体");// 字体
		dataStyle.setFont(dataFont);
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataList.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;// 0号位被占用 所以+1
			row = sheet.createRow(index);
			// 为序号赋值
			XSSFCell sequenceCellValue = row.createCell(0);// 序号值永远是第0列
			sequenceCellValue.setCellValue(index);
			sequenceCellValue.setCellStyle(dataStyle);
			sheet.autoSizeColumn(0);
			T t = (T) it.next();
			// 利用反射，根据传过来的字段名数组，动态调用对应的getXxx()方法得到属性值
			for (int i = 0; i < fieldArray.length; i++) {
				XSSFCell dataCell = row.createCell(i + 1);
				dataCell.setCellStyle(dataStyle);
				sheet.autoSizeColumn(i + 1);
				String fieldName = fieldArray[i];
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 取得对应getXxx()方法
				Class<? extends Object> tCls = t.getClass();// 泛型为Object以及所有Object的子类
				Method getMethod = tCls.getMethod(getMethodName, new Class[] {});// 通过方法名得到对应的方法
				Object value = getMethod.invoke(t, new Object[] {});// 动态调用方,得到属性值
				if (value != null) {
					dataCell.setCellValue(value.toString());// 为当前列赋值
				}
			}
		}

		OutputStream outputStream = response.getOutputStream();// 打开流
		wb.write(outputStream);// HSSFWorkbook写入流
		wb.close();// HSSFWorkbook关闭
		outputStream.flush();// 刷新流
		outputStream.close();// 关闭流
		return wb;
	}
}
