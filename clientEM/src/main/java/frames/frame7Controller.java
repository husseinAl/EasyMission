package frames;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl.Work;

import delegate.UserServiceDelegate;
import entities.Employer;
import entities.Skill;
import entities.Worker;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;

import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.UserServicesEJBRemote;
import javafx.scene.control.TextArea;

public class frame7Controller implements Initializable{
	
	UserServiceDelegate delegate= new UserServiceDelegate();
	public static ArrayList<Skill>skill=new ArrayList<>();
	public static Image image,image2;
	private  static ObservableList<Skill>data;
	File picture,picture2;
	@FXML
	private TextField pn;
	@FXML
	private TextField rib;
	@FXML
	private Button cv;
	@FXML
	private Button adsk;
	@FXML
	private Button fill;
	@FXML
	private Button pic;
	@FXML
	private TextArea d;
	@FXML
	private ImageView img;
	@FXML
    private WebView web;
	@FXML
    private TableView<Skill> table;
	@FXML
    private ComboBox<Skill>sk;
    @FXML
    private TableColumn<Skill, String> skillname;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
//		String url ="file:///C:/Users/louka/Downloads/S%C3%A9rie%201%20PL%20(1).pdf";
//        web.setZoom(0.83);
//        WebEngine we = web.getEngine();
//        we.load(url);
		
		
		
		
		
		InitialContext ctx = null;
		try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object objet = null;
		try {
			objet = ctx.lookup("/easyMission-ear/easyMission-ejb/UserServicesEJB!services.UserServicesEJBRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserServicesEJBRemote proxy=(UserServicesEJBRemote)objet;
		//List<Skill> lisSkills=proxy.findAllSkills();
		ArrayList<Skill>skill=(ArrayList<Skill>)proxy.findAllSkills();
		
		data =FXCollections.observableArrayList(skill);
		sk.getItems().addAll(data);
		
		
		
		
		
		
		
		
		
	}
	@FXML
    private void handleButtonAction(ActionEvent event) throws IOException, NamingException {
		//------cnx serveur---------
		/*InitialContext ctx=new InitialContext();
		Object objet=ctx.lookup("/easyMission-ear/easyMission-ejb/UserServicesEJB!services.UserServicesEJBRemote");
		UserServicesEJBRemote proxy=(UserServicesEJBRemote)objet;*/
		
		//Worker y=(Worker) proxy.findUserByName(frameE4Controller.nn);
		Worker y=(Worker) delegate.doFindUserByName(frameE4Controller.nn);
		System.out.println("test"+frameW3Controller2.nn);
		
		//Worker x=(Worker) proxy.findUserById(y.getIdUser());
		Worker x=(Worker)delegate.doFindUserById(y.getIdUser());
		//System.out.println("that work "+x.getFirstName());
		//----------------------------
		if((pn.getText().equals(""))||(rib.getText().equals(""))||(d.getText().equals(""))||(picture.toString().equals(""))
				||(picture2.toString().equals(""))){
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Warning ");
			alert.setHeaderText(null);
			alert.setContentText(" fill All the Information");

			alert.showAndWait();	
		}else{
		x.setPhoneNumber(pn.getText());
		x.setRib(rib.getText());
		x.setDescription(d.getText());
		x.setCv(picture2.toString());
		x.setPicture(picture.toString());
		x.setSkills(skill);
		
		try {
			//proxy.updateWorker(x);
			delegate.doUpdateWorker(x);
			
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
		Stage stage = (Stage) d.getScene().getWindow();
	    stage.close();
	    Parent root = FXMLLoader.load(getClass().getResource("frame1.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
		
	}
	@FXML
    private void handleButton2Action(ActionEvent event) throws IOException, NamingException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterPDF = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF");
		fileChooser.getExtensionFilters().addAll(extFilterPDF);
		picture2 = fileChooser.showOpenDialog(null);
		System.out.println(picture2);
		String url =picture2.toString();
        web.setZoom(0.83);
        WebEngine we = web.getEngine();
        we.load(url);
        
		
		
	}
	
	@FXML
    private void handleButton3Action(ActionEvent event) throws IOException, NamingException {
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
    private void handleButton4Action(ActionEvent event) throws IOException, NamingException {
		data=FXCollections.observableArrayList();
		
		skill.add(sk.getValue());
		data =FXCollections.observableArrayList(skill);
//		for (Skill s : skill)
//		{
//		data.add(new Skill(s.getName()));
//		}
		
		
//		table.setItems(data);
//		//skillname.setCellValueFactory( new PropertyValueFactory<Skill,String> (sk.getValue()));
//	skillname.setCellValueFactory((TableColumn.CellDataFeatures<Skill, String> t)
//            -> new SimpleStringProperty(sk.getValue().toString()));
		
table.setItems(data);
	skillname.setCellValueFactory( new PropertyValueFactory<Skill,String> ("name"));
}
		
		
	
}
