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

import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import delegate.RecommadationServiceDelegate;
import delegate.UserServiceDelegate;
import entities.Employer;
import entities.Rating;
import entities.Recommendation;
import entities.Skill;
import entities.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.RatingServiceEJBRemote;
import services.UserRecommandationServiceEJBRemote;
import services.UserServicesEJBRemote;

public class RecommandationController implements Initializable{
	private RecommadationServiceDelegate delegate=new RecommadationServiceDelegate();
	UserServiceDelegate delegate1= new UserServiceDelegate();
	@FXML
    private AnchorPane ap;

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

    @FXML
    private ComboBox<String> sk;

    @FXML
    private Button show;
    @FXML
    private TextField txtrcd;
    @FXML
    private JFXSlider rvalue;

    @FXML
    private Button rate;
    @FXML
    private Text rrr;

    @FXML
    private Button add;
    private  static ObservableList<Recommendation>data1;
    private  static ObservableList<String>data2;
    private  static ObservableList<Skill>data;
    public static Image image;
    File picture;

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
		InitialContext ctx2 = null;
		try {
			ctx2 = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			RatingServiceEJBRemote proxy2 = (RatingServiceEJBRemote)ctx2.lookup("/easyMission-ear/easyMission-ejb/RatingServiceEJB!services.RatingServiceEJBRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
		
		ArrayList<Worker>workers=(ArrayList<Worker>)proxy.findAllWorkers();
		List<String>workername=new ArrayList<>();
		for(Worker w : workers){
			workername.add(w.getFirstName());
		}
		data2 =FXCollections.observableArrayList(workername);
		sk.getItems().addAll(data2);
		
	}
	 @FXML
	    void add(ActionEvent event) throws NamingException, IOException {

			InitialContext ctx1=new InitialContext();
			InitialContext ctx2=new InitialContext();
			Object objet2=ctx2.lookup("/easyMission-ear/easyMission-ejb/UserRecommandationServiceEJB!services.UserRecommandationServiceEJBRemote");
			Object objet1=ctx1.lookup("/easyMission-ear/easyMission-ejb/UserServicesEJB!services.UserServicesEJBRemote");
			UserRecommandationServiceEJBRemote proxy=(UserRecommandationServiceEJBRemote)objet2;
			UserServicesEJBRemote proxy1=(UserServicesEJBRemote)objet1;
			Employer emp=proxy1.findEmploerById(frame1Controller.id);
			Worker w=null;
			try{
			w=proxy1.findWorkerByName(sk.getValue().toString());
			}catch(Exception e) {
				Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
				alert2.setTitle("Warning ");
				alert2.setHeaderText(null);
				alert2.setContentText("select User to recommand" );

				alert2.showAndWait();
			}
			
			if(txtrcd.getText().equals(null)){}else{
				try{
			//proxy.addUserRecommandation(emp,w,txtrcd.getText() );
					delegate.doAddRecommandation(emp, w, txtrcd.getText());
			
				}
			catch (Exception e) {
				Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
				alert2.setTitle("Warning ");
				alert2.setHeaderText(null);
				alert2.setContentText("User Already Recommanded" );

				alert2.showAndWait();
			}}
			Stage stage14 = (Stage) desc.getScene().getWindow();
		    stage14.close();
			Stage stage3=new Stage();
            Parent root3 = FXMLLoader.load(getClass().getResource("RecommandationFRame.fxml"));
            Scene scene13 = new Scene(root3);
            stage3.initStyle(StageStyle.UNDECORATED);
            stage3.setScene(scene13);
            stage3.show();

	    }

	    @FXML
	    void exit(ActionEvent event) {
	    	Stage stage = (Stage) ap.getScene().getWindow();
		    stage.close();
	    }

	    @FXML
	    void show(ActionEvent event) throws NamingException {
	    	InitialContext ctx22 = new InitialContext();
	    	RatingServiceEJBRemote proxy22 = (RatingServiceEJBRemote)ctx22.lookup("/easyMission-ear/easyMission-ejb/RatingServiceEJB!services.RatingServiceEJBRemote");
	    	
	    	String name=sk.getValue().toString();
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
			Worker emp=proxy.findWorkerByName(name);
			System.out.println(emp.getFirstName());
			fn.setText("First Name : "+emp.getFirstName());
			ln.setText("Last Name : "+emp.getLastName());
			mail.setText("Email address : "+emp.getEmail());
			country.setText("Country : "+emp.getCountry());
			field.setText("Field : "+emp.getField());
			bdate.setText("BirthDate  :"+emp.getBirthDate());
			bank.setText("Bank Account : "+emp.getRib());
	        number.setText("Phone Number : "+emp.getPhoneNumber());

	    	List<Rating>lrre=proxy22.findAllRate();
	    	int rate=0;
	    	int num=0;
	    	for( Rating r : lrre){
	    		if(r.getRated().getIdUser()==1){
	    			System.out.println(r.getMark());
	    			rate+=r.getMark();
	    			num++;
	    		}}
	    	float v=rate/num;
	        rrr.setText("Rate :"+v);
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
				List<Skill> sklist=proxy.findAllSkills();
				List<Worker>lw=proxy.findAllWorkers();
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
	    void rate(ActionEvent event) throws NamingException, IOException {
	    	InitialContext ctx2 = new InitialContext();
	    	RatingServiceEJBRemote proxy2 = (RatingServiceEJBRemote)ctx2.lookup("/easyMission-ear/easyMission-ejb/RatingServiceEJB!services.RatingServiceEJBRemote");
	    	Worker w=null;
			w=(Worker) delegate1.doFindUserByName(sk.getValue().toString());
			Employer emp=null;
			emp=delegate1.doFindEmployerById(frame1Controller.id);
			int x=(int) rvalue.getValue();
			float y=Float.parseFloat(x+"");
			System.out.println("test"+emp.getFirstName());
			System.out.println(y);
			try{
			proxy2.AddRate(emp, w,y);
			}catch(Exception e){
				Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
				alert2.setTitle("Warning ");
				alert2.setHeaderText(null);
				alert2.setContentText("User Already Rated" );

				alert2.showAndWait();
			}
			Stage stage14 = (Stage) desc.getScene().getWindow();
		    stage14.close();
			Stage stage3=new Stage();
            Parent root3 = FXMLLoader.load(getClass().getResource("RecommandationFRame.fxml"));
            Scene scene13 = new Scene(root3);
            stage3.initStyle(StageStyle.UNDECORATED);
            stage3.setScene(scene13);
            stage3.show();

			
	    }



}
