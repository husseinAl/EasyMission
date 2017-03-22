package admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Mission;
import entities.Skill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.AdminserviceEJBRemote;
import javafx.scene.control.TableColumn;

public class AdminManagementController implements Initializable {
	ObservableList<Mission> data;
	@FXML
	private Label lbmission;
	@FXML
	private TableView<Mission> tablemission;
	@FXML
	private TableColumn<Mission,Integer> id;
	@FXML
	private TableColumn <Mission,String>title;
	@FXML
	private TableColumn <Mission,String>price;
	@FXML
	private TableColumn<Mission,String> state;
	@FXML
	private TableColumn <Mission,String>field;
	@FXML
	private TableColumn <Mission,String>employer;
	@FXML
    private TableColumn<Mission, String> startdate;
	@FXML
    private TableColumn<Mission, String> enddate;

    @FXML
    private TableColumn<Mission, String> type;

    @FXML
    private TableColumn<Mission, String> skills;
    @FXML
    private TableColumn<Mission, String> Description;
    @FXML
    private TextField tftext;

    @FXML
    private TextField tfdescription;

    @FXML
    private TextField tfskills;

	@FXML
	private Button btnvalidate;
	@FXML
	private Button btnrefresh;

	// Event Listener on Button[#btnvalidate].onAction
	@FXML
	public void validermission(ActionEvent event) {
		
		try {
			InitialContext ic2 = new InitialContext();
			AdminserviceEJBRemote proxy2 = (AdminserviceEJBRemote) ic2.lookup("/easyMission-ear/easyMission-ejb/"
					+ "AdminserviceEJB!services.AdminserviceEJBRemote");
			
			
			Mission m = tablemission.getItems().get(tablemission.getSelectionModel().getSelectedIndex());
			
			proxy2.findmissionbyId(m.getIdMission());
			proxy2.validatemission(m);
			
			
		} catch (NamingException e)
		{
			e.printStackTrace();
		}
	}
	// Event Listener on Button[#btnrefresh].onAction
	@FXML
	public void refresh(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AdminManagement.fxml"));
	   	 Scene scene = new Scene(root);
	   	 Stage st = (Stage) lbmission.getScene().getWindow();
	   	 st.setScene(scene);
	   	 st.close();
		 st.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			 InitialContext	ic = new InitialContext();
				
			AdminserviceEJBRemote proxy = (AdminserviceEJBRemote) ic.lookup("/easyMission-ear/easyMission-ejb"
					+ "/AdminserviceEJB!services.AdminserviceEJBRemote");
		
		List<Mission>  elist = proxy.displayallmissions();
		data=FXCollections.observableArrayList();
		//////m.getIdMission(),m.getTitle(),m.getPrice(),m.getState(),m.getField(),m.getStartDate()/////
		for (Mission m : elist)
		{
			data.add(new Mission(m.getIdMission(),m.getTitle(),m.getPrice(),m.getState(),m.getField(),m.getEmployer(),m.getStartDate(),m.getEndDate(),m.getMissionType(),m.getSkills(),m.getDescription()));
		}
		
			/////
		
		/////
			tablemission.setItems(data);
			id.setCellValueFactory(new PropertyValueFactory<Mission, Integer>("idMission"));
			title.setCellValueFactory(new PropertyValueFactory<Mission, String>("title"));
			price.setCellValueFactory(new PropertyValueFactory<Mission, String>("price"));
			state.setCellValueFactory(new PropertyValueFactory<Mission, String>("state"));
			field.setCellValueFactory(new PropertyValueFactory<Mission, String>("field"));
			employer.setCellValueFactory(new PropertyValueFactory<Mission, String >("employerFullName"));
			startdate.setCellValueFactory(new PropertyValueFactory<Mission, String >("startDate"));
		 enddate.setCellValueFactory(new PropertyValueFactory<Mission, String >("endDate"));
		   type.setCellValueFactory(new PropertyValueFactory<Mission, String >("missionType"));
		   skills.setCellValueFactory(new PropertyValueFactory<Mission, String >("skills"));
		    Description.setCellValueFactory(new PropertyValueFactory<Mission, String >("description"));

			
		
	}
		catch(NamingException e)
		{
			e.printStackTrace();
		}
		setcellvaluefromrabletotextfield ();
}
	private void setcellvaluefromrabletotextfield() {
		
		tablemission.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event event) {
				
				Mission m = tablemission.getItems().get(tablemission.getSelectionModel().getSelectedIndex());
				String skills="";
				tftext.setText(m.getTitle());
				tfdescription.setText(m.getDescription());
				for(Skill s : m.getSkills()){
					skills+="-"+s.getName();
				}
				tfskills.setText(skills);
		
				
			}
		});
		
		
		
		
		
		
	}
}
