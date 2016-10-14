package com.superh.hz.common.util.dbtool;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能说明：mysql数据库连接和操作，静态方法
 */
public class MySqlUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(MySqlUtil.class);

	private static Connection con = null; //创建用于连接数据库的Connection对象
	private static Statement st = null; //创建用于连接数据库的Statement对象
	
	private static String url = null;
	private static String username = null;
	private static String password = null;

	/**
	 * 设置连接参数
	 * 
	 * @param url
	 *            String,地址
	 * @param username
	 *            String,用户名
	 * @param password
	 *            String,密码
	 */
	public static void setConnectionParam(String url, String username,
			String password) {
		MySqlUtil.url = url;
		MySqlUtil.username = username;
		MySqlUtil.password = password;

	}

	/* 获取数据库的连接 */
	public static Connection getConnection() {
		//Connection con = null; // 创建用于连接数据库的Connection对象
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动
			con = DriverManager.getConnection(url, username, password);// 创建数据连接

		} catch (Exception e) {
			logger.error("mysql connect failed.", e);
		}
		return con; // 返回所建立的数据库连接
	}

	/* 获取声明语句 */
	public static Statement getStatement() {
		//con = getConnection(); // 创建用于连接数据库的Connection对象
		//Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动

			con = DriverManager.getConnection(url, username, password);// 创建数据连接

		} catch (Exception e) {
			logger.error("mysql connect failed.", e);
		}
		try {
			st = (Statement) con.createStatement();
		} catch (SQLException e) {
			logger.error("statement creat failed.", e);
		}
		return st;
	}

	/* 查询方法 */
	public static ResultSet query(String sql) {
		ResultSet rt = null;
		Statement st = getStatement();
		try {
			rt = st.executeQuery(sql);
			st.close();
			con.close();
		} catch (SQLException e) {
			logger.error("query sql execute failed.", e);
		}
		return rt;
	}

	/* 查询方法,返回String[]集合 */
	public static List<String[]> queryResult(String sql) {
		List<String[]> lsit = new ArrayList<String[]>();
		ResultSet rt = query(sql);
		int columnCount;
		try {
			columnCount = rt.getMetaData().getColumnCount();
			while (rt.next()) {
				String[] record = new String[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					String column = rt.getString(i);
					record[i - 1] = column;
				}
				lsit.add(record);
			}
		} catch (SQLException e) {
			logger.error("query sql execute failed.", e);
		}
		return lsit;
	}

	/* 插入数据记录，并输出插入的数据记录数 */
	public static int insert(String sql) {
		int count = 0;
		Statement st = getStatement();
		try {
			count = st.executeUpdate(sql);
			st.close();
			con.close();
		} catch (SQLException e) {
			logger.error("insert sql execute failed.", e);
		}
		return count;
	}
	
	/* 插入数据记录，并输出插入的数据记录数 */
	public static int insertBatch(String[] sqlBatch) {
		int countsum = 0;
		Statement st = getStatement();
		try {
			
			for(int i=0;i<sqlBatch.length;i++){
				int count = st.executeUpdate(sqlBatch[i]);
				countsum += count;
			}
			
			st.close();
			con.close();
		} catch (SQLException e) {
			logger.error("insert sqls execute failed.", e);
		}
		return countsum;
	}

	/* 更新符合要求的记录，并返回更新的记录数目 */
	public static int update(String sql) {
		int count = 0;
		Statement st = getStatement();
		try {
			count = st.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数
			st.close();
			con.close();
		} catch (SQLException e) {
			logger.error("update sqls execute failed.", e);
		}
		return count;
	}

}