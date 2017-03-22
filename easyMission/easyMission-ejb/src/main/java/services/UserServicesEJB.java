package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Employer;
import entities.Skill;
import entities.User;
import entities.Worker;


/**
 * Session Bean implementation class UserServicesEJB
 */
@Stateless
@LocalBean
public class UserServicesEJB implements UserServicesEJBRemote, UserServicesEJBLocal {

	@PersistenceContext
	EntityManager em;
    public UserServicesEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addUser(User u) {
		em.persist(u);
		
	}

	@Override
	public void addWorker(Worker w) {
		em.persist(w);
		
	}

	@Override
	public void addEmployer(Employer E) {
		em.persist(E);
		
	}

	@Override
	public void updateUser(User u) {
		em.merge(u);
		
	}

	@Override
	public void updateWorker(Worker w) {
		em.merge(w);
		
	}
	@Override
	public void updateEmployer(Employer E) {
		em.merge(E);
		
	}
	@Override
	public void deleteUser(User u) {
		em.remove(em.merge(u));	
		
	}

	@Override
	public void deleteEmploer(Employer E) {
		em.remove(em.merge(E));	
		
	}

	@Override
	public void deleteWorker(Worker w) {
		em.remove(em.merge(w));	
		
	}

	@Override
	public User findUserById(int idUser) {
		
		return em.find(User.class,idUser);
	}

	@Override
	public Employer findEmploerById(int idEmploer) {

		return em.find(Employer.class,idEmploer);
	}

	@Override
	public Worker findWorkerById(int idWorker) {
		// TODO Auto-generated method stub
		return em.find(Worker.class,idWorker);
	}

	@Override
	public User findUserByName(String name) {
	return	em.createQuery("select c from User c where c.firstName=:pname",User.class)
		.setParameter("pname", name).getSingleResult();
	}

	@Override
	public Worker findWorkerByName(String name) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from Worker c where c.firstName=:pname",Worker.class)
				.setParameter("pname", name).getSingleResult();
	}

	@Override
	public Employer findEmployerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserBYLoginAndPassword(String login, String pwd) {
		return em.createQuery("select c from User c where c.Login=:login and c.password=:pwd",User.class)
		.setParameter("login", login).setParameter("pwd", pwd).getSingleResult();
	}

	@Override
	public List<Skill> findAllSkills() {
		
		return em.createQuery("select DISTINCT c from  Skill c ",Skill.class).getResultList();
	}

	@Override
	public List<Worker> findAllWorkers() {
		// TODO Auto-generated method stub
		return em.createQuery("select DISTINCT c from  Worker c ",Worker.class).getResultList();
	}

	@Override
	public Skill findSkillByName(String name) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from Skill c where c.name=:pname",Skill.class)
				.setParameter("pname", name).getSingleResult();
	}

	@Override
	public User findUserByLogin(String Login) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from User c where c.Login=:pname",User.class)
				.setParameter("pname", Login).getSingleResult();
	}

	

}
