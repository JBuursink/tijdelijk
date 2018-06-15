package nl.hu.ipass.webapp;

import java.util.Date;

import nl.hu.ipass.webservices.PlannerService;
import nl.hu.ipass.webservices.ServiceProvider;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		PostgresBaseDao PBD = new PostgresBaseDao();
		PlannerPostgresDaoImpl nana = new PlannerPostgresDaoImpl();
		Date begin = new Date();
		PlannerService service = ServiceProvider.getPlannerService();
		Planner test = new Planner(3131, "jhnkjhkjtest", "dijjjjtiseen tasad", begin, begin);
		service.save(test);
		// nana.save(test);
		// Planner test2 = new Planner(31231, "jsxczcxest", "dipo786239876opoasad",
		// begin, begin);
		// nana.update(test2);
	}
}
