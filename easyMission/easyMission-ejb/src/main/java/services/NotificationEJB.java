package services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Notification;
import entities.User;

/**
 * Session Bean implementation class Notification
 */
@Stateless
@LocalBean
public class NotificationEJB implements NotificationEJBLocal,NotificationEJBRemote {

   @PersistenceContext
   EntityManager em ;
    public NotificationEJB() {
        // TODO Auto-generated constructor stub
    }
    

	
	@Override
	public void notif(int id, String text) {
			User u =em.find(User.class, id)	;
			u.getNotifications().size();
			Notification n=new Notification();
			n.setDate(new Date());
			n.setNotified(u);
			n.setText(text);
			n.setState(0);
			em.persist(n);	
	}

		@Override
	public List<Notification> getnotif(int id) {
		String req="select n from Notification n where n.state=0 and n.notified.idUser="+id;
		 List<Notification> ln =em.createQuery(req, Notification.class).getResultList();
		 for(Notification n :ln)
		 {
			 n.setState(1);
		 }
		 return ln;
	}


}
