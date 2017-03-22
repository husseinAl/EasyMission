package entities;

import entities.User;
import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Employer
 *
 */
@Entity


public class Employer extends User implements Serializable {

	
	private String adress;
	private String company;
	private String companyNumber;
	private String CompanyLogo;
	@OneToMany(mappedBy="employer",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Mission> missions=new ArrayList<Mission>();
	
	@OneToMany(mappedBy="employer")
	private List<Suggestion> suggestions ;
	
	private static final long serialVersionUID = 1L;

	public Employer() {
		super();
	}   
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}   
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getCompanyLogo() {
		return CompanyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		CompanyLogo = companyLogo;
	}
	@Override
	public String toString() {
		return "Employer [adress=" + adress + ", company=" + company + ", companyNumber=" + companyNumber
				+ ", CompanyLogo=" + CompanyLogo + ", missions=" + missions + ", suggestions=" + suggestions + "]";
	}
	public List<Mission> getMissions() {
		return missions;
	}
	public void setMissions(List<Mission> missions) {
		this.missions = missions;
	}
	public List<Suggestion> getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}
	
	public Employer(int idUser, String login, String firstName, String lastName, String email, String password,
			String country, String state,String adress,String company) {
		super(idUser, login, firstName, lastName, email, password, country, state);
		this.adress=adress;
		this.company=company;
	}
	public void addMission(Mission m)
	{
		if (this.missions == null)
			this.missions= new ArrayList<Mission>();
		this.missions.add(m);
	}
	public Employer(String firstName, String lastName, String email, Date birthDate, String country,
			String field, String picture, String password,String adress, String company ) {
		super( firstName,  lastName,  email,  birthDate,  country, field,  picture,  password);
		this.adress = adress;
		this.company = company;		
	}
	
   
}
