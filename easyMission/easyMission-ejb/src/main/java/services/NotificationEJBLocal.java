package services;

import java.util.List;

import javax.ejb.Local;

import entities.Notification;

@Local
public interface NotificationEJBLocal {
	  public void notif(int id ,String text);
	  public List<Notification> getnotif(int id);

}
