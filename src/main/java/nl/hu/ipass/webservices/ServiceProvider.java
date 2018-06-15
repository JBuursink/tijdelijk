package nl.hu.ipass.webservices;

public class ServiceProvider {
	private static PlannerService plannerService = new PlannerService();

	public static PlannerService getPlannerService() {
		return plannerService;
	}
}