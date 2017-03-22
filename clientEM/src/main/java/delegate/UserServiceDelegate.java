package delegate;

import java.util.List;

import javax.naming.NamingException;

import entities.Employer;
import entities.Skill;
import entities.User;
import entities.Worker;
import serviceLocator.ServiceLocator;
import services.UserRecommandationServiceEJBRemote;
import services.UserServicesEJBRemote;

public class UserServiceDelegate {
	private static final String JNDI = "/easyMission-ear/easyMission-ejb/UserServicesEJB!services.UserServicesEJBRemote";
	private static UserServicesEJBRemote getProxy(){
		return (UserServicesEJBRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}
	public static void doAddUser( User u1)throws NamingException {
		getProxy().addUser(u1);
	}
	public static void doAddEmployer(Employer u1)throws NamingException {
		getProxy().addEmployer(u1);
	}
	public static void doAddWorker(Worker u1)throws NamingException {
		getProxy().addWorker(u1);
	}
	public static void doUpdateWorker(Worker u1)throws NamingException {
		getProxy().updateWorker(u1);
	}
	public static void doUpdateEmployer(Employer u1)throws NamingException {
		getProxy().updateEmployer(u1);
	}
	public static void doUpdateUser( User u1)throws NamingException {
		getProxy().updateUser(u1);
	}
	public static User doFindUserById(int id){
		return getProxy().findUserById(id);
	}
	public static Employer doFindEmployerById(int id){
		return getProxy().findEmploerById(id);
	}
	public static Worker doFindWorkerById(int id){
		return getProxy().findWorkerById(id);
		
	}
	public static User doFindUserByName(String name){
		return getProxy().findUserByName(name);
		
	}
	public static User doFindUserByLogin(String Login){
		return getProxy().findUserByLogin(Login);
		
	}
	public static User doFindUserByLoginAndPassword(String login, String pwd){
		return getProxy().findUserBYLoginAndPassword(login, pwd);
		
	}
	public static List<Skill> dofindAllSkills(){
		return getProxy().findAllSkills();
	}
	public static List<Worker> doFindAllWorker(){
	return getProxy().findAllWorkers();	
	}
	public static Skill doFindSkillByName(String name) {
		return getProxy().findSkillByName(name);
	}
	
	
	
	
	
}
