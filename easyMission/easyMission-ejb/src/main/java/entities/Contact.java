package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

import embadableIDs.ContactId;

/**
 * Entity implementation class for Entity: Contact
 *
 */
@Entity

public class Contact implements Serializable {

	@Id
	private ContactId idContact = new ContactId();

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "idUserPK", insertable = false, updatable = false)
	private User user;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "idContactPK", insertable = false, updatable = false)
	private User contact;

	private int state;
	private Date date;
	private static final long serialVersionUID = 1L;

	public Contact() {
		super();
	}

	public Contact(User user, User contact) {
		super();
		this.user = user;
		this.contact = contact;
	}

	public Contact(User user, User contact, int state) {
		super();
		Calendar c = Calendar.getInstance();
		this.idContact.setIdUserPK(user.getIdUser());
		this.idContact.setIdContactPK(contact.getIdUser());
		// this.user = user;
		// this.contact = contact;
		this.state = state;
		java.util.Date date = c.getTime();
		this.date = date;
//		this.user.getContacts().add(this);
//		this.contact.getReflexiveContacts().add(this);
	}

	public ContactId getIdContact() {
		return this.idContact;
	}

	public void setIdContact(ContactId idContact) {
		this.idContact = idContact;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
