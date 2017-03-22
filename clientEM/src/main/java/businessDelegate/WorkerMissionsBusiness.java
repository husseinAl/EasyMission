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

public class WorkerMissionsBusiness {
	
	private static String jndi = "/easyMission-ear/easyMission-ejb/WorkerMissionEJB!services.WorkerMissionEJBRemote";
	private Worker worker ;
	public WorkerMissionsBusiness(int id) {
		try {
			loadWorker(id);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private static WorkerMissionEJBRemote getProxy() {
		return (WorkerMissionEJBRemote) ServiceLocator.getInstance().getProxy(jndi);
	}
	
	public void loadWorker(int id) throws NamingException {
		
		worker = (Worker) getProxy().findWorker(id);
		System.out.println(worker.getIdUser());
	}
	
	public List<Mission>getApplications()
	{
		List<Mission> lm=new ArrayList<>();
		System.out.println(worker.getApplications().size());
		for(Application app :worker.getApplications())
		{
			Mission m = app.getMission();
			if (m.getState().equals("onhold"))
				lm.add(m);
			
		}
		return lm ;
		
	}
	public List<Mission>getAssignments()
	{
		List<Mission> lm=new ArrayList<>();
		for(Mission m :worker.getMissions())
		{
			if(m.getState().equals("active"))
				lm.add(m);
		}
		return lm ;
	}
	public List<Mission>getHistory()
	{
		List<Mission> lm=new ArrayList<>();
		for(Mission m :worker.getMissions())
		{
			if(m.getState().equals("done"))
				lm.add(m);
		}
		return lm ;
	}
	

}
