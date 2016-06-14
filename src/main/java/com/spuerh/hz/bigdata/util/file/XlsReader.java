package com.spuerh.hz.bigdata.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 功能说明：jxl解析excel，版本为2003以前的，后缀名xls
 * 2014-08-12
 */
public class XlsReader {
    
	/** 
     * 只获得一列的内容,sheet,row,column的下标从0开始
     * @param file 文件名，column，文件的指定列名
     * @return List<String> 指定列的值
     */ 
	public static List<String> getOneColumn(File file,int column){
		List<String> result = new ArrayList<String>();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Workbook rwb = null;
		Cell cell = null;
		try {
			rwb = Workbook.getWorkbook(inputStream);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sh = rwb.getSheet(0);
		for(int i=0;i<sh.getRows();i++){
			//for(int j=0;j<sh.getColumns();j++){}
			cell = sh.getCell(column,i);
			result.add(i,cell.getContents());
		}
		return result;	
	}
	
	/** 
     * 只获得一列的内容，而且不要第一行（表头行）
     * @param file 文件名，column，文件的指定列名
     * @return List<String> 指定列的值，去除别第一行
     */ 
	public static List<String> getOneColumnWithoutFirstRow(File file,int column){
		List<String> result = new ArrayList<String>();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Workbook rwb = null;
		Cell cell = null;
		try {
			rwb = Workbook.getWorkbook(inputStream);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sh = rwb.getSheet(0);
		for(int i=1;i<sh.getRows();i++){
			//for(int j=0;j<sh.getColumns();j++){}
			cell = sh.getCell(column,i);
			result.add(i-1,cell.getContents());
		}
		return result;	
	}
	
	/** 
     * 获得第一张表所有内容
     * @param File 文件名
     * @return List<String[]> 第一张表的所有值
     */ 
	public static List<String[]> getAllContent(File file){
		List<String[]> result = new ArrayList<String[]>();
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Workbook rwb = null;
		Cell cell = null;
		try {
			rwb = Workbook.getWorkbook(inputStream);
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sh = rwb.getSheet(0);
		for(int i=0;i<sh.getRows();i++){
			
			String[] rowContent = new String[sh.getColumns()];
			for(int j=0;j<sh.getColumns();j++){
				cell = sh.getCell(j,i);
				rowContent[j] = cell.getContents();
			}
			
			result.add(i,rowContent);
		}
		return result;	
	}
	
	/** 
     * 压缩列，去除重复值
     * @param List<String> 一列数据
     * @return List<String[]> 一列不重复的数据
     */ 
	public static List<String> compressColumn(List<String> colunm){
		List<String> result = new ArrayList<String>();
		int i = 0;
		for(String s:colunm){
			if(result.size()!=0){
			    if(result.contains(s)){
			    	if(s.equals("")||s==null)
			    	System.out.println("有空字段：" + s);
			    	else
			    	System.out.println("有重复字段：" + s);
			    	i++;
			    }
			    else{
			    	result.add(s);
			    }
			}else{
				result.add(s);
			}
		}
		System.out.println("共有" + i + "个字段重复");
		return result;
	}
}
