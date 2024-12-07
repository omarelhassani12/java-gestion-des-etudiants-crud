package TP_2_ex1_2;

import java.util.Scanner;

class Main {
	  public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
        int max = 0;

        

		for(int i = 0; i < 5; i++) {
			
		    System.out.println("Enter le 1er nombre :");
		    int nb = sc.nextInt();
		    
		    if(nb > max) {
		    	max = nb;
		    }

		}
		
		System.out.println("les valeur maximum est : " + max);			
		
		sc.close();
        
	  }
	}