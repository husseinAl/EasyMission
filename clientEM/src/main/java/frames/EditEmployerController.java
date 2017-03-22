package frames;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import entities.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.UserServicesEJBRemote;

public class EditEmployerController implements Initializable{
	@FXML
    private AnchorPane ap;

    @FXML
    private ImageView iv;

    @FXML
    private JFXHamburger ham;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private ImageView ppic;

    @FXML
    private WebView map;

    @FXML
    private ImageView cpic;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField email;

    @FXML
    private ComboBox field;

    @FXML
    private DatePicker bdate;

    @FXML
    private TextField address;

    @FXML
    private Button btpic;

    @FXML
    private Button btlogo;

    @FXML
    private Button update;

    @FXML
    private TextField cname;

    @FXML
    private TextField cnumber;
    public static Image image,image1;
    File picture,picture1;



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		field.getItems().addAll(
	            "Mecanique",
	            "Informatique",
	            "Design",
	            "ART",
	            "Other");
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
		//-------------- server cnx------------
				InitialContext ctx = null;
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
				UserServicesEJBRemote proxy=(UserServicesEJBRemote)objet;
				//-------affichage-------
				Employer emp=proxy.findEmploerById(frame1Controller.id);
				firstname.setPromptText(emp.getFirstName());
				lastname.setPromptText(emp.getLastName());
				email.setPromptText(emp.getEmail());
//				Date d=new Date(emp.getBirthDate());
//				DatePicker d1=new DatePicker(emp.getBirthDate());
				bdate.setPromptText(emp.getBirthDate().toString());
				address.setPromptText(emp.getAdress());
				cname.setPromptText(emp.getCompany());
				cnumber.setPromptText(emp.getCompanyNumber());
				field.setPromptText(emp.getField());
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
					cpic.setImage(image);
					ppic.setImage(image1);

				 String url ="https://www.google.tn/maps/place/"+emp.getAdress();
			        map.setZoom(0.83);
			        WebEngine we = map.getEngine();
			        we.load(url);
	}
    @FXML
    void exit(ActionEvent event) {
    	Stage stage = (Stage) ap.getScene().getWindow();
	    stage.close();
    }
    @FXML
    void changeLogo(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		picture = fileChooser.showOpenDialog(null);
		if (picture == null) {
			return;
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(picture);
		 image = SwingFXUtils.toFXImage(bufferedImage, null);
			cpic.setImage(image);
                        
                        
		} catch (IOException ex) {
		}
                System.out.println(picture);

    }

    @FXML
    void changePic(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		picture1 = fileChooser.showOpenDialog(null);
		if (picture1 == null) {
			return;
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(picture1);
		 image1 = SwingFXUtils.toFXImage(bufferedImage, null);
			ppic.setImage(image1);
                        
                        
		} catch (IOException ex) {
		}
                System.out.println(picture);
    }

    @FXML
    void update(ActionEvent event) throws IOException {
    	//-------------- server cnx------------
		InitialContext ctx = null;
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
		UserServicesEJBRemote proxy=(UserServicesEJBRemote)objet;
		//-------update-------
		Employer emp=proxy.findEmploerById(frame1Controller.id);
		//String fname=null;
		
		if(firstname.getText().equals("")){
			String fn=emp.getFirstName();
			emp.setFirstName(fn);
		}else{
			emp.setFirstName(firstname.getText());	
		}
		
		if(lastname.getText().equals("")){
			String ln=emp.getLastName();
			emp.setLastName(ln);
		}else{
			emp.setLastName(lastname.getText());
		}
		
		if(email.getText().equals("")){
			String em=emp.getEmail();
			emp.setEmail(em);
		}else{
			emp.setEmail(email.getText());
		}
		
		if(field.getValue()==null){
			String f=emp.getField();
			emp.setField(f);
		}else{
			emp.setField(field.getValue().toString());
		}
		
		if(bdate.getValue()==null){
			Date BD=emp.getBirthDate();
			emp.setBirthDate(BD);
		}else{
			 LocalDate localDateStart =bdate.getValue();
			Instant instant = Instant.from(localDateStart.atStartOfDay(ZoneId.systemDefault()));
			java.util.Date d = Date.from(instant);
			emp.setBirthDate(d);
		}
		
		if(address.getText().equals("")){
			String ad=emp.getAdress();
			emp.setAdress(ad);
		}else{
			emp.setAdress(address.getText());
		}
		
		if(cname.getText().equals("")){
			String ad=emp.getCompany();
			emp.setCompany(ad);
		}else{
			emp.setCompany(cname.getText());
		}
		
		if(cnumber.getText().equals("")){
			String ad=emp.getCompanyNumber();
			emp.setCompanyNumber(ad);
		}else{
			emp.setCompanyNumber(cnumber.getText());
		}
		
		if(picture.toString().equals("")){
			String ad=emp.getCompanyLogo();
			emp.setCompanyLogo(ad);
		}else{
			emp.setCompanyLogo(picture.toString());
		}
		
		if(picture1.toString().equals("")){
			String ad=emp.getPicture();
			emp.setPicture(ad);
		}else{
			emp.setPicture(picture1.toString());
		}
		//System.out.println("1"+field.getValue().toString());
		//System.out.println("2"+bdate.getValue().toString());
		proxy.updateEmployer(emp);
    	Stage stage = (Stage) cname.getScene().getWindow();
	    stage.close();
	    Parent root = FXMLLoader.load(getClass().getResource("User.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }

}
