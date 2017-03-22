package admin;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Repport;
import entities.Skill;
import entities.User;
import entities.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.AdminserviceEJBRemote;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class ManageUsersWorkersController implements Initializable{
	ObservableList<User> dataw;
	@FXML
	private TableView<User> tableworkers;
	@FXML
	private TableColumn <User,Integer>id;
	@FXML
	private TableColumn <User,String>login;
	@FXML
	private TableColumn<User,String> firstname;
	@FXML
	private TableColumn<User,String> lastname;
	@FXML
	private TableColumn<User,String> email;
	@FXML
	private TableColumn<User,String> password;
	@FXML
	private TableColumn <User,String>country;
	@FXML
	private TableColumn<User,String> state;
	   @FXML
	 private TableColumn<User, String> cv;
	   
	   @FXML
	 private TableColumn<User, String> description;

	    @FXML
	    private TableColumn<User, List<String>> columnskills;
	  // @FXML
	   // private TableColumn<Repport, String> description;
	   
	   
	 @FXML
	  private Button btnclose;
	 @FXML
	    private Label labelworker;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			 InitialContext	ic = new InitialContext();
				
			AdminserviceEJBRemote proxy = (AdminserviceEJBRemote) ic.lookup("/easyMission-ear/easyMission-ejb/"
					+ "AdminserviceEJB!services.AdminserviceEJBRemote");
		
		List<User>  elistw = proxy.displayallusers();
		
		dataw=FXCollections.observableArrayList();
			for (User us :elistw)
				
			{
				if (us instanceof Worker)
				{
				//dataw.add(new User (us.getIdUser(),us.getLogin(),us.getFirstName(),us.getLastName(),us.getEmail(),us.getPassword(),us.getCountry(),us.getState(),((Worker) us).getCv()));
					dataw.add(new Worker(us.getIdUser(),us.getLogin(),us.getFirstName(),us.getLastName(),us.getEmail(),us.getPassword(),us.getCountry(),us.getState(),((Worker) us).getCv(),((Worker) us).getDescription(),((Worker) us).getSkills()));
			}
			}
			
			tableworkers.setItems(dataw);
			id.setCellValueFactory(new PropertyValueFactory<User, Integer>("idUser"));
			login.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
			firstname.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
			lastname.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
			email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
			password.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
			country.setCellValueFactory(new PropertyValueFactory<User, String>("country"));
			state.setCellValueFactory(new PropertyValueFactory<User, String>("state"));
			cv.setCellValueFactory(new PropertyValueFactory<User, String>("cv"));
			description.setCellValueFactory(new PropertyValueFactory<User, String>("description"));
			columnskills.setCellValueFactory(new PropertyValueFactory<User, List<String>>("skills"));
			
			//description.setCellValueFactory(new PropertyValueFactory<Repport, String>("reporterFullName"));

			
			
			
		 } catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	

    @FXML
    void close(ActionEvent event) {
    	Stage st = (Stage) labelworker.getScene().getWindow();
    	st.close();

    }

}
