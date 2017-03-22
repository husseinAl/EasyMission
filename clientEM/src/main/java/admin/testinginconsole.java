package admin;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import embadableIDs.RepportId;
import entities.Employer;
import entities.Mission;
import entities.Repport;
import entities.Skill;
import entities.User;
import entities.Worker;
import javafx.scene.control.Alert;
import services.AdminserviceEJBRemote;

public class testinginconsole {

	public testinginconsole() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws NamingException {
		InitialContext	ic = new InitialContext();
		
		AdminserviceEJBRemote proxy = (AdminserviceEJBRemote) ic.lookup("/easyMission-ear/easyMission-ejb"
				+ "/AdminserviceEJB!services.AdminserviceEJBRemote");
		/* User u= proxy.finduserbyid(2);
		 Worker w = (Worker)u;
				List<Skill> ls = w.getSkills();
				for (Skill s : ls)
				{
					System.out.println("name"+s.getName());
				}
			}
			*/
		//User u = proxy.finduserbyid(5);
		//proxy.blockuser(u);
		//Employer e = (Employer) u;
		//Employer e = new Employer();
		//Mission m1 = new Mission("test","test","parttime",500,"php","on hold");
	    //proxy.addnewmission(m1);
		//proxy.addMission(m, e);
				
		
		//Worker w = new Worker ();
		//Employer e = new Employer();
				//proxy.addWorker(w);;
		/*Worker w = new Worker();
	proxy.addWorker(w);*/
		
		/*List<Worker> listworkers = proxy.displayallworkers();
		for (Worker w : listworkers)
		{
			System.out.println(w.getFirstName());
			System.out.println(w.getCv());
		}*/
		
		//List <User> listusers = proxy.displayallusers();
		
		/*for (User u : listusers)
		{
			if (u instanceof Worker)
			{
				System.out.println(((Worker) u).getCv());
			}
			
		}*/
		/*List<User> lu = proxy.displayallworkers();
		for (User u : lu)
		{
			
			
				System.out.println(u.getFirstName());
		}*/
		
	
	/*List<Mission>  elist = proxy.displayallmissions();
	for (Mission m : elist)
	{
		System.out.println(m.getEmployer().getFirstName());
	}*/
		
		User u = proxy.finduserbyid(5);
		//Worker w = (Worker)u;
		
		
		Employer e = (Employer)u;
		//Mission m =proxy.findmissionbyId(6);
		
		
		//proxy.repport(u, m);
		//List<Repport> listreports = proxy.displayholdingReclmations();
		//List<Repport> r = proxy.findRepportidmission(6);
		//System.out.println(r.size()+" -----"+r.get(0).getText());
		
	
		
		/*for (Repport r: listreports)
		{
			System.out.println("object of reclamation : "+r.getObject());
			System.out.println("reporteur "+r.getUser().getFirstName()+" "+r.getUser().getLastName());
			System.out.println("id mission to be reported"+r.getIdRepport().getIdMisssionPK());
		}*/
		
		/*List<Repport> listreports = proxy.displayinprogresstraitmentReclmations();
		for (Repport r: listreports)
		{
			System.out.println("object of reclamation : "+r.getObject());
		}*/
//int x = 0 ;int y=0; int z=0 ; int t=0;
		/*List<Worker> lw = proxy.displayallworkers();
		for (Worker w : lw )
		{
			List<Skill> ls = w.getSkills();
			 for (Skill s : ls)
				 if (s.getIdSkill()==3)
				 {
					 x=x+1;
				 }
				 else if (s.getIdSkill()==2)
				 {
					 y=y+1;
				 }
				 else if (s.getIdSkill()==1)
				 {
					 z=z+1;
				 }
				 else
					 t=t+1;
		}
		
		System.out.println("x "+x);
		System.out.println("y "+y);
		System.out.println("z "+z);
		System.out.println("t "+t);*/
		//List <Repport> lr = proxy.displayholdingReclmations();
		//RepportId ri = new RepportId(5, 6);
		/*List<Repport> lr = proxy.displayallpReclmations();
		for (Repport r : lr )
		{
			System.out.println("here"+r.getIdmissionreport());
		}*/
		//RepportId asba = new RepportId(5,6);
		//Repport zab = proxy.findRepportById(asba);
		//System.out.println(zab.getObject());
		/*List<Mission> lm = proxy.displayallmissions();
		for (Mission m : lm )
		{
			System.out.println(m.getDescription());
		}*/
		//Mission m = new Mission(e,"here","here");
		//proxy.addnewmission(m);
		//Mission m = proxy.findmissionbyId(5);
		//Repport r = new Repport("hello", "hello",0, u , m);
		//proxy.addReclamation(r);
		RepportId ri = new RepportId(5, 5);
		Repport r = proxy.findRepportById(ri);
		proxy.declinereclamation(r);
		
		
		
	}
}

