package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Notification;
import frames.frame1Controller;
import gui.LookForMissionController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import serviceLocator.ServiceLocator;
import services.ApplicationEJBRemote;
import services.NotificationEJBRemote;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class NotificationThread implements Runnable {
	public static int id = -1;
	private String command;
	private boolean flag;
	private TrayNotification tray;// =new TrayNotification();
	public List<Notification> ln;
	private static String jndi = "/easyMission-ear/easyMission-ejb/NotificationEJB!services.NotificationEJBRemote";

	public void stop() {
		this.flag = false;
	}

	private static NotificationEJBRemote getProxy() {
		return (NotificationEJBRemote) ServiceLocator.getInstance().getProxy(jndi);
	}

	public NotificationThread(String command) {
		super();
		this.command = command;
		this.flag = true;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Start. Command = " + command);
		boolean ok =true ;
		while (flag) {
			processCommand();
			id = frame1Controller.id;
			if (id > 0) {
				if (ok){
					System.out.println("User is Authentificated, his id is: " + id);
					ok=false ;
				}
			
				ln = new ArrayList<>();
				ln = getProxy().getnotif(id);
				System.out.println("nbr des notifications =" + ln.size());
				//--------------Creation de notification via troy api---------------
				Platform.runLater(new Runnable() {
					@Override
					public void run() {

						for (Notification n : ln) {
							tray = new TrayNotification();
							creatingANewTrayNotification(n.getDate().toString(), n.getText());
						}
					}
					public void creatingANewTrayNotification(String title, String message) {

						tray.setTitle(title);
						tray.setMessage(message);
						tray.setNotificationType(NotificationType.SUCCESS);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndWait();// wAndDismiss(Duration.seconds(5));
					}
				});
				//-----------------------------------------------------------
			}
		}
		System.out.println(Thread.currentThread().getName() + " End.");

	}

	private void processCommand() {

		try {
			Thread.sleep(5000);
			System.out.println("thread is running, the id of the current user is :" + id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return this.command;
	}

}
