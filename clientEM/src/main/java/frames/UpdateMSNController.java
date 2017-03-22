package frames;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import entities.Mission;
import entities.Skill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import services.MissionServiceEJBRemote;

public class UpdateMSNController implements Initializable {
	public static ArrayList<Skill> skill=new ArrayList<>();
	private static  String sirine;
	public	static ObservableList<Skill> data;

    @FXML
    private AnchorPane ap;

    @FXML
    private ImageView iv1;

    

    

    @FXML
    private JFXCheckBox flexbox1;

    @FXML
    private JFXCheckBox partbox1;

    @FXML
    private JFXTextField titletf1;

    @FXML
    private JFXComboBox<?> fieldcb1;

    @FXML
    private JFXDatePicker startdate1;

    @FXML
    private JFXDatePicker enddate1;

    @FXML
    private JFXTextField pricetf1;

    @FXML
    private JFXComboBox<Skill> skillscb1;

    @FXML
    private Label skills;

    @FXML
    private JFXButton sk1;

    @FXML
    private JFXTextArea descriptiontf1;

    @FXML
    private JFXCheckBox localbox1;

    @FXML
    private JFXButton updateBTN1;
    @FXML
    private JFXHamburger ham;

    @FXML
    private JFXDrawer drawer;
    
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
    	
    	//Fill combobox
		InitialContext ctx1 = null;
		try {
			ctx1 = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object objet = null;
		try {
			objet = ctx1.lookup("/easyMission-ear/easyMission-ejb/MissionServiceEJB!services.MissionServiceEJBRemote");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		MissionServiceEJBRemote proxy1=(MissionServiceEJBRemote)objet;
		
		
		//List<Skill> lisSkills=proxy.findAllSkills();
		ArrayList<Skill>skill=(ArrayList<Skill>)proxy1.findAllSkills();
		
		data =FXCollections.observableArrayList(skill);
		skillscb1.getItems().addAll(data);
    	
    	
    	 InitialContext ctx = null;
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object object=null;
			try {
				object=ctx.lookup("/easyMission-ear/easyMission-ejb/MissionServiceEJB!services."
						+ "MissionServiceEJBRemote");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MissionServiceEJBRemote proxy = (MissionServiceEJBRemote)object;
			Mission m=proxy.findMissionByName(ManageMissionController.namemission);
			System.out.println(ManageMissionController.namemission);
			titletf1.setPromptText(m.getTitle());
			fieldcb1.setPromptText(m.getField());
			startdate1.setPromptText(m.getStartDate().toString());
			enddate1.setPromptText(m.getEndDate().toString());
			pricetf1.setPromptText(m.getPrice()+"");
			skillscb1.setPromptText(m.getSkills().toString());
			descriptiontf1.setPromptText(m.getDescription());
			localbox1.setCache(m.getLocal());
			
		
	}

    @FXML
    void UPDATEMISSION(ActionEvent event) {
    	 InitialContext ctx = null;
 		try {
 			ctx = new InitialContext();
 		} catch (NamingException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 		Object object=null;
 			try {
 				object=ctx.lookup("/easyMission-ear/easyMission-ejb/MissionServiceEJB!services."
 						+ "MissionServiceEJBRemote");
 			} catch (NamingException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 			MissionServiceEJBRemote proxy = (MissionServiceEJBRemote)object;
 			Mission m=proxy.findMissionByName(ManageMissionController.namemission);
 			m.setTitle(titletf1.getText());
 			m.setDescription(descriptiontf1.getText());
 			//m.setField(fieldcb1.getValue().toString());
 			
 			//date
 			LocalDate localDateStart1 =startdate1.getValue();
 			Instant instant2 = Instant.from(localDateStart1.atStartOfDay(ZoneId.systemDefault()));
 			java.util.Date datestartdate1 = Date.from(instant2);
 		    m.setStartDate(datestartdate1);
 		    
 		   LocalDate localDateEnd1 =enddate1.getValue();
 			Instant instant1 = Instant.from(localDateEnd1.atStartOfDay(ZoneId.systemDefault()));
 			java.util.Date dateenddate1 = Date.from(instant1);
 		    m.setEndDate(dateenddate1);
 		   float f = Float.parseFloat(pricetf1.getText());
 		   //BOX
 		  if(localbox1.isSelected())
 			{m.setLocal(true);}
 			else  {m.setLocal(false);}
 		 if(partbox1.isSelected())
 		{ m.setMissionType("parttime");}
 		if(flexbox1.isSelected())
 		{ m.setMissionType("flextime");}
 		
 		m.setState("on hold");
 		proxy.UpdateMission(m);
 			
 			
 			
    	

    }

    @FXML
    void skills(ActionEvent event) {

    }

	

}
