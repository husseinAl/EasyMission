package entities;

import entities.User;
import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Worker
 *
 */
@Entity

public class Worker extends User implements Serializable {

	
	private String cv;
	private String description;
	private String rib;
	private String phoneNumber;
	@ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private List<Skill> skills;
	@OneToMany(mappedBy="worker")
	private List<Suggestion> suggestions ;
	@OneToMany(mappedBy="worker")
	private List<Application> applications ;
	@OneToMany(mappedBy="worker")
	private List<Mission> missions ;
	
	public List<Mission> getMissions() {
		return missions;
	}
	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
	private static final long serialVersionUID = 1L;

	public Worker() {
		super();
	}   
	public String getCv() {
		return this.cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public String getRib() {
		return this.rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	public List<Suggestion> getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}
	public List<Application> getApplications() {
		return applications;
	}
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Worker(int idUser, String login, String firstName, String lastName, String email, String password,
			String country, String state,String cv,String description,List<Skill> skills) {
		super(idUser, login, firstName, lastName, email, password, country, state);
		this.cv=cv;
		this.description=description;
		this.skills=skills;
	}
	public Worker(String firstName, String lastName, String email, Date birthDate, String country, String field,
			String picture, String password) {
		super(firstName, lastName, email, birthDate, country, field, picture, password);}
   
}
