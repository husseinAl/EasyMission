package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Employer;
import entities.Skill;
import entities.User;
import entities.Worker;



@Remote
public interface UserServicesEJBRemote {
	public void addUser(User u);
	public void addWorker(Worker w);
	public void addEmployer(Employer E);
	public void updateUser(User u);
	public void updateWorker(Worker w);
	public void updateEmployer(Employer E);
	public void deleteUser(User u);
	public void deleteEmploer(Employer E);
	public void deleteWorker(Worker w);
	public User findUserById(int idUser);
	public User findUserByLogin(String Login);
	public Employer findEmploerById(int idEmploer);
	public Worker findWorkerById(int idWorker);
	public User findUserByName(String name);
	public Worker findWorkerByName(String name);
	public Employer findEmployerByName(String name);
	public User findUserBYLoginAndPassword(String login ,String pwd);
	public List<Skill> findAllSkills();
	public List<Worker> findAllWorkers();
	public Skill findSkillByName(String name);

}
