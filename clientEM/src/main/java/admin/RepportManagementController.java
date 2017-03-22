package admin;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
//
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import embadableIDs.RepportId;
import entities.Repport;
import entities.User;
import entities.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.AdminserviceEJBRemote;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class RepportManagementController implements Initializable {
	ObservableList<Repport> dataRp;
	ObservableList<Repport> datatRpt;
	@FXML
	private Label labeladmin;
	@FXML
	private ImageView iv1;
	@FXML
	private Label labelreports;
	@FXML
	private TableView<Repport> tablerepports;
	@FXML
	private TableColumn<Repport,String> columnobject;
	@FXML
	private TableColumn<Repport,String> columntext;
	@FXML
	private TableColumn<Repport,String> columnstate;
	@FXML
	private TableColumn <Repport,String>columnreporter;
	 @FXML
	    private TextField tfmail;
	@FXML
    private Button btnaccept;
	@FXML
    private Button btnrefresh;
    @FXML
    private Button btndecline;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnsendmail;

    @FXML
    private TableView<Repport> tableuntraitedreports;

    @FXML
    private TableColumn<Repport, String> tobject;

    @FXML
    private TableColumn<Repport, String> ttest;

    @FXML
    private TableColumn<Repport, String> tstate;

    @FXML
    private TableColumn<Repport, String> treporter;
    @FXML
    private TableColumn<Repport, Integer> colmissionid;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Repport rp= tableuntraitedreports.getItems().get(tableuntraitedreports.getSelectionModel().getSelectedIndex());
		//System.out.println("hihi"+rp.getMission().getIdMission());
		try {
			 InitialContext	ic = new InitialContext();
				
			AdminserviceEJBRemote proxy = (AdminserviceEJBRemote) ic.lookup("/easyMission-ear/easyMission-ejb/"
					+ "AdminserviceEJB!services.AdminserviceEJBRemote");
		
		List<Repport>  elistR = proxy.displayholdingReclmations();
		List<Repport> elistRt= proxy.displaytraitedReclmations();
		
		dataRp=FXCollections.observableArrayList();
		
			for (Repport r :elistR)
				
			{
				
				dataRp.add(new Repport(r.getObject(),r.getText(),r.getState(),r.getUser(),r.getIdRepport()));
				//dataw.add(new User (us.getIdUser(),us.getLogin(),us.getFirstName(),us.getLastName(),us.getEmail(),us.getPassword(),us.getCountry(),us.getState(),((Worker) us).getCv()));
					//dataw.add(new Worker(us.getIdUser(),us.getLogin(),us.getFirstName(),us.getLastName(),us.getEmail(),us.getPassword(),us.getCountry(),us.getState(),((Worker) us).getCv(),((Worker) us).getDescription()));
			
			}
			
			tableuntraitedreports.setItems(dataRp);
			 tobject.setCellValueFactory(new PropertyValueFactory<Repport, String>("object"));
			ttest.setCellValueFactory(new PropertyValueFactory<Repport, String>("text"));
			 tstate.setCellValueFactory(new PropertyValueFactory<Repport, String>("state"));
			 treporter.setCellValueFactory(new PropertyValueFactory<Repport, String>("reporterFullName"));
			 colmissionid.setCellValueFactory(new PropertyValueFactory<Repport, Integer>("idmissionreport"));
			
			//description.setCellValueFactory(new PropertyValueFactory<Repport, String>("reporterFullName"));

			//////traited reportss
			 datatRpt=FXCollections.observableArrayList();
			 for (Repport r :elistRt)
					
				{
					
					datatRpt.add(new Repport(r.getObject(),r.getText(),r.getState(),r.getUser()));
					//dataw.add(new User (us.getIdUser(),us.getLogin(),us.getFirstName(),us.getLastName(),us.getEmail(),us.getPassword(),us.getCountry(),us.getState(),((Worker) us).getCv()));
						//dataw.add(new Worker(us.getIdUser(),us.getLogin(),us.getFirstName(),us.getLastName(),us.getEmail(),us.getPassword(),us.getCountry(),us.getState(),((Worker) us).getCv(),((Worker) us).getDescription()));
				
				}
				
				tablerepports.setItems(datatRpt);
				columnobject.setCellValueFactory(new PropertyValueFactory<Repport, String>("object"));
				columntext.setCellValueFactory(new PropertyValueFactory<Repport, String>("text"));
				 columnstate.setCellValueFactory(new PropertyValueFactory<Repport, String>("state"));
				 columnreporter.setCellValueFactory(new PropertyValueFactory<Repport, String>("reporterFullName"));
				 
			
			
		 } catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		setcellvaluefromrabletotextfield();
	}
	@FXML
    void accept(ActionEvent event) {
		try {
			InitialContext ic2 = new InitialContext();
			AdminserviceEJBRemote proxy2 = (AdminserviceEJBRemote) ic2.lookup("/easyMission-ear/easyMission-ejb/"
					+ "AdminserviceEJB!services.AdminserviceEJBRemote");
			
			
			Repport rp= tableuntraitedreports.getItems().get(tableuntraitedreports.getSelectionModel().getSelectedIndex());
			System.out.println("here"+rp.getIdmissionreport());
			RepportId ti = new RepportId(rp.getIdRepport().getIdUserPK(), rp.getIdRepport().getIdMisssionPK());
			proxy2.findRepportById(ti);
			proxy2.treatReport(rp);
			//proxy2.findmissionbyId(m.getIdMission());
			//proxy2.validatemission(m);
			//proxy2.treatReport(rp);
			
			//System.out.println("object"+r.getObject());
			//System.out.println("state "+r.getState());
			//System.out.println("idmiddionreported : "+r.getMission());
			
			///////
			/*RepportId ri = new RepportId();
			ri.setIdMisssionPK(r.getMission().getIdMission());
			ri.setIdUserPK(r.getUser().getIdUser());
			Repport rr = proxy2.findRepportById(ri);
			System.out.println("hhhhhhhhhhhhhhh");
			System.out.println(rr.getText());
			proxy2.treatReport(rr);*/
			
		} catch (NamingException e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void decline(ActionEvent event) throws NamingException {
    	
		
    	 InitialContext	ic3 = new InitialContext();
			
			AdminserviceEJBRemote proxy3 = (AdminserviceEJBRemote) ic3.lookup("/easyMission-ear/easyMission-ejb/"
					+ "AdminserviceEJB!services.AdminserviceEJBRemote");
			Repport rpt= tableuntraitedreports.getItems().get(tableuntraitedreports.getSelectionModel().getSelectedIndex());
			RepportId tir = new RepportId(rpt.getIdRepport().getIdUserPK(), rpt.getIdRepport().getIdMisssionPK());
			proxy3.findRepportById(tir);
			
			proxy3.declinereclamation(rpt);

    }

    @FXML
    void refresh(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("RepportManagement.fxml"));
      	 Scene scene = new Scene(root);
      	 Stage st = (Stage) labeladmin.getScene().getWindow();
      	 st.setScene(scene);
      	 st.close();
   	 st.show();
    }
    
    @FXML
    void retour(ActionEvent event) throws IOException {
    	Parent root2 = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
   	 Scene scene3 = new Scene(root2);
   	 Stage st1 = (Stage) labelreports.getScene().getWindow();
   	 st1.setScene(scene3);
	        st1.show();
    }
    @FXML
    void sendmail(ActionEvent event) {
    	
    	 String user = "pdevesprit2017@gmail.com";
			String password = "esprit2017";

			String email = tfmail.getText();

			Properties prop = new Properties();
			prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			prop.put("mail.smtp.auth", true);
			prop.put("mail.smtp.starttls.enable", true);
			prop.put("mail.smtp.host", "smtp.gmail.com");
//			prop.put("mail.smtp.port", "587");
//			Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
//				@Override
//				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//					return new javax.mail.PasswordAuthentication(user, password);
//				}
//			});
//			try {
//				MimeMessage msgg = new MimeMessage(session);
//				msgg.setFrom(new InternetAddress("pdevesprit2017@gmail.com"));
//				msgg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email));
//				msgg.setSubject("Report");
//				msgg.setText("your reclamation have been treated , we decided to remove the mission");
//				Transport.send(msgg);

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Email");
				alert.setContentText("Your email is sent successfully ");
				alert.setHeaderText(null);
				alert.showAndWait();
//
//			} catch (Exception e) {
//				System.out.println(e);
//			}
	    }
    	

    

    public void setcellvaluefromrabletotextfield ()
	{
    	tablerepports.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				Repport r = tablerepports.getItems().get(tablerepports.getSelectionModel().getSelectedIndex());
				
				tfmail.setText(r.getUser().getEmail());
				
				
		
				
			}
		});
	}

}
