package com.superh.hz.common.util.dbtool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 功能说明：mysql数据库连接和操作
 */
public class MySqlOpration {

	private static final Logger logger = LoggerFactory.getLogger(MySqlOpration.class);

	public String url = null;
	public String username = null;
	public String password = null;
	private Statement stmt = null;

	/**
	 * 构造方法设置连接参数
	 * 
	 * @param url
	 *            String,地址
	 * @param username
	 *            String,用户名
	 * @param password
	 *            String,密码
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
	 * @param url
	 *            String,地址
	 * @param username
	 *            String,用户名
	 * @param password
	 *            String,密码
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
			logger.error("mysql connect failed.", e);
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
			logger.error("mysql connect failed.", e);
		}
		try {
			st = (Statement) con.createStatement();
		} catch (SQLException e) {
			logger.error("statement creat failed.", e);
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
			logger.error("mysql connect failed.", e);
		}
		try {
			stmt = (Statement) con.createStatement();
		} catch (SQLException e) {
			logger.error("statement creat failed.", e);
		}
	}

	/**
	 * 查询方法
	 * 
	 * @param sql
	 *            String,sql语句
	 */
	public ResultSet query(String sql) {
		ResultSet rt = null;
		// Statement st = getStatement();
		try {
			rt = stmt.executeQuery(sql);
		} catch (SQLException e) {
			logger.error("query sql execute failed.", e);
		}
		return rt;
	}

	/**
	 * 插入数据记录，并返回插入的数据记录数
	 * 
	 * @param sql
	 *            String,sql语句
	 */
	public int insert(String sql) {
		int count = 0;
		try {
			count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			logger.error("insert sql execute failed.", e);
		}
		
		return count;
	}

	/**
	 * 插入数据记录，并输出插入的数据记录数
	 * 
	 * @param sqls
	 *            List<String>,sql语句
	 */
	public int[] insertBatch(List<String> sqls) {
		int[] counts = null;
		try {
			for (String sql : sqls) {
				stmt.addBatch(sql);
			}
			counts = stmt.executeBatch();
		} catch (SQLException e) {
			logger.error("insert sqls execute failed.", e);
		}
		
		return counts;
	}

	/**
	 * 更新符合要求的记录，并返回更新的记录数目
	 * 
	 * @param sql
	 *            String,sql语句
	 */
	public int update(String sql) {
		int count = 0;
		try {
			count = stmt.executeUpdate(sql);// 执行更新操作的sql语句，返回更新数据的个数

		} catch (SQLException e) {
			logger.error("update sql execute failed.", e);
		}
		return count;
	}

	/**
	 * 执行ddl语句
	 * 
	 * @param sql
	 *            String,sql语句
	 */
	public void excuteDDL(String sql) {
		// Statement st = getStatement();
		try {
			stmt.execute(sql);// 执行更新操作的sql语句，返回更新数据的个数
			logger.error("ddl sql:[{}] execute failed.", sql);
		} catch (SQLException e) {
			logger.error("ddl sql execute failed.", e);
		}
	}

}