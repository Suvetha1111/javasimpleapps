public class Board {
    private char[][] board;
    private int boardSize;

    public Board(int size) {
        this.boardSize = size;
        this.board = new char[size][size];
        initializeBoard();
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = (char) ('1' + i * boardSize + j);
            }
        }
    }

    public void designBoard() {
        System.out.println("\n--->TIC TAC TOE GAME<<--\n");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print("\t" + board[i][j] + " ");
                if (j < boardSize - 1) System.out.print("|");
            }
            System.out.println();
            if (i < boardSize - 1) {
                System.out.println("\t" + "-".repeat(boardSize * 2 - 1));
            }
        }
        System.out.println();
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize && board[row][col] != 'X' && board[row][col] != 'O';
    }

    public void makeMove(int row, int col, char mark) {
        board[row][col] = mark;
    }

    public int checkWin() {
        for (int i = 0; i < boardSize; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return 1;
            }
        }
        if (checkMainDiagonal() || checkAntiDiagonal()) {
            return 1;
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return -1; // Game still ongoing
                }
            }
        }
        return 0; // Draw
    }

    private boolean checkRow(int row) {
        char first = board[row][0];
        if (first == 'X' || first == 'O') {
            for (int col = 1; col < boardSize; col++) {
                if (board[row][col] != first) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkColumn(int col) {
        char first = board[0][col];
        if (first == 'X' || first == 'O') {
            for (int row = 1; row < boardSize; row++) {
                if (board[row][col] != first) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkMainDiagonal() {
        char first = board[0][0];
        if (first == 'X' || first == 'O') {
            for (int i = 1; i < boardSize; i++) {
                if (board[i][i] != first) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean checkAntiDiagonal() {
        char first = board[0][boardSize - 1];
        if (first == 'X' || first == 'O') {
            for (int i = 1; i < boardSize; i++) {
                if (board[i][boardSize - 1 - i] != first) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
