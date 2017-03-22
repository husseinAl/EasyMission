package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Mission;
import entities.Rating;
import entities.User;

@Remote
public interface RatingServiceEJBRemote {
	public void AddRate(User u1,User u2,float ratev);
	//public void AddRate(Rating r);
	public void addUser(User u);
	public User finduserById(int id);
	public List<Rating> findAllRate();

}
