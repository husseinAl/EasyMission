package services;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entities.Recommendation;
import entities.User;

@Remote
public interface UserRecommandationServiceEJBRemote {
	public void addUserRecommandation(User u1, User u2, String text);
	public List<Recommendation>findAllRecommandation();
	public Recommendation FindRecommandationBTextAndRecommander(String text);
	public void deleteRecommandation(Recommendation r);
	public void changeState(Recommendation r);
}
