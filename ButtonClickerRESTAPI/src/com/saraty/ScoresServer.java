package com.saraty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class ScoresServer extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//Needs to be able to get a list of all the highScores
		//Needs to be able to get the score of a single person
		
		//All the scores
		String requestUrl = request.getRequestURI();
		String name = requestUrl.substring("/ButtonClickerRESTAPI/people/".length()); //Creates the last bit of the URL
		String output = "";
		System.out.println(name);
		if(name.equals("all"))
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
					output += "\"Name\": " + rs.getString("name");
					output += " \"Score\": " + rs.getString("score")+"\n";
				}
				
				
			} catch (Exception e) {
				System.out.println(e);
			
			}
			
			/**
			for(String key: DataStore.getInstance().getAllKeys())
			{
				HighScore scores = DataStore.getInstance().getHighScoreOfPerson(key);
				output += "\"Name\": " + JSONObject.quote(scores.getName());
				output += " \"Score\": " + scores.getScore() + ",\n";
			}*/
			
		}else
		{
			
			DB_Connection obj_DB = new DB_Connection();
			Connection c = obj_DB.get_connection();
			PreparedStatement ps = null;
			try {
				String query = "select * from userTable WHERE name='"+name+"'";
				ps=c.prepareStatement(query);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					System.out.println("Username: "+rs.getString("name")+" Score: "+rs.getString("score"));
					output += "\"Name\": " + rs.getString("name");
					output += " \"Score\": " + rs.getString("score")+"\n";
				}
				
				
			} catch (Exception e) {
				System.out.println(e);
			
			}
			
			
			/**HighScore score = DataStore.getInstance().getHighScoreOfPerson(name);
			if(score != null)
			{
				output += "\"Name\": " + JSONObject.quote(score.getName());
				output += " \"Score\": " + score.getScore() + ",\n";
			}else
			{
				output = "That person is not in our database";
			}*/
			
			
		}
		response.getOutputStream().println(output);
		
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name = request.getParameter("name");
		int score = Integer.parseInt(request.getParameter("highScore"));
		
		DB_Connection obj_DB = new DB_Connection();
		Connection c = obj_DB.get_connection();
		PreparedStatement ps = null;
		try {
			String query = "insert into userTable (name, score)"
			        + " values ('"+name+"','"+score+"')";
			ps=c.prepareStatement(query);
			
			ps.execute();

		} catch (Exception e) {
			System.out.println(e);
		
		}
		
		
		//DataStore.getInstance().addHighScore(name,score);
	}

}
