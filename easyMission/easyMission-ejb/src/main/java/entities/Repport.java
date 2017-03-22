package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import embadableIDs.RepportId;

/**
 * Entity implementation class for Entity: Repport
 *
 */
@Entity

public class Repport implements Serializable {

	@EmbeddedId
	private RepportId idRepport;
	private String object ;
	private String text ;
	private int state;	
	private Date date =Calendar.getInstance().getTime();
	@ManyToOne
	private User user;
	@ManyToOne
	private Mission mission;
	private static final long serialVersionUID = 1L;

	public Repport() {
		super();
	}

	public RepportId getIdRepport() {
		return idRepport;
	}

	public void setIdRepport(RepportId idRepport) {
		this.idRepport = idRepport;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}
	public Repport(String object, String text, int state, User user, Mission mission) {
		super();
		this.object = object;
		this.text = text;
		this.state = state;
		
		this.idRepport.setIdMisssionPK(mission.getIdMission());
		this.idRepport.setIdUserPK(user.getIdUser());
		this.user=user;
		this.mission = mission;
	}

public String getReporterFullName(){
		return this.user.getFirstName()+" "+this.user.getLastName();
	}
public Repport(String object, String text, int state, User user, RepportId idRepport) {
	super();
	this.object = object;
	this.text = text;
	this.state = state;
	this.user = user;
	this.idRepport = idRepport;
}
public Repport(String object, String text, int state, User user) {
	super();
	this.object = object;
	this.text = text;
	this.state = state;
	this.user = user;
}
public int getIdmissionreport()
{
	return this.idRepport.getIdMisssionPK();
}
   
}
