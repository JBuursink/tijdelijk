package nl.hu.ipass.webapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IdeePostgresDaoImpl extends PostgresBaseDao implements IdeeDao {

	@Override
	public boolean delete(Idee idee) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeQuery("DELETE FROM public.idee WHERE idee_id = '" + idee.getIdee_ID() + " AND planner_id ='"
					+ idee.getPlanner_ID() + "';");
			return true;
		} catch (SQLException sqle) {
			System.out.println(sqle);
			return false;
		}
	}

	public int getHighestID(int plan_id) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("SELECT max(idee_id) as idee_id FROM public.idee;");
			while (dbResultSet.next()) {
				int hoogsteId = dbResultSet.getInt("idee_id");
				return hoogsteId;
			}
			return 0;
		} catch (SQLException sqle) {
			System.out.println(sqle);
			return 0;
		}
	}

	@Override
	public boolean deleteAll(String planner_id) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeQuery("DELETE FROM public.idee WHERE planner_id = '" + planner_id + "';");
			return true;
		} catch (SQLException sqle) {
			System.out.println(sqle);
			return false;
		}
	}

	@Override
	public boolean save(Idee idee) {
		try (Connection con = super.getConnection()) {
			PreparedStatement stmt = con.prepareStatement("insert into public.idee values(?,?,?,?,?,?,?);");
			stmt.setInt(1, idee.getIdee_ID());
			stmt.setInt(2, idee.getAantalStemmen());
			stmt.setString(3, idee.getTitel());
			stmt.setString(4, idee.getTijdvereist());
			stmt.setDouble(5, idee.getPrijs());
			stmt.setString(6, idee.getOpmerking());
			stmt.setInt(7, idee.getPlanner_ID());
			stmt.executeQuery();
			return true;
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return false;
	}

	@Override
	public List<Idee> findAll(String planner_id) {
		try (Connection con = super.getConnection()) {
			List<Idee> ideeLijst = new ArrayList<Idee>();
			Statement stmt = con.createStatement();
			String s = ("SELECT * from public.idee WHERE planner_id = '" + planner_id
					+ "' ORDER BY aantalstemmen DESC;");
			ResultSet dbResultSet = stmt.executeQuery(s);
			while (dbResultSet.next()) {
				int idee_ID = dbResultSet.getInt("idee_id");
				int planner_ID = dbResultSet.getInt("planner_id");
				int aantalStemmen = dbResultSet.getInt("aantalstemmen");
				String titel = dbResultSet.getString("titel");
				String tijdvereist = dbResultSet.getString("tijdvereist");
				double prijs = dbResultSet.getDouble("prijs");
				String opmerking = dbResultSet.getString("opmerking");
				Idee i1 = new Idee(idee_ID, planner_ID, aantalStemmen, titel, tijdvereist, prijs, opmerking);
				ideeLijst.add(i1);
			}
			return ideeLijst;
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return null;
	}

	public void stemIdee(int idee_id) {
		try (Connection con = super.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(
					"update public.idee set aantalstemmen = aantalstemmen + 1 where idee_id = '" + idee_id + "'");
			stmt.executeQuery();
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}

	}

}
