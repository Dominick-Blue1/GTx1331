import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[][] userArray = new int[5][5];

        for (int i = 0; i < userArray.length; i++)
            for(int j = 0; j < userArray[i].length; j++)
            {
                System.out.println("Please enter numbers between -10 and 10 for your matrix");
                int valueIn = input.nextInt();
                while (valueIn > 10 || valueIn < -10)
                {
                    System.out.println("Sorry. Your number is not the correct size. " + "Please enter a number between 10 and -10.");
                    valueIn = input.nextInt();
                }
                userArray[i][j]=valueIn;
            }
    }

    
}
