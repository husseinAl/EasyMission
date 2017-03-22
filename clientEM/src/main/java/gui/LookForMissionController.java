/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import businessDelegate.ApplicationBusiness;
import delegate.UserServiceDelegate;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import entities.Mission;
import entities.Notification;
import entities.Skill;
import entities.Worker;
import frames.frame1Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableView;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import services.ApplicationEJBRemote;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author hussein
 */
public class LookForMissionController implements Initializable {
	UserServiceDelegate delegate1= new UserServiceDelegate();

	private Image img1 = new Image(getClass().getResourceAsStream("/img/red_close.png"));
	private List<Skill> ls = new ArrayList<>();
	private List<Mission> lm = new ArrayList<Mission>();
	private ObservableList<Mission> data;
	private ObservableList<Skill> datas;
	private String type = "";
	private ApplicationBusiness delegate ;
	private AutoCompleteComboBoxListener ac;
	private Mission m ;
	
	
	private static List<Notification> ln =new ArrayList<>();
	
	 private static TrayNotification tray;
	
	public static void notif(List<Notification> ln)
	{
		for(Notification n :ln)
		{
			tray=new TrayNotification();
			creatingANewTrayNotification(n.getDate().toString(),n.getText());
			
		}
	}

	List<Skill> selectedSkills = new ArrayList<>();
    @FXML
    private JFXHamburger ham;

    @FXML
    private JFXDrawer drawer;
	@FXML
	private TextField title;
	@FXML
	private CheckBox pt;
	@FXML
	private CheckBox ft;
	@FXML
	private Button search;
	@FXML
	private ComboBox<Skill> combo;

	@FXML
	private TilePane skillsPane;

	@FXML
	private Button add;

	

	@FXML
	private TableView<Mission> tab;
	@FXML
	private TableColumn<Mission, String> col1;
	@FXML
	private TableColumn<Mission, String> col2;
	@FXML
	private TableColumn<Mission, String> col3;

	@FXML
	private CheckBox part;

	@FXML
	private CheckBox flex;
	
    @FXML
    private TextField min;

    @FXML
    private TextField max;
    
    @FXML
    private Label affich;
    @FXML
    private Label affichtt;
    @FXML
    private Label affichdesc;
    

    @FXML
    private JFXTextArea Jaffichdesc;

    

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		System.out.println(ln.size());
		
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
		Jaffichdesc.setWrapText(true);
	     form.setVisible(false);
	     Worker emp=delegate1.doFindWorkerById(frame1Controller.id);
	    delegate = new ApplicationBusiness(emp.getIdUser());
	
	
		try {
			lm = delegate.getAllMissions();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fillTab();
		tab.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		// -----------------------------------------------------------

		try {
			ls = (ArrayList<Skill>) delegate.getAllSkills();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fillCombo();

		skillsPane.setPadding(new Insets(4,4,4,4));
		skillsPane.setVgap(4);
		skillsPane.setHgap(4);	
	}
	
	
	@FXML
	void add(ActionEvent event) {
		Skill s = combo.getSelectionModel().getSelectedItem();
		if(s==null)
			return ;
		selectedSkills.add(s);
		ls.remove(s);
		fillCombo();
		Button btn = new Button(s.getName(), new ImageView(img1));
		btn.setPrefSize(80, 20);
		skillsPane.getChildren().add(btn);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (Skill s : selectedSkills) {
					if (s.getName().equals(btn.getText())) {
						ls.add(s);
						selectedSkills.remove(s);
						System.out.println(selectedSkills.size());
						break;
					}
				}
				skillsPane.getChildren().remove(btn);
				fillCombo();
			}
		});
	}

	@FXML
	void checkflex(ActionEvent event) {
		pt.setSelected(false);
		//type = "flextime";
	}

	@FXML
	void checkpart(ActionEvent event) {
		ft.setSelected(false);
		//type = "parttime";
	}

	@FXML
	private void search(ActionEvent event) {

	}

	@FXML
	void comboload(ActionEvent event) {

	}

	@FXML
	void typename(InputMethodEvent event) {

	}

	private void fillCombo() {
		datas = FXCollections.observableArrayList(ls);
		combo.setItems(datas);
		ac = new AutoCompleteComboBoxListener(combo);
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
	
	   @FXML
	    void typenumber(KeyEvent event) {
		   String c = event.getCharacter();
		  // System.out.println(c);
		    if("1234567890".contains(c)) {}
		    else {
		        event.consume();
		    }
	    }
	   
	   @FXML
	    void filter(ActionEvent event) throws NamingException {		  	
		executeFilter();
	    }
	   
	   private void executeFilter() throws NamingException
	   {
		   int x = -1 ;
			  int y=-1;
			   if (min.getText().length()>0)
			    x = Integer.parseInt(min.getText());
			   if (max.getText().length()>0)
			    y = Integer.parseInt(max.getText());
			   lm = delegate.FilterMissions(title.getText(), selectedSkills, getType(), x, y);
			   fillTab();
	   }
	  
	   
	   
	   public String getType()
	   {
		   if (pt.isSelected())
			   return "parttime";
		   else {
			   if (ft.isSelected()) return "flextime";
		   }
		   return "" ;
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
	    private AnchorPane form;
	   @FXML
	    private Button apply;
	   
	   @FXML
	    void tabclick(MouseEvent event) {
		    m =tab.getSelectionModel().getSelectedItem();
		   if(m!=null)
		   {
			   affichtt.setText(m.getTitle());
			   String ch ="";
			   ch+=m.getDescription()+"\n from "+m.getStartDate().toString()+" until "+m.getEndDate().toString()+"\n";
			   ch+= "Price: "+m.getPrice()+"\n";
			   ch+="Required Skills: "+chaineSkills(m.getSkills());
			   affichdesc.setText(ch);
			  // Jaffichdesc.setVisible(true);
			   form.setVisible(true);
			  
		   }
	    }
	   
	   @FXML
	    void apply(ActionEvent event) throws NamingException {
		   String text = Jaffichdesc.getText();
		   Worker w =new Worker();
		   if(delegate.apply(w, m, text))
		   {
			   			  
			   Alert alert = new Alert(AlertType.INFORMATION);
			   alert.setTitle("Success");
			   alert.setHeaderText(null);
			   alert.setContentText("Your Application is successfuly sent");			   
//			   lm=delegate.getAllMissions();
			   executeFilter();
			   form.setVisible(false);
			   fillTab();
			   alert.showAndWait();
			   
		   }
		   else{
			   Alert alert = new Alert(AlertType.ERROR);
			   alert.setTitle("Error");
			   alert.setHeaderText(null);
			   alert.setContentText("There was an error, Your Application has not been delivered");
			   alert.showAndWait();
		   }

	    }

	   public static void creatingANewTrayNotification(String title, String message) {

	        tray.setTitle(title);
	        tray.setMessage(message);
	        tray.setNotificationType(NotificationType.SUCCESS);
	        tray.setAnimationType(AnimationType.POPUP);
	        tray.showAndWait();//wAndDismiss(Duration.seconds(5));
	    }
	   

}
