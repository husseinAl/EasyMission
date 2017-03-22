package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Mission;
import entities.Suggestion;
import entities.User;

/**
 * Session Bean implementation class SuggestionCRUD
 */
@Stateless
@LocalBean
public class SuggestionCRUD implements SuggestionCRUDRemote, SuggestionCRUDLocal {

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public SuggestionCRUD() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addSuggestion(User u1, User u2, Mission m, String text) {
		Mission mm =em.merge(m);
		Suggestion s = new Suggestion(u1, u2, mm, text);
		mm.getSuggestions().add(s);
		
		//em.persist(s);
		
//		m.getSuggestions().add(s);

//		s.setMission(em.merge(m));

	}


}
