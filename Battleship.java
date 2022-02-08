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
        

        // Begin Game
		System.out.println("Welcome to Battleship!"); 
		System.out.println();
		
		
		System.out.println("PLAYER 1, ENTER YOUR SHIPS' COORDINATES.");
        char[][] player1Board = createGameBoard(gameBoardLength, water, ship, shipNumber);
        char[][] player1BattleHistoryBoard = createBattleLogBoard(gameBoardLength, water, ship, shipNumber);
        
        System.out.println("Player 1 Board");
        printBattleShip(player1Board);
        System.out.println();


        for (int i = 1; i <= 100; i++) {
			System.out.println();
		}


		System.out.println("PLAYER 2, ENTER YOUR SHIPS' COORDINATES.");
        char[][] player2Board = createGameBoard(gameBoardLength, water, ship, shipNumber);
        char[][] player2BattleHistoryBoard = createBattleLogBoard(gameBoardLength, water, ship, shipNumber);

        // This board tracks the volley history of Player 2 aiming at Player 1
        printBattleShip(player2Board);


        for (int i = 1; i <= 100; i++) {
			System.out.println();
		}

        boolean gameOver = false;
        // int playerTurn = 1;
        int player1Hits = 0;
        // int player2Hits = 0;
        
        
        
        // Gameover starts as false
        do {

            System.out.println("Player 1, enter hit row/column:");
            int[] playerGuessCoordinates = playerVolley(gameBoardLength);
            char battleBoardUpdate = evaluatePlayerBoard(playerGuessCoordinates, player2Board, ship, water, hit, miss);
            if (player1Hits != shipNumber) {
                player2Board = updateBattleBoard(player2Board, playerGuessCoordinates, battleBoardUpdate);
                player2BattleHistoryBoard = updateBattleBoard(player2BattleHistoryBoard, playerGuessCoordinates, battleBoardUpdate);
                player1Hits++;
            }
            
            printBattleShip(player2BattleHistoryBoard);
            System.out.println();
            if (player1Hits == gameBoardLength) {
                System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
                gameOver = true;
            }
            System.out.println();

            


            


            // Player 1's turn
            // if (playerTurn % 2 == 1) {
            //     System.out.println("It's player one's turn.");
            //     playerTurn--;

            //     if (player2Hits == shipNumber) {
            //         gameOver = true;
            //         System.out.println("PLAYER 1 WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
            //         break;
            //     }
            // }

            // if (playerTurn % 2 == 0) {
            //     System.out.println("It's player two's turn.");
            //     playerTurn++;

            //     if (player2Hits == shipNumber) {
            //         gameOver = true;
            //         System.out.println("PLAYER 2 WINS! YOU SUNK ALL OF YOUR OPPONENT’S SHIPS!");
            //         break;
            //     }
            // }
        } while (gameOver != true);
        
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

    private static char evaluatePlayerBoard(int[] playerGuessCoordinates, char[][] player2Board, char ship, char water, char hit, char miss) {
        String message;
        int row = playerGuessCoordinates[0];
        int col = playerGuessCoordinates[1];
        char target = player2Board[row][col];

        if ((target == hit) || (target == miss)) {
            message = "You already fired on this spot. Choose different coordinates.";
        }
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
			while (placedShips < shipNumber) {
                
                System.out.println("Enter ship " + (placedShips + 1) + " location:");
                
                int row = INPUT.nextInt();
                int col = INPUT.nextInt();
                if (invalidCoordinates(row, col, gameBoardLength)) {
                    System.out.println("Invalid coordinates. Choose different coordinates.");    
                }
				char possiblePlacement = gameBoard[row][col];
				if (possiblePlacement == water) {
					gameBoard[row][col] = ship;
                    placedShips++;
				} else {
                    System.out.println("You already have a ship there. Choose different coordinates.");
                }
			}
		return gameBoard;
	}
    public static boolean invalidCoordinates(int row, int col, int gameBoardLength) {
        if (row < 0 || row > gameBoardLength || col < 0 || col > gameBoardLength) {
            return true;
        }
        return false;
    }

    private static int[] generateShipCoordinates(int gameBoardLength) {
        int[] coordinates = new int[2];
		for (int i = 0; i < coordinates.length; i++) {
            if ((coordinates[i] > gameBoardLength) || (!INPUT.hasNextInt())) {
                System.out.println("Invalid coordinates. Choose different coordinates.");
                INPUT.nextLine();
            } else {
                coordinates[i] = INPUT.nextInt();    
                }
            }
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
