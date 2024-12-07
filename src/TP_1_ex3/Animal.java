package TP_1_ex3;

public class Animal {
    public String nom;
    public int age;

    public Animal() {}
    
    public Animal(String nom, int age) {
        this.nom = nom;
        this.age = age;
    }
    
    public void manger() {
    	System.out.println("je manger....");
    }
    
    public void dormir() {
    	System.out.println("je dormir ...");
    }

    public void faireSonCri() {
    	System.out.println("émet us son ...");
    }
}


