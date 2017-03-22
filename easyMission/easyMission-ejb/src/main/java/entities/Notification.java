package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Notification
 *
 */
@Entity

public class Notification implements Serializable {

	   
	@Id
	@GeneratedValue
	private int idNotification;
	@ManyToOne
	private User notified;
	
	private String text;
	private int state;
	private Date date ;
	private static final long serialVersionUID = 1L;

	public Notification() {
		super();
	}   
	public int getIdNotification() {
		return this.idNotification;
	}

	public void setIdNotification(int idNotification) {
		this.idNotification = idNotification;
	}   
	
	
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}   
	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}
	public Notification(int idNotification, User notified, String text, int state) {
		super();
		this.idNotification = idNotification;
		this.notified = notified;
		this.text = text;
		this.state = state;
	}
	public Notification(User notified, String text, int state) {
		super();
		this.notified = notified;
		this.text = text;
		this.state = state;
	}
	public User getNotified() {
		return notified;
	}
	public void setNotified(User notified) {
		this.notified = notified;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Notification:" + text + ", date=" + date ;
	}
	
   
}
