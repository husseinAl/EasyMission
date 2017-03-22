package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import businessDelegate.WorkerMissionsBusiness;
import delegate.UserServiceDelegate;
import entities.Mission;
import entities.Skill;
import entities.Worker;
import frames.frame1Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class workerMissionsController implements Initializable {
	UserServiceDelegate delegate1= new UserServiceDelegate();

	private List<Mission> lm = new ArrayList<Mission>();
	private Mission m = new Mission();
	private ObservableList<Mission> data;
	private WorkerMissionsBusiness delegate ;
	
	
	
	@FXML
	private AnchorPane form;

	@FXML
	private Label affich;

	@FXML
	private Label affichtt;

	@FXML
	private Label affichdesc;

	@FXML
	private TableView<Mission> tab;
	@FXML
	private TableColumn<Mission, String> col1;
	@FXML
	private TableColumn<Mission, String> col2;
	@FXML
	private TableColumn<Mission, String> col3;

	@FXML
	private JFXButton applications;
	  @FXML
	    private JFXHamburger ham;

	    @FXML
	    private JFXDrawer drawer;

	@FXML
	void applications(ActionEvent event) {
		form.setVisible(false);
		lm=delegate.getApplications();		
		 assignments.setStyle("-fx-background-color:transparent ");
		 history.setStyle("-fx-background-color: transparent");
		 suggestions.setStyle("-fx-background-color: transparent");
		 applications.setStyle("-fx-background-color: #999999");
		 
		 fillTab();
	}

	@FXML
	private JFXButton assignments;
	@FXML
	void assignments(ActionEvent event) {
		form.setVisible(false);
		lm=delegate.getAssignments();		
		 assignments.setStyle("-fx-background-color: #999999");
		 history.setStyle("-fx-background-color: transparent");
		 suggestions.setStyle("-fx-background-color: transparent");
		 applications.setStyle("-fx-background-color: transparent");
	
		  fillTab();
	}
	
	@FXML
	private JFXButton history;
	@FXML
	void history(ActionEvent event) {
		form.setVisible(false);
		lm=delegate.getHistory();		
		 history.setStyle("-fx-background-color: #999999");
		 suggestions.setStyle("-fx-background-color: transparent");
		 applications.setStyle("-fx-background-color: transparent");
		 assignments.setStyle("-fx-background-color: transparent");
		  fillTab();

	}
	
	@FXML
	private JFXButton suggestions;
	@FXML
	void suggestions(ActionEvent event) {
		form.setVisible(false);
		/// sliiiim 
		System.out.println("sliiiiiiiiiim");
		lm=new ArrayList<>();
		 suggestions.setStyle("-fx-background-color: #999999");
		 applications.setStyle("-fx-background-color: transparent");
		 history.setStyle("-fx-background-color: transparent");
		 assignments.setStyle("-fx-background-color: transparent");
		  fillTab();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		

		try {
			VBox box =FXMLLoader.load(getClass().getResource("../frames/DrawerContent2.fxml"));
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
		  System.out.println(frame1Controller.id);
		 // Worker emp=delegate1.doFindWorkerById(frame1Controller.id);
		  System.out.println(frame1Controller.id);
		  delegate=new WorkerMissionsBusiness(frame1Controller.id);
		  lm=delegate.getAssignments();
		  assignments.setStyle("-fx-background-color: #999999");
			tab.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		  fillTab();

	}

	@FXML
	void tabclick(MouseEvent event) {

		   m =tab.getSelectionModel().getSelectedItem();
		   if(m!=null)
		   {
			   affichtt.setText(m.getTitle());
			   affichdesc.setText(m.getDescription());
			   form.setVisible(true);
			  
		   }
	}
	

	private void fillTab() {
		data = FXCollections.observableArrayList(lm);

		col1.setCellValueFactory((TableColumn.CellDataFeatures<Mission, String> tt) -> new SimpleStringProperty(
				tt.getValue().getTitle()));
		col2.setCellValueFactory((TableColumn.CellDataFeatures<Mission, String> desc) -> new SimpleStringProperty(
				desc.getValue().getDescription()));
		
		col3.setCellValueFactory((TableColumn.CellDataFeatures<Mission, String> sk) -> new SimpleStringProperty(
				chaineSkills(sk.getValue().getSkills())));

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

}
