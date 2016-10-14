package com.superh.hz.common.util.downloadtool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.superh.hz.common.util.dbtool.MySqlUtil;

/**
 * 功能说明：从某个URL下载文件到java_download目录 2014-8-27
 */
public class DownloadUtil {

	private static final Logger logger = LoggerFactory.getLogger(DownloadUtil.class);

	/**
	 * 从网络上下载单个文件
	 * 
	 * @param downloadUrl String, 下载链接
	 */
	public static void downloadApp(String downloadUrl) {
		// 地址网上可用
		try {
			String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1);
			URL url = new URL(downloadUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if (HttpURLConnection.HTTP_OK != conn.getResponseCode()) {
				logger.error("链接无效");
			} else {
				File file = new File(System.getProperty("user.dir") + File.separator + "java_download");
				// (System.getProperty("user.dir"):获取本项目的文件夹路径,\
				if (!file.exists()) {
					file.mkdir();// file是文件夹并非文件
				}

				File cfile = new File(file.getPath(), fileName);
				if (!cfile.exists()) {
					cfile.createNewFile();// 这个才是文件，路径文件名
				}

				InputStream is = conn.getInputStream();
				FileOutputStream os = new FileOutputStream(cfile);
				byte[] buffer = new byte[2048];
				int a = 0;
				while ((a = is.read(buffer)) != -1) {
					os.write(buffer, 0, a);
				}
				is.close();
				os.flush();
				os.close();
			}
		} catch (Exception e) {
			logger.error("download exception,url is [{}]", downloadUrl, e);
		}
	}
}
