package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Mission
 *
 */
@Entity

public class Mission implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMission;
		
	private String missionType="";
	private Boolean local;
	private String title="";
	private String description="";	
	private String field="";
	private float price;
	private String state="";
	private Date startDate;
	private Date endDate;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Skill> skills=new ArrayList<Skill>();
	@ManyToOne
	private Employer employer;
	@ManyToOne
	private Worker worker;
	@OneToMany(mappedBy = "mission")
	private List<Suggestion> suggestions;
	@OneToMany(mappedBy="mission",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Application> applications=new ArrayList<>();
	@OneToMany(mappedBy="mission")
	private List<Repport> repports;

	private static final long serialVersionUID = 1L;

	public Mission() {
		super();
	}
	

	public Mission(String title, String description, String field, float price, String state, Date startDate,
			Date endDate, String missionType, Boolean local) {
		super();
		this.title = title;
		this.description = description;
		this.field = field;
		this.price = price;
		this.state = state;
		this.startDate = startDate;
		this.endDate = endDate;
		this.missionType = missionType;
		this.local = local;
		
	}


	public Mission(String title, String description, List<Skill> skills, String field, float price, String state,
			Date startDate, Date endDate, String missionType, Boolean local) {
		super();
		this.title = title;
		this.description = description;
		this.skills = skills;
		this.field = field;
		this.price = price;
		this.state = state;
		this.startDate = startDate;
		this.endDate = endDate;
		this.missionType = missionType;
		this.local = local;
	}
	public Mission( int idMission,  String title,  float price,String state,String field,Employer employer,  
			Date startDate, Date endDate, String missionType, List<Skill> skills,String description) {
		super();
		this.idMission = idMission;
		this.title = title;
		this.description = description;
		this.skills = skills;
		this.field = field;
		this.price = price;
		this.state = state;
		this.startDate = startDate;
		this.endDate = endDate;
		this.missionType = missionType;
		this.employer = employer;
		
	}
	
	


	public Mission(int idMission, Employer employer, String title, String description, List<Skill> skills, String field,
			float price, String state, Date startDate, Date endDate, String missionType) {
		super();
		this.idMission = idMission;
		this.employer = employer;
		this.title = title;
		this.description = description;
		this.skills = skills;
		this.field = field;
		this.price = price;
		this.state = state;
		this.startDate = startDate;
		this.endDate = endDate;
		this.missionType = missionType;
	}


	public List<Skill> getSkills() {
		return skills;
	}


	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}


	public Mission(String title) {
		super();
		this.title = title;
	}


	public int getIdMission() {
		return this.idMission;
	}

	public void setIdMission(int idMission) {
		this.idMission = idMission;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
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

	public List<Repport> getRepports() {
		return repports;
	}

	public void setRepports(List<Repport> repports) {
		this.repports = repports;
	}

	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public String getMissionType() {
		return missionType;
	}

	public void setMissionType(String missionType) {
		this.missionType = missionType;
	}

	public Boolean getLocal() {
		return local;
	}

	public void setLocal(Boolean local) {
		this.local = local;
	}


	public Mission(List<Skill> skills) {
		super();
		this.skills = skills;
	}


	@Override
	public String toString() {
		return "Mission [idMission=" + idMission + ", employer=" + employer + ", title=" + title + ", description="
				+ description + ", skills=" + skills + ", field=" + field + ", price=" + price + ", state=" + state
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", worker=" + worker + ", missionType="
				+ missionType + ", local=" + local + ", suggestions=" + suggestions + ", applications=" + applications
				+ ", repports=" + repports + "]";
	}
	public Mission(String title, String description, String field, float price, String state, Date startDate,
			Date endDate,  String missionType, Boolean local, List<Skill> skills) {
		super();
		this.title = title;
		this.description = description;
		this.field = field;
		this.price = price;
		this.state = state;
		this.startDate = startDate;
		this.endDate = endDate;
		this.missionType = missionType;
		this.local = local;
		this.skills = skills;
	}
	public Mission(String title, String description, String field,
			float price, String state, Date startDate, Date endDate, Worker worker, String missionType, Boolean local) {
		
		
		this.title = title;
		this.description = description;		
		this.field = field;
		this.price = price;
		this.state = state;
		this.startDate = startDate;
		this.endDate = endDate;
		this.worker = worker;
		this.missionType = missionType;
		this.local = local;
	}
	
}
