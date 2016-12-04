package rubrica.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class GestoreFile {

	static final String nomeFile = "Persona"; 


	public ArrayList<Persona> leggiFile (){
		File file = new File("informazioni");
		if (!(file.isDirectory())) file.mkdir();
		ArrayList<Persona> voci =new ArrayList<>();
		for (final File fileEntry : file.listFiles()) {

			Scanner scanner = null;
			try {
				scanner = new Scanner(fileEntry);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (line.matches("^*.*;.*;.*;.*;.*$")) voci.add(leggiRiga(line));

				}
			} catch (FileNotFoundException e) {
				//e.printStackTrace();
			}
			finally {
				if (scanner != null)scanner.close();
			}
		}

		return voci;

	}

	public Persona leggiRiga (String riga){
		String [] campi = riga.split(";");
		Persona p = new Persona(campi[0], campi[1], campi[2], campi[3], Integer.parseInt(campi[4]));
		return p;
	}




	public void addLinea (Persona p, int size){

		PrintStream scrivi = null;
		try {

			scrivi = new PrintStream(new FileOutputStream("informazioni/"+nomeFile+size+".txt", true));
			scrivi.append(p.getNome() + ";" + p.getCognome() + ";" + p.getIndirizzo() + ";" + p.getTelefono() + ";" + p.getEta());
			scrivi.close();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		finally {
			if (scrivi != null) scrivi.close();
		}

	}
	
	public void removeFile(int progr){
		Path p = Paths.get("informazioni/Persona"+progr+".txt");
		try {
			Files.delete(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public void editFile (int progr, Persona p){
		PrintStream scrivi = null;
		Path path = Paths.get("informazioni/Persona"+progr+".txt");
		try {
			Files.delete(path);
			scrivi = new PrintStream(new FileOutputStream("informazioni/Persona"+progr+".txt", true));
			scrivi.append(p.getNome() + ";" + p.getCognome() + ";" + p.getIndirizzo() + ";" + p.getTelefono() + ";" + p.getEta());
			
			scrivi.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally {
			if (scrivi != null) scrivi.close();
		}
		
		
	}

	public void rewriteFile (ArrayList<Persona> voci, int p){
		
		for (int i =p;i<voci.size();i++){
			try {
				Path source = Paths.get("informazioni/Persona"+(i+1)+".txt");
				Files.move(source, source.resolveSibling("Persona"+i+".txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		

	}




}
