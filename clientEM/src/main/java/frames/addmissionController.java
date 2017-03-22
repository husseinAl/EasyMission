package frames;


import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.MissionServiceEJBRemote;

public class addmissionController implements Initializable{
	
	public static ArrayList<Skill> skl=new ArrayList<>();
	private static  String sirine;
	@FXML
	private CheckBox partbox;
	@FXML
	private CheckBox flexbox;
	@FXML
	private TextField titletf;
	@FXML
	private ComboBox<String> fieldcb;
	@FXML
	private DatePicker startdate;
	@FXML
	private DatePicker enddate;
	@FXML
	private TextField pricetf;
	@FXML
	private ComboBox<Skill> skillscb;
	@FXML
	private TextField descriptiontf;
	@FXML
	private CheckBox localbox;
	@FXML
	private Button AddBTN;
	@FXML
	private Button sk;
	@FXML
	private Label skills;
private	static ObservableList<Skill> data;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		fieldcb.getItems().addAll("IT","Finance","Marketing");
		
		//Fill combobox
		InitialContext ctx = null;
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object objet = null;
		try {
			objet = ctx.lookup("/easyMission-ear/easyMission-ejb/MissionServiceEJB!services.MissionServiceEJBRemote");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		MissionServiceEJBRemote proxy=(MissionServiceEJBRemote)objet;
		
		
		//List<Skill> lisSkills=proxy.findAllSkills();
		ArrayList<Skill>skill=(ArrayList<Skill>)proxy.findAllSkills();
		
		data =FXCollections.observableArrayList(skill);
		skillscb.getItems().addAll(data);
		
		
	}
	
	//ACTION boutoon
	@FXML
    void skills(ActionEvent event) {
		skl.add(skillscb.getValue());//hethi fibeli matjich skl list de skills w skills = combo irajaa string
		sirine = " - "+skillscb.getValue();
		skills.setText(sirine);
    }
    @FXML
    private void ADDMISSION(ActionEvent event) throws NamingException, ParseException, IOException {
    	
    	
    	InitialContext ctx = new InitialContext();
		Object object =ctx.lookup("/easyMission-ear/easyMission-ejb/MissionServiceEJB!services.MissionServiceEJBRemote");
		MissionServiceEJBRemote proxy=(MissionServiceEJBRemote)object;
		Mission m= new Mission();
		
		
		if(partbox.isSelected())
		{ m.setMissionType("parttime");}
		if(flexbox.isSelected())
		{ m.setMissionType("flextime");}
		m.setField(fieldcb.getValue());
		//with,without Local
		if(localbox.isSelected())
		{m.setLocal(true);}
		else  {m.setLocal(false);}
		//Price
		float f = Float.parseFloat(pricetf.getText());
		m.setPrice(f);
		//Skills required
		//m.setSkills(skillscb.getValue());
		//List<Skill> skilllist=new ArrayList<>();
		m.setSkills(skl);
		
		//description 
		m.setDescription(descriptiontf.getText());
		//END+START dates
		
		//Convert Local Date to Date End
	    LocalDate localDateStart =startdate.getValue();
		Instant instant2 = Instant.from(localDateStart.atStartOfDay(ZoneId.systemDefault()));
		java.util.Date datestartdate = Date.from(instant2);
	    m.setStartDate(datestartdate);
	    
	    LocalDate localDateEnd =enddate.getValue();
		Instant instant1 = Instant.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()));
		java.util.Date dateenddate = Date.from(instant1);
	    m.setEndDate(dateenddate);
		//Title
		m.setTitle(titletf.getText());
		//default state=on hold
		m.setState("onhold");
		proxy.AddMission(m);
		
		  Parent root=FXMLLoader.load(getClass().getResource("ManageMission.fxml"));
	        Scene scene=new Scene(root);
	        Stage Sc=new Stage();
	        Sc.setScene(scene);
	        Sc.show();
	        final Node source =(Node) event.getSource();
	        final Stage stage=(Stage) source.getScene().getWindow();
	        stage.close();
	        
	
    	

    }





	


}
