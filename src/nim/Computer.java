package nim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Computer {
	
	private HashMap<Board, Record> boardState;
	
	public static Random gen = new Random();
	
	public Computer(){
		boardState = new HashMap<Board, Record>();
	}
	
	public Board makeMove(Board theBoard){
		ArrayList<Board> possibleMoves = generatePossibleMoves(theBoard.getRows());
		updateBoardHashMap(possibleMoves);
		
		Board newBoardState = determineBestMove(possibleMoves);

		return newBoardState;
	}
	
	private ArrayList<Board> generatePossibleMoves(int[] boardRows){
		ArrayList<Board> possibleMoves = new ArrayList<Board>();
		
		for(int i = (boardRows[0] - 1); i >= 0; i--)
			possibleMoves.add(new Board(i, boardRows[1], boardRows[2]));
		
		for(int i = (boardRows[1] - 1); i >= 0; i--)
			possibleMoves.add(new Board(boardRows[0], i, boardRows[2]));
		
		for(int i = (boardRows[2] - 1); i >= 0; i--)
			possibleMoves.add(new Board(boardRows[0], boardRows[1], i));
		
		return possibleMoves;
	}
	
	private void updateBoardHashMap(ArrayList<Board> possibleMoves){
		for(Board b : possibleMoves){
			if(!boardState.containsKey(b)){
				boardState.put(b, new Record());
			}
		}
	}
	
	private Board determineBestMove(ArrayList<Board> possibleMoves){
		Board bestMoveState = null;
		Record bestRecord = null;
		
		for(Board b : possibleMoves){
			if(bestMoveState == null){
				bestMoveState = b;
				bestRecord = boardState.get(b);
			}
			else{
				if(boardState.get(b).getWinRate() > bestRecord.getWinRate()){
					bestMoveState = b;
					bestRecord = boardState.get(b);
				}
				else if(boardState.get(b).getWinRate() == bestRecord.getWinRate()){
					if(gen.nextInt(10) == 5){
						bestMoveState = b;
						bestRecord = boardState.get(b);
					}
				}
			}
		}
		
		return bestMoveState;
	}
	
	public void learnStuff(Board theBoard, boolean isWin){
		boardState.get(theBoard).addRecord(isWin);
	}
	
}
