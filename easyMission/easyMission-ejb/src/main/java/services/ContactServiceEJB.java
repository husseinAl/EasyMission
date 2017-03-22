package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Contact;
import entities.User;

/**
 * Session Bean implementation class ContactServiceEJB
 */
@Stateless
@LocalBean
public class ContactServiceEJB implements ContactServiceEJBRemote, ContactServiceEJBLocal {

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public ContactServiceEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addContact(User user1, User user2) {
		em.merge(user1);
		em.merge(user2);

		Contact c = new Contact(user1, user2, 0);
		
		em.persist(c);
		
//
//		user1.addContactToList(c);
//		user2.addReflexiveContactToList(c);
//		
//		em.merge(user1);
//		em.merge(user2);
//		em.merge(c);

	}

	@Override
	public User addUser(User u) {
		em.persist(u);
		return u;
	}

	@Override
	public User findUser(int id) {
		return em.find(User.class, id);

	}

	@Override
	public List<Contact> findAllContacts(int id) {
		return em.createQuery("select c from Contact c where c.state=?1 and c.idContact.idUserPK=?2 or c.idContact.idContactPK=?3 and c.state=?4", Contact.class).setParameter(1, 1).setParameter(2, id).setParameter(3, id).setParameter(4, 1).getResultList();
	}

	@Override
	public User findUserById(int id) {
		return em.find(User.class, id);
	}

	@Override
	public List<Contact> findAllContacts() {
		return em.createQuery("select c from Contact c", Contact.class).getResultList();
	}

	@Override
	public void deleteContact(User u1, User u2) {
		 em.createQuery("delete from Contact c where c.idContact.idUserPK=?1 and c.idContact.idContactPK=?2").setParameter(1, u1.getIdUser()).setParameter(2, u2.getIdUser()).executeUpdate();
		
	}

	@Override
	public List<Contact> findRequests(int id, int state) {
		return em.createQuery("select c from Contact c where c.idContact.idContactPK=?1 and c.state=?2", Contact.class).setParameter(1, id).setParameter(2, state).getResultList();
	}
	
	@Override
	public List<Contact> findPending(int id, int state) {
		return em.createQuery("select c from Contact c where c.idContact.idUserPK=?1 and c.state=?2", Contact.class).setParameter(1, id).setParameter(2, state).getResultList();
	}

	@Override
	public void updateContact(User u1, User u2) {
		Contact c = new Contact();
		c = em.createQuery("select c from Contact c where c.idContact.idUserPK=?1 and c.idContact.idContactPK=?2", Contact.class).setParameter(1, u1.getIdUser()).setParameter(2, u2.getIdUser()).getSingleResult();
		c.setState(1);
		em.merge(c);
	}
	
}
