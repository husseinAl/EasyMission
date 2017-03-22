package frames;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserServicesEJBRemote;

public class RecapPwdController implements Initializable{
	 @FXML
	    private PasswordField pwd;

	    @FXML
	    private PasswordField rpwd;

	    @FXML
	    private Button l1;

	    @FXML
	    private Button exit;

	    @FXML
	    private TextField code;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    @FXML
    void exit(ActionEvent event) {
    	Stage stage = (Stage) l1.getScene().getWindow();
	    stage.close();
    }

    @FXML
    void send(ActionEvent event) throws NamingException, IOException {
    	InitialContext ctx=new InitialContext();
		Object objet=ctx.lookup("/easyMission-ear/easyMission-ejb/UserServicesEJB!services.UserServicesEJBRemote");
		UserServicesEJBRemote proxy=(UserServicesEJBRemote)objet;
		User u1=null;
		try{
			u1=proxy.findUserById(pwdSMSController.id);
		}catch (Exception e){
			
			
		}
		String vcode=pwdSMSController.recapcode;
		if(code.getText().equals(vcode)){
		if(pwd.getText().equals(rpwd.getText())){
			u1.setPassword(pwd.getText());
		
			try{
				proxy.updateUser(u1);
				Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
				alert2.setTitle("Password ");
				alert2.setHeaderText(null);
				alert2.setContentText("Password updated" );

				alert2.showAndWait();
			}catch(Exception e){
				
			}
		
		}else{
			Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
			alert2.setTitle("Wrong Informations ");
			alert2.setHeaderText(null);
			alert2.setContentText("Passwords are not the same" );

			alert2.showAndWait();
		}}else{
			Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
			alert2.setTitle("Wrong Informations ");
			alert2.setHeaderText(null);
			alert2.setContentText("Verification code is invalid" );

			alert2.showAndWait();
		}
		
		Stage stage = (Stage) l1.getScene().getWindow();
	    stage.close();
	    Parent root = FXMLLoader.load(getClass().getResource("frame1.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

}
