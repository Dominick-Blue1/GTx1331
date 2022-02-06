/*
Author: Dominick Blue
Date: 01/31/2022
Description: A text-based calculator.
*/
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("List of operations: add subtract multiply divide alphabetize");
        System.out.println("Enter an operation: ");
        String operation = input.next();
        input.nextLine();

        switch (operation.toLowerCase()) {
            case "add":
                System.out.println("Enter two integers: ");
                
                if (input.hasNextInt()) {
                    int firstInt = input.nextInt();
                    int secondInt = input.nextInt();
                    input.nextLine();
                
                    int additionResult = firstInt + secondInt;
                    System.out.println("Answer: " + additionResult);
                }
                else {
                    System.out.println("Invalid input entered. Terminating...");
                }
                break;
            case "subtract":
                System.out.println("Enter two integers: ");
                
                if (input.hasNextInt()) {
                    int firstInt = input.nextInt();
                    int secondInt = input.nextInt();
                    input.nextLine();
                    
                    int subtractionResult = firstInt - secondInt;
                    System.out.println("Answer: " + subtractionResult);
                }
                else {
                    System.out.println("Invalid input entered. Terminating...");
                }
                break;
            case "multiply":
                System.out.println("Enter two doubles: ");
                if (input.hasNextDouble()) {
                    double firstDouble = input.nextDouble();
                    double secondDouble = input.nextDouble();
                    input.nextLine();
                    
                    double multiplicationResult = firstDouble * secondDouble;
                    System.out.printf("Answer: %.2f", multiplicationResult);
                    System.out.println();    
                }
                else {
                    System.out.println("Invalid input entered. Terminating...");
                }
                break;
            case "divide":
                System.out.println("Enter two doubles: ");
                if (input.hasNextDouble()) {
                double firstDouble = input.nextDouble();
                double secondDouble = input.nextDouble();
                input.nextLine();
                
                if (secondDouble == 0) {
                    System.out.println("Invalid input entered. Terminating..."); 
                    break;
                }
                double divisionResult = firstDouble / secondDouble;
                System.out.printf("Answer: %.2f", divisionResult);
                System.out.println();
                }
                else {
                    System.out.println("Invalid input entered. Terminating...");
                }
                break;
            case "alphabetize":
                System.out.println("Enter two words: ");
                String firstString = input.next();
                String secondString = input.next();
                input.nextLine();

                if (firstString.compareToIgnoreCase(secondString) < 0) {
                    System.out.printf("Answer: %s comes before %s alphabetically.", firstString, secondString);
                    System.out.println();
                }
                else if (firstString.compareToIgnoreCase(secondString) == 0) {
                    System.out.printf("Answer: Chicken or Egg.");
                    System.out.println();
                }
                else {
                    System.out.printf("Answer: %s comes before %s alphabetically.", secondString, firstString);
                    System.out.println();
                }
                break;
            default:
                System.out.println("Invalid input entered. Terminating...");
        }
    }
}