/*
 * Game: Tic-Tac-Toe
 * 
 * @author Goran Arsenic
 * @version 0.1
 */

package tic_tac_toe;

import java.util.Scanner;

public class Tic_Tac_Toe {
	//matrica sadrzi polja za igru
	static char[][] grid = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
	//niz za vodjenje evidencije koji potezi su odigrani tokom partije, tj. koja polja su popunjena
	static int[] usedFields = new int[9];
	/*
	 * metoda koja sadrzi meni
	 */
	public void meni(){
		//stampanje zaglavlja
		System.out.println("*************************************************\n"
				         + "*************************************************\n"
						 + "              TIC - TAC - TOE\n"
				         + "                  The Game\n"
						 + "              by Goran Arsenic\n"
				         + "*************************************************\n"
						 + "*************************************************");
		
		boolean uslov = true;
		//petlja se vrti i ispisuje meni dok se ne odabere opcija za izlaz
		do{
			//ispisivanje menia
			System.out.println("[1] Zapocni igru\n[2] Uputstvo\n[3] Izadji");
			
			Scanner in = new Scanner(System.in);
			int opcija = in.nextInt();
			//u zavisnosti od unijete opcije, pokrece se igra, ispisuje uputstvo ili izlazi iz igre
			switch(opcija){
			case 1:{
				//pozivanje metode za pokretanje igre
				igra();
				break;
			}
			case 2:{
				//pozivanje metode za stampanje uputstva
				odstampajUputstvo();
				break;
			}
			default:{
				//ispunjen uslov za izlaz iz igre
				uslov = false;
				break;
			}
			}
		}while(uslov);
	}
	/*
	 * metoda za igranje igre tic - tac - toe
	 */
	public void igra(){
		
		boolean isOn = true;
		int brojacPoteza = 0;
		//stampanje praznog grida
		stampajGrid();
		//petlja se vrti dok se igra ne zavrsi
		while(isOn){
			//igrac jedan bira polje
			odigrajPotez(1);
			brojacPoteza++;
			//stampanje grida
			stampajGrid();
			//ako je odigrano devet poteza, igra se zavrsava bez pobjednika
			if(brojacPoteza == 9){
				System.out.println("GAME OVER\nNema pobjednika\n");
				resetujGrid();
				break;
			}
			//testiranje da li je prethodni potez pobjedonosni
			if(imaLiPobjednika()){
				System.out.println("Pobjednik je igrac 1 (X)\n");
				//resetovanje grida u slucaju da se igra nova partija
				resetujGrid();
				break;
			}
			//igrac dva bira polje
			odigrajPotez(2);
			brojacPoteza++;
			//stampanje grida
			stampajGrid();
			//testiranje da li je prethodni potez pobjedonosni
			if(imaLiPobjednika()){
				System.out.println("Pobjednik je igrac 2 (O)\n");
				//resetovanje grida u slucaju da se igra nova partija
				resetujGrid();
				break;
			}
		}
		
	}
	
	/*
	 * metoda za stampanje grida
	 */
	public void stampajGrid(){
		
		System.out.println("  " + grid[0][0] + " | " + grid[0][1] + " | " + grid[0][2] + "\n"
						  + "-------------\n "
						  + " " + grid[1][0] + " | " + grid[1][1] + " | " + grid[1][2] + "\n"
						  + "-------------\n "
						  + " " + grid[2][0] + " | " + grid[2][1] + " | " + grid[2][2] + "\n");
		
	}
	/*
	 * metoda za stamoanje uputstva za igranje
	 */
	public void odstampajUputstvo(){
		
		System.out.println("Za biranje polja koriste se brojevi od 1 do 9. Unos je prilagodjen numerickoj tastaturi.");
		System.out.println("  7 | 8 | 9\n"
				  		  + "-------------\n "
				  	   	  + " 4 | 5 | 6\n"
				  		  + "-------------\n "
				  		  + " 1 | 2 | 3\n");
		System.out.println("Znaci, za unos znaka na poziciju 0,0 pritisnete broj 7, za poziciju 0,1 broj 8 itd.\n");	
	}
	/*
	 * metoda za unos poteza
	 */
	public void odigrajPotez(int igrac){
		char karakter;
		int potez;
		boolean uslov = true;
		//za igraca 1 se dodjeljuje karakter X, za igraca 2 karakter O
		if(igrac == 1){
			karakter = 'X';
		}else{
			karakter = 'O';
		}
		
		Scanner in = new Scanner(System.in);
		do{
			//ispisivanje koji je igrac na potezu
			System.out.println("Igrac " + karakter + " je na potezu. ");
			//unos poteza od strane igraca
			potez = in.nextInt();
			//testiranje da li je potez validan (u zadatom opsegu i da do sada nije odigran)
			if(potez >= 1 && potez <= 9 && usedFields[potez - 1] == 0){
				uslov = false;
				//odigrani potez se biljezi, da se ne bi mogao ponoviti tokom partije
				usedFields[potez - 1]++;
			}else{
				System.out.println("Greska pri unosu.");
			}
		}while(uslov);
		//u zavisnosti koji je potez igrac odigrao, na odgovarajuce mjesto u gridu se dodaje karakter koji pripada trenutnom igracu
		switch (potez){
		case 7:{
			grid[0][0] = karakter;
			break;
		}
		case 8:{
			grid[0][1] = karakter;
			break;
		}
		case 9:{
			grid[0][2] = karakter;
			break;
		}
		case 4:{
			grid[1][0] = karakter;
			break;
		}
		case 5:{
			grid[1][1] = karakter;
			break;
		}
		case 6:{
			grid[1][2] = karakter;
			break;
		}
		case 1:{
			grid[2][0] = karakter;
			break;
		}
		case 2:{
			grid[2][1] = karakter;
			break;
		}
		case 3:{
			grid[2][2] = karakter;
			break;
		}
		}
	}
	/*
	 * metoda koja ispituje ima li pobjednika
	 */
	public boolean imaLiPobjednika(){
		boolean imaLiPobjednika = false;
		//testiranje da li su svi clanovi u kolonama, redovima ili dijagonalama isti, ali da nisu prazna mijesta
		if((grid[0][0] == grid[0][1] && grid[0][0] == grid[0][2] && grid[0][0] != ' ') ||
		   (grid[1][0] == grid[1][1] && grid[1][0] == grid[1][2] && grid[1][0] != ' ') ||
		   (grid[2][0] == grid[2][1] && grid[2][0] == grid[2][2] && grid[2][0] != ' ') ||
		   (grid[0][0] == grid[1][0] && grid[0][0] == grid[2][0] && grid[0][0] != ' ') ||
		   (grid[0][1] == grid[1][1] && grid[0][1] == grid[2][1] && grid[0][1] != ' ') ||
		   (grid[0][2] == grid[1][2] && grid[0][2] == grid[2][2] && grid[0][2] != ' ') ||
		   (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] != ' ') ||
		   (grid[2][0] == grid[1][1] && grid[2][0] == grid[0][2] && grid[2][0] != ' ')){
			imaLiPobjednika = true;
		}
		
		return imaLiPobjednika;
	}
	/*
	 * metoda koja vraca grid u pocetno stanje
	 */
	public void resetujGrid(){
		//vracanje grida u pocetno stanje, upisivanje praznih mijesta
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[i].length; j++){
				grid[i][j] = ' ';
			}
		}
		//vracanje brojaca odigranih poteza na pocetno stanje
		for(int i = 0; i < usedFields.length; i++){
			usedFields[i] = 0;
		}
	}
	
}
