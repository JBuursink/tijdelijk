package nl.hu.ipass.webservices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.ipass.webapp.Planner;

@Path("/planners")
public class PlannerRecource {

	@POST
	@Produces("application/json")
	public Response nieuwePlanner(@FormParam("titelCadeau") String titel, @FormParam("redenCadeau") String reden,
			@FormParam("deadlineCadeau") String deadline) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			Date deadlinePlanner = format.parse(deadline);
			PlannerService service = ServiceProvider.getPlannerService();
			int plan_ID = service.findHighestID() + 1;
			Date begindatum = new Date();
			Planner p1 = new Planner(plan_ID, titel, reden, begindatum, deadlinePlanner);
			return Response.ok(service.save(p1)).build();
		} catch (Exception E) {
			System.out.println(E);
			return null;
		}
	}

	@GET
	@Produces("application/json")
	public String allePlanners() {
		PlannerService service = ServiceProvider.getPlannerService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (Planner p : service.getAllePlanners()) {
			JsonObjectBuilder con = Json.createObjectBuilder();
			con.add("planner_id", p.getPlannerID());
			con.add("titel", p.getTitel());
			con.add("beschrijving", p.getBeschrijving());
			con.add("einddatum", df.format(p.getEindDatum()));
			jab.add(con);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@GET
	@Path("{planner_id}")
	@Produces("application/json")
	public String plannerId(@PathParam("planner_id") String planner_id) {
		PlannerService service = ServiceProvider.getPlannerService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Planner p = service.getPlannerById(planner_id);
		JsonObjectBuilder con = Json.createObjectBuilder();
		con.add("planner_id", p.getPlannerID());
		con.add("titel", p.getTitel());
		con.add("beschrijving", p.getBeschrijving());
		con.add("einddatum", df.format(p.getEindDatum()));
		jab.add(con);
		JsonArray array = jab.build();
		return array.toString();
	}

	@DELETE
	@Path("{planner_id}")
	@Produces("application/json")
	public Response verwijerPlanner(@PathParam("planner_id") int planner_id) {
		PlannerService service = ServiceProvider.getPlannerService();
		if (!service.delete(planner_id)) {
			return Response.status(404).build();
		}

		return Response.ok().build();
	}

}
