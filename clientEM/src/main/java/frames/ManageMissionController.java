package frames;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import delegate.UserServiceDelegate;
import entities.Employer;
import entities.Mission;
import entities.Skill;
import gui.treatApplicationsController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.MissionServiceEJBRemote;


public class ManageMissionController implements Initializable {
	UserServiceDelegate delegate= new UserServiceDelegate();
	public static String namemission="";
	@FXML
    private JFXDatePicker date;
    @FXML
    private JFXButton jfx;
    @FXML
    private JFXHamburger ham;

    @FXML
    private JFXDrawer drawer;
	@FXML
    private TableView<Mission> tablemission;

    @FXML
    private TableColumn<Mission, String> titleC;

    @FXML
    private TableColumn<Mission, String> descriptionC;

    @FXML
    private TableColumn<Mission, String> fieldC;

    @FXML
    private TableColumn<Mission, String> startC;

    @FXML
    private TableColumn<Mission, String> endC;

    @FXML
    private TableColumn<Mission, String> missiontypeC;

    @FXML
    private TableColumn<Mission, String> stateC;

    @FXML
    private TableColumn<Mission, String> localC;

    @FXML
    private TableColumn<Mission, String> priceC;

    @FXML
    private TableColumn<Mission, String> skillsC;
    @FXML
    private JFXButton btassign;

	    @FXML
	    private Button Updatemission;
	    
	    ObservableList<Mission>data;
	    ObservableList<Mission>data1;
	    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btassign.setDisable(true);
		
		
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
		try{
		 InitialContext ctx = new InitialContext();
			MissionServiceEJBRemote proxy = (MissionServiceEJBRemote)ctx.lookup("/easyMission-ear/easyMission-ejb/MissionServiceEJB!services."
					+ "MissionServiceEJBRemote");
			
			List<Mission> listmissions =  proxy.findMission(frame1Controller.id);
			
			data1=FXCollections.observableArrayList(listmissions);
			
//			Employer emp=delegate.doFindEmployerById(frame1Controller.id);
//			for (Mission m : listmissions)
//			{
//				if(m.getEmployer().getIdUser()==emp.getIdUser()){
//				
//				Convert Float to String
//				float fvar = m.getPrice();
//				String str = String.valueOf(fvar);
//				data.add(new Mission(str));
//				
//				data1.add(new Mission(m.getTitle(),m.getDescription(),m.getField(),m.getPrice(),
//						m.getState(),m.getStartDate(),m.getEndDate(),m.getMissionType(),m.getLocal(),m.getSkills()));
				
				/*data.add(new Mission(m.getDescription()));
				data.add(new Mission(m.getField()));
				data.add(new Mission(m.getStartDate().toString()));
				data.add(new Mission(m.getEndDate().toString()));
				data.add(new Mission(m.getMissionType()));
				data.add(new Mission(m.getState()));
				data.add(new Mission(m.getLocal().toString()));
				//Convert String to Float
				/*float f = Float.parseFloat("25");
				String s = Float.toString(25.0f);*/
				//List<Skill>skl=new ArrayList<>();
//				String ch ="";
//				for(Skill s :m.getSkills())
//				{
//					ch+=" "+s.getName();
//					//skl.add(s);
//				}
//				}
				//String x= skl.toString();
				//data1.add(new Mission(ch));
//			}
			
			titleC.setCellValueFactory( new PropertyValueFactory<Mission,String> ("title"));
			descriptionC.setCellValueFactory( new PropertyValueFactory<Mission,String> ("description"));
			fieldC.setCellValueFactory( new PropertyValueFactory<Mission,String> ("field"));
			startC.setCellValueFactory( new PropertyValueFactory<Mission,String> ("startDate"));
			endC.setCellValueFactory( new PropertyValueFactory<Mission,String> ("endDate"));
			missiontypeC.setCellValueFactory( new PropertyValueFactory<Mission,String> ("missionType"));
			stateC.setCellValueFactory( new PropertyValueFactory<Mission,String> ("state"));
			localC.setCellValueFactory( new PropertyValueFactory<Mission,String> ("local"));
			priceC.setCellValueFactory( new PropertyValueFactory<Mission,String> ("price"));
			skillsC.setCellValueFactory( new PropertyValueFactory<Mission,String>("skills"));
			tablemission.setItems(data1);
			
		
		}
catch(Exception e)
{
	
}
	}

	

	    @FXML
	    void UpdateMission(ActionEvent event) throws NamingException, IOException {
	    	InitialContext ctx = new InitialContext();
			MissionServiceEJBRemote proxy = (MissionServiceEJBRemote)ctx.lookup("/easyMission-ear/easyMission-ejb/MissionServiceEJB!services."
					+ "MissionServiceEJBRemote");
	    	
	    	//Mission m= tablemission.getItems().get(tablemission.getSelectionModel().getSelectedIndex());
			 Mission m = tablemission.getSelectionModel().getSelectedItem();
	    	namemission=m.getTitle();
	    	System.out.println(m.getTitle());
	    	 Parent root=FXMLLoader.load(getClass().getResource("UpdateMSN.fxml"));
		        Scene scene=new Scene(root);
		        Stage Sc=new Stage();
		        Sc.getIcons().add(new Image("img/ee.png"));
		        Sc.setScene(scene);
		        Sc.show();
		        final Node source =(Node) event.getSource();
		        final Stage stage=(Stage) source.getScene().getWindow();
		        stage.close();
		        
	 }
	    @FXML
	    void assign(ActionEvent event) throws IOException {
	    	Mission m = tablemission.getSelectionModel().getSelectedItem();
	    	treatApplicationsController.id=m.getIdMission();
	    	Parent root=FXMLLoader.load(getClass().getResource("../gui/treatApplications.fxml"));
	        Scene scene=new Scene(root);
	        Stage Sc=new Stage();
	        Sc.getIcons().add(new Image("img/ee.png"));
	        Sc.setScene(scene);
	        Sc.show();
	        final Node source =(Node) event.getSource();
	        final Stage stage=(Stage) source.getScene().getWindow();
	        stage.close();

	    }
	    @FXML
	    void tabclick(MouseEvent event) {
	    	 Mission m = tablemission.getSelectionModel().getSelectedItem();
	    	 if(m!=null){
	    		 System.out.println(m.getState()+" ok"+m.getApplications().size());
	    	 if((m!=null) &&( m.getState().equals("onhold"))&& (m.getApplications().size()>0))
	    		 btassign.setDisable(false);
	    		 else
	    			 btassign.setDisable(true); 
	    	 
	    	 }
	    	 else btassign.setDisable(true); 
	    }

		
}
