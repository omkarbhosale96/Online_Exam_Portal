package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	
	public static Connection getJdbcConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/online_exam_portal";
		Connection connection=DriverManager.getConnection(url, "root", "root");
		return connection;
	}

}

