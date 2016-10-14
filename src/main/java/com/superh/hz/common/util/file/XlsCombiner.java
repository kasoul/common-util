package com.superh.hz.common.util.file;

import java.io.File;
import java.util.List;

/**
 * 功能说明：jxl解析excel，版本为2003以前的，后缀名xls 2014-08-12
 */
public class XlsCombiner {

	/**
	 * 取得指定字段的共同值
	 * 
	 * @param file1
	 *            File, 文件1
	 * @param file1colunm
	 *            int, 文件1的指定列名
	 * @param file2
	 *            File, 文件2
	 * @param file2colunm
	 *            int, 文件1的指定列名
	 * @return List<String> 指定字段的共同值
	 */
	public static List<String> getSameValueForOneColumn(File file1, int file1colunm, File file2, int file2colunm) {
		List<String> els1 = XlsReader.getOneColumn(file1, file1colunm);
		List<String> els2 = XlsReader.getOneColumn(file2, file2colunm);
		els2.retainAll(els1);
		return els2;
	}

	/**
	 * 取得指定字段的独有值
	 * 
	 * @param mainfile
	 *            File, 主文件
	 * @param mainfilecolunm
	 *            int, 主文件的指定列名
	 * @param filterfile
	 *            File,过滤文件
	 * @param filterfilecolunm
	 *            int, 过滤文件的指定列名，
	 * @return List<String> 指定字段的独有值
	 */
	public List<String> getUniqueValueForOneColumn(File mainfile, int mainfilecolunm, File filterfile,
			int filterfilecolunm) {
		List<String> mainExcel = XlsReader.getOneColumn(mainfile, mainfilecolunm);
		List<String> filterExcel = XlsReader.getOneColumn(filterfile, filterfilecolunm);
		mainExcel.removeAll(filterExcel);
		return mainExcel;
	}

}
