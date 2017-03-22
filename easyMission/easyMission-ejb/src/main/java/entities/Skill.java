package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Skill
 *
 */
@Entity

public class Skill implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSkill;
	private String name;	
	@ManyToMany(mappedBy="skills",fetch=FetchType.EAGER,cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Worker> workers;
	@ManyToMany(mappedBy="skills",cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	private List<Mission> missions= new ArrayList<Mission>();
	private static final long serialVersionUID = 1L;

	public Skill() {
		super();
	}  

	public Skill(String name) {
		super();
		this.name = name;
	}

	public int getIdSkill() {
		return this.idSkill;
	}

	public void setIdSkill(int idSkill) {
		this.idSkill = idSkill;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Worker> getWorkers() {
		return workers;
	}
	public void setWorkers(List<Worker> workers) {
		this.workers = workers;
	}
	@Override
	public String toString() {
		return "" + name ;
	}

	public List<Mission> getMissions() {
		return missions;
	}

	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
   
}
