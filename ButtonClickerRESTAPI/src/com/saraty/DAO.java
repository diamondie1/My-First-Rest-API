package com.saraty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {
	
	
	public void Check_Data()
	{
		DB_Connection obj_DB = new DB_Connection();
		Connection c = obj_DB.get_connection();
		PreparedStatement ps = null;
		try {
			String query = "select * from userTable";
			ps=c.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println("Username: "+rs.getString("name")+" Score: "+rs.getString("score"));
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
