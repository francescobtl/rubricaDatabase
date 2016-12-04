package rubrica.logica;

public class ConvalidaDati {
	
	public boolean convalida (String nome, String cognome, String indirizzo, String telefono, String eta){
		boolean errors = false;
		
		if (nome == null || nome.trim().equals("")) errors = true;
		if (cognome == null || cognome.trim().equals("")) errors = true;
		if (indirizzo == null || indirizzo.trim().equals("")) errors = true;
		if (telefono == null || telefono.trim().equals("")) errors = true;
		int e=0;
		try {
		    e = Integer.parseInt(eta);
		} catch (NumberFormatException ex) {
		    errors = true;
		}
		if(e<0) errors=true;
		return errors;
	}

}
