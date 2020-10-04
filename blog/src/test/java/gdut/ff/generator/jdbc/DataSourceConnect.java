package gdut.ff.generator.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import gdut.ff.generator.util.PropertyUtil;
import gdut.ff.generator.util.StringUtil;
import gdut.ff.generator.util.TypeAliasRegistry;

/**
 * @author liuffei
 * @date 2018-03-28
 * @description
 */
public class DataSourceConnect {

	public Map<String, Class<?>> typeAliases = new TypeAliasRegistry().getTYPE_ALIASES();
	
	/**
	 * 建立数据库连接
	 * @return
	 */
	public Connection getConnect() {
		try {
			//1.加载驱动
			Class.forName(PropertyUtil.JDBC_DRIVER);
			//2.连接
			Connection connection = DriverManager.getConnection(PropertyUtil.JDBC_URL, PropertyUtil.JDBC_USERNAME, PropertyUtil.JDBC_PASSWORD);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 关闭
	 * @param connection
	 */
	public void close(Connection connection,Statement statement,ResultSet resultSet) {
		if(null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(null != statement) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(null != resultSet) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 查询数据库全部的表
	 */
	public List<String> QueryAllTable() {
		Connection conn = null;
		Statement prepareStatement = null;
		ResultSet resultSet = null;
		List<String> tables = new LinkedList<String>();
		try {
			conn = getConnect();
			String sql = "SHOW TABLES";
			prepareStatement = conn.prepareStatement(sql);
			resultSet = prepareStatement .executeQuery(sql);
			while(resultSet.next()) {
				tables.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(conn,prepareStatement,resultSet);
		return tables;
	}

	/**
	 * 查询一张表全部的字段
	 * @param tableName
	 * @param databaseName
	 * @return
	 */
	public List<Map<String,Object>> queryColumn(String tableName,String databaseName) {
		Connection conn = null;
		Statement prepareStatement = null;
		ResultSet resultSet = null;
		List<Map<String,Object>> columns = new LinkedList<Map<String,Object>>();
		try {
			conn = getConnect();
			String sql = "SELECT COLUMN_COMMENT,COLUMN_NAME,DATA_TYPE,IS_NULLABLE,CHARACTER_MAXIMUM_LENGTH,COLUMN_KEY FROM information_schema.`COLUMNS` WHERE TABLE_NAME = '"+tableName+"' AND TABLE_SCHEMA = '"+databaseName+"'";
			prepareStatement = conn.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery(sql);
			while(resultSet.next()) {
				String columnComment = resultSet.getString(1);
				String columnName = resultSet.getString(2);
				String dataType = resultSet.getString(3);
				String isNullable = resultSet.getString(4);
				int length = resultSet.getInt(5);
				String primaryKey = resultSet.getString(6);
				Map<String,Object> column = new HashMap<String,Object>();
				column.put("columnComment", columnComment);
				column.put("propertyName", StringUtil.tableColumnToClassProperty(columnName.toLowerCase()));
				column.put("dataType", dataType);
				column.put("isNullable", isNullable.toLowerCase().equals("yes") ? "true" : "false");
				column.put("length", length);
				column.put("primaryKey", primaryKey);
				column.put("javaType", typeAliases.get(dataType) != null ? typeAliases.get(dataType).getSimpleName() : "String");
				column.put("columnName", columnName.toLowerCase());
				columns.add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(conn,prepareStatement,resultSet);
		return columns;
	}
	
	/**
	 * 获取连接的数据库的名称
	 * @param url
	 * @return
	 */
	public String getDatabaseName() {
		int index1 = PropertyUtil.JDBC_URL.lastIndexOf("/");
		int index2 = PropertyUtil.JDBC_URL.lastIndexOf("?");
		if(index2 > -1) {
			//http://127.0.0.1:8080/xxx?
			return PropertyUtil.JDBC_URL.substring(index1+1,index2);
		}else {
			//http://127.0.0.1:8080/xxx
			return PropertyUtil.JDBC_URL.substring(index1+1);
		}
	}
	

}
