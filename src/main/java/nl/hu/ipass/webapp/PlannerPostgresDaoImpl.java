package nl.hu.ipass.webapp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	public int getHighestID() {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("SELECT max(planner_id) as planner_id FROM public.planner;");
			while (dbResultSet.next()) {
				int hoogsteId = dbResultSet.getInt("planner_id");
				return hoogsteId;
			}
			return 0;
		} catch (SQLException sqle) {
			System.out.println(sqle);
			return 0;
		}
	}

	public List<Planner> findByUser(String accID) {
		// -----------------EVENTUELE-TOEVOEGING--------------
		return null;
	}

	public Planner findById(String id) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery("SELECT * FROM public.planner WHERE planner_id ='" + id + "';");
			while (dbResultSet.next()) {
				Planner p1 = new Planner(dbResultSet.getInt("planner_id"), dbResultSet.getString("titel"),
						dbResultSet.getString("beschrijving"), dbResultSet.getDate("begindatum"),
						dbResultSet.getDate("einddatum"));
				return p1;
			}
			return null;
		} catch (SQLException sqle) {
			return null;
		}
	}

	public boolean verwijder(int ID) {
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeQuery("DELETE FROM public.planner WHERE planner_id = '" + ID + "';");
			return true;
		} catch (SQLException sqle) {
			System.out.println(sqle);
			return false;
		}
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


	@Override
	public List<Planner> findAll() {
		try (Connection con = super.getConnection()) {
			List<Planner> allePlanners = new ArrayList<Planner>();
			Statement stmt = con.createStatement();
			String s = ("SELECT * from public.planner;");
			ResultSet dbResultSet = stmt.executeQuery(s);
			while (dbResultSet.next()) {
				int id = dbResultSet.getInt("planner_id");
				String titel = dbResultSet.getString("titel");
				String beschrijving = dbResultSet.getString("beschrijving");
				Date begindatum = dbResultSet.getDate("begindatum");
				Date einddatum = dbResultSet.getDate("einddatum");
				Planner p1 = new Planner(id, titel, beschrijving, begindatum, einddatum);
				allePlanners.add(p1);
			}
			return allePlanners;
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return null;
	}

	@Override
	public boolean delete(Planner planner) {
		// TODO Auto-generated method stub
		return false;
	}

}
