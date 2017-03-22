package services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import embadableIDs.RepportId;
import entities.Employer;
import entities.Mission;
import entities.Repport;
import entities.User;
import entities.Worker;

/**
 * Session Bean implementation class AdminserviceEJB
 */
@Stateful
@LocalBean
public class AdminserviceEJB implements AdminserviceEJBRemote, AdminserviceEJBLocal {
@PersistenceContext
EntityManager em;
    /**
     * Default constructor. 
     */
    public AdminserviceEJB() {
        // TODO Auto-generated constructor stub
    }



	@Override
	public void bloqueruser(User u) {
		User user =finduserbyid(u.getIdUser()) ;
		user.setState("blocked");
		em.persist(user);
		em.flush();
		
	}

	@Override
	public List<Mission> displayallmissions() {
		
		return em.createQuery("select m from Mission m",Mission.class).getResultList();
	}

	@Override
	public Mission findmissionbyId(int idmission) {
		
		return em.find(Mission.class, idmission);
		
	}

	@Override
	public void validatemission(Mission m) {
		m.setState("valide");	
		em.merge(m);
		
	}

	@Override
	public void treatReport(Repport r) {
		r.setState(1);
		em.merge(r);
		
		
	}

	@Override
	public List<Repport> displayrepportbydate(Date d) {
		// TODO Auto-generated method stub
		return em.createQuery("select r from Repport c where c.date=?1",Repport.class)
				.setParameter(1, d)
				.getResultList();
	}



	@Override
	public User finduserbyid(int iduser) {
		
		return em.find(User.class, iduser);
	}



	@Override
	public List<User> displayallusers() {
		
		return em.createQuery("select u from User u",User.class).getResultList();
	}



	@Override
	public User login(String email, String password) {
		
		return em.createQuery("select u from User",User.class).getSingleResult();
	}



	@Override
	public void addEmployer(Employer e) {
		em.persist(e);
		
	}



	@Override
	public void addMission(Mission m,Employer e) {
		em.merge(e);
		m.setEmployer(e);
		//em.persist(m);
		e.addMission(m);
		
		
	}



	@Override
	public void addWorker(Worker w) {
		em.persist(w);
		
	}



	@Override
	public void repport(User u, Mission m) {
	
	Repport r = new Repport("rec1","terrible mission",0,u,m);
	em.persist(r);
		
	}



	@Override
	public List<Repport> displaytraitedReclmations() {
		
		return em.createQuery("select r from Repport r where r.state=1",Repport.class).getResultList();
				
	}



	



	@Override
	public void addReclamation(Repport r) {

		
		em.persist(r);
		
	}



	


	


	@Override
	public List<Repport> displayholdingReclmations() {
		return em.createQuery("select r from Repport r where r.state=0",Repport.class).getResultList();
		
	}



	@Override
	public List<Worker> displayallworkers() {
		
		return em.createQuery("select w from Worker w",Worker.class).getResultList();
	}



	@Override
	public long nbrworkers() {
		
		String sql = "SELECT COUNT(w) FROM Worker w";
		Query q = em.createQuery(sql);
		long count = (long)q.getSingleResult();
		return count;
	}



	@Override
	public long nbremployers() {
		
		String sql = "SELECT COUNT(e) FROM Employer e";
		Query q = em.createQuery(sql);
		long count = (long)q.getSingleResult();
		return count;
	}



	@Override
	public void addnewmission(Mission m) {
		em.persist(m);
		
	}



	@Override
	public void blockuser(User u) {
		u.setState("blocked");
		em.merge(u);
		
	}



	@Override
	public void declinereclamation(Repport r) {
		em.remove(r);
		
	}



	@Override
	public List<Repport> displayallpReclmations() {
		return em.createQuery("select r from Repport r ",Repport.class).getResultList();
	}



	@Override
	public List<Repport> findRepportidmission(int id) {
		
		Mission m = em.find(Mission.class,id);
		m.getRepports().size();
		return m.getRepports();
	}



	@Override
	public Repport findRepportById(RepportId id) {
		return em.find(Repport.class, id);
		
	}



	@Override
	public Repport findRepport(User u, Mission m) {
		return null;
	}



	@Override
	public Repport findRepportbyidmission(int id) {
		return em.find(Repport.class,id);

	}

	





	



	

}
