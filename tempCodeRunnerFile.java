  
        System.out.println("Enter ship " + (placedShips + 1) + " location:");
        do {
            int[] location = generateShipCoordinates(gameBoardLength);
            try {
                if (location[0] >= gameBoardLength || location[1] >= gameBoardLength) {
                    System.out.println("Invalid coordinates. Choose different coordinates.");    
                }
            } 
            catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                System.out.println("Invalid coordinates. Choose different coordinates.");
                INPUT.nextLine();
            }
            try {
                if (!INPUT.hasNextInt()) {
                    System.out.println("Invalid coordinates. Choose different coordinates.");    
                } else {
                    location[0] = INPUT.nextInt();
                    location[1] = INPUT.nextInt();
                }
            }
            catch (InputMismatchException inputMismatchException) {
                System.out.println("Invalid coordinates. Choose different coordinates.");    
            }
        } while (placedShips < shipNumber);