package nim;

public class Board {
	
	private int[] rows = {3, 5, 7};
	public static final int TOP = 0, MID = 1, LOW = 2;

	public Board() {}

	public Board(int i, int j, int k) {
		rows[TOP] = i;
		rows[MID] = j;
		rows[LOW] = k;
	}
	
	public int[] getRows(){
		return rows;
	}
	
	public void displayBoard() {		
		for(int row: rows){
			addPadding(1);
			for(int i = 0; i < row; i++){
				System.out.print("*");
			}
		}
		addPadding(2);
	}

    private void addPadding(int lines) {
        for (int i = 0; i < lines; i++) {
            System.out.print("\n");
        }
    }


	public boolean isOver() {
		boolean isOver = true;
		for(int i = 0; i < rows.length && isOver; i++){
			isOver = (rows[i] == 0);
		}
		return isOver;
	}

	public void displayChanges(Board oldBoard) {
		if (oldBoard.rows[TOP] > this.rows[TOP]) {
			System.out.println((oldBoard.rows[TOP] - this.rows[TOP]) + " removed from row 1");
		}
		else if (oldBoard.rows[MID] > this.rows[MID]) {
			System.out.println((oldBoard.rows[MID] - this.rows[MID]) + " removed from row 2");
		}
		else if (oldBoard.rows[LOW] > this.rows[LOW]) {
			System.out.println((oldBoard.rows[LOW] - this.rows[LOW]) + " removed from row 3");
		}
		else {
			System.out.println("No change!");
		}
	}

	public void makeMove(int i, int x) {
		rows[i] -= x;
	}

}