package com.superh.hz.common.util.downloadtool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 功能说明：从某个URL下载文件到java_download目录
 * 2014-8-27
 */
public class DownloadUtil {
	/**
	 * 从网络上下载单个文件
	 * @param DownloadUrl
	 */
	public static void downloadApp(String DownloadUrl){
		// 地址网上可用
		try
		{
			String fileName = DownloadUrl.substring(DownloadUrl.lastIndexOf("/") + 1);
			URL url = new URL(DownloadUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if (HttpURLConnection.HTTP_OK != conn.getResponseCode())
			{
				System.out.println("链接无效");
			}
			else
			{
				File file = new File(System.getProperty("user.dir") + File.separator + "java_download");
				//(System.getProperty("user.dir"):获取本项目的文件夹路径,\
				if (!file.exists())
				{
					file.mkdir();//file是文件夹并非文件
				}

				File cfile = new File(file.getPath(), fileName);
				if (!cfile.exists())
				{
					cfile.createNewFile();//这个才是文件，路径文件名
				}

				InputStream is = conn.getInputStream();
				FileOutputStream os = new FileOutputStream(cfile);
				byte[] buffer = new byte[2048];
				int a = 0;
				while ((a = is.read(buffer)) != -1)
				{
					os.write(buffer, 0, a);
				}
				is.close();
				os.flush();
				os.close();
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
