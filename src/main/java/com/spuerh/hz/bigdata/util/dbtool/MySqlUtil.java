package com.spuerh.hz.bigdata.util.dbtool;

/**
 * 功能说明：mysql数据库连接和操作，静态方法
 * 成员属性
 * 成员方法：getConnection()，获取连接；getStatement()，获取声明；query(String sql) 查询数据
 * 2014-08-11
 * @update 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlUtil {

	private static Connection con = null; //创建用于连接数据库的Connection对象
	private static Statement st = null; //创建用于连接数据库的Statement对象
	
	private static String url = null;
	private static String username = null;
	private static String password = null;

	/* 设置连接参数 */
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
			System.out.println("数据库连接失败" + e.getMessage());
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
			System.out.println("数据库连接失败" + e.getMessage());
		}
		try {
			st = (Statement) con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lsit;
	}

	/* 插入数据记录，并输出插入的数据记录数 */
	public static void insert(String sql) {
		Statement st = getStatement();
		try {
			int count = st.executeUpdate(sql);
			System.out.println("表中插入 " + count + " 条数据");
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* 插入数据记录，并输出插入的数据记录数 */
	public static void insertBatch(String[] sqlBatch) {
		Statement st = getStatement();
		try {
			int countsum = 0;
			for(int i=0;i<sqlBatch.length;i++){
			int count = st.executeUpdate(sqlBatch[i]);
			countsum += count;
			}
			System.out.println("表中插入 " + countsum + " 条数据");
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* 更新符合要求的记录，并返回更新的记录数目 */
	public static void update(String sql) {
		Statement st = getStatement();
		try {
			int count = st.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数
			System.out.println("表中更新 " + count + " 条数据"); // 输出更新操作的处理结果
			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("更新数据失败");
		}
	}

}