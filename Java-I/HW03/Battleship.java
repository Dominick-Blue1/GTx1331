import java.util.InputMismatchException;
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
        String player1Name = "PLAYER 1";
        String player2Name = "PLAYER 2";
		
        

        // Begin Game
		System.out.println("Welcome to Battleship!"); 
		System.out.println();
		
		
		System.out.println(player1Name + ", ENTER YOUR SHIPS' COORDINATES.");
        char[][] player1Board = createGameBoard(gameBoardLength, water, ship, shipNumber);
        char[][] player1BattleHistoryBoard = createBattleLogBoard(gameBoardLength, water, ship, shipNumber);
        
        System.out.println("Player 1 Board");
        printBattleShip(player1Board);
        System.out.println();


        for (int i = 1; i <= 100; i++) {
			System.out.println();
		}


		System.out.println(player2Name + ", ENTER YOUR SHIPS' COORDINATES.");
        char[][] player2Board = createGameBoard(gameBoardLength, water, ship, shipNumber);
        char[][] player2BattleHistoryBoard = createBattleLogBoard(gameBoardLength, water, ship, shipNumber);

        // This board tracks the volley history of Player 2 aiming at Player 1
        printBattleShip(player2Board);


        for (int i = 1; i <= 100; i++) {
			System.out.println();
		}

    
        boolean gameOn = true;
        int playerTurn = 0;
        int player1Hits = 0;
        int player2Hits = 0;

        
        do {

            // Player 1
            playerTurn++;
            if (playerTurn % 2 == 1) {
                System.out.println("Player 1, enter hit row/column:");
                int[] playerGuessCoordinates = playerVolley(gameBoardLength);
                char battleBoardUpdate = evaluatePlayerBoard(playerGuessCoordinates, player2Board, ship, water, hit, miss, player1Name, player2Name);
                if (player1Hits != shipNumber) {
                    player2Board = updateBattleBoard(player2Board, playerGuessCoordinates, battleBoardUpdate);
                    player2BattleHistoryBoard = updateBattleBoard(player2BattleHistoryBoard, playerGuessCoordinates, battleBoardUpdate);
                    player1Hits++;
                }
                printBattleShip(player2BattleHistoryBoard);
                System.out.println();
                if (player1Hits == gameBoardLength) {
                    System.out.println(player1Name + " WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
                    gameOn = false;
                }
            }
            playerTurn--;
            // Player Two
            if (playerTurn % 2 == 0) {
                System.out.println("Player 2, enter hit row/column:");
                int[] playerGuessCoordinates = playerVolley(gameBoardLength);
                char battleBoardUpdate = evaluatePlayerBoard(playerGuessCoordinates, player1Board, ship, water, hit, miss, player2Name, player1Name);
                if (player2Hits != shipNumber) {
                    player1Board = updateBattleBoard(player1Board, playerGuessCoordinates, battleBoardUpdate);
                    player1BattleHistoryBoard = updateBattleBoard(player1BattleHistoryBoard, playerGuessCoordinates, battleBoardUpdate);
                    player2Hits++;
                }
                
                
                printBattleShip(player1BattleHistoryBoard);
                System.out.println();
                if (player2Hits == gameBoardLength) {
                    System.out.println(player2Name + " WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
                    gameOn = false;
                }
            }
        } while (gameOn);        
        System.out.println();
        System.out.println("Final boards:");
        System.out.println();
        System.out.println();

        printBattleShip(player1BattleHistoryBoard);
        System.out.println();
        printBattleShip(player2BattleHistoryBoard);

        // End of main method
    }
    
    private static char[][] createBattleLogBoard(int gameBoardLength, char water, char ship, int shipNumber) {
        char[][] gameBoard = new char[gameBoardLength][gameBoardLength];

		for (int row = 0; row < gameBoardLength; row++) {
			for (int col = 0; col < gameBoardLength; col++) {
				gameBoard[row][col] = water;
			}
		}
        return gameBoard;
    }

    private static char[][] updateBattleBoard(char[][] player2BattleHistoryBoard, int[] playerGuessCoordinates,
            char battleBoardUpdate) {
        
        int row = playerGuessCoordinates[0];
        int col = playerGuessCoordinates[1];
        player2BattleHistoryBoard[row][col] = battleBoardUpdate;
        return player2BattleHistoryBoard;
    }

    private static char evaluatePlayerBoard(int[] playerGuessCoordinates, char[][] playerBoard, char ship, char water, char hit, char miss, String playerName1, String playerName2) {
        String message;
        int row = playerGuessCoordinates[0];
        int col = playerGuessCoordinates[1];
        char target = playerBoard[row][col]; 


        if ((target == hit) || (target == miss)) {
            message = "You already fired on this spot. Choose different coordinates.";
        }
        if (target == ship) {
            message = playerName1 + " HIT " + playerName2 + "’s SHIP!";
            target = hit;
        } else if (target == water) {
            message = playerName1 + " MISSED!";
            target = miss;
        } else {
            message = "You already fired on this spot. Choose different coordinates.";
        }
        System.out.println(message);
        return target;
    }

    private static int[] playerVolley(int gameBoardLength) {
        int[] attackCoordinates = new int[2];
                
        for (int i = 0; i < attackCoordinates.length; i++) {
            attackCoordinates[i] = INPUT.nextInt();
        }
        return attackCoordinates;
    }

    private static char[][] createGameBoard(int gameBoardLength, char water, char ship, int shipNumber) {
        char[][] gameBoard = new char[gameBoardLength][gameBoardLength];

		for (int row = 0; row < gameBoardLength; row++) {
			for (int col = 0; col < gameBoardLength; col++) {
				gameBoard[row][col] = water;
			}
		}
		return placeInitialPlayerShips(gameBoard, shipNumber, water, ship);
	}

    private static char[][] placeInitialPlayerShips(char[][] gameBoard, int shipNumber, char water, char ship) {
        int placedShips = 0;
		int gameBoardLength = gameBoard.length;
        do {
            System.out.println("Enter ship " + (placedShips + 1) + " location:");
            int[] location = generateShipCoordinates(gameBoardLength, placedShips);
            char possiblePlacement = gameBoard[location[0]][location[1]];
            if (possiblePlacement == water) {
                gameBoard[location[0]][location[1]] = ship;
                placedShips++;
            } else {
                System.out.println("You already have a ship there. Choose different coordinates.");
            }
        } while (placedShips < shipNumber);  
		return gameBoard;
	}

    private static int[] generateShipCoordinates(int gameBoardLength, int placedShips) {
        int[] coordinates = new int[2];
        
            do {
                int row = INPUT.nextInt();
                int col = INPUT.nextInt();
                try {
                    
                        if (row < gameBoardLength && col < gameBoardLength) {
                            coordinates[0] = row;
                            coordinates[1] = col;                    
                        } else {
                            System.out.println("Invalid coordinates. Choose different coordinates.");
                            System.out.println("Enter ship " + (placedShips + 1) + " location:");
                            
                            INPUT.next();
                            
                        }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid coordinates. Choose different coordinates.");
                    System.out.println("Enter ship " + (placedShips + 1) + " location:");
                    INPUT.next();
                }
                
            } while (coordinates.length != 2);
		return coordinates;
	}

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
