package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import businessDelegate.AssignmentDelegate;
import businessDelegate.WorkerMissionsBusiness;
import delegate.UserServiceDelegate;
import entities.Application;
import entities.Mission;
import entities.Skill;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class treatApplicationsController implements Initializable {
	UserServiceDelegate delegate1= new UserServiceDelegate();
	private List<Application> la = new ArrayList<Application>();
	private Application app;
	private ObservableList<Application> data;
	private AssignmentDelegate delegate ;
	public static int id ;
	public void setId(int id)
	{
		this.id=id;
	}

	
	
	
	@FXML
	private AnchorPane form;

	@FXML
	private Label affich;

	@FXML
	private Label affichtt;

	@FXML
	private Label affichdesc;
	
	  @FXML
	    private Label affichskills;
	    @FXML
	    private JFXHamburger ham;

	    @FXML
	    private JFXDrawer drawer;


	@FXML
	private TableView<Application> tab;
	@FXML
	private TableColumn<Application, String> col1;
	@FXML
	private TableColumn<Application, String> col2;
	@FXML
	private TableColumn<Application, String> col3;

	  @FXML
	  private Button assign;

	

	
	

	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		

		try {
			VBox box =FXMLLoader.load(getClass().getResource("../frames/DrawerContent.fxml"));
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
		
		
		affichtt.setWrapText(true);
		affichdesc.setWrapText(true);
		  form.setVisible(false);
		  la=new ArrayList<>();
		  delegate=new AssignmentDelegate(id);				 
		tab.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		  fillTab();

	}

	@FXML
	void tabclick(MouseEvent event) {

		   app =tab.getSelectionModel().getSelectedItem();
		   if(app!=null)
		   {
			   affichtt.setText(app.getWorker().getFirstName()+" "+app.getWorker().getLastName());
			   affichdesc.setText(app.getText());
			   affichskills.setText(chaineSkills(app.getWorker().getSkills()));
			   form.setVisible(true);
			  
		   }
	}
	

	private void fillTab() {
		  la=delegate.getApplications();
		data = FXCollections.observableArrayList(la);

		col1.setCellValueFactory((TableColumn.CellDataFeatures<Application, String> tt) -> new SimpleStringProperty(
				tt.getValue().getNom()));
		col2.setCellValueFactory((TableColumn.CellDataFeatures<Application, String> desc) -> new SimpleStringProperty(
				desc.getValue().getText()));
		
		col3.setCellValueFactory((TableColumn.CellDataFeatures<Application, String> sk) -> new SimpleStringProperty(
				sk.getValue().getDate().toString()));

		tab.setItems(data);
	}
	  public String chaineSkills(List<Skill> ls)
	   {
		   String ch ="";
		   if(ls.size()==0) return ch ;
			for(Skill s :ls)
			{
				ch+=""+s.getName()+",";
			}
			ch=ch.substring(0, ch.length()-1);
			return ch;
	   }
	  
	   @FXML
	    void assign(ActionEvent event) throws NamingException {

if(delegate.assign(app))
{
	   Alert alert = new Alert(AlertType.INFORMATION);
	   alert.setTitle("Success");
	   alert.setHeaderText(null);
	   alert.setContentText("Successful Assignments");			   
	  // la=delegate.getApplications();
	   delegate.loadMission(id);
	   form.setVisible(false);
	   fillTab();
	   alert.showAndWait();
	   
}
else{
	   Alert alert = new Alert(AlertType.ERROR);
	   alert.setTitle("Error");
	   alert.setHeaderText(null);
	   alert.setContentText("There was an error, your mission still not assigned");
	   alert.showAndWait();
} 
	   
	   }


}
