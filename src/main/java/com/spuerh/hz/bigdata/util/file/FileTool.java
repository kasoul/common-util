package com.spuerh.hz.bigdata.util.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明： 对文件系统上的文件 和目录操作
 * 2014-12-10
 */
public class FileTool {
	
	 /** 
     * 根据dir_path_file，获取真实文件，而非目录
     * @param dir_path_file 文件系统下的路径文件名，绝对路径
     * @return 返回真实file，或null
     */  
	public static File getTrueFile(String dir_path_file){
		File dirFile = new File(dir_path_file);
		if (!dirFile.exists() || dirFile.isDirectory()) {
			System.err.println("the dir paht '" + dir_path_file
					+ "' is not exist or it is not a directory,please check!");
			return null;
		}else{
			return dirFile;
		}
	}
	
	/** 
     * 递归遍历一个目录下的所有真实文件
     * @param File dirFile, List<File> fileList
     * @return 返回真实file，或null
     */  
	public static List<File> getFilesFromDir(File dirFile, List<File> fileList){
		if(dirFile.isDirectory()){
			File[] files = dirFile.listFiles(new FilenameFilter() {

				@Override
				//设置文件名过滤器，获取指定文件名的文件
				public boolean accept(File dir, String name) {
					//if (name.endsWith(".dat")) {
						return true;
					//}
					//return false;
				}

			});
			for(File file:files){
				getFilesFromDir(file,fileList);
			}
		}else{
			fileList.add(dirFile);
		}
		return fileList;
	}
	
	/** 
     * 读取文件数据，每行转化为一个String
     * 处理空白字符串
     * @param File dirFile
     * @return List<String>，或null
     */  
	public static List<String> getContentFromFile(File file ){
		List<String> fileContent = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			//BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				fileContent.add(line.replaceAll("\\s", ""));
				line = reader.readLine();
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileContent;
	}
	
	/** 
     * 读取文件数据，每行转化为一个String
     * 处理空白字符串
     * @param String filepath:路径文件名
     * @return List<String>，或null
     */  
	public static List<String> getContentFromFile(String filepath){
		File file = new File(filepath);
		return getContentFromFile(file);
	}
}
