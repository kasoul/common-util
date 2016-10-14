package com.superh.hz.common.util.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 功能说明：jxl解析excel，版本为2003以前的，后缀名xls 2014-08-12
 */
public class XlsWriter {

	private static final Logger logger = LoggerFactory.getLogger(XlsWriter.class);

	/**
	 * 在指定目录生成EXCEL文件
	 * 
	 * @param filename
	 *            String, 路径文件全名,不包括扩展名，
	 * @param content
	 *            List<String[]>, 数据内容
	 */
	public void creatExcelDir(String filename, List<String[]> content) {
		File file = new File(filename + ".xls");
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.error("creat new file failed:[{}]", filename, e);
			}
		WritableWorkbook wbook = null;
		try {
			wbook = Workbook.createWorkbook(file);
		} catch (IOException e) {
			logger.error("creat workbook failed:[{}]", file.getPath(), e);
		}
		WritableSheet wsheet = wbook.createSheet("名字", 0);
		WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
				UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
		WritableCellFormat wcfFC = new WritableCellFormat(wfc);

		for (int i = 0; i < content.size(); i++) {
			for (int j = 0; j < content.get(i).length; j++) {
				Label labelcontent = new Label(j, i, content.get(i)[j], wcfFC);
				try {
					wsheet.addCell(labelcontent);
				} catch (RowsExceededException e) {
					logger.error("write workbook failed:[{}]", file.getPath(), e);
				} catch (WriteException e) {
					logger.error("write workbook failed:[{}]", file.getPath(), e);
				}
			}
		}

		try {
			wbook.write();
		} catch (IOException e) {
			logger.error("write workbook failed:[{}]", file.getPath(), e);
		}
		try {
			wbook.close();
		} catch (WriteException e) {
			logger.error("write workbook failed:[{}]", file.getPath(), e);
		} catch (IOException e) {
			logger.error("write workbook failed:[{}]", file.getPath(), e);
		}
	}

	/**
	 * 生成指定字段含指定值的内容，content的contentColunm列包含在colunmContent中的记录为有效记录
	 * 
	 * @param content
	 *            List<String[]>, 数据内容，
	 * @param contentColunm
	 *            int, 列序号
	 * @param colunmContent
	 *            List<String>, 指定内容列
	 * @return List<String[]> 新的数据内容
	 */
	public List<String[]> dealExcelContentFromCulonm(List<String[]> content, int contentColunm,
			List<String> colunmContent) {
		List<String[]> contentResult = new ArrayList<String[]>();
		for (int i = 0; i < content.size(); i++) {
			if (colunmContent.contains(content.get(i)[contentColunm])) {
				contentResult.add(content.get(i));
			}
		}
		return contentResult;
	}
}