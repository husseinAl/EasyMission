package frames;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import delegate.UserServiceDelegate;

import java.awt.image.BufferedImage;
import entities.Employer;
import entities.Recommendation;
import entities.Skill;
import entities.Worker;
import entities.Recommendation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import services.UserRecommandationServiceEJBRemote;
import services.UserServicesEJBRemote;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserController2 implements Initializable{
	UserServiceDelegate delegate= new UserServiceDelegate();

	private  static ObservableList<Skill>data;
	private  static ObservableList<Recommendation>data1;
    public static Image image;
   
    File picture;
    @FXML
    public static  AnchorPane ap;

    @FXML
    private ImageView iv;

    @FXML
    private JFXHamburger ham;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Text fn;

    @FXML
    private Text ln;

    @FXML
    private Text mail;

    @FXML
    private Text country;

    @FXML
    private Text field;

    @FXML
    private ImageView ppic;

    @FXML
    private Text bdate;

    @FXML
    private Text bank;

   

    @FXML
    private TableView<Skill> tabskill;

    @FXML
    private TableColumn<Skill,String> skil;

    @FXML
    private Text number;

    @FXML
    private TextArea desc;

    @FXML
    private TableView<Recommendation> rcd;

    @FXML
    private TableColumn<Recommendation,String> txt;

    @FXML
    private TableColumn<Recommendation,String> user;

    @FXML
    private Text number1;
  

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//-------------- server cnx------------
		/*InitialContext ctx = null;
		try {
			ctx = new InitialContext();
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Object objet = null;
		try {
			objet = ctx.lookup("/easyMission-ear/easyMission-ejb/UserServicesEJB!services.UserServicesEJBRemote");
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		UserServicesEJBRemote proxy=(UserServicesEJBRemote)objet;*/
		
		
		
		
		//--------------------------- hamburger menu----------------
		try {
			VBox box =FXMLLoader.load(getClass().getResource("DrawerContent2.fxml"));
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
                drawer.open();});
    }catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		//a-----------------affichage
			//Worker emp=proxy.findWorkerById(frame1Controller.id);
			Worker emp=delegate.doFindWorkerById(frame1Controller.id);
			System.out.println(emp.getFirstName());
			fn.setText("First Name : "+emp.getFirstName());
			ln.setText("Last Name : "+emp.getLastName());
			mail.setText("Email address : "+emp.getEmail());
			country.setText("Country : "+emp.getCountry());
			field.setText("Field : "+emp.getField());
			bdate.setText("BirthDate  :"+emp.getBirthDate());
			bank.setText("Bank Account : "+emp.getRib());
	        number.setText("Phone Number : "+emp.getPhoneNumber());
	        picture=new File(emp.getPicture());
	       
	        BufferedImage bufferedImage = null;
	      
			try {
				bufferedImage = ImageIO.read(picture);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 image = SwingFXUtils.toFXImage(bufferedImage, null);
			
				ppic.setImage(image);
				desc.setText(emp.getDescription());
				//----------------list skills
				int userid=emp.getIdUser();
			//	List<Skill> sklist=proxy.findAllSkills();
				List<Skill> sklist=delegate.dofindAllSkills();
				//List<Worker>lw=proxy.findAllWorkers();
				List<Worker>lw=delegate.doFindAllWorker();
				List<Skill>lskl=new ArrayList<>();
				List<Worker>lworkers=null;
				data=FXCollections.observableArrayList();
				for(Skill s : sklist){
					lworkers=s.getWorkers();
					for(Worker w : lworkers){
						if(w.getIdUser()==userid){
							lskl.add(s);}}}
				
				for( Skill s : lskl){
					System.out.println(s.getName());
					data.add(new Skill(s.getName()));
				}
				tabskill.setItems(data);
				skil.setCellValueFactory(new PropertyValueFactory<Skill,String>("name"));
				//-------------------------- list recommandation-----------
				InitialContext ctx2 = null;
				try {
					ctx2 = new InitialContext();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Object objet2 = null;
				try {
					objet2 = ctx2.lookup("/easyMission-ear/easyMission-ejb/UserRecommandationServiceEJB!services.UserRecommandationServiceEJBRemote");
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UserRecommandationServiceEJBRemote proxy2=(UserRecommandationServiceEJBRemote)objet2;
				
				List<Recommendation>lrr=new ArrayList<>();
				List<Recommendation>lr=proxy2.findAllRecommandation();
				for(Recommendation r : lr){
					if(r.getRecommended().getIdUser()==1){
						//System.out.println("test");
						lrr.add(r);
					}}
				data1=FXCollections.observableArrayList();
				
				
				for(Recommendation r: lrr){
					System.out.println(r.getText()+" "+r.getRecommender().getFirstName());
					data1.add(new Recommendation(r.getText(),r.getRecommender()));
					
				}
				rcd.setItems(data1);
				txt.setCellValueFactory(new PropertyValueFactory<Recommendation,String>("text"));
				user.setCellValueFactory(new PropertyValueFactory<Recommendation,String>("RecommanderName"));
		}
    
		
		
	  @FXML
	    private void exit(ActionEvent event) {
	    	Stage stage = (Stage) mail.getScene().getWindow();
		    stage.close();
	    }
		
	}
		
	

