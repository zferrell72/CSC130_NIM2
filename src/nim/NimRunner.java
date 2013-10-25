package nim;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NimRunner {
        private static Scanner input = new Scanner(System.in);
        private static Computer comp = new Computer(), comp2 = new Computer();
        
        private enum Operations {
        	HUMAN_HUMAN("[0] Human vs Human"){
        		public void operate(){
        			HvH();
        		}
        	},
        	HUMAN_COMP("[1] Human vs Computer"){
        		public void operate(){
        			HvC();
        		}
        	},
        	COMP_COMP("[2] Computer vs Computer"){
        		public void operate(){
        			System.out.println("How many games would you like the game to play?");
                    String choice = input.nextLine();
                    int games = Integer.parseInt(choice);
        			CvC(games);
        		}
        	},
        	LEARN("[3] Learn"){
        		public void operate(){
        			Learn();
        		}
        	},
        	EXIT("[4] Exit"){
        		public void operate(){
        			System.out.println("Exiting game...");
        			System.exit(-1);
        		}
        	};
        	
        	private String text;
        	Operations(String string){
        		text = string;
        	}
        	
        	public String getText(){
        		return text;
        	}
        	
        	public void operate(){}
        }

        public static void main(String args[])
        {
                runMenu();
        }
        
        public static void runMenu()
        {
        	Operations[] menu = Operations.values();
        	boolean isValid = false;
        	
        	while(!isValid){
        		System.out.println("Please choose a game mode:");
        		for(Operations operator : menu){
        			System.out.println(operator.getText());
        		}
        		
        		String choice = input.nextLine();
        		int selection = Integer.parseInt(choice);
        		menu[selection].operate();
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

