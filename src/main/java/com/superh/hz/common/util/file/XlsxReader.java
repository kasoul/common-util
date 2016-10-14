package com.superh.hz.common.util.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 功能说明：解析07版的excel，后缀名为xlsx
 * 2014-9-16
 */
public class XlsxReader {

	/** 
     * 获得第一张表所有内容
     * @param fileName String, 文件名
     * @return List<String[]> 第一张表的所有值
     */ 
	public static List<String[]> readXlsx(String fileName) throws IOException {
		List<String[]> result = new ArrayList<String[]>();
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileName);

		// 暂时不必循环工作表Sheet，获取第一张sheet就可以了
		// for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets();
		// numSheet++){
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		if (xssfSheet == null) {
			return result;
		} else {

			// 循环行Row
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				String[] rowString = new String[xssfRow.getLastCellNum() + 1];
				// 循环列Cell
				for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
					XSSFCell xssfCell = xssfRow.getCell(cellNum);
					if (xssfCell == null) {
						continue;
					}
					rowString[cellNum] = getValue(xssfCell);
				}
				result.add(rowString);
			}
			return result;
		}
		// }
	}

	/** 
     * 获得第一张表某一列的内容
     * @param fileName String, 文件名,\
     * @param columnIndex int, 列序号
     * @return List<String> 一列的内容
     */ 
	public static List<String> readXlsxOneColumnIndex(String fileName,int columnIndex) throws IOException {
		List<String> result = new ArrayList<String>();
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileName);

		// 暂时不必循环工作表Sheet，获取第一张sheet就可以了
		// for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets();
		// numSheet++){
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		if (xssfSheet == null) {
			return result;
		} else {

			// 循环行Row
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				String string = new String();
				// 循环列Cell
				XSSFCell xssfCell = xssfRow.getCell(columnIndex);
				if (xssfCell == null) {
					continue;
				}
				string = getValue(xssfCell);
				if (string.equals("")) {
					continue;
				}
				result.add(string);
			}
			return result;
		}
		// }
	}
	
	/** 
     * 获取单元格cell的值，必须为本文类型
     */ 
	@SuppressWarnings("static-access")
	private static String getValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfCell.getNumericCellValue());
		} else {
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

}

