package nl.hu.ipass.webapp;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		PostgresBaseDao PBD = new PostgresBaseDao();
		PlannerPostgresDaoImpl nana = new PlannerPostgresDaoImpl();
		Date begin = new Date();
		// Planner test = new Planner(3131, "jhnkjhkjtest", "dijjjjtiseen tasad", begin,
		// begin);
		// nana.save(test);
		Planner test2 = new Planner(3131, "jsxczcxest", "dipo786239876opoasad", begin, begin);
		nana.update(test2);
	}
}
