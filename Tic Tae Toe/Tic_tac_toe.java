import java.util.Scanner;

public class TicTacToe {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public TicTacToe(int size) {
        this.board = new Board(size);
        this.player1 = new Player(1, 'X');
        this.player2 = new Player(2, 'O');
        this.currentPlayer = player1;  // Start with player 1
    }

    public void startGame() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            board.designBoard();
            System.out.printf("\nEnter row and column (1 to %d) for player %d (row col): ", board.getBoardSize(), currentPlayer.getPlayerId());

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Enter numbers.");
                sc.next(); // Clear invalid input
                continue;
            }

            int row = sc.nextInt() - 1;
            int col = sc.nextInt() - 1;

            if (!board.isValidMove(row, col)) {
                System.out.println("Invalid input. Enter a valid position.");
                continue;
            }

            board.makeMove(row, col, currentPlayer.getMark());

            int result = board.checkWin();
            if (result == 1) {
                board.designBoard();
                System.out.printf("\nPlayer %d Wins!\n", currentPlayer.getPlayerId());
                break;
            } else if (result == 0) {
                board.designBoard();
                System.out.println("Match draw!");
                break;
            }

            switchPlayer();
        }
        sc.close();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the board (n x n): ");
        int boardSize = sc.nextInt();
        TicTacToe game = new TicTacToe(boardSize);
        game.startGame();
    }
}
