package admin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class main extends Application{

	  @Override
	    public void start(Stage stage) throws Exception {
	        //Parent root = FXMLLoader.load(getClass().getResource("ReclamationManagement.fxml"));
		 Parent root = FXMLLoader.load(getClass().getResource("AdminManagement.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("BarChartSkills.fxml"));
		// Parent root = FXMLLoader.load(getClass().getResource("RepportManagement.fxml"));
		// Parent root = FXMLLoader.load(getClass().getResource("Chart1.fxml"));
		//  Parent root = FXMLLoader.load(getClass().getResource("ManageUsersWorkers.fxml"));
		// Parent root = FXMLLoader.load(getClass().getResource("Stats.fxml"));
	       // Parent root = FXMLLoader.load(getClass().getResource("UsersManagement.fxml"));
	        Scene scene = new Scene(root);
	        //scene.getStylesheets().add(this.getClass().getResource("list.css").toExternalForm());
	       // scene.getStylesheets().add(this.getClass().getResource("list.css").toExternalForm());
	        stage.initStyle(StageStyle.DECORATED);
	        stage.setScene(scene);
	        
	        stage.show();
	       
	    }
	  public static void main(String[] args) {
	        launch(args);
	    }
	    

}
