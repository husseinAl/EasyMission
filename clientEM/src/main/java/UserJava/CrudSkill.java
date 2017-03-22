package UserJava;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Skill;
import entities.Worker;
import frames.frame1Controller;
import services.UserServicesEJBRemote;

public class CrudSkill {

	public static void main(String[] args) throws NamingException {
		InitialContext ctx1=new InitialContext();
    	Object objet1=ctx1.lookup("/easyMission-ear/easyMission-ejb/UserServicesEJB!services.UserServicesEJBRemote");
    	UserServicesEJBRemote proxy1=(UserServicesEJBRemote)objet1;
    	Worker emp=proxy1.findWorkerById(1);
    	//Skill s=tabskill.getSelectionModel().getSelectedItem();
    	List<Skill>ls=emp.getSkills();
//    	for(Skill s1 : ls){
//    		if(s1.getName().equals("c")){
//    			ls.remove(s1);
//    		}
//    	}
    	Skill s=proxy1.findSkillByName("c");
    	List<Skill>l=new ArrayList<>();
    	for(Skill s1 : ls){
    		l.add(s1);
    		if(s1.getName().equals(s.getName())){
    			l.remove(s1);
    		}
    	}
    	System.out.println("final :"+l.size());
    	
    	
    	//emp.setSkills(ls);
    	//proxy1.updateWorker(emp);

	}

}
