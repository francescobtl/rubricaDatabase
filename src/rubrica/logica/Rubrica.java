package rubrica.logica;

import java.util.ArrayList;


public class Rubrica {
	
	
	private ArrayList<Persona> voci = new ArrayList<>();
	private ArrayList<Utente> utenti = new ArrayList<>();
	
	static GestoreDB gd;
	
	public Rubrica (){
		
		//this.voci= f.leggiFile();
		gd = new GestoreDB();
		this.utenti = gd.caricaUtenti();
		gd = new GestoreDB();
		this.voci = gd.caricaPersone();
	}
	
	public void addVoce (Persona p){
		//f.addLinea(p, this.voci.size());
		gd = new GestoreDB();
		gd.addVoce(p);
		this.voci.add(p);
	}

	public ArrayList<Persona> getVoci() {
		return voci;
	}
	
	public void modificaVoce(int posizione, Persona nEw){
		gd = new GestoreDB();
		gd.modifica(nEw);
		this.voci.set(posizione, nEw);
		//f.editFile(posizione, nEw);
		//f.rewriteFile(voci);
		
		
	}
	

	
	public void eliminaVoci (int p){
		if (this.voci.size()>0){
			gd = new GestoreDB();
			gd.elimina(this.voci.get(p).getCodFiscale());
			this.voci.remove(p);
		}
		
	}
	
	public boolean login (String us, String pw){
		gd = new GestoreDB();
		for (Utente u : this.utenti){
			System.out.println(u.toString());
			if ((u.getUsername().equals(us))&&u.getPassword().equals(pw)) return true;
		}
		return false;
	}
	




}
