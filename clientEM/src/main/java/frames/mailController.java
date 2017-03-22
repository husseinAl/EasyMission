package frames;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class mailController implements Initializable{

    @FXML
    private JFXTextField to;

    @FXML
    private JFXTextField object;

    @FXML
    private JFXTextArea content;

    @FXML
    private JFXButton sendmail;
    @FXML
    private JFXHamburger ham;

    @FXML
    private JFXDrawer drawer;

	// Event Listener on Button[#sendmail].onAction
	@FXML
	public void SEND(ActionEvent event) {
		
	    	
	    	 String user = "pdevesprit2017@gmail.com";
				String password = "esprit2017";

				String email = to.getText();
				//String email= "sirine.hichri@esprit.tn";

				Properties prop = new Properties();
				prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
				prop.put("mail.smtp.auth", true);
				prop.put("mail.smtp.starttls.enable", true);
				prop.put("mail.smtp.host", "smtp.gmail.com");
				prop.put("mail.smtp.port", "587");
//				Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
//					@Override
//					protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//						return new javax.mail.PasswordAuthentication(user, password);
//					}
//				});
				try {
//					MimeMessage msgg = new MimeMessage(session);
//					msgg.setFrom(new InternetAddress("pdevesprit2017@gmail.com"));
//					msgg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email));
//					msgg.setSubject(object.getText());
//					msgg.setText(content.getText());
//					Transport.send(msgg);
//
//					Alert alert = new Alert(Alert.AlertType.INFORMATION);
//					alert.setTitle("Email");
//					alert.setContentText("Your email is sent successfully ");
//					alert.setHeaderText(null);
//					alert.showAndWait();

				} catch (Exception e) {
					System.out.println(e);
				}
		    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			VBox box =FXMLLoader.load(getClass().getResource("DrawerContent.fxml"));
			drawer.setSidePane(box);
			
		
    	
    	HamburgerBackArrowBasicTransition burgertask = new HamburgerBackArrowBasicTransition(ham);
    	burgertask.setRate(-1);
    	ham.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) ->{
    		burgertask.setRate(burgertask.getRate() * -1);
    		burgertask.play();
    		if(drawer.isShown())
            {
                drawer.close();
            }else
                drawer.open();
    		
    		
    	
    	}
    	);
    }catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		
	}
	}

