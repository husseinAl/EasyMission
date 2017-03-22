package frames;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DrawerContentController2 implements Initializable{

    @FXML
    private VBox box;

    @FXML
    private ImageView iv1;

    @FXML
    private JFXButton btn1;

    @FXML
    private JFXButton btn2;

    @FXML
    private JFXButton btn3;

    @FXML
    private JFXButton btn31;

    @FXML
    private JFXButton btn4;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

    @FXML
    void change(ActionEvent event) throws IOException {
    	JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
            case "Home":
            	Stage stage2=new Stage();
            	stage2.getIcons().add(new Image("img/ee.png"));
                Parent root2 = FXMLLoader.load(getClass().getResource("User2.fxml"));
                Scene scene11 = new Scene(root2);
//                stage2.initStyle(StageStyle.UNDECORATED);
                stage2.setScene(scene11);
                stage2.show();
                Stage stage22 = (Stage) btn1.getScene().getWindow();
    		    stage22.close();

            
                break;
            case "Edit":
            	Stage stage=new Stage();
            	stage.getIcons().add(new Image("img/ee.png"));
            Parent root = FXMLLoader.load(getClass().getResource("EditWorker.fxml"));
            Scene scene1 = new Scene(root);
//            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene1);
            stage.show();
            Stage stage1 = (Stage) btn1.getScene().getWindow();
		    stage1.close();
                break;
           
        }
    }

    @FXML
    void exit(ActionEvent event) {
    	System.exit(0);
    	

    }
    @FXML
    void msearch(ActionEvent event) throws IOException {
    	Stage stage=new Stage();
    	stage.getIcons().add(new Image("img/ee.png"));
        Parent root = FXMLLoader.load(getClass().getResource("../gui/lookForMission.fxml"));
        Scene scene1 = new Scene(root);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene1);
        stage.show();
        Stage stage1 = (Stage) btn1.getScene().getWindow();
	    stage1.close();

    }

    @FXML
    void myMission(ActionEvent event) throws IOException {
    	Stage stage=new Stage();
    	stage.getIcons().add(new Image("img/ee.png"));
        Parent root = FXMLLoader.load(getClass().getResource("../gui/workerMissions.fxml"));
        Scene scene1 = new Scene(root);
//        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene1);
        stage.show();
        Stage stage1 = (Stage) btn1.getScene().getWindow();
	    stage1.close();
    }



}
