package TP_2_ex3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Apogee extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel label_cne = new JLabel("CNE:");
    private JTextField input_cne = new JTextField(10);
    private JLabel label_nom = new JLabel("Nom:");
    private JTextField input_nom = new JTextField(10);
    private JLabel label_prenom = new JLabel("Prénom:");
    private JTextField input_prenom = new JTextField(10);
    private JLabel label_filire = new JLabel("Filière:");
    private JTextField input_filire = new JTextField(10);

    private JButton envoyer_btn = new JButton("Soumettre");
    private JButton clear_btn = new JButton("Effacer");
    private JButton annuler_btn = new JButton("Annuler");
    
    private JButton delete_btn = new JButton("Supprimer");
    private JButton update_btn = new JButton("Modifier");
    
    private JButton search_btn = new JButton("Rechercher");

    private JComboBox<String> selectOption = new JComboBox<>();


    private DefaultTableModel tableModel;
    private JTable table;

    public Apogee() {
        setTitle("Gestion des Étudiants");
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Détails de l'Étudiant"));

        // L'ajoute inputs & labels to inputPanel
        inputPanel.add(label_cne);
        inputPanel.add(input_cne);
        inputPanel.add(label_nom);
        inputPanel.add(input_nom);
        inputPanel.add(label_prenom);
        inputPanel.add(input_prenom);
        inputPanel.add(label_filire);
        inputPanel.add(input_filire);

        // Recherche Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 30)); 
        searchPanel.setBorder(BorderFactory.createTitledBorder("Recherche en utilisant le CNE"));

        fetchCNEData(selectOption);
        selectOption.setPreferredSize(new Dimension(250, 20));

        search_btn.setPreferredSize(new Dimension(110, 20));

        searchPanel.add(selectOption);
        searchPanel.add(search_btn);

        JPanel combinedPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        combinedPanel.add(inputPanel);
        combinedPanel.add(searchPanel);

        add(combinedPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(envoyer_btn);
        buttonPanel.add(clear_btn);
        buttonPanel.add(annuler_btn);

        update_btn.setBackground(Color.BLUE);
        delete_btn.setBackground(Color.RED);
        update_btn.setForeground(Color.WHITE);
        delete_btn.setForeground(Color.WHITE);

        buttonPanel.add(update_btn);
        buttonPanel.add(delete_btn);

        String[] columnNames = {"CNE", "Nom", "Prénom", "Filière"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Liste des Étudiants"));

        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        //fetch data
        fetchData();

        //l'ajout des event
        addListeners();

        setVisible(true);
    }

    //method event Listenes
    private void addListeners() {
    	//btn Ajouter event
        envoyer_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertData();
            }
        });
        
        //btn Effacer event
        clear_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        annuler_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        //btn Recherche event
        search_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchByCNE((String) selectOption.getSelectedItem());
            }
        });
        
        //btn Modification event
        update_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cne = input_cne.getText().trim();
                if (cne.isEmpty()) {
                    JOptionPane.showMessageDialog(Apogee.this, "Veuillez sélectionner un CNE!", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    updateData(cne);
                }
            }
        });

        //btn suppression event
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cne = input_cne.getText().trim();
                if (cne.isEmpty()) {
                    JOptionPane.showMessageDialog(Apogee.this, "Veuillez sélectionner un CNE!", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    deleteData(cne);
                }
            }
        });
    }

    private void clearFields() {
        input_cne.setText("");
        input_nom.setText("");
        input_prenom.setText("");
        input_filire.setText("");
    }

    // Méthode pour insérer un nouvel étudiant
    private void insertData() {
        String cne = input_cne.getText().trim();
        String nom = input_nom.getText().trim();
        String prenom = input_prenom.getText().trim();
        String filire = input_filire.getText().trim();

        if (cne.isEmpty() || nom.isEmpty() || prenom.isEmpty() || filire.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO etudiant (cne, nom, prenom, filire) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cne);
            stmt.setString(2, nom);
            stmt.setString(3, prenom);
            stmt.setString(4, filire);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Étudiant ajouté avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                fetchData();
                selectOption.removeAllItems();
                fetchCNEData(selectOption);
                clearFields();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'insertion: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fetchData() {

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM etudiant";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0);

            while (rs.next()) {
                String cne = rs.getString("cne");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String filire = rs.getString("filire");

                tableModel.addRow(new Object[]{cne, nom, prenom, filire});

            }
            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de la récupération des données: " + ex.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    //obitner tous les CNE des étudiants
    private void fetchCNEData(JComboBox<String> comboBox) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT cne FROM etudiant"; 
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String cne = rs.getString("cne");
                comboBox.addItem(cne);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de la récupération des CNE: " + ex.getMessage(),
                    "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //rechercher un étudiant par son CNE
    private void searchByCNE(String cne) {
        if (cne == null || cne.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un CNE valide!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM etudiant WHERE cne = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cne);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                input_cne.setText(rs.getString("cne"));
                input_nom.setText(rs.getString("nom"));
                input_prenom.setText(rs.getString("prenom"));
                input_filire.setText(rs.getString("filire"));
            } else {
                JOptionPane.showMessageDialog(this, "Étudiant non trouvé!", "Information", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la recherche: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateData(String cne) {
        String nom = input_nom.getText().trim();
        String prenom = input_prenom.getText().trim();
        String filire = input_filire.getText().trim();

        if (nom.isEmpty() || prenom.isEmpty() || filire.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "UPDATE etudiant SET nom = ?, prenom = ?, filire = ? WHERE cne = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setString(2, prenom);
            stmt.setString(3, filire);
            stmt.setString(4, cne);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Étudiant modifié avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                fetchData(); 
            } else {
                JOptionPane.showMessageDialog(this, "Étudiant non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la modification: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteData(String cne) {
        int confirmed = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer cet étudiant?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "DELETE FROM etudiant WHERE cne = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, cne);

                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Étudiant supprimé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    fetchData(); 
                    selectOption.removeAllItems();
                    fetchCNEData(selectOption);
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this, "Étudiant non trouvé!", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    
    public static void main(String[] args) {
        new Apogee();
    }
}
