package TP_1_ex3;

public class Chat extends Animal {

	public Chat(String nom, int age) {
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
    	System.out.println("Chat miaule...");
    }
}


