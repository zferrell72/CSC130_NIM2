package nim;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
<<<<<<< HEAD
	private String name;
	private static Scanner input = new Scanner(System.in);
	
	public Player(String name)
	{
		this.name = name;
	}
	
	public Board takeTurn(Board board)
	{
		int row, x;
		board.displayBoard();
		System.out.println("Player " + name + "'s turn! Please choose a row(1, 2, or 3):");
		while(true)
		{
			try
			{
				row = input.nextInt();
				if(row > 3 || row < 1)
					throw new Exception();
				
				if(board.getRows()[row - 1] == 0)
				{
					System.out.println("That row is already empty! Please try another.");
					throw new Exception();
				}
					
					
				break;
			}
			catch(Exception e)
			{
				System.err.println("Invalid Input");
				System.out.println("You goofed. Please choose an integer 1, 2, or 3:");
			}
		}
		
		
		System.out.println("You have chosen row " + row + ". Now please choose a number to remove between 1 and " + board.getRows()[row - 1]);
		while(true)
		{
			try
			{
				x = input.nextInt();
				if(x > 0 && x <= board.getRows()[row - 1])
				{
					board.makeMove((row - 1), x);
					System.out.println(x + " removed from row " + row + ".");
				}
				else
				{
					throw new Exception();
				}
				
				break;
			}
			catch(Exception e)
			{
				System.err.println("Invalid Input");
				System.out.println("You goofed. Please choose an integer between 1 and " + board.getRows()[row - 1]);
			}
		}
		return board;
	}
=======
    private String name;
    private static Scanner input = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }

    public int getRowInput(Board board) {
        boolean inputIsValid = false;
        int row = 0;

        while (!inputIsValid) {
            try {
                row = input.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Invalid Input, Please choose an Integer");
            }

            if (row > 3 || row < 1) {
                System.out.println("Invalid choice! Please choose a row between 1 and 3");
            } else if (board.getRow(row) == 0) {
                System.out.println("That row is already empty! Please try another.");
            } else
                inputIsValid = true;
        }

        return row;
    }

    public int getXInput(int row, Board board) {
        boolean inputIsValid = false;
        int x = 0;

        while (!inputIsValid) {
            try {
                x = input.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Invalid Input. Please choose an Integer");
            }

            if (x < 0 || x > board.getRow(row)) {
                System.out.println("\nInvalid choice! please choose an integer between 1 and" + board.getRow(row) + "\n");
            } else
                inputIsValid = true;
        }
        return x;
    }

    public Board takeTurn(Board board) {
        int row, x;
        board.displayBoard();
        System.out.println("Player " + name + "'s turn! Please choose a row(1, 2, or 3):");
        row = getRowInput(board);
        System.out.println("You have chosen row " + row + ". Now please choose a number to remove between 1 and " + board.getRow(row));
        x = getXInput(row, board);

        board.makeMove(row, x);

        return board;
    }
>>>>>>> 2ef0336e03f98fbe36bbb4628ed63a2e5402526f
}
