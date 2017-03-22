package slim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage Primarystage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("contact.fxml"));
//		Parent root = FXMLLoader.load(getClass().getResource("suggestion.fxml"));
		Scene scene = new Scene(root);
//		stage.setAlwaysOnTop(true);
		Primarystage.setResizable(false);
		Primarystage.getIcons().add(new Image("/slim/images/Letter-E-icon.png"));
		Primarystage.setTitle("Easy Mission");
		Primarystage.setScene(scene);
		Primarystage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}