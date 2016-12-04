package rubrica.logica;

import java.util.ArrayList;


public class Rubrica {
	
	
	private ArrayList<Persona> voci = new ArrayList<>();
	
	static GestoreFile f = new GestoreFile();
	
	public Rubrica (){
		
		this.voci= f.leggiFile();
	}
	
	public void addVoce (Persona p){
		f.addLinea(p, this.voci.size());
		this.voci.add(p);
	}

	public ArrayList<Persona> getVoci() {
		return voci;
	}
	
	public void modificaVoce(int posizione, Persona nEw){
		this.voci.set(posizione, nEw);
		f.editFile(posizione, nEw);
		//f.rewriteFile(voci);
		
		
	}
	

	
	public void eliminaVoci (int p){
		if (this.voci.size()>0){
			this.voci.remove(p);
			f.removeFile(p);
			f.rewriteFile(voci, p);
		}
		
	}
	




}
