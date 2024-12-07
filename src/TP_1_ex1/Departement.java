package TP_1_ex1;

public class Departement {
    public String nom;
    private int budget;
    private int nombreEmployes;
    private int coutMoyenParEmploye;
    
    public Departement (String nom, int budget, int nombreEmployes, int coutMoyenParEmploye){
       this.nom = nom;
       this.budget = budget;
       this.nombreEmployes = nombreEmployes;
       this.coutMoyenParEmploye = coutMoyenParEmploye;
    }
    
    public void afficherBudget(){
        System.out.println("le budget de le departement est : " + this.budget);
    }
    
    public int calculerBudget() {
    	return budget = (nombreEmployes * coutMoyenParEmploye);
    }
    
}
