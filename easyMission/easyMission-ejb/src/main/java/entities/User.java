package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

public class User implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUser;
	@Column(unique=true)
	private String Login;
	private String state; 
	private String firstName;
	private String lastName;
	private String email;
	private Date birthDate ;
	private String country;
	private String field ;
	private String picture;
	private String password ;
	
	@OneToMany(mappedBy="notified")
	private List<Notification> notifications=new ArrayList<Notification>();
	// fetch=FetchType.LAZY 
	@OneToMany(mappedBy="user",cascade = { CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)	
	private List<Contact> contacts =new ArrayList<Contact>() ;
	public List<Contact> getReflexiveContacts() {
		return reflexiveContacts;
	}
	public void setReflexiveContacts(List<Contact> reflexiveContacts) {
		this.reflexiveContacts = reflexiveContacts;
	}
	@OneToMany(mappedBy="recommender",cascade = { CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	private List<Recommendation> recommendations;
	@OneToMany(mappedBy="recommended", cascade = { CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	private List<Recommendation> recievedRecomendations;
	@OneToMany(mappedBy="reciever")
	private List<Discussion> discussions ;
	
	@OneToMany(mappedBy="rater",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Rating> ratings ;
	
	@OneToMany(mappedBy="rated",cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private List<Rating> recievedRatings;


	
	
	@OneToMany(mappedBy="user")
	private List<Repport> repports;
	@OneToMany(mappedBy = "contact", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	private List<Contact> reflexiveContacts ;
	
 
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Notification> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public List<Recommendation> getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(List<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}
	public List<Recommendation> getRecievedRecomendations() {
		return recievedRecomendations;
	}
	public void setRecievedRecomendations(List<Recommendation> recievedRecomendations) {
		this.recievedRecomendations = recievedRecomendations;
	}
	public List<Discussion> getDiscussions() {
		return discussions;
	}
	public void setDiscussions(List<Discussion> discussions) {
		this.discussions = discussions;
	}
	public List<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	public List<Rating> getRecievedRatings() {
		return recievedRatings;
	}
	public void setRecievedRatings(List<Rating> recievedRatings) {
		this.recievedRatings = recievedRatings;
	}
	public List<Repport> getRepports() {
		return repports;
	}
	public void setRepports(List<Repport> repports) {
		this.repports = repports;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public User(int idUser, String login, String firstName, String lastName, String email, String password,
			String country, String state) {
		super();
		this.idUser = idUser;
		Login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.country = country;
		this.state = state;
	}
	public User(String firstName, String lastName, String email, Date birthDate, String country,
			String field, String picture, String password) {
		super();
		this.state = "active";
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
		this.country = country;
		this.field = field;
		this.picture = picture;
		this.password = password;
	}
	
	
	
   
}
