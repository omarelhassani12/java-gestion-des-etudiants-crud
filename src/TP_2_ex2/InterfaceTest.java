package TP_2_ex2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;


class InterfaceTest extends JFrame{
	
	private static final long serialVersionUID = 1L;
		private JLabel label_nom = new JLabel("nom");
		private JTextField input_nom = new JTextField();
		private JLabel label_prenom = new JLabel("prenom");
		private JTextField input_prenom = new JTextField();

		private JLabel label_resultat = new JLabel("The resultat");
		private JTextField input_resultat = new JTextField();
			
		private JButton envoyer_btn = new JButton("Soumettre");
		private JButton annuler_btn = new JButton("annuler");
	    private JButton clear_btn = new JButton("Effacer");
			
		private JPanel J1 = new JPanel();
		private JPanel J2 = new JPanel();
		private JPanel J3 = new JPanel();
		private JPanel J6 = new JPanel();
			
		ButtonGroup G = new ButtonGroup();
		JOptionPane Jo = new JOptionPane();
			
		public InterfaceTest() {
			setTitle("information d'utilisateur");
			setSize(400, 230);
			J1.setLayout(new BorderLayout());
			add(J1);
			J1.add(J2, BorderLayout.CENTER);
			J1.add(J6, BorderLayout.SOUTH);

			J2.setLayout(new BorderLayout());
			J2.add(J3, BorderLayout.NORTH);
			
			J3.setLayout(new GridLayout(6, 1, 5, 5));
			J3.add(label_nom);
			J3.add(input_nom);
			J3.add(label_prenom);
			J3.add(input_prenom);
			
			input_resultat.setEditable(false);  
		    J3.add(label_resultat);
		    J3.add(input_resultat);

			J6.setLayout(new FlowLayout());
			J6.add(envoyer_btn);
			J6.add(annuler_btn);
	        J6.add(clear_btn);
	        J1.add(J6, BorderLayout.SOUTH);

	        // Add Tooltips
	        input_nom.setToolTipText("Entrez votre nom");
	        input_prenom.setToolTipText("Entrez votre prénom");
	        envoyer_btn.setToolTipText("Cliquez pour afficher le résultat");
	        clear_btn.setToolTipText("Cliquez pour effacer les champs");
	        annuler_btn.setToolTipText("Cliquez pour quitter l'application");

			
	        
	        // Action listener for "Envoyer" button
	        envoyer_btn.addActionListener(new ActionListener() {	
					@Override
					public void actionPerformed(ActionEvent e) {
//						 String nom = input_nom.getText();
//			             String prenom = input_prenom.getText();
//			             String result = "Bonjour " + nom + " " + prenom;
//			             input_resultat.setText(result);
						
						
		                String nom = input_nom.getText().trim();
		                String prenom = input_prenom.getText().trim();

		                if (nom.isEmpty() || prenom.isEmpty()) {
		                    JOptionPane.showMessageDialog(InterfaceTest.this,
		                            "Veuillez remplir tous les champs!",
		                            "Erreur", JOptionPane.ERROR_MESSAGE);
		                } else {
		                    String greeting = getGreeting();
		                    String result = greeting + " " + nom + " " + prenom;
		                    input_resultat.setText(result);
		                }
					}
			});
	        
	        // "Effacer" Button Action
	        clear_btn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                input_nom.setText("");
	                input_prenom.setText("");
	                input_resultat.setText("");
	            }
	        });
				
		
	       // Action listener for "Annuler" button
			annuler_btn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                System.exit(0);
	                dispose(); 
	            }
	        });
	        
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
		}
		
	    private String getGreeting() {
	        LocalTime now = LocalTime.now();
	        if (now.isBefore(LocalTime.NOON)) {
	            return "Bonjour ";
	        } else if (now.isBefore(LocalTime.of(18, 0))) {
	            return "Bon Après-midi";
	        } else {
	            return "Bonsoir";
	        }
	    }
			
	 public static void main(String[] args) {
		 new InterfaceTest();
	 }
}