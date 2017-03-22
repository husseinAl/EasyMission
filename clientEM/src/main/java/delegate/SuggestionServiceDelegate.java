package delegate;

import java.util.List;

import javax.naming.NamingException;

import serviceLocator.ServiceLocator;
import services.SuggestionCRUDRemote;

public class SuggestionServiceDelegate {
	private static final String JNDI = "/easyMission-ear/easyMission-ejb/SuggestionCRUD!services.SuggestionCRUDRemote";

	private static SuggestionCRUDRemote getProxy() {
		return (SuggestionCRUDRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}
}