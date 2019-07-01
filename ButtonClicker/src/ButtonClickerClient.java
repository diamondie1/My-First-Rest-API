


import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ButtonClickerClient extends Application {
	
	private int count = 0;
	private Label score;
	
	@Override
	public void start(Stage stage) throws Exception {
		Button gameButton = new Button("Press for Points");
		gameButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				count++;
				score.setText(Integer.toString(count));
				
			}
		});
		score = new Label("0");
		TextArea nameArea = new TextArea("Please input name");
		Button sumbitHighScore = new Button("Submit High Score");
		sumbitHighScore.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event)  {
				System.out.println("This is being clicked");
				String name = nameArea.getText();
				try {
					sendData(name);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		
		GridPane gridPane = new GridPane();    
	      
	      //Setting size for the pane  
	      gridPane.setMinSize(400, 200); 
	       
	      //Setting the padding  
	      gridPane.setPadding(new Insets(10, 10, 10, 10)); 
	      
	      //Setting the vertical and horizontal gaps between the columns 
	      gridPane.setVgap(5); 
	      gridPane.setHgap(5);       
	      
	      //Setting the Grid alignment 
	      gridPane.setAlignment(Pos.CENTER); 
	      gridPane.add(gameButton, 0, 0); 
	      gridPane.add(score, 1, 0); 
	      gridPane.add(sumbitHighScore, 0, 1);  
	      gridPane.add(nameArea, 1, 1);
	     
	      //Creating a scene object 
	      Scene scene = new Scene(gridPane);  
	      
	      //Setting title to the Stage 
	      stage.setTitle("Grid Pane Example"); 
	         
	      //Adding scene to the stage 
	      stage.setScene(scene); 
	         
	      //Displaying the contents of the stage 
	      stage.show(); 
	   } 
	   public static void main(String args[]){ 
	      launch(args); 
	   } 
	   
	   public void sendData(String name) throws IOException
	   {
		   System.out.println(name);
		   HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/ButtonClickerRESTAPI/people/" + name).openConnection();

			connection.setRequestMethod("POST");
			
			String postData = "name=" + URLEncoder.encode(name);
			postData += "&highScore=" + count;
			
			connection.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
		    wr.write(postData);
		    wr.flush();
			
			int responseCode = connection.getResponseCode();
			if(responseCode == 200){
				System.out.println("POST was successful.");
			}
			else if(responseCode == 401){
				System.out.println("Wrong password.");
			}
			
		}

		   
	   }
	   
	

