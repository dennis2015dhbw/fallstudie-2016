package version1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Tabellen.Projekt;



public class schnittstelle {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs;

	public schnittstelle() {
		try // Treiber für mySQL laden
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Treiber gefunden\n--------------------------------");
		} // try
		catch (ClassNotFoundException e) {
			System.out.println("Treiber NICHT gefunden\n--------------------------------");
		} // catch

		try // Connection zur Datenbank aufbauen
		{
			con = DriverManager.getConnection("jdbc:mysql://lolstats.org:3306/fallstudie_2016", "fallstudie_user2", "passwort123!");
			System.out.println("Connection zur Datenbank aufgebaut\n--------------------------------");
		} // try
		catch (SQLException e) {
			System.out.println("Connection zur Datenbank NICHT aufgebaut\n--------------------------------");
		} // catch

		try // Statement erzeugen
		{
			stmt = con.createStatement();
			System.out.println("Statement erzeugt\n--------------------------------");
		} // try
		catch (SQLException e) {
			System.out.println("Statement NICHT erzeugt\n--------------------------------");
		} // catch
	}

	public List<Projekt> projekte_laden() throws SQLException {	
		
		List<Projekt> projekte = new ArrayList<Projekt>();
		
		try {
			// List l1 = new LinkedList();
			rs = stmt.executeQuery("select * from Projekt");
			
			while (rs.next()){
				projekte.add(new Projekt(rs.getInt("ProjektID"), rs.getString("Name")));				
			}
			
						
		} catch (SQLException e) {
			System.out.println("Fehler: " + e);
		}
		

		
		
		return projekte;

	}

}
