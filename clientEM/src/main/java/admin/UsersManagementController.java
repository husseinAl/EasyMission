package admin;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Employer;
import entities.Mission;
import entities.User;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.AdminserviceEJBRemote;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class UsersManagementController  implements Initializable{
	ObservableList<User> data;
	@FXML
	private TableView<User> tablereclamation;
	@FXML
	private TableColumn <User,Integer>columnid;
	@FXML
	private TableColumn <User,String>login;
	@FXML
	private TableColumn <User,String>columnfname;
	@FXML
	private TableColumn<User,String> columnlname;
	@FXML
	private TableColumn <User,String>columnemail;
	@FXML
	private TableColumn<User,String> columnpwd;
	@FXML
	private TableColumn <User,String>columncountry;
	@FXML
	private TableColumn <User,String>columnstate;

    @FXML
    private TableColumn<User, String> columnadress;

    @FXML
    private TableColumn<User, String> columncompany;
	@FXML
	private ImageView iv1;
	@FXML
	private ImageView iv2;
	@FXML
	private ImageView iv3;
	@FXML
	private Label labeladmn;
	@FXML
    private Button exit;
	 @FXML
	 private Button btnnext;
	 @FXML
	    private Label labelfname;

	    @FXML
	    private Label labellname;

	    @FXML
	    private TextField tffname;

	    @FXML
	    private TextField tflname;

	    @FXML
	    private Button btnblock;



    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			 InitialContext	ic = new InitialContext();
				
			AdminserviceEJBRemote proxy = (AdminserviceEJBRemote) ic.lookup("/easyMission-ear/easyMission-ejb/"
					+ "AdminserviceEJB!services.AdminserviceEJBRemote");
		
		List<User>  elist = proxy.displayallusers();
		
		data=FXCollections.observableArrayList();
			for (User us :elist)
				
			{
				if (us instanceof Employer)
				{
				data.add(new Employer (us.getIdUser(),us.getLogin(),us.getFirstName(),us.getLastName(),us.getEmail(),us.getPassword(),us.getCountry(),us.getState(),((Employer) us).getAdress(),((Employer) us).getCompany()));
			}
			}
			
			tablereclamation.setItems(data);
			columnid.setCellValueFactory(new PropertyValueFactory<User, Integer>("idUser"));
			login.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
			columnfname.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
			columnlname.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
			columnemail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
			columnpwd.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
			columncountry.setCellValueFactory(new PropertyValueFactory<User, String>("country"));
			columnstate.setCellValueFactory(new PropertyValueFactory<User, String>("state"));
			columnadress.setCellValueFactory(new PropertyValueFactory<User, String>("adress"));
			columncompany.setCellValueFactory(new PropertyValueFactory<User, String>("company"));
			
			
			
		 } catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		setcellvaluefromrabletotextfield ();
	}
	
	public void setcellvaluefromrabletotextfield ()
	{
		tablereclamation.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				User u = tablereclamation.getItems().get(tablereclamation.getSelectionModel().getSelectedIndex());
				
				tffname.setText(u.getFirstName());
				tflname.setText(u.getLastName());
				
		
				
			}
		});
	}
    @FXML
    void exit(ActionEvent event) {
    	Stage st = (Stage) iv1.getScene().getWindow();
    	st.close();
    }
    
    @FXML
    void block(ActionEvent event) {

		try {
			InitialContext ic2 = new InitialContext();
			AdminserviceEJBRemote proxy2 = (AdminserviceEJBRemote) ic2.lookup("/easyMission-ear/easyMission-ejb/"
					+ "AdminserviceEJB!services.AdminserviceEJBRemote");
			
			
			User u = tablereclamation.getItems().get(tablereclamation.getSelectionModel().getSelectedIndex());
			System.out.println("id "+u.getIdUser());
			System.out.println("state "+u.getState());
			proxy2.finduserbyid(u.getIdUser());
			proxy2.blockuser(u);
			
		} catch (NamingException e)
		{
			e.printStackTrace();
		}

    }
    @FXML
    void next(ActionEvent event) throws IOException  {
    	Parent root = FXMLLoader.load(getClass().getResource("ManageUsersWorkers.fxml"));
    	 Scene scene = new Scene(root);
    	 Stage st = (Stage) iv1.getScene().getWindow();
    	 st.setScene(scene);
	        st.show();

    }

}
