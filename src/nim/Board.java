package nim;

public class Board {
	
	private int[] rows = {3, 5, 7};

	public Board() {}

	public Board(int i, int j, int k) {
		rows[0] = i;
		rows[1] = j;
		rows[2] = k;
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
		if (oldBoard.rows[0] > this.rows[0]) {
			System.out.println((oldBoard.rows[0] - this.rows[0]) + " removed from row 1");
		}
		else if (oldBoard.rows[1] > this.rows[1]) {
			System.out.println((oldBoard.rows[1] - this.rows[1]) + " removed from row 2");
		}
		else if (oldBoard.rows[2] > this.rows[2]) {
			System.out.println((oldBoard.rows[2] - this.rows[2]) + " removed from row 3");
		}
		else {
			System.out.println("No change!");
		}
	}

	public void makeMove(int i, int x) {
		rows[i] -= x;
	}

}