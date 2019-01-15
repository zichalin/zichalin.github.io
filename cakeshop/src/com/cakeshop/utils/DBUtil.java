package com.cakeshop.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	
	private static DataSource dsDataSource = new ComboPooledDataSource();
	
	public static DataSource getDataSource() {
		return dsDataSource;
	}
	
	public static Connection getConnection() throws SQLException {
		return dsDataSource.getConnection();
	}
	
}
