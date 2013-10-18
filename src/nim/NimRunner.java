package nim;

import java.util.ArrayList;
import java.util.Scanner;

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
                int choice = -1;
                
                boolean done = false;
                while(!done)
                {
                        done = true;
                        
                        try{choice = input.nextInt();}
                        catch(Exception e)
                        {
                                choice = -1;
                        }
                        
                        
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
                                System.out.println("How many games would you like the game to play?");
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
                Player p1 = new Player("1"), p2 = new Player("2");
                boolean isRunning = true, turn = true;
                Board board = new Board();
                
                while(isRunning)
                {
                        if(board.isOver())
                        {
                                System.out.println("Game over! Player " + (turn?"1 ":"2 ") + "wins!");
                                isRunning = false;
                        }
                        else if(turn)
                        {
                                board = p1.takeTurn(board);
                                turn = turn?false:true;
                        }
                        else
                        {
                                board = p2.takeTurn(board);
                                turn = turn?false:true;
                        }
                }
                runMenu();
        }
        
        public static void HvC()
        {
                Player p = new Player("Player");
                boolean isRunning = true, turn = true;
                Board board = new Board();
                ArrayList<Board> boards = new ArrayList<Board>();
                
                while(isRunning)
                {
                        if(board.isOver())
                        {
                                System.out.println("Game over! " + (turn?"Player ":"Computer ") + "wins!");
                                isRunning = false;
                                for(Board b : boards)
                                {
                                        comp.learnStuff(b, turn);
                                        turn = turn?false:true;
                                }
                        }
                        else if(turn)
                        {
                                board = p.takeTurn(board);
                                turn = turn?false:true;
                        }
                        else
                        {
                                System.out.println("Computer Turn in Progress");
                                Board oldBoard = board;
                                board = comp.makeMove(board);
                                turn = turn?false:true;
                                board.displayChanges(oldBoard);
                                System.out.println("Computer Turn Completed");
                        }
                        
                        
                        boards.add(board);
                }
                runMenu();
        }
        
        public static void CvC(int games)
        {
                boolean isRunning = true, turn = true;
                Board board = new Board();
                ArrayList<Board> boards = new ArrayList<Board>();
                
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
                                        for(Board b : boards)
                                        {
                                                comp2.learnStuff(b, turn);
                                                turn = turn?false:true;
                                                comp.learnStuff(b, turn);
                                        }
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
                                boards.add(board);
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

