package application;


// Java Program to create fileChooser
// and add it to the stage
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.*;
import javafx.stage.FileChooser;


public class Main extends Application {

	private static String address;
		
	// Main Method
	public static void main(String args[])
	{
		// launch the application
		launch(args);
	}

	
	
	public void start(Stage stage)
	{
		try {
	
			// set title for the stage
			stage.setTitle("Simulink viewer");
	
			// create a File chooser
			FileChooser fileChooser = new FileChooser();
	
			// create a Text
			 Text label = new Text("Choose an .mdl file");
		     label.setTextAlignment(TextAlignment.CENTER);
		     label.setFont(new Font(24));
		     label.setLayoutX(150);
		     label.setLayoutY(200);
		     label.setStyle("-fx-font-weight: bold");
			
	
			// create a Button
			Button button = new Button("Select file");
			
			
			
			button.setOnAction((ActionEvent) -> {
				
				fileChooser.setTitle("Open .mdl file");
                
				FileChooser.ExtensionFilter extensionFilter
                        = new FileChooser.ExtensionFilter("MDL Files (*.mdl)", "*.mdl");
                fileChooser.getExtensionFilters().add(extensionFilter);
				
                // get the file selected
				File file = fileChooser.showOpenDialog(stage);
	
				if (file != null) {
					address = file.getAbsolutePath();
					label.setText(address + " selected");	
					
					draw(stage);
				}
			});
			
			
			// create a VBox
			VBox vbox = new VBox(30, label, button);
			// set Alignment
			vbox.setAlignment(Pos.CENTER);
			// create a scene
			Scene scene = new Scene(vbox, 500, 500);
			// set the scene
			
			stage.setScene(scene);
			stage.getIcons().add(new Image(Main.class.getResourceAsStream("/Assets/Logo.jpg")));
			stage.show();
			
			
			
		}
		catch (Exception e) {
	
			System.out.println(e.getMessage());
		}
	}


	
	private static void draw(Stage stage) {
		Systemm system = App.read(address);
		
		Pane pane = new Pane();
		Scene scene2 = new Scene(pane, 800, 500);
		
		
		
		for (Linee line : system.lines) {
			if(line.endPoint != null)
			{
				double x1 = ((line.startPoint.getX() / 1920.0) * scene2.getWidth());
				x1 *= Math.pow((2 * x1 / scene2.getWidth()), 2);
				
				double y1  =  line.startPoint.getY();
				y1 *= (2 * y1 / scene2.getHeight());
				
				double x2 = ((line.endPoint.getX() / 1920.0) * scene2.getWidth());
				x2 *= Math.pow((2 * x2 / scene2.getWidth()), 2);
				
				double y2  =  line.endPoint.getY();
				y2 *= (2 * y2 / scene2.getHeight());
				
				Line l = new Line(x1, y1, x2, y2);
				line.endPoint = new Point2D(x2, y2);
				l.setFill(Color.BLACK);
				
				pane.getChildren().add(l);
			}
		}
			
			for (Linee line1 : system.branchestoLines) {
				if(line1.endPoint != null)
				{
					double x1 = ((line1.startPoint.getX() / 1920.0) * scene2.getWidth());
					x1 *= Math.pow((2 * x1 / scene2.getWidth()), 2);
					
					double y1  =  line1.startPoint.getY();
					y1 *= (2 * y1 / scene2.getHeight());
					
					double x2 = ((line1.endPoint.getX() / 1920.0) * scene2.getWidth());
					x2 *= Math.pow((2 * x2 / scene2.getWidth()), 2);
					
					double y2  =  line1.endPoint.getY();
					y2 *= (2 * y2 / scene2.getHeight());
					
					Line l = new Line(x1, y1, x2, y2);
					l.setFill(Color.BLACK);
					
					pane.getChildren().add(l);
				}
			}
		
		for (Block block : system.blocks) {
			//Adjusting the scale of the position of the blocks
			double x = ((block.position[0] / 1920.0) * scene2.getWidth());
			x *= Math.pow((2 * x / scene2.getWidth()), 2);
			
			double y  =  block.position[1];
			y *= (2 * y / scene2.getHeight());
			
			Rectangle rect = new Rectangle(x,y,block.getWidth()
					,block.getLength());
			
			Text text = new Text(x , y + 70, block.getName());
			
		        text.setStyle(
		                "-fx-font: 15px Helvetica; -fx-fill:blue;"
		        );
		       text.setTextAlignment(TextAlignment.CENTER);
		     
		       Image symbolImage = new Image(Main.class.getResourceAsStream("/Assets/"+block.getName()+".jpg"));
		       ImageView imageView = new ImageView(symbolImage);
		       
		       
		       
	           if(block.getName().equals("Unit Delay")){
	        	   imageView.setScaleX(0.3);
		           imageView.setScaleY(0.3);
		            imageView.setLayoutY(y - 40);
		            imageView.setLayoutX(x - 40);
		        }else {     
		        	imageView.setScaleX(0.42);
			        imageView.setScaleY(0.42);
		            imageView.setLayoutY(y - 35);
		            imageView.setLayoutX(x - 35);
		        }
		       
		       
			rect.setStroke(Color.BLUE);
			rect.setFill(Color.WHITE);
			//imageView.toFront();
			pane.getChildren().addAll(rect, text, imageView);	
		}			
		
		
		
		system.makeTriangles();
		pane.getChildren().addAll(system.triangles);

        
		stage.setScene(scene2);
		stage.show();
	}
}
