package admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import services.AdminserviceEJBRemote;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Skill;
import entities.Worker;
import javafx.event.ActionEvent;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BarChartSkillsController implements Initializable {
	@FXML
	private BarChart<String,Long> barchartskills;
	@FXML
	private Button btnload;
	@FXML
	private CategoryAxis x;
	@FXML
	private NumberAxis y;

	// Event Listener on Button[#btnload].onAction
	@FXML
	public void loadChart(ActionEvent event) {
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		XYChart.Series<String, Long> series = new XYChart.Series<>();
		long x = 0 ;long y=0; long z=0 ; long t=0;
		try 
		{
InitialContext	ic = new InitialContext();
		
		AdminserviceEJBRemote proxy = (AdminserviceEJBRemote) ic.lookup("/easyMission-ear/easyMission-ejb"
				+ "/AdminserviceEJB!services.AdminserviceEJBRemote");
		
		List<Worker> lw = proxy.displayallworkers();
		for (Worker w : lw )
		{
			List<Skill> ls = w.getSkills();
			 for (Skill s : ls)
				 if (s.getIdSkill()==3)
				 {
					 x=x+1;
				 }
				 else if (s.getIdSkill()==2)
				 {
					 y=y+1;
				 }
				 else if (s.getIdSkill()==1)
				 {
					 z=z+1;
				 }
				 else
					 t=t+1;
		}
		
		series.getData().add(new XYChart.Data<String, Long>("php", z));
		series.getData().add(new XYChart.Data<String, Long>("java", y));
		series.getData().add(new XYChart.Data<String, Long>("C#", x));
		series.getData().add(new XYChart.Data<String, Long>("c++", t));
		barchartskills.getData().addAll(series);
		}
		catch (NamingException e)
		{
			System.out.println(e);
		}
	}
}
