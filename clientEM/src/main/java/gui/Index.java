package gui;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Index extends javafx.application.Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("../gui/frame.fxml"));
	//	Parent root = FXMLLoader.load(getClass().getResource("workerMissions.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("treatApplications.fxml"));
		// set scene
		Scene scene = new Scene(root);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		// show scene
		primaryStage.show();
	}

	public static void main(String[] args) {
//		  ExecutorService executor = Executors.newFixedThreadPool(1);
//			 Runnable thread = new NotificationThread("thread1");
//			 executor.execute(thread);
//			 executor.shutdown(); 
	  launch(args);
//		  ((NotificationThread)thread).stop();

	}

}
