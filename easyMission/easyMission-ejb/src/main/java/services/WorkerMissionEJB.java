package services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import embadableIDs.ApplicationId;
import entities.Application;
import entities.Employer;
import entities.Mission;
import entities.Worker;

/**
 * Session Bean implementation class WorkerMissionEJB
 */
@Stateless
@LocalBean
public class WorkerMissionEJB implements WorkerMissionEJBRemote, WorkerMissionEJBLocal {

   
	@PersistenceContext
	EntityManager em;
	@EJB
	NotificationEJB notif;
	
    public WorkerMissionEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public Worker findWorker(int id) {
		Worker w =em.find(Worker.class, id);
		if (w!=null)
		{
			w.getApplications().size();
			w.getSuggestions().size();
			w.getMissions().size();
		}
		return w;
	}

	
	@Override
	public Mission findMission(int id) {
	 Mission m =em.find(Mission.class, id);
	 if (m!=null)
		 m.getApplications().size();
	 return m;
	}

	@Override
	public boolean assign(Application app) {

		Mission m=app.getMission();
		Worker w=app.getWorker();
		m.setState("active");
		m.setWorker(w);
		ApplicationId id=new ApplicationId();
		id.setIdApplicantPK(w.getIdUser());
		id.setIdMissionPK(m.getIdMission());
		app.setIdApplication(id);
		em.merge(m);
		em.merge(app);
		notif.notif(app.getWorker().getIdUser() , "You have been assigned  for the mission "+m.getTitle());
	
		return true;
	}

}
