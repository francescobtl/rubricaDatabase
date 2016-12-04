package rubrica.logica;
import java.sql.*;
import java.util.ArrayList;


public class GestoreDB {

	private Connection con=null;


	public GestoreDB(){

		String userName = "francesco"; 
		String password = "Fp-11392";
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String connectionURL = "jdbc:mysql://localhost:3306/rubrica?autoReconnect=true&useSSL=false";
		try {
			this.con = DriverManager.getConnection(connectionURL, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 



	}

	public ArrayList<Utente> caricaUtenti (){
		ArrayList<Utente> utenti = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM utenti");
			rs = stmt.getResultSet();
			while (rs.next()){
				Utente u = new Utente();
				u.setUsername(rs.getString("Username"));
				u.setPassword(rs.getString("Password"));
				utenti.add(u);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utenti;


	}
	
	
	public ArrayList<Persona> caricaPersone (){
		ArrayList<Persona> ppl = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM persone");
			rs = stmt.getResultSet();
			while (rs.next()){
				Persona u = new Persona();
				u.setCodFiscale(rs.getString("cf"));
				u.setNome(rs.getString("nome"));
				u.setCognome(rs.getString("cognome"));
				u.setIndirizzo(rs.getString("indirizzo"));
				u.setTelefono(rs.getString("telefono"));
				u.setEta(Integer.parseInt(rs.getString("eta")));
				ppl.add(u);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ppl;


	}
	
	
	public void elimina (String cf){
		
		try {
			String insertTableSQL = "DELETE FROM persone WHERE cf=?";
			PreparedStatement preparedStatement = this.con.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, cf);
			preparedStatement.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void modifica (Persona p){
		
		try {
			System.out.println(p.getCodFiscale());
			String insertTableSQL = "UPDATE persone SET nome = ?, cognome = ?, indirizzo=?, telefono=?, eta=? WHERE cf=?";
			PreparedStatement preparedStatement = this.con.prepareStatement(insertTableSQL);
			preparedStatement.setInt(5, p.getEta());
			preparedStatement.setString(6, p.getCodFiscale());
			preparedStatement.setString(1, p.getNome());
			preparedStatement.setString(2, p.getCognome());
			preparedStatement.setString(3, p.getIndirizzo());
			preparedStatement.setString(4, p.getTelefono());
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	
	public void addVoce (Persona p){

		try {
			System.out.println(p.getCodFiscale());
			String insertTableSQL = "INSERT INTO persone"
					+ "(cf, nome, cognome, indirizzo, telefono, eta) VALUES"
					+ "(?,?,?,?,?,?)";
			PreparedStatement preparedStatement = this.con.prepareStatement(insertTableSQL);
			preparedStatement.setInt(6, p.getEta());
			preparedStatement.setString(1, p.getCodFiscale());
			preparedStatement.setString(2, p.getNome());
			preparedStatement.setString(3, p.getCognome());
			preparedStatement.setString(4, p.getIndirizzo());
			preparedStatement.setString(5, p.getTelefono());
			preparedStatement .executeUpdate();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




	public void printU(){
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rs = stmt.executeQuery("SELECT * FROM utenti");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rs = stmt.getResultSet();
			while (rs.next()){
				System.out.println(rs.getString("Username")+" "+rs.getString("Password"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}



	public static void main(String[] args) throws SQLException {
		GestoreDB db = new GestoreDB();
		//db.printU();
		//ArrayList<Utente> us = db.caricaUtenti();
		//for (Utente u : us)
		//System.out.println(u.toString());
		
		String insertTableSQL = "INSERT INTO persone"
				+ "(cf, nome, cognome, indirizzo, telefono, eta) VALUES"
				+ "(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = db.con.prepareStatement(insertTableSQL);
		preparedStatement.setInt(6, 24);
		preparedStatement.setString(1, "fnjcpjasd");
		preparedStatement.setString(2, "francesco");
		preparedStatement.setString(3, "patanè");
		preparedStatement.setString(4, "via bev 41");
		preparedStatement.setString(5, "3394450677");
		preparedStatement .executeUpdate();
		
		
		

	}
}
