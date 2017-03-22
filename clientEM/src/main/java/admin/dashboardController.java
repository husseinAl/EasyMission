package admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class dashboardController implements Initializable {
	@FXML
	private ListView<String> listView;
	@FXML
	private VBox vbox;

	// Event Listener on ListView[#listView].onMouseClicked
	@FXML
	public void handle(MouseEvent event) {
		String x = listView.getSelectionModel().getSelectedItem();
		System.out.println("x "+x);
		if (x=="Manage Users")
		{
            
//			try {
//				Parent root1 = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
//				//Parent root = FXMLLoader.load(getClass().getResource("UsersManagement.fxml"));
//				 //Parent root = FXMLLoader.load(getClass().getResource("RepportManagement.fxml"));
//				 // Parent root = FXMLLoader.load(getClass().getResource("Chart1.fxml"));
//				  //Parent root = FXMLLoader.load(getClass().getResource("ManageUsersWorkers.fxml"));
//		        Stage stage = (Stage) vbox.getScene().getWindow();
//			        Scene scene1 = new Scene(root1);
//			        stage.initStyle(StageStyle.DECORATED);
//			        stage.setScene(scene1);
//			        
//			        stage.close();
				//////////////////////////////////
			try {
				Parent root = FXMLLoader.load(getClass().getResource("UsersManagement.fxml"));
				Scene scene = new Scene(root);
				 Stage st = (Stage) vbox.getScene().getWindow();
			       // st.initStyle(StageStyle.DECORATED);
			        st.setScene(scene);
			        
			        st.show();
			        ////////
			        
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	///////////////////////////////////MIssions REclamations
		if (x=="Manage Missions")
		{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdminManagement.fxml"));
			Scene scene = new Scene(root);
			 Stage st = (Stage) vbox.getScene().getWindow();
		       // st.initStyle(StageStyle.DECORATED);
		        st.setScene(scene);
		        
		        st.show();
		        ////////
		        
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		else if (x=="Manage Reclamations")
		{
			try {
				Parent root = FXMLLoader.load(getClass().getResource("RepportManagement.fxml"));
				Scene scene = new Scene(root);
				 Stage st = (Stage) vbox.getScene().getWindow();
			       // st.initStyle(StageStyle.DECORATED);
			        st.setScene(scene);
			        
			        st.show();
			   
			        	
			        
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Parent root = FXMLLoader.load(getClass().getResource("Chart1.fxml"));
				Scene scene = new Scene(root);
				 Stage st = (Stage) vbox.getScene().getWindow();
			       // st.initStyle(StageStyle.DECORATED);
			        st.setScene(scene);
			        
			        st.show();
			   
			        	
			        
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
ObservableList<String> list =FXCollections.observableArrayList("Manage Users","","","","Manage Missions","","","","Manage Reclamations","","","","Statictics");
		
		listView.setItems(list);
		
		//listView.setPrefWidth(170);
		//listView.setPrefHeight(270);
		
		
	}
}
