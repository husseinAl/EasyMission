package frames;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class frame2Controller  implements Initializable {
	@FXML
	private Button bt2;
	@FXML
	private Button bt1;
	@FXML
	private Button bt3;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
	 
	    @FXML
	    private void handleButtonAction(ActionEvent event) throws IOException, NamingException {
	    	Stage stage = (Stage) bt1.getScene().getWindow();
		    stage.close();
		    Parent root = FXMLLoader.load(getClass().getResource("frameE4.fxml"));
	        Scene scene1 = new Scene(root);
	        stage.setScene(scene1);
	        stage.show();
	    }
	    @FXML
	    private void handleButton2Action(ActionEvent event) throws IOException, NamingException {
	    	Stage stage = (Stage) bt1.getScene().getWindow();
		    stage.close();
		    Parent root = FXMLLoader.load(getClass().getResource("frameW3.fxml"));
	        Scene scene1 = new Scene(root);
	        stage.setScene(scene1);
	        stage.show();
	    }
	    @FXML
	    private void handleButton3Action(ActionEvent event) throws IOException, NamingException {
	    	Stage stage = (Stage) bt1.getScene().getWindow();
		    stage.close();
		    Parent root = FXMLLoader.load(getClass().getResource("frame1.fxml"));
	        Scene scene1 = new Scene(root);
	        stage.setScene(scene1);
	        stage.show();
	    }
	    
	    
	

}
