package nim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Computer {
	
	private HashMap<Board, Record> boardState;
	
	public Computer() {
	
		boardState = new HashMap<Board, Record>();
	}
	
	public Board makeMove(Board theBoard) {
	
		ArrayList<Board> possibleMoves = generatePossibleMoves(theBoard);
		updateBoardHashMap(possibleMoves);
		
		Board newBoardState = determineBestMove(possibleMoves);
		
		return newBoardState;
	}
	
	private ArrayList<Board> generatePossibleMoves(Board theBoard) {
	
		ArrayList<Board> possibleMoves = new ArrayList<Board>();
		
		for (int i = (theBoard.getRow1() - 1); i >= 0; i--)
			possibleMoves.add(new Board(i, theBoard.getRow2(), theBoard.getRow3()));
		for (int i = (theBoard.getRow2() - 1); i >= 0; i--)
			possibleMoves.add(new Board(theBoard.getRow1(), i, theBoard.getRow3()));
		for (int i = (theBoard.getRow3() - 1); i >= 0; i--)
			possibleMoves.add(new Board(theBoard.getRow1(), theBoard.getRow2(), i));
		
		return possibleMoves;
	}
	
	private void updateBoardHashMap(ArrayList<Board> possibleMoves) {
	
		for (Board b : possibleMoves) {
			if (!boardState.containsKey(b)) {
				boardState.put(b, new Record());
			}
		}
	}
	
	private Board determineBestMove(ArrayList<Board> possibleMoves) {
	
		Board bestMoveState = null;
		Record bestRecord = null;
		Random gen = new Random();
		
		for (Board b : possibleMoves) {
			if (bestMoveState == null) {
				bestMoveState = b;
				bestRecord = boardState.get(b);
			} else {
				if (boardState.get(b).getWinRate() > bestRecord.getWinRate()) {
					bestMoveState = b;
					bestRecord = boardState.get(b);
				} else if (boardState.get(b).getWinRate() == bestRecord.getWinRate()) {
					if (gen.nextInt(10) == 5) {
						bestMoveState = b;
						bestRecord = boardState.get(b);
					}
				}
			}
		}
		
		return bestMoveState;
	}
	
	public void learnStuff(Board theBoard, boolean isWin) {
	
		boardState.get(theBoard).addRecord(isWin);
	}
	
}
