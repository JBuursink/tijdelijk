package nl.hu.ipass.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import nl.hu.ipass.webapp.Idee;

@Path("/idee")
public class IdeeRecourse {

	@POST
	@Produces("application/json")
	public Response nieuwIdee(@FormParam("id") int plan_id, @FormParam("titel") String titel,
			@FormParam("tijd") String tijd, @FormParam("prijs") double prijs,
			@FormParam("opmerking") String opmerking) {
		try {
			IdeeService service = ServiceProvider.getIdeeService();
			int idee_id = service.findHighestID(plan_id) + 1;
			Idee i1 = new Idee(idee_id, plan_id, 0, titel, tijd, prijs, opmerking);
			return Response.ok(service.save(i1)).build();
		} catch (Exception E) {
			System.out.println(E);
			return null;
		}
	}

	@PUT
	@Path("{idee_id}")
	@Produces("application/json")
	public Response updateCustomer(@PathParam("idee_id") int idee_id) {
		try {
			System.out.println(idee_id);
			IdeeService service = ServiceProvider.getIdeeService();
			return Response.ok(service.stemmen(idee_id)).build();
		} catch (Exception E) {
			return null;
		}

	}

	@GET
	@Path("/{planner_id}")
	@Produces("application/json")
	public String allePlanners(@PathParam("planner_id") String planner_id) {
		IdeeService service = ServiceProvider.getIdeeService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Idee i : service.findAll(planner_id)) {
			JsonObjectBuilder con = Json.createObjectBuilder();
			con.add("idee_id", i.getIdee_ID());
			con.add("planner_id", i.getPlanner_ID());
			con.add("aantalstemmen", i.getAantalStemmen());
			con.add("titel", i.getTitel());
			con.add("tijdvereist", i.getTijdvereist());
			con.add("prijs", i.getPrijs());
			con.add("opmerking", i.getOpmerking());
			jab.add(con);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@DELETE
	@Path("{planner_id}")
	@Produces("application/json")
	public Response verwijerPlanner(@PathParam("planner_id") String planner_id) {
		IdeeService service = ServiceProvider.getIdeeService();
		if (!service.deleteAll(planner_id)) {
			return Response.status(404).build();
		}

		return Response.ok().build();
	}
	//
	// @DELETE
	// @Path("{planner_id}")
	// @Produces("application/json")
	// public Response verwijerPlanner(@PathParam("planner_id") int planner_id) {
	// PlannerService service = ServiceProvider.getPlannerService();
	// if (!service.delete(planner_id)) {
	// return Response.status(404).build();
	// }
	//
	// return Response.ok().build();
	// }
}
