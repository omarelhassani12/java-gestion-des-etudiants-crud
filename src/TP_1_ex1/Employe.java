package TP_1_ex1;

public class Employe {
    public String nom;
    private int salaire;
    protected String poste;
    String departement;
    
    public Employe (String nom, int salaire, String poste, String departement){
            this.nom = nom;
            this.salaire = salaire;
            this.poste = poste;
            this.departement = departement;
    }
    
    public void afficherSalaire(){
        System.out.println("le salaire est : " + this.salaire);
    }
    
    public void afficherInfo(){
        System.out.println("le nom : " + nom + "\nle salaire est : " + this.salaire + "\nle poste est :" + poste + "\nle departement est : " + departement  );
    }
    
    protected void modifierPoste(String nouveauPoste) {
    	this.poste = nouveauPoste;
    }
    
    public int getSalaire() {
    	return salaire;
    }
    public void setSalaire(int salaire) {
    	this.salaire = salaire;
    }
    
}
