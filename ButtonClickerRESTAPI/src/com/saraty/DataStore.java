package com.saraty;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataStore {
	
	private Map<String, HighScore> highScores = new HashMap<>();
	
	//this class is a singleton and should not be instantiated directly!
		private static DataStore instance = new DataStore();
		public static DataStore getInstance(){
			return instance;
		}
		
		private DataStore()
		{
			highScores.put("Chris", new HighScore("Chris",10));
			highScores.put("John", new HighScore("John",5));
			
		}
		
		public HighScore getHighScoreOfPerson(String name)
		{
			return highScores.get(name);
		}
		
		public void addHighScore(String name,int score)
		{
			highScores.put(name, new HighScore(name, score));
		}
		
		public Set<String> getAllKeys()
		{
			return highScores.keySet();
		}
		

}
