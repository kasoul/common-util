package com.spuerh.hz.common.util.dbtool;

/**
 * 功能说明：mysql数据库连接和操作
 * 成员属性
 * 成员方法：getConnection()，获取连接；getStatement()，获取声明；query(String sql) 查询数据
 * 2014-08-12
 * @update   
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MySqlOpration {

	public String url = null;
	public String username = null;
	public String password = null;
	private Statement stmt = null;

	/**
	 * 构造方法设置连接参数
	 * 
	 * @param url地址
	 *            ，username用户名，password方法
	 */
	public MySqlOpration(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
		setStatement();
	}

	/**
	 * 构造方法设置连接参数
	 * 
	 * @param url地址
	 *            ，username用户名，password方法
	 */
	public MySqlOpration() {
		this.url = "127.0.0.1";
		this.username = "root";
		this.password = "1111";
		setStatement();
	}

	/**
	 * 设置连接参数
	 * 
	 * @param url地址
	 *            ，username用户名，password方法
	 */
	@Deprecated
	public void setConnectionParam(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/**
	 * 获取数据库的连接 用全局变量
	 */
	public Connection getConnection() {
		Connection con = null; // 创建用于连接数据库的Connection对象
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动
			con = DriverManager.getConnection(url, username, password);// 创建数据连接

		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
		}
		return con; // 返回所建立的数据库连接
	}

	/**
	 * 获取声明语句 用全局变量
	 */
	public Statement getStatement() {
		Connection con = null; // 创建用于连接数据库的Connection对象
		Statement st = null;
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

	public void setStatement() {
		Connection con = null; // 创建用于连接数据库的Connection对象
		// Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动
			con = DriverManager.getConnection(url, username, password);// 创建数据连接

		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
		}
		try {
			stmt = (Statement) con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查询方法
	 * 
	 * @param String
	 *            sql语句
	 */
	public ResultSet query(String sql) {
		ResultSet rt = null;
		// Statement st = getStatement();
		try {
			rt = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rt;
	}

	/**
	 * 插入数据记录，并输出插入的数据记录数
	 * 
	 * @param String
	 *            sql语句
	 */
	public void insert(String sql) {
		// Statement st = getStatement();
		try {
			int count = stmt.executeUpdate(sql);
			//System.out.println("表中插入 " + count + " 条数据");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入数据记录，并输出插入的数据记录数
	 * 
	 * @param String
	 *            sql语句
	 */
	public void insertBatch(List<String> sqls) {
		// Statement st = getStatement();
		try {
			for(String sql:sqls){
				stmt.addBatch(sql);
			}
			int[] count = stmt.executeBatch();
			System.out.println("表中插入 " + count.length + " 种类的数据");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 更新符合要求的记录，并返回更新的记录数目
	 * 
	 * @param String
	 *            sql语句
	 */
	public void update(String sql) {
		// Statement st = getStatement();
		try {
			int count = stmt.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数
			System.out.println("表中更新 " + count + " 条数据"); // 输出更新操作的处理结果
		} catch (SQLException e) {
			System.out.println("更新数据失败");
		}
	}
	

	/**
	 * 执行ddl语句
	 * 
	 * @param String
	 *            sql语句
	 */
	public void excuteDDL(String sql) {
		// Statement st = getStatement();
		try {
			stmt.execute(sql);// 执行更新操作的sql语句，返回更新数据的个数
			System.out.println("执行sql语句成功"); // 输出更新操作的处理结果
		} catch (SQLException e) {
			System.out.println("执行sql语句失败");
			e.printStackTrace();
		}
	}

}