package nl.hu.ipass.webapp;

import java.util.Date;

public class Planner {
	private int planner_ID;
	private String titel;
	private String beschrijving;
	private Date begindatum;
	private Date einddatum;

	public Planner(int plan_ID, String ttl, String beschr, Date begin, Date eind) {
		this.planner_ID = plan_ID;
		this.titel = ttl;
		this.beschrijving = beschr;
		this.begindatum = begin;
		this.einddatum = eind;

	}

	public int getPlannerID() {
		return planner_ID;
	}

	public String getTitel() {
		return titel;
	}

	public String getBeschrijving() {
		return beschrijving;
	}

	public Date getBeginDatum() {
		return begindatum;
	}

	public Date getEindDatum() {
		return einddatum;
	}
}
