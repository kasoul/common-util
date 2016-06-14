package com.spuerh.hz.util.dbtool;

import com.spuerh.hz.bigdata.util.dbtool.MySqlUtil;

/**
 * 功能说明：
 * 
 * @author: huangchaohz
 * @datetime:2014年9月18日-上午10:56:55 update by：，update：
 */
public class InsertData {

	public void test_insertConversion() {
		/*
		 * String insertSql = "INSERT INTO \'cmcc_data'.\'st_performance_d\'" +
		 * "(`app_id`,`acct_no`,`app_version`,`area_id`,`network_type`,`device_type`,`user_group`,"
		 * +"`f001`,`f002`,`f003`,`f004`,`f005`,`f006`,`f007`,`f008`," +
		 * "`f009`,`f010`,`f011`,`f012`,`f013`,`f014`,`f015`,`f016`)"
		 * +"VALUES(<{app_id: }>,<{acct_no: }>,<{app_version: }>,<{area_id: }>,"
		 * +"<{network_type: }>,<{device_type: }>,<{user_group: }>,"
		 * +"<{f001: }>,<{f002: }>,<{f003: }>,<{f004: }>,<{f005: }>,<{f006: }>,"
		 * +"<{f007: }>,<{f008: }>,<{f009: }>,<{f010: }>,<{f011: }>,<{f012: }>,"
		 * +"<{f013: }>,<{f014: }>,<{f015: }>,<{f016: }>);";
		 */
		MySqlUtil.setConnectionParam("jdbc:mysql://192.168.15.6/cmcc_data",
				"root", "");
		String[] sqls = new String[20];
		for (int i = 0; i < 20; i++) {
			int web_id = 1;
			String web_name = "migu";
			int acct_no = 20141000 + ((int) (Math.random() * 10) + 1);
			int area_id = 110000;
			int refer_id = (int) (Math.random() * 3) + 1;
			String browser_name = "firefox";
			String os_name = "linux(cent os 6.4)";

			int search_pv = (int) (Math.random() * 100) + 10;
			int play_pv = (int) (Math.random() * 80) + 5;
			int enter_pv = (int) ((search_pv + play_pv) * 0.9);
			double conversion_rate = Math.random() * 0.2 + 0.1;
			int conversion_pv = (int) (enter_pv * conversion_rate);

			int non_conversion_path = (int) (Math.random() * 30) + 20;

			String insertSql = "INSERT INTO `cmcc_data`.`st_web_conversion_d_201410`"
					+ "(`web_id`,`web_name`,`acct_no`,`area_id`,`refer_id`,`browser_name`,`os_name`,"
					+ "`conversion_pv`,`enter_pv`,`non_conversion_path`,`conversion_rate`,`search_pv`,`play_pv`)"
					+ "VALUES("
					+ web_id
					+ ",\'"
					+ web_name
					+ "\',"
					+ acct_no
					+ ","
					+ area_id
					+ ","
					+ refer_id
					+ ",\'"
					+ browser_name
					+ "\',\'"
					+ os_name
					+ "\',"
					+ conversion_pv
					+ ","
					+ enter_pv
					+ ","
					+ non_conversion_path
					+ ","
					+ conversion_rate * 100
					+ ","
					+ search_pv + "," + play_pv + " );";
			sqls[i] = insertSql;
		}
		MySqlUtil.insertBatch(sqls);

	}

	public void test_insertEvent() {
		/*
		 * String insertSql = "INSERT INTO \'cmcc_data'.\'st_performance_d\'" +
		 * "(`app_id`,`acct_no`,`app_version`,`area_id`,`network_type`,`device_type`,`user_group`,"
		 * +"`f001`,`f002`,`f003`,`f004`,`f005`,`f006`,`f007`,`f008`," +
		 * "`f009`,`f010`,`f011`,`f012`,`f013`,`f014`,`f015`,`f016`)"
		 * +"VALUES(<{app_id: }>,<{acct_no: }>,<{app_version: }>,<{area_id: }>,"
		 * +"<{network_type: }>,<{device_type: }>,<{user_group: }>,"
		 * +"<{f001: }>,<{f002: }>,<{f003: }>,<{f004: }>,<{f005: }>,<{f006: }>,"
		 * +"<{f007: }>,<{f008: }>,<{f009: }>,<{f010: }>,<{f011: }>,<{f012: }>,"
		 * +"<{f013: }>,<{f014: }>,<{f015: }>,<{f016: }>);";
		 */
		MySqlUtil.setConnectionParam("jdbc:mysql://192.168.15.6/cmcc_data",
				"root", "");
		String[] sqls = new String[20];
		for (int i = 0; i < 20; i++) {
			
			int acct_no = 20141000 + ((int) (Math.random() * 10) + 1);
			int web_id = 1;
			String web_name = "migu";
			
			int area_id = 110000;
			int refer_id = (int) (Math.random() * 3) + 1;
			String browser_name = "ie8";
			String os_name = "linux";

			int event_num = (int) (Math.random() * 1000) + 100;

			int event_type_id = (int) (Math.random() * 5);
			String event_type = null;
			switch (event_type_id) {
			case 0:
				event_type = "downLoad";
				break;
			case 1:
				event_type = "play";
				break;
			case 2:
				event_type = "order";
				break;
			case 3:
				event_type = "login";
				break;
			default:
				event_type = "share";
			}

			int event_durtion = (int) (Math.random() * 10);
			double event_value = Math.random() * 100 + 10;
			int visit_uv = (int) (Math.random() * 50) + 10;

			int visit_vv = (int) (Math.random() * 200) + 50;

			String insertSql = "INSERT INTO `cmcc_data`.`st_web_event_d_201410`"
					+ "(`acct_no`,`web_id`,`web_name`,`area_id`,`refer_id`,`browser_name`,`os_name`,"
					+ "`event_num`,`event_type`,`event_durtion`,`event_value`,`visit_uv`,`visit_vv`)"
					+ "VALUES("
					+ acct_no
					+ ","
					+ web_id
					+ ",\'"
					+ web_name
					+ "\',"
					+ area_id
					+ ","
					+ refer_id
					+ ",\'"
					+ browser_name
					+ "\',\'"
					+ os_name
					+ "\',"
					+ event_num
					+ ",\'"
					+ event_type
					+ "\',"
					+ event_durtion
					+ ","
					+ event_value
					+ ","
					+ visit_uv
					+ ","
					+ visit_vv + " );";
			sqls[i] = insertSql;
		}
		MySqlUtil.insertBatch(sqls);

	}
	
