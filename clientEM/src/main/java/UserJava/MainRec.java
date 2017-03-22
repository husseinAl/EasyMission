package UserJava;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Skill;
import entities.User;
import entities.Worker;
import services.UserRecommandationServiceEJBRemote;
import services.UserServicesEJBRemote;





public class MainRec {
	public static boolean isEmail(String correo) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\\\\\+]+(\\.[\\w\\\\]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }
public static void main(String[] args) throws NamingException {

	
	
		InitialContext ctx1=new InitialContext();
		InitialContext ctx2=new InitialContext();
		Object objet2=ctx2.lookup("/easyMission-ear/easyMission-ejb/UserRecommandationServiceEJB!services.UserRecommandationServiceEJBRemote");
		Object objet1=ctx1.lookup("/easyMission-ear/easyMission-ejb/UserServicesEJB!services.UserServicesEJBRemote");
		UserRecommandationServiceEJBRemote proxy=(UserRecommandationServiceEJBRemote)objet2;
		UserServicesEJBRemote proxy1=(UserServicesEJBRemote)objet1;
		
		
		
		/*User u1=proxy1.findUserById(4);
		User u2=proxy1.findUserById(3);
		//proxy.addUserRecommandation(u1, u2, "nhhh");
		
		//proxy.addUserRecommandation(7,8,"nas tayba");
		
		//System.out.println("done");
	
		List<Recommendation>lrr=new ArrayList<>();
		List<Recommendation>lr=proxy.findAllRecommandation();
		for(Recommendation r : lr){
			if(r.getRecommended().getIdUser()==1){
				//System.out.println("test");
				lrr.add(r);
			}}
		
	*/
Worker emp=(Worker) proxy1.findUserById(2);
		ArrayList<Skill>skill=(ArrayList<Skill>)proxy1.findAllSkills();
		ArrayList<Skill>skk=new ArrayList<>();
		List<Skill>userSkill=emp.getSkills();
		ArrayList<Skill>nsk=new ArrayList<>();
		for(Skill s: skill){
			skk.add(s);
			for( Skill s1 : userSkill){
				if(s.getName().equals(s1.getName())){
					skk.remove(s);
				}
			}
			
		}
		
		for(Skill s1 : skk){
			System.out.println(s1.getName());
		}
		
		
	}
}
