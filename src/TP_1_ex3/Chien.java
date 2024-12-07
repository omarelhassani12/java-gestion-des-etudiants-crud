package TP_1_ex3;

public class Chien extends Animal {

	public Chien(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }
	
    @Override
    public void manger() {
    	System.out.println("je manger....");
    }
    
    @Override
    public void dormir() {
    	System.out.println("je dormir ...");
    }

    @Override
    public void faireSonCri() {
    	System.out.println("Chien aboie...");
    }
}


