package services;

import javax.ejb.Remote;

import entities.Application;
import entities.Employer;
import entities.Mission;
import entities.Worker;

@Remote
public interface WorkerMissionEJBRemote {
	public Worker findWorker(int id);	
	public Mission findMission(int id);
	public boolean assign(Application app);
	

}
