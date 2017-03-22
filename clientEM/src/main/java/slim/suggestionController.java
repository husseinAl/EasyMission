package slim;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Mission;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
//import services.MissionCRUDRemote;
import services.SuggestionCRUDRemote;

public class suggestionController implements Initializable {

	@FXML
	private TilePane mPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {/*
		try {
			InitialContext ctx = new InitialContext();
			Object obj = ctx.lookup("/easyMission-ear/easyMission-ejb/MissionCRUD!services.MissionCRUDRemote");
			MissionCRUDRemote proxy = (MissionCRUDRemote) obj;

			Object obj2 = ctx.lookup("/easyMission-ear/easyMission-ejb/SuggestionCRUD!services.SuggestionCRUDRemote");
			SuggestionCRUDRemote proxy2 = (SuggestionCRUDRemote) obj2;

			mPane.setPadding(new Insets(10, 10, 10, 10));
			mPane.setVgap(4);
			mPane.setHgap(4);
			
			VBox vb = new VBox();
			vb.setPadding(new Insets(10, 50, 50, 50));
		    vb.setSpacing(10);

			for (Mission m : proxy.findAllMissions()) {
				
//				ImageView imageView = new ImageView(new Image("http://icons.iconarchive.com/icons/tinylab/android-lollipop-apps/128/8Tracks-icon.png"));
//				imageView.setFitHeight(20);
//				imageView.setFitWidth(20);
				TitledPane tp = new TitledPane();
//				tp.setPrefSize(100, 100);
				
				tp.setMaxHeight(100);
				tp.setMaxWidth(100);
				
				tp.setText(m.getTitle());
				
//				Button btn = new Button("Suggest");
//				vb.getChildren().add(btn);
				
				tp.setContent(vb);
				
				mPane.getChildren().add(tp);

			}

		} catch (NamingException e) {
			e.printStackTrace();
			//System.out.println("HELLO");
		}*/

	}

}
