package services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Application;
import entities.Employer;
import entities.Mission;
import entities.Notification;
import entities.Skill;
import entities.User;
import entities.Worker;

/**
 * Session Bean implementation class ApplicationEJB
 */
@Stateless
@LocalBean
public class ApplicationEJB implements ApplicationEJBRemote, ApplicationEJBLocal {

	@PersistenceContext
	EntityManager em;
	
	@EJB
	NotificationEJB notif;

	public ApplicationEJB() {
		// TODO Auto-generated constructor stub
	}

	// -------------------------------------------
	public void AddUser(User u) {
		if (u instanceof Employer) {
			em.persist((Employer) u);
		} else
			em.persist((Worker) u);
	}

	public User findUser(int id) {
		User u = em.find(User.class, id);
		if (u instanceof Employer) {
			System.out.println("Employer");
			((Employer) u).getMissions().size();
		}else
		((Worker) u).getApplications().size();
		return u;
	}

	// ----------------------------------------------------
	@Override
	public void add(Mission m, Employer e) {
		m.setEmployer(e);
		e.getMissions().add(m);
		em.merge(e);
		em.flush();

	}

	@Override
	public List<Mission> findAll(int id) {
		String req = "";
		req = "SELECT m from Mission m WHERE m.state='onhold' AND"
				+ "((SELECT app.mission.idMission from Application app where app.mission.idMission=m.idMission AND app.worker.idUser="
				+ id + ") IS NULL)";
		// + "AND (app IS NULL OR app.worker.idUser<>2) ";
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println(req);
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return em.createQuery(req, Mission.class).getResultList();

	}

	@Override
	public List<Mission> find(ArrayList<Skill> skills) {
		List<Mission> missions = new ArrayList<>();
		for (Skill s : skills) {
			missions.addAll(s.getMissions());
		}
		return missions;
		/*
		 * return em.createQuery("select c from Client c where c.nom=?1"
		 * ,Client.class).setParameter(1, name) .getSingleResult();
		 */
	}

	@Override
	public void update(Mission m) {

		em.merge(m);

	}

	@Override
	public Mission find(int id) {
		Mission m = em.find(Mission.class, id);
		System.out.println(m.getSkills().size());
		return m;

	}

	@Override
	public List<Skill> findallSkills() {
		return em.createQuery("select s from Skill s ", Skill.class).getResultList();
	}

	@Override
	public void addSkill(Skill s) {
		em.persist(s);

	}

	@Override
	public Skill findSkill(int id) {

		return null;
	}

	@Override
	public List<Mission> filter(int id, String title, List<Skill> ls, String type, int min, int max) {
		if (title.length() == 0 && (ls.size() == 0) && type.length() == 0 && min < 0 && max < 0)
			return findAll(2);
		int i = 0;
		String req = "select m from Mission m ";
		if (ls.size() > 0) {
			req += ",IN(m.skills) s ";
		}
		req += "WHERE m.state='onhold' and ";

		if (title.length() > 0) {
			req += "m.title LIKE'%" + title + "%' ";
			i++;
		}
		if (type.length() > 0) {
			if (i > 0)
				req += "and ";

			req += "m.missionType='" + type + "' ";
			i++;

		}
		if (min > 0) {
			if (i > 0)
				req += "and ";

			req += "m.price>=" + min + " ";
			i++;
		}
		if (max > 0) {
			if (i > 0)
				req += "and ";

			req += "m.price<=" + max + " ";
			i++;
		}
		if (ls.size() > 0) {
			if (i > 0)
				req += "and ";
			String ch = "";
			// a partir d'une List<Skill> obtenir un interval celon le syntax du jpql
			
			for (Skill s : ls) {
				ch += "'" + s.getName() + "',";
			}
			ch = ch.substring(0, ch.length() - 1);
			//////////
			req += "s.name IN(" + ch + ") ";
			i++;
		}
		req += "AND (SELECT app.mission.idMission from Application app WHERE"
				+ " app.mission.idMission=m.idMission AND app.worker.idUser=" + id + ") IS NULL)";
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println(req);
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		return em.createQuery(req, Mission.class).getResultList();
	}

	@Override
	public void apply(Worker w, Mission m, String text) {
		System.out.println(m.getIdMission());
		System.out.println(w.getIdUser());
		Mission mm = em.merge(m);
		Worker ww = em.merge(w);

		Application a = new Application(text, mm, ww);
		mm.getApplications().add(a);
		 notif.notif(m.getEmployer().getIdUser() , "New Application for the mission "+m.getTitle());

	}
	//---------------------------- A Deplacer vers NotificationEJB----------------------
	
	
	

}
