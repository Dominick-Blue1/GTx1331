/*
Author: Dominick Blue
Date: 02/01/2022
Description: A two-person battleship game.
*/
import java.util.Scanner;
public class Battleship {
	public static final Scanner INPUT = new Scanner(System.in);
	public static void main(String[] args) {
		int gameBoardLength = 5;
		char water = '-';
		char ship = '@';
		char hit = 'X';
		char miss = 'O';
		int shipNumber = 5;
		boolean winner = false;
		String[] players = {"PLAYER 1", "PLAYER 2"};
		
		System.out.println("Welcome to Battleship!");
		System.out.println();
		
		
		System.out.println(players[0] + ", ENTER YOUR SHIPS' COORDINATES.");
		char[][] player1 = createGameBoard(gameBoardLength, water, ship, shipNumber);
		char[][] player1TargetHistoryBoard = player1;
		printBattleShip(player1);

		for (int i = 1; i <= 100; i++) {
			System.out.println(i);
		}

		System.out.println(players[1] + ", ENTER YOUR SHIPS' COORDINATES.");
		char[][] player2 = createGameBoard(gameBoardLength, water, ship, shipNumber);
		char[][] player2TargetHistoryBoard = player2;
		printBattleShip(player2);

		for (int i = 1; i <= 100; i++) {
			System.out.println(i);
		}

		
		while (winner == false) {
			player1Volley(player2TargetHistoryBoard, gameBoardLength, ship, water, hit, miss);
			player2Volley(player1TargetHistoryBoard, gameBoardLength, ship, water, hit, miss);
		}

		System.out.println("Final boards:");
		printBattleShip(player1TargetHistoryBoard);
		printBattleShip(player2TargetHistoryBoard);
    }

	private static void player2Volley(char[][] player1TargetHistoryBoard, int gameBoardLength, char ship, char water, char hit, char miss) {
		System.out.println("Player 2, enter hit row/column:");

		printBattleShip(player1TargetHistoryBoard);
	}

	private static void player1Volley(char[][] player2TargetHistoryBoard, int gameBoardLength, char ship, char water, char hit, char miss) {
		String message;
		int row = guessCoordinates[0];
		int col = guessCoordinates[1];
		char locationViewUpdate = evaluateGuessAndGetTheTarget(guessCoordinates, player2TargetHistoryBoard, ship, water, hit, miss);
		char target = player2TargetHistoryBoard[row][col];
		System.out.println("Player 1, enter hit row/column:");

		if (target == ship) {
			message = "PLAYER 1 HIT PLAYER 2’s SHIP!";
			target = hit;
		} else if (target == water) {
			message = "PLAYER 1 MISSED!";
			target = miss;
		} else {
			message = "You already fired on this spot. Choose different coordinates.";
		}
		System.out.println(message);
		updateGameBoard(player2TargetHistoryBoard, guessCoordinates, locationViewUpdate);
		printBattleShip(player2TargetHistoryBoard);
	}

	// Create Game Board.
	private static char[][] createGameBoard(int gameBoardLength, char water, char ship, int shipNumber) {
		char[][] gameBoard = new char[gameBoardLength][gameBoardLength];

		for (int row = 0; row < gameBoardLength; row++) {
			for (int col = 0; col < gameBoardLength; col++) {
				gameBoard[row][col] = water;
			}
		}
		return placeShips(gameBoard, shipNumber, water, ship);
	}

	private static char[][] placeShips(char[][] gameBoard, int shipNumber, char water, char ship) {
		int placedShips = 0;
		int gameBoardLength = gameBoard.length;
			while (placedShips < shipNumber) {
				System.out.println("Enter ship " + (placedShips + 1) + " location:");
				int[] location = generateShipCoordinates(gameBoardLength);
				char possiblePlacement = gameBoard[location[0]][location[1]];

				if (possiblePlacement == water) {
					gameBoard[location[0]][location[1]] = ship;
					placedShips++;
				}

				
			}
		
		return gameBoard;
	}

	private static int[] generateShipCoordinates(int gameBoardLength) {
		int[] coordinates = new int[2];
		for (int i = 0; i < coordinates.length; i++) {
			coordinates[i] = INPUT.nextInt();
		}
		return coordinates;
	}

	public static char[][] updateGameBoard(char[][] gameBoard, int[] guessCoordinates, char locationViewUpdate) {
		int row = guessCoordinates[0];
		int col = guessCoordinates[1];
		gameBoard[row][col] = locationViewUpdate;
		return gameBoard;
	}
	private static char evaluateGuessAndGetTheTarget(int[] guessCoordinates, char[][] gameBoard, char ship, char water,
			char hit, char miss) {
			int row = guessCoordinates[0];
			int col = guessCoordinates[1];
			char target = gameBoard[row][col];
		return target;
	}
	// DO NOT DELETE OR TOUCH
	private static void printBattleShip(char[][] player) {
		System.out.print("  ");
		for (int row = -1; row < 5; row++) {
			if (row > -1) {
				System.out.print(row + " ");
			}
			for (int column = 0; column < 5; column++) {
				if (row == -1) {
					System.out.print(column + " ");
				} else {
					System.out.print(player[row][column] + " ");
				}
			}
			System.out.println("");
		}
	}
}