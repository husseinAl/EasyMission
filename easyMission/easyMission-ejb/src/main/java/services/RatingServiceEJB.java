package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import embadableIDs.RatingId;
import entities.Rating;
import entities.Recommendation;
import entities.User;

/**
 * Session Bean implementation class RatingServiceEJB
 */
@Stateful
@LocalBean
public class RatingServiceEJB implements RatingServiceEJBRemote, RatingServiceEJBLocal {
	@PersistenceContext
	EntityManager em;
    public RatingServiceEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void AddRate(User u1,User u2,float ratev)  {
		
		Rating r=new Rating();
		RatingId ri = new RatingId();
		ri.setIdRaterPK(u1.getIdUser());
		ri.setIdRatedPK(u2.getIdUser());
	//	r.getIdRating().setIdRatedPK(u2.getIdUser());
	//	r.getIdRating().setIdRaterPK(u1.getIdUser());
		r.setIdRating(ri);
		r.setMark(ratev);
			
		//Rating rm =em.merge(r);
		
		User um1 =em.merge(u1);
		User um2 =em.merge(u2);

		Rating rm =em.merge(r);
		rm.setRated(um2);
		rm.setRater(um1);	
		um1.getRatings().add(rm);
		um2.getRecievedRatings().add(rm);
		
		//em.merge(u2);
		//em.merge(u2).getRecievedRatings().add(rm);
	
		
	}

	@Override
	public void addUser(User u) {
		em.persist(u);
		
	}

	@Override
	public User finduserById(int id) {
		// TODO Auto-generated method stub
		User u= em.find(User.class,id);
		u.getRatings().size();
		u.getRecievedRatings().size();
		
		return u ;
	}

	@Override
	public List<Rating> findAllRate() {
		// TODO Auto-generated method stub
		return  em.createQuery("select c from  Rating c ",Rating.class).getResultList();
	}

	/*@Override
	public void AddRate(Rating r) {
		em.persist(r);
		
	}
	*/

}
