package nl.hu.ipass.webservices;

import nl.hu.ipass.webapp.Planner;
import nl.hu.ipass.webapp.PlannerPostgresDaoImpl;

public class PlannerService {
	PlannerPostgresDaoImpl ppdi = new PlannerPostgresDaoImpl();

	public boolean delete(Planner planner) {
		ppdi.delete(planner);
		return true;
	}

	public int findHighestID() {
		return ppdi.getHighestID();
	}

	public boolean update(Planner planner) {
		ppdi.update(planner);
		return true;
	}

	public boolean save(Planner planner) {
		ppdi.save(planner);
		return true;

	}
	//
	// public Planner save(int i, String string, String string2, Date begindatum,
	// Date begindatum2) {
	// Planner p1 = new Planner(i, string, string2, begindatum, begindatum2);
	// ppdi.save(p1);
	// return p1;
	// }

}