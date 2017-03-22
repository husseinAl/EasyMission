package frames;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Mission;
import entities.Skill;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.MissionServiceEJBRemote;

public class test extends Application {
	  public void start(Stage primaryStage) throws IOException {
		  //FXMLLoader loader = new FXMLLoader(Main.class.getResource("addmission.fxml"));
	         Parent root=FXMLLoader.load(getClass().getResource("mail.fxml"));//les infos de fxml /load : convertir/inteface sappelle scene
	        Scene scene=new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	public static void main(String[] args) throws NamingException{
				 launch(args);
				 
				 
					
					/*List<Mission> listmissions = (List<Mission>) proxy.findMission();
					for (Mission m : listmissions)
					{
						System.out.println(m.getMissionType());
						System.out.println(m.getState());
						System.out.println(m.getPrice());
						System.out.println(m.getSkills());
						
					
					}*/

	
		
	}

}
