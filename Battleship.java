/*
Author: Dominick Blue
Date: 02/01/2022
Description: A two-person battleship game.
*/
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Battleship {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int gameBoardLength = 5;
		char water = '-';
		char ship = '@';
		char hitShip = 'X';
		char missedShot = 'O';
		int amountOfShips = 5;

		char[][] gameBoard = createGameBoard(gameBoardLength, water, ship, amountOfShips);

		printGameBoard(gameBoard, water, ship);



		// System.out.println("Welcome to Battleship!");
		// System.out.println();
		
		// System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
		// System.out.println("Enter ship 1 location:");
		// if (input.hasNextInt()) {
		// 	shipLocation1 = input.nextInt();
		// }
		// else {
		// 	System.out.println("Invalid coordinates. Choose different coordinates.");
		// }
		// if (input.hasNextInt()) {
		// 	shipLocation2 = input.nextInt();
		// }
		// else {
		// 	System.out.println("Invalid coordinates. Choose different coordinates.");
		// }
		// System.out.println(shipLocation1 + " " + shipLocation2);

		// input.close();
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
	private static char[][] createGameBoard(int gameBoardLength, char water, char ship, int amountOfShips) {
		char[][] gameBoard = new char[gameBoardLength][gameBoardLength];

		for (char[] row: gameBoard) {
			Arrays.fill(row, water);


		}
		return placeShips(gameBoard, amountOfShips, water, ship);
	}



    // Use this method to print game boards to the console.
	private static char[][] placeShips(char[][] gameBoard, int amountOfShips, char water, char ship) {
		int placedShips = 0;
		int gameBoardLength = gameBoard.length;
		while (placedShips < amountOfShips) {
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