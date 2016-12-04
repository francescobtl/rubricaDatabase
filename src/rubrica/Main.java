package rubrica;
import java.sql.*;

import rubrica.grafica.Grafica;

public class Main {

	public static void main(String[] args) {

		//new Grafica();


		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "rubrica";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "francesco"; 
		String password = "Fp-11392";
		
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			Class.forName(driver).newInstance();
			//Connection conn = DriverManager.getConnection(url+dbName,userName,password);


			String connectionURL = "jdbc:mysql://localhost:3306/rubrica?autoReconnect=true&useSSL=false";

			Connection con = DriverManager.getConnection(connectionURL, userName, password); 

			//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?");
			stmt = con.createStatement();
		    rs = stmt.executeQuery("SELECT * FROM utenti");

		    // or alternatively, if you don't know ahead of time that
		    // the query will be a SELECT...

		    if (stmt.execute("SELECT * FROM utenti")) {
		        rs = stmt.getResultSet();
		        
		        while (rs.next()){
		        	System.out.println(rs.getString("Username")+rs.getString("Password"));
		        }
		    }

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
		
		
		
		
	}







}
