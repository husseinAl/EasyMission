package gui;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




public class main extends Application{

	  @Override
	    public void start(Stage stage) throws Exception {
	        Parent root = FXMLLoader.load(getClass().getResource("../frames/frame1.fxml"));	        
	        Scene scene = new Scene(root);
	        stage.setResizable(false);
			stage.getIcons().add(new Image("img/ee.png"));
			stage.setTitle("Easy Mission");
	       // stage.initStyle(StageStyle.UNDECORATED);
	        stage.setScene(scene);
	        stage.show();
	       
	    }
	  public static void main(String[] args) {
		  
		  ExecutorService executor = Executors.newFixedThreadPool(1);
			 Runnable thread = new NotificationThread("../gui/lookForMission.fxml");
			NotificationThread.id=-1;
			 executor.execute(thread);
			 executor.shutdown(); 
			 launch(args);
	  ((NotificationThread)thread).stop();
	    }
	    

}
