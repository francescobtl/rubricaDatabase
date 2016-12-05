package rubrica.grafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import rubrica.logica.Rubrica;


@SuppressWarnings("serial")
public class Login extends JFrame{

	private JPanel panel;
	private JTextField f1, f2;
	private JButton b;
	private JLabel l1, l2;
	
	private Rubrica r;

	public Login (){
		this.l1= new JLabel("Username: ");
		this.l2= new JLabel("Password: ");
		this.f1 = new JTextField();
		this.f2 = new JTextField();
		this.b= new JButton("LOGIN");
		this.panel= new JPanel(new GridLayout(0,1));
		panel.add(l1);
		panel.add(f1);
		panel.add(l2);
		panel.add(f2);

		panel.add(b);
		b.setActionCommand("login");
		b.addActionListener(new Listener());
		Border empty = new EmptyBorder(20, 20, 20, 20);
		panel.setBorder(empty);
		add(panel, BorderLayout.CENTER);
		setSize(300, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	private class Listener implements ActionListener{    
		public void actionPerformed(ActionEvent e){  
			String command = e.getActionCommand();  
			if( command.equals( "login" ))  {
				String un = (f1.getText());
				String pw = (f2.getText());
				r= new Rubrica();
				if (r.login(un, pw)) {
					new Grafica(r);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(panel, "Credenziali non valide", "Errore", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		}
	}

}
