package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Contact;
import entities.User;

@Remote
public interface ContactServiceEJBRemote {
	public User addUser(User u);

	public User findUser(int id);

	public void addContact(User u1, User u2);
	
	public List<Contact> findAllContacts();
	
	public List<Contact> findAllContacts(int id);
	
	public List<Contact> findRequests(int id, int state);
	
	public List<Contact> findPending(int id, int state);
	
	public User findUserById(int id);
	
	public void deleteContact(User u1, User u2);
	
	public void updateContact(User u1, User u2);

}
