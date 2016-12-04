package rubrica.logica;

public class Utente {
	
	@Override
	public String toString() {
		return "Utente [username=" + username + ", password=" + password + "]";
	}
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
//	public int hashCode (){
//		return this.username.hashCode();
//	}
//	
//	public boolean equals (Object o){
//		Utente u = (Utente) o;
//		return this.username.equals(u.getUsername());
//	}
	
	

}
