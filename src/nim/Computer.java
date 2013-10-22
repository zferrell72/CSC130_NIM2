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
		
		for(int i = (boardRows[Board.TOP] - 1); i >= 0; i--)
			possibleMoves.add(new Board(i, boardRows[Board.MID], boardRows[Board.TOP]));
		
		for(int i = (boardRows[Board.MID] - 1); i >= 0; i--)
			possibleMoves.add(new Board(boardRows[Board.TOP], i, boardRows[Board.LOW]));
		
		for(int i = (boardRows[Board.LOW] - 1); i >= 0; i--)
			possibleMoves.add(new Board(boardRows[Board.TOP], boardRows[Board.MID], i));
		
		return possibleMoves;
	}
	
	private void updateBoardHashMap(ArrayList<Board> possibleMoves){
		for(Board possibleMove : possibleMoves){
			if(!boardState.containsKey(possibleMove)){
				boardState.put(possibleMove, new Record());
			}
		}
	}
	
	private Board determineBestMove(ArrayList<Board> possibleMoves){
		Board bestMoveState = null;
		Record bestRecord = null;
		
		for(Board possibleMove : possibleMoves){
			if(bestMoveState == null){
				bestMoveState = possibleMove;
				bestRecord = boardState.get(possibleMove);
			}
			else{
				if(boardState.get(possibleMove).getWinRate() > bestRecord.getWinRate()){
					bestMoveState = possibleMove;
					bestRecord = boardState.get(possibleMove);
				}
				else if(boardState.get(possibleMove).getWinRate() == bestRecord.getWinRate()){
					if(gen.nextInt(10) == 5){
						bestMoveState = possibleMove;
						bestRecord = boardState.get(possibleMove);
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
