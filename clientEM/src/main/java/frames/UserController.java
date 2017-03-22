package frames;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import delegate.UserServiceDelegate;
import entities.Employer;
import entities.Worker;
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
import services.UserServicesEJBRemote;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;


public class UserController implements Initializable{
	UserServiceDelegate delegate= new UserServiceDelegate();


    @FXML
    public static AnchorPane ap;

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
    private Text adress;

    @FXML
    private WebView map;

    @FXML
    private ImageView cpic;
    @FXML
    private Text cnumber;

    @FXML
    private Text cname;
    public static Image image;
    public static Image image1;
    File picture,picture1;

  

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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
		String var=frame1Controller.x;
		System.out.println("type user  "+var);
		Worker w=null;
		Employer e=null;
		if(var.equals("worker")){
			 w=frame1Controller.w;
		}
		if(var.equals("Employer")){
			 e=frame1Controller.e;
		}
		
			//System.out.println("user is employer");
		//	Employer emp=proxy.findEmploerById(frame1Controller.id);
		Employer emp=delegate.doFindEmployerById(frame1Controller.id);
			
			System.out.println(emp.getFirstName());
			fn.setText("First Name : "+emp.getFirstName());
			ln.setText("Last Name : "+emp.getLastName());
			mail.setText("Email address : "+emp.getEmail());
			country.setText("Country : "+emp.getCountry());
			field.setText("Field : "+emp.getField());
			bdate.setText("BirthDate  :"+emp.getBirthDate());
			adress.setText("Address : "+emp.getAdress());
			String url ="https://www.google.tn/maps/place/"+emp.getAdress();
	        map.setZoom(0.83);
	        WebEngine we = map.getEngine();
	        we.load(url);
	        cname.setText("Company Name : "+emp.getCompany());
	        
	        picture=new File(emp.getCompanyLogo());
	        picture1=new File(emp.getPicture());
	        BufferedImage bufferedImage = null;
	        BufferedImage bufferedImage1 = null;
			try {
				bufferedImage = ImageIO.read(picture);
				bufferedImage1 = ImageIO.read(picture1);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 image = SwingFXUtils.toFXImage(bufferedImage, null);
			 image1 = SwingFXUtils.toFXImage(bufferedImage1, null);
			 ppic.setClip(new Circle(50,50,50));
				cpic.setImage(image);
				ppic.setImage(image1);
				cname.setText("Company Name : "+emp.getCompany());
				cnumber.setText("Company Number : "+emp.getCompanyNumber());
			
		
    
		
		}
	  @FXML
	    private void exit(ActionEvent event) {
	    	Stage stage = (Stage) cname.getScene().getWindow();
		    stage.close();
	    }
		
	}
		
	

