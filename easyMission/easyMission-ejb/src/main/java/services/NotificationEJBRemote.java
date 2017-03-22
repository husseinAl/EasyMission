package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Application;
import entities.Employer;
import entities.Mission;
import entities.Notification;
import entities.Worker;

@Remote
public interface NotificationEJBRemote {
	 public List<Notification> getnotif(int id);
	

}
