package TP_1_ex3;
class TestHeritageSimple {

    public static void main(String[] args) {
        Chien chien1 = new Chien("chien1", 14);
        Chat chat1 = new Chat("chat1", 19);
        
        chien1.manger();
        chien1.dormir();
        chien1.faireSonCri();
        
        chat1.manger();
        chat1.dormir();
        chat1.faireSonCri();

    }
}