package delegate;

import java.util.List;

import javax.naming.NamingException;

import entities.Recommendation;
import entities.User;
import serviceLocator.ServiceLocator;
import services.UserRecommandationServiceEJBRemote;

public class RecommadationServiceDelegate {
	private static final String JNDI = "/easyMission-ear/easyMission-ejb/UserRecommandationServiceEJB!services.UserRecommandationServiceEJBRemote";
	
	private static UserRecommandationServiceEJBRemote getProxy(){
		return (UserRecommandationServiceEJBRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}
	public static void doAddRecommandation( User u1,User u2,String text)throws NamingException {
		getProxy().addUserRecommandation(u1, u2, text);
	}
	public static Recommendation doFindRecommandationByText(String text) {
		return getProxy().FindRecommandationBTextAndRecommander(text);
		
	}
	public static List<Recommendation> doFindAllRecommandation() {
		return getProxy().findAllRecommandation();
	}
	public static void dochangeState(Recommendation r){
		getProxy().changeState(r);
	}


}
