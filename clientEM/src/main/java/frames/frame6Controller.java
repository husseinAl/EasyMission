package frames;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import delegate.UserServiceDelegate;
import entities.Employer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.UserServicesEJBRemote;

public class frame6Controller implements Initializable{
	UserServiceDelegate delegate= new UserServiceDelegate();
	public static Image image,image1;
	File picture,picture1;
	@FXML
	private TextField c;
	@FXML
	private TextField cn;
	@FXML
	private TextField ad;
	@FXML
	private Button pick;
	@FXML
	private Button pick1;
	@FXML
	private Button fill;
	@FXML
	private ImageView img;
	@FXML
	private ImageView img1;

	// Event Listener on Button[#pick].onAction
	@FXML
	public void handleButton2Action(ActionEvent event) {
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
			img.setImage(image);
                        
                        
		} catch (IOException ex) {
		}
                System.out.println(picture);
	}
	@FXML
	public void handleButton3Action(ActionEvent event) {
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
			img1.setImage(image1);
                        
                        
		} catch (IOException ex) {
		}
                System.out.println(picture1);
	}
	// Event Listener on Button[#fill].onAction
	@FXML
	public void handleButtonAction(ActionEvent event) throws NamingException, IOException {
		/*InitialContext ctx=new InitialContext();
		Object objet=ctx.lookup("/easyMission-ear/easyMission-ejb/UserServicesEJB!services.UserServicesEJBRemote");
		UserServicesEJBRemote proxy=(UserServicesEJBRemote)objet;*/
		Employer y=null;
		y=(Employer) delegate.doFindUserByName(frameW3Controller2.nn);
		//Employer y=(Employer) proxy.findUserByName(frameW3Controller2.nn);
		System.out.println("test"+frameW3Controller2.nn);
		//Employer x=(Employer) proxy.findUserById(y.getIdUser());
		Employer x=(Employer) delegate.doFindUserById(y.getIdUser());
		System.out.println("that work "+x.getFirstName());
		if((ad.getText().equals(""))||(c.getText().equals(""))||(cn.getText().equals(""))||(picture.toString().equals(""))
				||(picture1.toString().equals("")))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Warning ");
			alert.setHeaderText(null);
			alert.setContentText(" fill All the Information");

			alert.showAndWait();	
		}else{
		x.setAdress(ad.getText());
		x.setCompany(c.getText());
		x.setCompanyNumber(cn.getText());
		x.setCompanyLogo(picture.toString());
		x.setPicture(picture1.toString());
		try {
		//	proxy.updateEmployer(x);
			delegate.doUpdateEmployer(x);
			
		} catch (Exception E) {
			Alert alert22 = new Alert(Alert.AlertType.INFORMATION);
			alert22.setTitle("warning ");
			alert22.setHeaderText(null);
			alert22.setContentText("Please Fill all the fields" );

			alert22.showAndWait();
		}
		
		}
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Welcome ");
		alert.setHeaderText(null);
		alert.setContentText(" your Profile is now ready");

		alert.showAndWait();
		Stage stage = (Stage) c.getScene().getWindow();
	    stage.close();
	    
	    Parent root = FXMLLoader.load(getClass().getResource("frame1.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
