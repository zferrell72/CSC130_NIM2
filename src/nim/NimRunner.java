package nim;

import java.util.Scanner;

import nim.Board;
import nim.Computer;

public class NimRunner {
	private static Scanner input;
	private static Computer comp = new Computer(), comp2 = new Computer();

	public static void main(String args[])
	{
		runMenu();
	}
	
	public static void runMenu()
	{
		input = new Scanner(System.in);
		System.out.println("Please choose a game mode:\n[1]Human vs Human\n[2]Human vs Computer\n[3]Computer vs Computer\n[4]Learn\n[0]Exit");
		int choice = input.nextInt();
		
		boolean done = false;
		while(!done)
		{
			done = true;
			
			switch(choice)
			{
			case(0):
				System.exit(0);
			break;
			case(1):
				HvH();
			break;
			case(2):
				HvC();
			break;
			case(3):
				System.out.println("How many ames would you like the game to play?");
				int games = input.nextInt();
				CvC(games);
			break;
			case(4):
				Learn();
			break;
			default:
				System.out.println("Invalid input. Please enter an integer 0-4.");
				done = false;
			break;
			}
		}
	}
	
	public static void HvH()
	{
		boolean isRunning = true, turn = true;
		Board board = new Board();
		
		while(isRunning)
		{
			if(board.isOver())
			{
				System.out.println("Game over! Player " + (turn?"1 ":"2 ") + "wins!");
				isRunning = false;
			}
			else
			{
				int row, x;
				board.displayBoard();
				System.out.println("Player " + (turn?"1's ":"2's ") + "turn! Please choose a row(1, 2, or 3):");
				while(true)
				{
					try
					{
						row = input.nextInt();
						if(row > 3 || row < 1)
							throw new Exception();
						
						if(board.getRow(row) == 0)
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
				
				System.out.println("You have chosen row " + row + ". Now please choose a number to remove between 1 and " + board.getRow(row));
				while(true)
				{
					try
					{
						x = input.nextInt();
						if(x > 0 && x <= board.getRow(row))
						{
							board.makeMove(row, x);
							System.out.println(x + " removed from row " + row + ".");
							turn = turn?false:true;
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
						System.out.println("You goofed. Please choose an integer between 1 and " + board.getRow(row));
					}
				}
				
			}
		}
		runMenu();
	}
	
	public static void HvC()
	{
		boolean isRunning = true, turn = true;
		Board board = new Board();
		
		while(isRunning)
		{
			if(board.isOver())
			{
				System.out.println("Game over! " + (turn?"Player ":"Computer ") + "wins!");
				isRunning = false;
			}
			else if(turn)
			{
				int row, x;
				board.displayBoard();
				System.out.println("Players turn! Please choose a row(1, 2, or 3):");
				while(true)
				{
					try
					{
						row = input.nextInt();
						if(row > 3 || row < 1)
							throw new Exception();
						
						if(board.getRow(row) == 0)
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
				
				System.out.println("You have chosen row " + row + ". Now please choose a number to remove between 1 and " + board.getRow(row));
				while(true)
				{
					try
					{
						x = input.nextInt();
						if(x > 0 && x <= board.getRow(row))
						{
							board.makeMove(row, x);
							System.out.println(x + " removed from row " + row + ".");
							turn = turn?false:true;
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
						System.out.println("You goofed. Please choose an integer between 1 and " + board.getRow(row));
					}
				}
			}
			else
			{
				System.out.println("Computer Turn in Progress");
				Board oldBoard = board;
				board = comp.makeMove(board);
				turn = turn?false:true;
				System.out.println("Computer Turn Completed");
			}
		}
		runMenu();
	}
	
	public static void CvC(int games)
	{
		boolean isRunning = true, turn = true;
		Board board = new Board();
		
		for(int i = 0; i < games; i++)
		{
			isRunning = true;
			board = new Board();
			
			while(isRunning)
			{
				if(board.isOver())
				{
					System.out.println("\nGame over! " + (turn?"Computer1 ":"Computer2 ") + "wins!\n");
					isRunning = false;
				}
				else if(turn)
				{
					System.out.println("Main Computer Turn in Progress");
					Board oldBoard = board;
					board = comp.makeMove(board);
					turn = turn?false:true;
					board.displayChanges(oldBoard);
					System.out.println("Main Computer Turn Completed");
				}
				else
				{
					System.out.println("Computer 2 Turn in Progress");
					Board oldBoard = board;
					board = comp2.makeMove(board);
					turn = turn?false:true;
					board.displayChanges(oldBoard);
					System.out.println("Computer 2 Turn Completed");
				}
			}
		}
		runMenu();
	}
	
	public static void Learn()
	{
		System.out.println("Not Implemented in this version! sorry!");
		runMenu();
	}
}
