package UserJava;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.omg.PortableInterceptor.USER_EXCEPTION;







public class CrudUser {

	public static void main(String[] args) throws NamingException {
		
	/*
		InitialContext ctx=new InitialContext();
		Object objet=ctx.lookup("/easyMission-ear/easyMission-ejb/UserServicesEJB!services.UserServicesEJBRemote");
		UserServicesEJBRemote proxy=(UserServicesEJBRemote)objet;
		/*User u=new User();
		Date d=new Date(2017,6,3);
		u.setBirthDate(d);
		u.setCountry("Tunisia");
		u.setEmail("malek.bejaoui@esprit.tn");
		u.setField("GL");
		u.setFirstName("Bejaoui");
		u.setLastName("Malek");
		u.setLogin("malek1");
		u.setPassword("123");
		u.setPicture("url");
		u.setState("Active");
		//proxy.addUser(u);
		//User u1=proxy.findUserByName("Bejaoui");
		User u1=proxy.findUserBYLoginAndPassword("malek1","123");
		System.out.println(u1.getFirstName());*/
	/*Worker u=new Worker();
		
		u.setBirthDate("12/12/2015");
		u.setCountry("Tunisia");
		u.setEmail("malek.bejaoui@esprit.tn");
		u.setField("GL");
		u.setFirstName("Bejaoui");
		u.setLastName("Malek");
		u.setLogin("malek1");
		u.setPassword("123");
		u.setPicture("url");
		u.setState("Active");
		u.setCv("url");
		u.setDescription("hello");
		u.setPhoneNumber("22432121");
		List<Skill> ls=new ArrayList<>();
		Skill s1=new Skill();
			s1.setName("JEE");
			
		Skill s2=new Skill();
			s2.setName("WEB");
			
		Skill s3=new Skill();
			s3.setName("C#");
		ls.add(s1);
		ls.add(s2);
		ls.add(s3);
		u.setSkills(ls);
		proxy.addWorker(u);
		
		*/
//		User u=proxy.findUserById(1);
//		System.out.println(u.getType());
//		if(u.getType().equals("worker")){
//			System.out.println("done");
//		}
//		List<Worker>lw=proxy.findAllWorkers();
//		for(Worker w : lw){
//			System.out.println(w.getFirstName());
//		}
		
		
//		List<Skill> lisSkills=proxy.findAllSkills();
//		for(Skill l:lisSkills){
//			System.out.println(l.toString());
//		}
		
//		
//		List<Skill> sklist=proxy.findAllSkills();
//		List<Worker>lw=proxy.findAllWorkers();
//		List<Skill>lskl=new ArrayList<>();
//		List<Worker>lworkers=null;
//		int userid=3;
//		
//		
////		for(Worker w : lw){
////			if(w.getIdUser()==userid){
////				lwork.add(proxy.findWorkerById(userid));
////			}
////		}
//		for(Skill s : sklist){
//			lworkers=s.getWorkers();
//			for(Worker w : lworkers){
//				if(w.getIdUser()==userid){
//					lskl.add(s);}}}
//		
//		for( Skill s : lskl){
//			System.out.println(s.getName());
//		}
		
		
		
		
		
	}

}
