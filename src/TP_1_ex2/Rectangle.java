package TP_1_ex2;

public class Rectangle {
    public double largeur;
    public double hauteur;

    public Rectangle(double largeur, double hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public void calculerPerimetre() {
        double perimetre = 2 * (largeur + hauteur);
        System.out.println("Le périmètre du rectangle est : " + perimetre);
    }

    public void calculerSurface() {
        double surface = largeur * hauteur;
        System.out.println("La surface du rectangle est : " + surface);
    }

    public void afficherDetails() {
        System.out.println("Détails du rectangle:");
        System.out.println("Largeur : " + largeur);
        System.out.println("Hauteur : " + hauteur);
        calculerPerimetre();
        calculerSurface();
    }

    public void modifierDimensions(double nouvelleLargeur, double nouvelleHauteur) {
        largeur = nouvelleLargeur;
        hauteur = nouvelleHauteur;
        System.out.println("Les dimensions du rectangle ont été modifiées.");
        afficherDetails();
    }

}
