package TP_1_ex1;

public class Test {
    public static void main(String[] args) {
        Employe E = new Employe("E1", 6000, "Dev it", "IT");
        E.afficherSalaire();
        E.afficherInfo();
        
        Departement D = new Departement("D1", 0, 12, 3);
        D.afficherBudget();
        D.calculerBudget();
        D.afficherBudget();
    }
}
