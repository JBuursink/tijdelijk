package nl.hu.ipass.webapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PlannerPostgresDaoImpl extends PostgresBaseDao implements PlannerDao {

	public boolean save(Planner planner) {
		try (Connection con = super.getConnection()) {
			PreparedStatement stmt = con.prepareStatement("insert into public.planner values('" + planner.getPlannerID()
					+ "',?,?,'" + planner.getBeginDatum() + "','" + planner.getEindDatum() + "')");
			stmt.setString(1, planner.getTitel());
			stmt.setString(2, planner.getBeschrijving());
			stmt.executeQuery();
			return true;
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return false;
	}

	public List<Planner> findByUser(String accID) {
		// -----------------EVENTUELE-TOEVOEGING--------------
		// try (Connection con = super.getConnection()) {
		// Statement stmt = con.createStatement();
		// String s = ("INSERT INTO public.planner VALUES ('" + planner.getPlannerID() +
		// "');");
		// stmt.executeUpdate(s);
		// } catch (SQLException sqle) {
		// System.out.println(sqle);
		// }
		// ---------------------------------------------------
		return null;
	}

	public boolean update(Planner planner) {
		try (Connection con = super.getConnection()) {
			PreparedStatement stmt = con
					.prepareStatement("UPDATE public.planner SET titel = ?, beschrijving = ?, begindatum = '"
							+ planner.getBeginDatum() + "', einddatum = '" + planner.getEindDatum()
							+ "' WHERE planner_id = '" + planner.getPlannerID() + "';");
			stmt.setString(1, planner.getTitel());
			stmt.setString(2, planner.getBeschrijving());
			stmt.executeQuery();
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return false;
	}

	public boolean delete(Planner planner) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			// String s = ("INSERT INTO public.planner VALUES ('" + planner.getPlannerID() +
			// "');");
			// stmt.executeUpdate(s);
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return false;
	}

}