package com.superh.hz.common.util.dbtool;

import java.util.ArrayList;
import java.util.List;

public class TestMysql {
	
	public static void main(String args[]){
		testInsertBatch();
		testInsert();
	}


	public static void testInsertBatch(){
		MySqlOpration mo = new MySqlOpration(
				"jdbc:mysql://localhost/personal_account", "root", "1111");
		MySqlUtil.setConnectionParam("jdbc:mysql://localhost/personal_account", "root", "1111");
		List<String> batchSql = new ArrayList<String>();
		for(int i=0;i<5000;i++){
			String isertSql = "insert into `test_table` (`id`,`name`)"
					+ "VALUES(" + i + ",\'aaa\')";
			batchSql.add(isertSql);
		}
		long startTime = System.currentTimeMillis();
		mo.insertBatch(batchSql);
		long endTime = System.currentTimeMillis();
		System.out.println("批量插入时间：" + (endTime-startTime)/1000f);
	}



	public static void testInsert(){
		MySqlOpration mo = new MySqlOpration(
				"jdbc:mysql://localhost/personal_account", "root", "1111");
		MySqlUtil.setConnectionParam("jdbc:mysql://localhost/personal_account", "root", "1111");
		List<String> batchSql = new ArrayList<String>();
		long startTime = System.currentTimeMillis();
		for(int i=0;i<5;i++){
			String isertSql = "insert into `test_table` (`id`,`name`)"
					+ "VALUES(" + i + ",\'aaa\')";
			//batchSql.add(isertSql);
			mo.insert(isertSql);
		}
		//mo.insertBatch(batchSql);
		long endTime = System.currentTimeMillis();
		System.out.println("单条插入时间：" + (endTime-startTime)/1000f);
	}
}
