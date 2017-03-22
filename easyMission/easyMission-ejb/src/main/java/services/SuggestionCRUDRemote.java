package services;

import javax.ejb.Remote;

import entities.Mission;
import entities.User;

@Remote
public interface SuggestionCRUDRemote {

	void addSuggestion(User u1, User u2, Mission m, String text);
}
