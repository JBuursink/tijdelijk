package nl.hu.ipass.webservices;

import java.util.List;

import nl.hu.ipass.webapp.Idee;
import nl.hu.ipass.webapp.IdeePostgresDaoImpl;

public class IdeeService {
	IdeePostgresDaoImpl ipdi = new IdeePostgresDaoImpl();

	public boolean delete(Idee idee) {
		ipdi.delete(idee);
		return true;
	}

	public int findHighestID(int plan_id) {
		return ipdi.getHighestID(plan_id);
	}

	public boolean deleteAll(String planner_id) {
		ipdi.deleteAll(planner_id);
		return true;
	}

	public boolean save(Idee idee) {
		ipdi.save(idee);
		return true;
	}

	public List<Idee> findAll(String planner_id) {
		return ipdi.findAll(planner_id);
	}

	public boolean stemmen(int idee_id) {
		ipdi.stemIdee(idee_id);
		return true;
	}
}
