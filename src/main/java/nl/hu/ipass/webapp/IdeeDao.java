package nl.hu.ipass.webapp;

import java.util.List;

public interface IdeeDao {

	public boolean delete(Idee idee);

	public boolean deleteAll(String planner_id);

	public boolean save(Idee idee);

	public List<Idee> findAll(String planner_id);
}