	public void test_insertDqipage() {
		/*
		 * String insertSql = "INSERT INTO \'cmcc_data'.\'st_performance_d\'" +
		 * "(`app_id`,`acct_no`,`app_version`,`area_id`,`network_type`,`device_type`,`user_group`,"
		 * +"`f001`,`f002`,`f003`,`f004`,`f005`,`f006`,`f007`,`f008`," +
		 * "`f009`,`f010`,`f011`,`f012`,`f013`,`f014`,`f015`,`f016`)"
		 * +"VALUES(<{app_id: }>,<{acct_no: }>,<{app_version: }>,<{area_id: }>,"
		 * +"<{network_type: }>,<{device_type: }>,<{user_group: }>,"
		 * +"<{f001: }>,<{f002: }>,<{f003: }>,<{f004: }>,<{f005: }>,<{f006: }>,"
		 * +"<{f007: }>,<{f008: }>,<{f009: }>,<{f010: }>,<{f011: }>,<{f012: }>,"
		 * +"<{f013: }>,<{f014: }>,<{f015: }>,<{f016: }>);";
		 */
		MySqlUtil.setConnectionParam("jdbc:mysql://192.168.15.6/cmcc_data",
				"root", "");
		String[] sqls = new String[20];
		for (int i = 0; i < 20; i++) {
			
			int acct_no = 20141001;
			int web_id = 1;
			String session_id = "aaaaaa"+(char)((int) (Math.random() * 26) + 56);
			String web_name = "migu";
			
			int area_id = 110000;
			int refer_id = (int) (Math.random() * 3) + 1;
			String browser_name = "ie8";
			String os_name = "linux";

			int event_num = (int) (Math.random() * 1000) + 100;

			int event_type_id = (int) (Math.random() * 5);
			String event_type = null;
			switch (event_type_id) {
			case 0:
				event_type = "downLoad";
				break;
			case 1:
				event_type = "play";
				break;
			case 2:
				event_type = "order";
				break;
			case 3:
				event_type = "login";
				break;
			default:
				event_type = "share";
			}

			int event_durtion = (int) (Math.random() * 10);
			double event_value = Math.random() * 100 + 10;
			int visit_uv = (int) (Math.random() * 50) + 10;

			int visit_vv = (int) (Math.random() * 200) + 50;

			String insertSql = "INSERT INTO `cmcc_data_di`.`di_web_pagevisit_20141001`"
					+ "(`acct_no`,`SESSION_ID`,`APP_ID`,`CUST_ID`,`FIRST_TIME`,`LAST_TIME`,`VISIT_INTERVAL`,"
					+ "`IDENTIFY_ID`,`IF_NEWVISIT`,`REFER_URL`,`REFER_DOMAIN`,`REFER_URL_PARAM`,"
					+ "`VISIT_URL`,`VISIT_URL_PARAM`,`VISIT_IP`,`AREA_ID`,`REC_TIME`,`INPAGE_TIME`,"
					+ "`OUTPAGE_TIME`,`TERMINAL_TYPE`,`TERMINAL_NAME`,`OPER_SYSTEM`,`BROWSER_NAME`,"
					+ "`BROWSER_CORE`,`BROWSER_VERSION`,`RESOLUTION`,`SCREEN_COLORS`,`FLASH_VERSION`,"
					+ "`IS_JAVA`,`LANGUAGE_EN`,`IS_COOKIE`)"
					+ "VALUES("
					+ acct_no
					+ ",\'"
					+ session_id
					+ "\',\'"
					+ web_id
					+ "\',"
					+ area_id
					+ ","
					+ refer_id
					+ ",\'"
					+ browser_name
					+ "\',\'"
					+ os_name
					+ "\',"
					+ event_num
					+ ",\'"
					+ event_type
					+ "\',"
					+ event_durtion
					+ ","
					+ event_value
					+ ","
					+ visit_uv
					+ ","
					+ visit_vv + " );";
			sqls[i] = insertSql;
		}
		MySqlUtil.insertBatch(sqls);

	}
}
