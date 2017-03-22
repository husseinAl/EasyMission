package frames;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class frame0Controller implements Initializable{
	@FXML
	private StackPane rootPane;
	@FXML
	private Button bt;
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        new SplashScreen().start();
    }
	class SplashScreen extends Thread{
	      @Override
	      public  void run(){
	          
	              try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	               
	              Platform.runLater(new Runnable() {
	                  @Override
	                  public void run() {
	                      Parent root=null;
	                      
	                          try {
								root = FXMLLoader.load(getClass().getResource("frame1.fxml"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                     
	        Scene scene = new Scene(root);
	        Stage stage=new Stage();
	        stage.initStyle(StageStyle.UNDECORATED);
	        stage.setScene(scene);
	        stage.show();
	        bt.getScene().getWindow().hide();
	                  }
	              });
	       
	          
	      }
	    }
   
      
      
       
         
      
    

}
