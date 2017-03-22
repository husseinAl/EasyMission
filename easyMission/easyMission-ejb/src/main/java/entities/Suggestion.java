package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

import embadableIDs.SuggestionId;

/**
 * Entity implementation class for Entity: Suggestion
 *
 */
@Entity

public class Suggestion implements Serializable {

	@EmbeddedId
	private SuggestionId idSuggestion = new SuggestionId();

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "idWorkerPK", insertable = false, updatable = false)
	private Worker worker;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "idEmployerPK", insertable = false, updatable = false)
	private Employer employer;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	private Mission mission;

	private String text;
	private Date date;

	private static final long serialVersionUID = 1L;

	public Suggestion() {
		super();
	}

	public Suggestion(User u1, User u2, Mission mission, String text) {
		super();
		Calendar c = Calendar.getInstance();
		// this.worker = worker;
		// this.employer = employer;
		this.idSuggestion.setIdEmployerPK(u2.getIdUser());
		this.idSuggestion.setIdWorkerPK(u1.getIdUser());
		this.mission = mission;
		this.text = text;
		java.util.Date date = c.getTime();
		this.date = date;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public SuggestionId getIdSuggestion() {
		return idSuggestion;
	}

	public void setIdSuggestion(SuggestionId idSuggestion) {
		this.idSuggestion = idSuggestion;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

}
