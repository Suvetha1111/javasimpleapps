import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Snake {
    private char[][] snakeBoard = null;
    Queue<Node> queue = new LinkedList<Node>();

    Snake(int row, int col) {
        this.snakeBoard = new char[row][col];
        
        // Initialize the board with 'O'
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                snakeBoard[i][j] = 'O';
            }
        }
        
        // Place obstacles ('X') on the board
        this.queue.add(new Node(0, 0));  // Snake starting position
        this.snakeBoard[1][0] = 'X';
        this.snakeBoard[2][2] = 'X';
        this.snakeBoard[3][4] = 'X';
        this.snakeBoard[5][2] = 'X';
    }

    public void snakeMove(int row, int col) {
        if (row >= 0 && row < snakeBoard.length && col >= 0 && col < snakeBoard[0].length) {
            if (snakeBoard[row][col] == '.') {
                System.out.println("Game Over");
                System.exit(0);
            }
            queue.add(new Node(row, col));
            if (snakeBoard[row][col] != 'X') {
                Node node = queue.poll();
                int r = node.getRow();
                int c = node.getColumn();
                snakeBoard[r][c] = 'O';  // Reset the old position to 'O'
            }
            snakeBoard[row][col] = '.';  // Mark the snake's current position
            
            printSnake();
            System.out.print("Enter a direction (u/d/l/r): ");
            Scanner s = new Scanner(System.in);
            char direction = s.next().charAt(0);

            if (direction == 'u') {
                snakeMove(--row, col);
            } else if (direction == 'd') {
                snakeMove(++row, col);
            } else if (direction == 'r') {
                snakeMove(row, ++col);
            } else if (direction == 'l') {
                snakeMove(row, --col);
            }
        } else {
            System.out.println("Invalid Move");
            System.exit(0);
        }
    }

    public void printSnake() {
        for (char[] chars : snakeBoard) {
            for (int j = 0; j < snakeBoard[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }
}
