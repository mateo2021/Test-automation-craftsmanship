package Projekt1;

public class Game {
	private char[][] gameBoard;
	public char Mark;

	public Game() {
		gameBoard = new char[3][3];
		Mark = 'X';
		createGameBoard();
	}

	public void createGameBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameBoard[i][j] = '-';
			}
		}
	}

	public void changePlayer() {
		if (Mark == 'X') {
			Mark = 'O';
		} else {
			Mark = 'X';
		}
	}

	boolean checkMarks(char mark1, char mark2, char mark3) {
		return ((mark1 != '-') && (mark1 == mark2) && (mark2 == mark3));
	}

	public void addMark(int row, int column) throws Exception {
		if ((row >= 0) && (row < 3)) {
			if ((column >= 0) && (column < 3)) {
				if (gameBoard[row][column] == '-') {
					gameBoard[row][column] = Mark;
				} else
					throw new Exception("Pozycja zajeta lub zly znak");
			} else
				throw new Exception("Kolumna poza tablica");
		} else
			throw new Exception("Wiersz poza tablica");
	}

	public boolean checkDiagonals() {
		return ((checkMarks(gameBoard[0][0], gameBoard[1][1], gameBoard[2][2]) == true)
				|| (checkMarks(gameBoard[0][2], gameBoard[1][1], gameBoard[2][0]) == true));
	}

	public boolean checkRows() {
		for (int i = 0; i < 3; i++) {
			if (checkMarks(gameBoard[i][0], gameBoard[i][1], gameBoard[i][2]) == true) {
				return true;
			}
		}
		return false;
	}

	public boolean checkColumns() {
		for (int i = 0; i < 3; i++) {
			if (checkMarks(gameBoard[0][i], gameBoard[1][i], gameBoard[2][i]) == true) {
				return true;
			}
		}
		return false;
	}

	public boolean isDraw() {
		boolean isGameBoardFull = true;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gameBoard[i][j] == '-') {
					isGameBoardFull = false;
				}
			}
		}

		return isGameBoardFull;
	}

	public boolean checkWinner() {
		return (checkRows() || checkColumns() || checkDiagonals());
	}
}
