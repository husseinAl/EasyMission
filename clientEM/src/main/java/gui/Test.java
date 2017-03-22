package gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.sql.Update;

import entities.Employer;
import entities.Mission;
import entities.Skill;
import entities.User;
import entities.Worker;
import services.ApplicationEJB;
import services.ApplicationEJBRemote;

public class Test {

	public static void main(String[] args) throws NamingException {
		
//		InitialContext ctx = new InitialContext();
//		Object obj =ctx.lookup("/easyMission-ear/easyMission-ejb/MissionEJB!services.MissionEJBRemote");
//		MissionEJBRemote proxy = (MissionEJBRemote) obj;
//		obj =ctx.lookup("/easyMission-ear/easyMission-ejb/SkillEJB!services.SkillEJBRemote");
//		SkillEJBRemote proxy1=(SkillEJBRemote) obj ;
//
//
//		Date d = new Date();
//		/*Mission m = new Mission("test", "desc", "field", 10, "state", d, d, null, "flex", true);
//		proxy.add(m);*/
//		Mission m = new Mission("test5", "description", "field", 10, "state", d, d, null, "flex", true);
//		proxy.add(m);
		
	/*	Skill s = new Skill();
		s.setName("Java SE");
		proxy1.add(s);
		
		s= proxy1.find(2);
		Mission m = proxy.find(1);
	
		m.getSkills().add(s);
		proxy.update(m);
		List<Mission> lm = new ArrayList<Mission>();
		lm=proxy.findAll();
		System.out.println(lm.size()+" "+lm.get(0).getSkills().size());
		
		
		*/
	/*	Employer e = new Employer("houssein", "bouguerra", "houssein.bouguerra@esprit.tn", null, "tunisia", "field", null, "houssein","tunisia", null);
		Date d = new Date();
		Mission m = new Mission("test5", "description", "field", 10, "state", d, d, null, "flex", true);*/
		String path="/easyMission-ear/easyMission-ejb/ApplicationEJB!services.ApplicationEJBRemote";
		InitialContext	ctx = new InitialContext();
		Object obj =ctx.lookup(path);
		ApplicationEJBRemote proxy = (ApplicationEJBRemote) obj;


	
//		Employer e = new Employer("houssein", "bouguerra", "houssein.bouguerra@esprit.tn", null, "tunisia", "IT", null, "houssein","tunisia", null);
//	  Worker w = new Worker("slim", "belhaj", "slim.belhaj@esprit.tn", null, "tunisia", "IT", null,"slim");
//		proxy.AddUser(e);
//		proxy.AddUser(w);


		Skill s=new Skill("JAVA SE");
		Skill s1=new Skill("JAVA EE");
		Skill s2=new Skill("C++");
		Skill s3=new Skill("SQL");
		Skill s4=new Skill("PHP5");
		Skill s5=new Skill("Data Base");
		Skill s6=new Skill("Web");
		proxy.addSkill(s);
		proxy.addSkill(s1);
		proxy.addSkill(s2);
		proxy.addSkill(s3);
		proxy.addSkill(s4);
		proxy.addSkill(s5);
		proxy.addSkill(s6);
//	ArrayList<Skill> skills = (ArrayList<Skill>) proxy.findallSkills();
//		
//		skills.remove(0);
//		skills.remove(1);
//		skills.remove(2);
//		skills.remove(3);
//		
//		Employer e1 = (Employer) proxy.findUser(1);
//		
//		String ch = "Focus is the entity of Focus group specialized in the IT Outsourcing and software development";
//		Date d = new Date();
//		Mission m = new Mission("BI Support", ch, "IT", 10, "onhold", d, d, null, "flextime", true);
//		m.setSkills(skills);
//		proxy.add(m, e1);
//		
//		
//		 m = new Mission("test", "Description", "IT", 10, "onhold", d, d, null, "flextime", true);
//		 skills=(ArrayList<Skill>) proxy.findallSkills();
//		skills.remove(0);
//		skills.remove(0);
//		skills.remove(0);
//		skills.remove(0);
//		m.setSkills(skills);
//		proxy.add(m, e1);
//
	
		
		
		
//		Employer e = (Employer) proxy.findUser(1);
//		List<Mission>lm = proxy.findAll();
//		Mission m = lm.get(0);
//		m.setEmployer(e);
//		proxy.update(m);
				
		
		
		
	} 

}
