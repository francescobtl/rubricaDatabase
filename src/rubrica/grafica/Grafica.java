package rubrica.grafica;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import rubrica.logica.Persona;
import rubrica.logica.Rubrica;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;




@SuppressWarnings("serial")
public class Grafica extends JFrame {

	private JFrame frame;
	private JButton bn, bm, be;
	private Rubrica r;
	private JTable table;
	private JToolBar tools;

	public Grafica( Rubrica r){
		this.setTitle("Gestore Rubrica");
		this.r= r;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Object rowData[][] = new Object [r.getVoci().size()] [3];
		Object columnNames[] = { "Nome", "Cognome", "Telefono" };
		int i = 0;
		for (Persona p : this.r.getVoci()){
			rowData[i][0] = p.getNome();
			rowData[i][1] = p.getCognome();
			rowData[i][2] = p.getTelefono();
			i++;
		}
		table = new JTable(new DefaultTableModel(rowData, columnNames));
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(800, 400);
		bn = new JButton();
		bm = new JButton();
		be = new JButton();
		tools = new JToolBar();
		
		
		try {
			ImageIcon image1 = new ImageIcon(ImageIO.read(new URL("https://www.bignerdranch.com/img/blog/2014/07/Button-2.png")));
			ImageIcon image2 = new ImageIcon(ImageIO.read(new URL("https://image.freepik.com/free-icon/edit-button_318-99287.jpg")));	
			ImageIcon image3 = new ImageIcon(ImageIO.read(new URL("https://image.freepik.com/free-icon/delete-button_318-27987.jpg")));
			Image newimg1 = image1.getImage().getScaledInstance( 25, 25,  java.awt.Image.SCALE_SMOOTH ) ;
			Image newimg2 = image2.getImage().getScaledInstance( 25, 25,  java.awt.Image.SCALE_SMOOTH ) ;
			Image newimg3 = image3.getImage().getScaledInstance( 25, 25,  java.awt.Image.SCALE_SMOOTH ) ;
		    bn.setIcon(new ImageIcon( newimg1 ));
		    bm.setIcon(new ImageIcon( newimg2 ));
		    be.setIcon(new ImageIcon( newimg3 ));
		  } catch (Exception ex) {
			  //ex.printStackTrace();
			  System.out.println("can't get images");
			  bn = new JButton("Nuovo");
			  bm = new JButton("Modifica");
			  be = new JButton("Elimina");
		  } 
		
//		bn = new JButton("Nuovo");
//		bm = new JButton("Modifica");
//		be = new JButton("Elimina");
		
		tools.add(bn);
		tools.add(bm);
		tools.add(be);
	
		bn.setActionCommand("aggiungi");
		bm.setActionCommand("modifica");
		be.setActionCommand("elimina");
		
		bn.addActionListener(new Listener());
		bm.addActionListener(new Listener());
		be.addActionListener(new Listener());
		
		frame.add("North", tools);
		frame.setVisible(true);
	}





	private class Listener implements ActionListener{    
		public void actionPerformed(ActionEvent e){  
			String command = e.getActionCommand();  
			if( command.equals( "elimina" )){
					removeSelectedRow(table);
			}

			if( command.equals( "aggiungi" ))  {
				new Editor_Persona(r, table);
			}

			if( command.equals( "modifica" ))  {
				int row = table.getSelectedRow();

				if (row == (-1)){
					JOptionPane.showMessageDialog(frame, "Nessuna voce selezionata", "Errore", JOptionPane.ERROR_MESSAGE);
				} else {
					Persona p = r.getVoci().get(row);
					new Editor_Persona(r, table, p, row);
				}

			}

		}  

		public void removeSelectedRow(JTable table){
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			int row = table.getSelectedRow();
			if (row==(-1)){
				JOptionPane.showMessageDialog(frame, "Nessuna voce selezionata", "Errore", JOptionPane.ERROR_MESSAGE);
			}else{
				int reply = JOptionPane.showConfirmDialog(null, "Eliminare la persona " + r.getVoci().get(row).getNome() + " " + r.getVoci().get(row).getCognome(), null, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION)
				{
					r.eliminaVoci(row);
					model.removeRow(row);
				}
			}



		}


	}



}
