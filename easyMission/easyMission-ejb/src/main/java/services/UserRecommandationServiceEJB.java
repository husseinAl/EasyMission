package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import embadableIDs.RecommendationId;
import entities.Recommendation;
import entities.User;
import entities.Worker;

/**
 * Session Bean implementation class UserRecommandationServiceEJB
 */
@Stateless
@LocalBean
public class UserRecommandationServiceEJB implements UserRecommandationServiceEJBRemote, UserRecommandationServiceEJBLocal {

	@PersistenceContext
	EntityManager em;
    public UserRecommandationServiceEJB() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public void addUserRecommandation(User u1, User u2, String text) {
//		em.merge(u1);
//		em.merge(u2);
		RecommendationId  rr=new RecommendationId();
		Recommendation r=new Recommendation();
		rr.setIdRecommendedPK(u2.getIdUser());
		rr.setIdRecommenderPK(u1.getIdUser());
		r.setIdRecommendation(rr);
		r.setRecommender(u1);
		r.setRecommended(u2);
		r.setState(1);
		r.setText(text);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(dateFormat.format(cal.getTime()));
		//Date d=new Date();
		r.setDate(cal.getTime());
		em.persist(em.merge(r));
		
	}
	@Override
	public List<Recommendation> findAllRecommandation() {
		// TODO Auto-generated method stub
		return  em.createQuery("select c from  Recommendation c where c.State=:ptext ",Recommendation.class).setParameter("ptext",1).getResultList();
	}
	@Override
	public Recommendation FindRecommandationBTextAndRecommander(String text) {
		// TODO Auto-generated method stub
		return em.createQuery("select c from  Recommendation c where c.text=:ptext ",Recommendation.class).setParameter("ptext", text).getSingleResult();
	}
	@Override
	public void deleteRecommandation(Recommendation r) {
		//System.out.println(r.getIdRecommendation().getIdRecommendedPK());
		User recommender=r.getRecommender();
		recommender.getRecommendations().remove(r);
		User recommended=r.getRecommended();
		recommended.getRecievedRecomendations().remove(r);
		em.merge(recommended);
		em.merge(recommender);
		
		em.remove(em.merge(r));

		
	}
	@Override
	public void changeState(Recommendation r) {
		em.merge(r);
		
	}



}
