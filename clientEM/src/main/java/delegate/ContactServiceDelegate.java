package delegate;

import java.util.List;

import javax.naming.NamingException;

import entities.Contact;
import entities.User;
import serviceLocator.ServiceLocator;
import services.ContactServiceEJBRemote;

public class ContactServiceDelegate {
	private static final String JNDI = "/easyMission-ear/easyMission-ejb/ContactServiceEJB!services.ContactServiceEJBRemote";

	private static ContactServiceEJBRemote getProxy() {
		return (ContactServiceEJBRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static List<Contact> getAllContacts() throws NamingException {
		return getProxy().findAllContacts();
	}
	
	public List<Contact> getContactsByUserId(int id) throws NamingException {
		return getProxy().findAllContacts(id);
	}

	public List<Contact> getRequests(int id, int state) throws NamingException{
		return getProxy().findRequests(id, state);
	}

	public List<Contact> getPendingRequests(int id, int state) throws NamingException {
		return getProxy().findPending(id, state);

	}

	public User findUserById(int id) throws NamingException {
		return getProxy().findUserById(id);

	}

	public void deleteUser(int id1, int id2) throws NamingException {
		getProxy().deleteContact(findUserById(id1), findUserById(id2));
	}
	
	public void updateContact(int id1, int id2) throws NamingException {
		getProxy().updateContact(findUserById(id1), findUserById(id2));
	}
}
