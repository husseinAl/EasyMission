package services;

import javax.ejb.Remote;

import java.util.ArrayList;
import java.util.List;


import entities.Employer;
import entities.Mission;
import entities.Notification;
import entities.Skill;
import entities.User;
import entities.Worker;

@Remote
public interface ApplicationEJBRemote {

	public void add(Mission m,Employer e);
	public List<Mission> findAll(int id);
	public List<Mission> find(ArrayList<Skill> skills);
	public void update(Mission m);
	public Mission find(int id);
	//-----------------------------
	public List<Skill> findallSkills();	
	public void addSkill(Skill s);
	public Skill findSkill(int id);

	//----------------
	 public User findUser(int id);	 
	 public void AddUser(User u);
	 //----------
	 public List<Mission> filter(int id,String title,List<Skill> ls,String type,int min,int max);
	 public void apply(Worker w, Mission m,String text);
	 
	 //-------------------------------
	 
	
	 
	 
	 
}
