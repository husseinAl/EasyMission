package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import embadableIDs.ApplicationId;

/**
 * Entity implementation class for Entity: Application
 *
 */
@Entity

public class Application implements Serializable {

	@EmbeddedId
	private ApplicationId idApplication=new ApplicationId();
	private Date date;
	private String text ;
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	private Mission mission;
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	private Worker worker ;	
	
	
	private static final long serialVersionUID = 1L;

	public Application() {
		super();
	}
	
	

	public Application(String text, Mission mission, Worker worker) {
		super();
		this.text = text;
		this.mission = mission;
		this.worker = worker;
		this.date=new Date();
		this.idApplication.setIdApplicantPK(worker.getIdUser());
		this.idApplication.setIdMissionPK(mission.getIdMission());
	}



	public ApplicationId getIdApplication() {
		return idApplication;
	}

	public void setIdApplication(ApplicationId idApplication) {
		this.idApplication = idApplication;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}
	
	public String getNom()
	{
		return this.getWorker().getFirstName()+" "+this.getWorker().getLastName();
	}
   
}
