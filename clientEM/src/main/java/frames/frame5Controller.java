package frames;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class frame5Controller implements Initializable{
	@FXML
	private Button next;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
	public void handleButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) next.getScene().getWindow();
	    stage.close();
	    Parent root = FXMLLoader.load(getClass().getResource("frame6.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}

	
}
