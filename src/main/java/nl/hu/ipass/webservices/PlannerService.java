package nl.hu.ipass.webservices;

import java.util.List;

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

	public Planner getPlannerById(String id) {
		return ppdi.findById(id);

	}

	public List<Planner> getAllePlanners() {
		return ppdi.findAll();

	}

	// public Planner getPlannerById(int id) {
	// return ppdi.findByPlannerId(id);
	// }

	public boolean delete(int id) {
		return ppdi.verwijder(id);
	}

}