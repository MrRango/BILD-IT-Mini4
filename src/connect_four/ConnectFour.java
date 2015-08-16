package connect_four;

import java.util.Scanner;

public class ConnectFour {
	//matrica sadrzi polja za igru
	static char[][] grid = {{' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' '},
							{' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' '},
							{' ', ' ', ' ', ' ', ' ', ' ', ' '}, {' ', ' ', ' ', ' ', ' ', ' ', ' '}};
	//niz za vodjenje evidencije koliko su kolone popunjene
	static int[] brojUKoloni = new int[7];
	/*
	 * metoda koja sadrzi meni
	 */
	public void meni(){
		//stampanje zaglavlja
		System.out.println("*************************************************\n"
					     + "*************************************************\n"
					     + "                CONNECT FOUR\n"
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
	 * metoda za igranje igre connect four
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
			//testiranje da li je prethodni potez pobjedonosni
			if(imaLiPobjednika()){
				System.out.println("Pobjednik je igrac 1 (R)\n");
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
				System.out.println("Pobjednik je igrac 2 (Y)\n");
				//resetovanje grida u slucaju da se igra nova partija
				resetujGrid();
				break;
			}
			//ako su odigrana 42 poteza, igra se zavrsava bez pobjednika
			if(brojacPoteza == 42){
				System.out.println("GAME OVER\nNema pobjednika\n");
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
		
		for(int i = grid.length - 1; i >= 0; i--){
			for(int j = 0; j < grid[i].length; j++){
				System.out.print(" | " + grid[i][j]);
			}
			System.out.print(" |\n");
		}
		System.out.println(" -----------------------------");
	}
	/*
	 * metoda za unos poteza
	 */
	public void odigrajPotez(int igrac){
		
		char karakter;
		int potez;
		boolean uslov = true;
		//za igraca 1 se dodjeljuje karakter R, za igraca 2 karakter Y
		if(igrac == 1){
			karakter = 'R';
		}else{
			karakter = 'Y';
		}
		
		Scanner in = new Scanner(System.in);
		do{
			//ispisivanje koji je igrac na potezu
			System.out.println("Igrac " + karakter + " je na potezu. ");
			//unos poteza od strane igraca
			potez = in.nextInt();
			//testiranje da li je potez validan (u zadatom opsegu i da kolona nije ispunjena)
			if(potez >= 0 && potez <= 6 && brojUKoloni[potez] < 6){
				uslov = false;
			}else{
				System.out.println("Greska pri unosu.");
			}
		}while(uslov);
		
		//u zavisnosti koji je potez igrac odigrao, na odgovarajuce mjesto u gridu se dodaje karakter koji pripada trenutnom igracu
		//u koji red ce se karakter staviti zavisi od broja koristenja te kolone
		switch (potez){
		case 0:{
			grid[brojUKoloni[0]][0] = karakter;
			brojUKoloni[potez]++;
			break;
		}
		case 1:{
			grid[brojUKoloni[1]][1] = karakter;
			brojUKoloni[potez]++;
			break;
		}
		case 2:{
			grid[brojUKoloni[2]][2] = karakter;
			brojUKoloni[potez]++;
			break;
		}
		case 3:{
			grid[brojUKoloni[3]][3] = karakter;
			brojUKoloni[potez]++;
			break;
		}
		case 4:{
			grid[brojUKoloni[4]][4] = karakter;
			brojUKoloni[potez]++;
			break;
		}
		case 5:{
			grid[brojUKoloni[5]][5] = karakter;
			brojUKoloni[potez]++;
			break;
		}
		case 6:{
			grid[brojUKoloni[6]][6] = karakter;
			brojUKoloni[potez]++;
			break;
		}
		}
	}
	/*
	 * metoda koja ispituje ima li pobjednika
	 */
	public boolean imaLiPobjednika(){
		boolean imaLiPobjednika = false;
		
		//testiranje uspravno
		for(int j = 0; j < 7; j++){
			for(int i = 0; i < 3; i++){
				if(grid[i][j] == grid[i + 1][j] && grid[i][j] == grid[i + 2][j] && grid[i][j] == grid[i + 3][j] && grid[i][j] != ' '){
					imaLiPobjednika = true;
				}
			}
		}
		//testiranje vodoravno
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < 4; j++){
				if(grid[i][j] == grid[i][j + 1] && grid[i][j] == grid[i][j + 2] && grid[i][j] == grid[i][j + 3] && grid[i][j] != ' '){
					imaLiPobjednika = true;
				}
			}
		}
		//testiranje opadajuce
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 4; j++){
				if(grid[i][j] == grid[i + 1][j + 1] && grid[i][j] == grid[i + 2][j + 2] && grid[i][j] == grid[i + 3][j + 3] && grid[i][j] != ' '){
					imaLiPobjednika = true;
				}
			}
		}
		//testiranje rastuce
		for(int i = 3; i < grid.length; i++){
			for(int j = 0; j < 4; j++){
				if(grid[i][j] == grid[i - 1][j + 1] && grid[i][j] == grid[i - 2][j + 2] && grid[i][j] == grid[i - 3][j + 3] && grid[i][j] != ' '){
					imaLiPobjednika = true;
				}
			}
		}
		
		return imaLiPobjednika;
	}
	/*
	 * metoda za stampanje uputstva za igranje
	 */
	public void odstampajUputstvo(){
		System.out.println("Za biranje polja koriste se brojevi od 0 do 6.");

		for(int i = grid.length - 2; i >= 0; i--){
			for(int j = 0; j < grid[i].length; j++){
				System.out.print(" | " + " ");
			}
			System.out.print(" |\n");
		}
		System.out.println(" | 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
		System.out.println(" -----------------------------");
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
		for(int i = 0; i < brojUKoloni.length; i++){
			brojUKoloni[i] = 0;
		}
	}
	
}	
