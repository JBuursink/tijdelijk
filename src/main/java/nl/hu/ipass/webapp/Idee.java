package nl.hu.ipass.webapp;

public class Idee {
	private int idee_ID;
	private int planner_ID;
	private int aantalStemmen;
	private String titel;
	private String tijdvereist;
	private double prijs;
	private String opmerking;

	public Idee(int id, int plan_id, int stemmen, String ttl, String tijdver, double prs, String opm) {
		this.idee_ID = id;
		this.planner_ID = plan_id;
		this.aantalStemmen = stemmen;
		this.titel = ttl;
		this.tijdvereist = tijdver;
		this.prijs = prs;
		this.opmerking = opm;

	}

	public int getIdee_ID() {
		return idee_ID;
	}

	public int getPlanner_ID() {
		return planner_ID;
	}

	public int getAantalStemmen() {
		return aantalStemmen;
	}

	public String getTitel() {
		return titel;
	}

	public String getTijdvereist() {
		return tijdvereist;
	}

	public double getPrijs() {
		return prijs;
	}

	public String getOpmerking() {
		return opmerking;
	}

}