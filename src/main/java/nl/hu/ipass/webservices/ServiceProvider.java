package nl.hu.ipass.webservices;

public class ServiceProvider {
	private static PlannerService plannerService = new PlannerService();
	private static IdeeService ideeService = new IdeeService();

	public static PlannerService getPlannerService() {
		return plannerService;
	}

	public static IdeeService getIdeeService() {
		return ideeService;
	}
}
