package nl.hu.ipass.webapp;

import java.util.List;

public interface PlannerDao {
	public boolean save(Planner planner);

	public List<Planner> findByUser(String accID);

	public boolean update(Planner planner);

	public boolean delete(Planner planner);

}
