package businessDelegate;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Application;
import entities.Mission;
import entities.Worker;
import serviceLocator.ServiceLocator;
import services.ApplicationEJBRemote;
import services.WorkerMissionEJBRemote;

public class AssignmentDelegate {
	private Mission m=new Mission() ;
	private static String jndi = "/easyMission-ear/easyMission-ejb/WorkerMissionEJB!services.WorkerMissionEJBRemote";
	

	private static WorkerMissionEJBRemote getProxy() {
		return (WorkerMissionEJBRemote) ServiceLocator.getInstance().getProxy(jndi);
	}
	
	public AssignmentDelegate(int id) {
	
		try {
			loadMission(id);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	public List<Application> getApplications()
	{
		List<Application> la=new ArrayList<Application>();
					
			for(Application app :m.getApplications())
			{
				if (app.getMission().getState().equals("onhold"))
					la.add(app);
			}
		
			return la;
		
	}



	public void loadMission(int id) throws NamingException {
		InitialContext ctx = new InitialContext();
		
		m =getProxy().findMission(id);
	
	}
	
	public boolean assign(Application app) throws NamingException
	{
		InitialContext ctx = new InitialContext();
		
		System.out.println(app.getWorker().getIdUser()+" "+app.getMission().getIdMission());
	  if( getProxy().assign(app))		 
		  return true;
		  
	  return false;
	}
}
