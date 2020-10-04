package gdut.ff.generator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


import gdut.ff.generator.jdbc.DataSourceConnect;

public class InsertBillionData {
	
	DataSourceConnect connect = new DataSourceConnect();
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmsss");
	
	@Test
	public void testInsertBillionData() {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = connect.getConnect();
			connection.setAutoCommit(false);
			String sql = "insert into user(user_id,user_name,birth,address,telephone,education,sex) values(?,?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			long a=System.currentTimeMillis();  
			for(int i = 0;i < 10000000;i++) {
				statement.setString(1, format.format(new Date()));
				statement.setString(2, "liuffei");
				statement.setString(3, "1995-08-21");
				statement.setString(4, "广东省广州市天河区");
				statement.setString(5, "13642315483");
				statement.setString(6, "大学本科");
				statement.setString(7, "1");
				statement.addBatch();
				if(i % 100000 == 0) {
					statement.executeBatch();
				}
			}
			statement.executeBatch();
			connection.commit();
			long b=System.currentTimeMillis();  
            System.out.println("MySql批量插入10万条记录用时"+ (b-a)+" ms");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != statement) {
					statement.close();
				}
				if(null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
