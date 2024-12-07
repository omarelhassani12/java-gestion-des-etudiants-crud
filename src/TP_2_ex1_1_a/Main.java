package TP_2_ex1_1_a;

import java.util.Scanner;

class Main {
	  public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		String paire[] = new String[10];
		String impaire[] = new String[10];
		
        int pi = 0, impi = 0;

        

		for(int i = 1; i <= 10; i++) {
			
		    System.out.println("Enter le "+ i +" nombre :");
		    String nb = sc.nextLine();
		    
		    if(Integer.parseInt(nb) % 2 == 0) {
		    	paire[pi++] = nb;		    	
		    }else {
		    	impaire[impi++] = nb;
		    }
		}
		
		System.out.println("\nles valeur paire\n");			
		for(int i = 0; i < pi; i++) {
			System.out.println(paire[i]);			
		}
		
		System.out.println("\nles valeur impaire\n");			
		for(int i = 0; i < impi; i++) {
			System.out.println(impaire[i]);			
		}

		sc.close();
        
	  }
	}