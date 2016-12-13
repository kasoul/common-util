package com.superh.hz.common.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 功能说明：解析07版的excel，后缀名为xlsx
 * 2016-12-13
 */
public class XlsxWriter {
	
	
	/** 
     * 写入第一张表所有内容
     * @param fileName String, 路径文件名
	 * @throws IOException 
	 * @throws FileNotFoundException 
     */ 
	public static void writeXlsx(String fileName, String[] defaultFirstRow, List<String[]> content) throws FileNotFoundException, IOException  {
		XSSFWorkbook xssfWorkbook = null;
		
		File file = new File(fileName);
		if(!file.exists()){
			xssfWorkbook = new XSSFWorkbook();
		}else{
			xssfWorkbook = new XSSFWorkbook(new FileInputStream(fileName));
		}
		 
		XSSFSheet xssfSheet = null;
		if(xssfWorkbook.getNumberOfSheets() == 0){
			xssfSheet = xssfWorkbook.createSheet();
			XSSFRow xssfRow = xssfSheet.createRow(0);
			for(int columnIndex = 0; columnIndex < defaultFirstRow.length; columnIndex++){
				XSSFCell xssfCell = xssfRow.createCell(columnIndex);
				xssfCell.setCellValue(defaultFirstRow[columnIndex]);
			}
		}else{
			xssfSheet = xssfWorkbook.getSheetAt(0);
		}
		int currentRowNum = xssfSheet.getLastRowNum();
		for(String[] rowContent: content){
			XSSFRow xssfRow = xssfSheet.createRow(++currentRowNum);
			for(int columnIndex = 0; columnIndex < rowContent.length; columnIndex++){
				XSSFCell xssfCell = xssfRow.createCell(columnIndex);
				xssfCell.setCellValue(rowContent[columnIndex]);
			}
		}
		
		xssfWorkbook.write(new FileOutputStream(fileName));
		
	}
	
	public static void main(String[] args) {
		
		String[] firstRow = new String[]{"卡口id","抓拍时间","抓拍图片","比对结果图片1","比对分值1","比对结果图片2","比对分值2",
			"比对结果图片3","比对分值3","比对结果图片4","比对分值4","比对结果图片5","比对分值5","比对结果图片6","比对分值6",
			"比对结果图片7","比对分值7","比对结果图片8","比对分值8","比对结果图片9","比对分值9","比对结果图片10","比对分值10"};
		List<String[]> conent = new ArrayList<String[]>();
		String[] rowConent1 =  new String[]{"卡口1","20161213110500","图片1","000001.jpg","0.586","000002.jpg","0.426"};
		String[] rowConent2 =  new String[]{"卡口1","20161213110500","图片1","000001.jpg","0.586","000002.jpg","0.426",
				"000003.jpg","0.386","000004.jpg","0.326"};
		conent.add(rowConent1);
		conent.add(rowConent2);
		
		try {
			writeXlsx("C:\\Users\\Administrator\\Desktop\\testdata\\1.xlsx",firstRow,conent);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

