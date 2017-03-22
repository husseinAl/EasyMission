package frames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Employer;
import entities.User;
import entities.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserServicesEJBRemote;
import javafx.fxml.Initializable;

public class pwdSMSController implements Initializable{
	public static int id;
	public static String recapcode;
	@FXML
    private TextField login;

    @FXML
    private Button l1;

    @FXML
    private Button exit;
    private String executeUrl(final String http_url) {
        String res = null;
        try {
            URL url = new URL(http_url);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            while (null != (strTemp = br.readLine())) {
                res += strTemp;
            }
        } catch (Exception ex) {
        }
        return res;
    }
    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
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
		Worker w=null;
		Employer E=null;
		try{
		u1=proxy.findUserByLogin(login.getText());
		w=(Worker) proxy.findUserByLogin(login.getText());
		E=(Employer) proxy.findUserByLogin(login.getText());
		}catch(Exception e){
			/*Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
			alert2.setTitle("Wrong Informations ");
			alert2.setHeaderText(null);
			alert2.setContentText("check your Login " );

			alert2.showAndWait();*/
		}
		id=u1.getIdUser();
		String num;
		recapcode=getSaltString();
		System.out.println(recapcode);
		if(u1 instanceof Employer){
			 num=E.getCompanyNumber();
		}else{
			num=w.getPhoneNumber();
		}
		
    	String nexmocmd =  "https://rest.nexmo.com/sms/json?api_key=4a7932d7&api_secret=f83a94935210801d&from=NEXMO&to="+26+num+"&text="+recapcode;
        String response = executeUrl(nexmocmd);
        if (response != null) {
        	Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
			alert2.setTitle("SMS ");
			alert2.setHeaderText(null);
			alert2.setContentText("Verification Code Sent " );

			alert2.showAndWait();
			Stage stage = (Stage) l1.getScene().getWindow();
		    stage.close();
		    Parent root = FXMLLoader.load(getClass().getResource("recapPwd.fxml"));
	        Scene scene1 = new Scene(root);
	        stage.setScene(scene1);
	        stage.show();
        } else {
        	Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
			alert2.setTitle("SMS ");
			alert2.setHeaderText(null);
			alert2.setContentText("Verification Code not Sent " );

			alert2.showAndWait();
			Stage stage = (Stage) l1.getScene().getWindow();
		    stage.close();
		    Parent root = FXMLLoader.load(getClass().getResource("frame1.fxml"));
	        Scene scene1 = new Scene(root);
	        stage.setScene(scene1);
	        stage.show();
        }

    }

}
