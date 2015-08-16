/*
 * (Game: play a tic-tac-toe game) In a game of tic-tac-toe, two players take turns
 * marking an available cell in a 3 * 3 grid with their respective tokens (either
 * X or O). When one player has placed three tokens in a horizontal, vertical, or
 * diagonal row on the grid, the game is over and that player has won. A draw (no
 * winner) occurs when all the cells on the grid have been filled with tokens and
 * neither player has achieved a win. Create a program for playing tic-tac-toe.
 * The program prompts two players to enter an X token and O token alternately.
 * Whenever a token is entered, the program redisplays the board on the
 * console and determines the status of the game (win, draw, or continue).
 */

package tic_tac_toe;

public class Tic_Tac_Toe_TheGame {

	public static void main(String[] args) {
		//pravljenje novog objekta ticTacToe i pozivanje menija
		Tic_Tac_Toe ticTacToe = new Tic_Tac_Toe();
		ticTacToe.meni();

	}

}
