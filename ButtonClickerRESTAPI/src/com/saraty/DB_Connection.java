package com.saraty;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
	
	public static void main(String[] args) {
		DB_Connection obConnection = new DB_Connection();
		Connection c = null;
		c = obConnection.get_connection();
		
		System.out.println(c);
	}
	
	
	public Connection get_connection()
	{
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://LocalHost:3306/javatesting?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root","Stormcatcat10185");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
		return connection;
	}

}
