package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import embadableIDs.DiscussionId;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity

public class Discussion implements Serializable {

	@Id
	private DiscussionId idDiscussion;
	private int state;
	@ManyToOne
	private User sender;
	@OneToMany(mappedBy = "discussion")
	private List<Message> messages;
	@ManyToOne
	private User reciever;

	private static final long serialVersionUID = 1L;

	public Discussion() {
		super();
	}

	public DiscussionId getIdDiscussion() {
		return idDiscussion;
	}

	public void setIdDiscussion(DiscussionId idDiscussion) {
		this.idDiscussion = idDiscussion;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
