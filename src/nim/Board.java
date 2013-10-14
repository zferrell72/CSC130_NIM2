package nim;

public class Board {
	private int row1, row2, row3;
	public static final int ROW1 = 1, ROW2 = 2, ROW3 = 3;

	public Board() {
		row1 = 3;
		row2 = 5;
		row3 = 7;
	}

	public Board(int i, int j, int k) {
		row1 = i;
		row2 = j;
		row3 = k;
	}

	public void displayBoard() {
		System.out.println();

		for (int i = 0; i < row1; i++) {
			System.out.print("*");
		}
		System.out.println();

		for (int i = 0; i < row2; i++) {
			System.out.print("*");
		}
		System.out.println();

		for (int i = 0; i < row3; i++) {
			System.out.print("*");
		}
		System.out.println("\n");
	}

	public boolean isOver() {
		if (row1 == 0 && row2 == 0 && row3 == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void displayChanges(Board b) {
		if (b.getRow1() != this.getRow1()) {
			if (b.getRow1() > this.getRow1()) {
				System.out.println((b.getRow1() - this.getRow1())
						+ " removed from row 1");
			} else {
				System.out.println((this.getRow1() - b.getRow1())
						+ " removed from row 1");
			}
		} else if (b.getRow2() != this.getRow2()) {
			if (b.getRow2() > this.getRow2()) {
				System.out.println((b.getRow2() - this.getRow2())
						+ " removed from row 2");
			} else {
				System.out.println((this.getRow2() - b.getRow2())
						+ " removed from row 2");
			}
		} else if (b.getRow3() != this.getRow3()) {
			if (b.getRow3() > this.getRow3()) {
				System.out.println((b.getRow3() - this.getRow3())
						+ " removed from row 3");
			} else {
				System.out.println((this.getRow3() - b.getRow3())
						+ " removed from row 3");
			}
		} else {
			System.out.println("No change!");
		}
	}

	public void makeMove(int i, int x) {
		if (i == ROW1) {
			row1 -= x;
		} else if (i == ROW2) {
			row2 -= x;
		} else if (i == ROW3) {
			row3 -= x;
		} else {
			System.err.println("INVALID INPUT");
		}
	}

	public int getRow(int i) {
		if (i == ROW1) {
			return row1;
		} else if (i == ROW2) {
			return row2;
		} else {
			return row3;
		}
	}

	public int getRow1() {
		return row1;
	}

	public int getRow2() {
		return row2;
	}

	public int getRow3() {
		return row3;
	}
}