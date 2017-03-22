package admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import services.AdminserviceEJBRemote;
//import services.ReclamationserviceEJBRemote;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Mission;
import entities.Repport;
import entities.User;
import javafx.event.ActionEvent;

import javafx.scene.control.TextArea;

public class addreclamationController implements Initializable {
	@FXML
	private TextField tfobject;
	@FXML
	private TextArea tftext;
	@FXML
	private Button btnsend;

	// Event Listener on Button[#btnsend].onAction
	@FXML
	public void sendreclamation(ActionEvent event) throws NamingException {
		 InitialContext	ic = new InitialContext();
			
			AdminserviceEJBRemote proxy = (AdminserviceEJBRemote) ic.lookup("/easyMission-ear/easyMission-ejb"
					+ "/AdminserviceEJB!services.AdminserviceEJBRemote");
		
		Repport r= new Repport();
		User u = proxy.finduserbyid(1);
		Mission m = proxy.findmissionbyId(5);
		
		r.setObject(tfobject.getText());
	    r.setText(tftext.getText());
		proxy.repport(u, m);;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
