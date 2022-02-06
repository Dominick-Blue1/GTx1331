/*
Author: Dominick Blue
Date: 02/01/2022
Description: A two-person battleship game.
*/
import java.util.Arrays;
import java.util.Random;
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

		char[][] gameBoard = createGameBoard(gameBoardLength, water, ship, shipNumber);

		printGameBoard(gameBoard, water, ship);
		int undetectedShipNumber = shipNumber;
		while (undetectedShipNumber > 0) {
			int[] guessCoordinates = getUserCoordinates(gameBoardLength);
			char locationViewUpdate = evaluateGuessAndGetTheTarget(guessCoordinates, gameBoard, ship, water, hit, miss);
			if (locationViewUpdate == hit) {
				undetectedShipNumber--;
			}
			gameBoard = updateGameBoard(gameBoard, guessCoordinates, locationViewUpdate);
			printGameBoard(gameBoard, water, ship);
		}

		// public static startGame() {
		// 	// System.out.println("Welcome to Battleship!");
		// // System.out.println();
		
		// // System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
		// // System.out.println("Enter ship 1 location:");
		// // if (INPUT.hasNextInt()) {
		// // 	shipLocation1 = INPUT.nextInt();
		// // }
		// // else {
		// // 	System.out.println("Invalid coordinates. Choose different coordinates.");
		// // }
		// // if (INPUT.hasNextInt()) {
		// // 	shipLocation2 = INPUT.nextInt();
		// // }
		// // else {
		// // 	System.out.println("Invalid coordinates. Choose different coordinates.");
		// // }
		// // System.out.println(shipLocation1 + " " + shipLocation2);

		// // INPUT.close();
		// }
		
    }
    private static char[][] updateGameBoard(char[][] gameBoard, int[] guessCoordinates, char locationViewUpdate) {
		int row = guessCoordinates[0];
		int col = guessCoordinates[1];
		gameBoard[row][col] = locationViewUpdate;
		return gameBoard;
	}
	private static char evaluateGuessAndGetTheTarget(int[] guessCoordinates, char[][] gameBoard, char ship, char water,
			char hit, char miss) {
			String message;
			int row = guessCoordinates[0];
			int col = guessCoordinates[1];
			char target = gameBoard[row][col];

			if (target == ship) {
				message = "Hit!";
				target = hit;
			} else if (target == water) {
				message = "Miss!";
				target = miss;
			} else {
				message = "Already hit!";
			}
			System.out.println(message);
		return target;
	}
	private static int[] getUserCoordinates(int gameBoardLength) {
		int row;
		int col;
		do {
			System.out.print("Row: ");
			row = INPUT.nextInt();
		} while (row < 1 || row > gameBoardLength + 1);
		do {
			System.out.print("Column: ");
			col = INPUT.nextInt();
		} while (col < 1 || col > gameBoardLength + 1);

		return new int[]{row - 1, col - 1};
	}
	private static void printGameBoard(char[][] gameBoard, char water, char ship) {
		int gameBoardLength = gameBoard.length;
		System.out.print("  ");
		for (int i = 0; i < gameBoardLength; i++) {
			System.out.print(i + 1 + " ");
		}
		System.out.println();
		for (int row = 0; row < gameBoardLength; row++) {
			System.out.print(row + 1 + " ");
			for (int col = 0; col < gameBoardLength; col++) {
				char position = gameBoard[row][col];
				if (position == ship) {
					System.out.print(water + " ");
				} else {
					System.out.print(position + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	private static char[][] createGameBoard(int gameBoardLength, char water, char ship, int shipNumber) {
		char[][] gameBoard = new char[gameBoardLength][gameBoardLength];

		for (char[] row: gameBoard) {
			Arrays.fill(row, water);


		}
		return placeShips(gameBoard, shipNumber, water, ship);
	}

    // Use this method to print game boards to the console.
	private static char[][] placeShips(char[][] gameBoard, int shipNumber, char water, char ship) {
		int placedShips = 0;
		int gameBoardLength = gameBoard.length;
		while (placedShips < shipNumber) {
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
			coordinates[i] = new Random().nextInt(gameBoardLength);
		}
		return coordinates;
	}
}