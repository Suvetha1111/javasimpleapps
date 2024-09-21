
import java.util.Random;
import java.util.Scanner;

public class PuzzleGame {

    private int size;
    private int[][] puzzle;
    private int emptyRow, emptyCol;

    public PuzzleGame(int size) {
        this.size = size;
        this.puzzle = new int[size][size];
        initializePuzzle();
    }

    private void initializePuzzle() {
        int[] numbers = new int[size * size];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }

        // Shuffling the array
        Random rand = new Random();
        for (int i = numbers.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }

        // Fill the puzzle grid
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                puzzle[i][j] = numbers[index++];
                if (puzzle[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                }
            }
        }
    }

    private void displayPuzzle() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (puzzle[i][j] == 0) {
                    System.out.print("   "); // Print empty space
                } else {
                    System.out.printf("%3d", puzzle[i][j]);
                }
            }
            System.out.println();
        }
    }

    // Check if the puzzle is solved
    private boolean isSolved() {
        int correct = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size - 1 && j == size - 1) {
                    return puzzle[i][j] == 0;
                }
                if (puzzle[i][j] != correct++) {
                    return false;
                }
            }
        }
        return true;
    }

    // Move empty space if possible
    private void moveTile(char direction) {
        int newRow = emptyRow, newCol = emptyCol;
        switch (direction) {
            case 'u':
                newRow--;
                break; // move up
            case 'd':
                newRow++;
                break; // move down
            case 'l':
                newCol--;
                break; // move left
            case 'r':
                newCol++;
                break; // move right
            default:
                System.out.println("Invalid move! Use w, a, s, d.");
                return;
        }

        // Check boundaries
        if (newRow >= 0 && newRow < size && newCol >= 0 && newCol < size) {
            // Swap empty space with adjacent one
            puzzle[emptyRow][emptyCol] = puzzle[newRow][newCol];
            puzzle[newRow][newCol] = 0;
            emptyRow = newRow;
            emptyCol = newCol;
        } else {
            System.out.println("Invalid move!");
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (!isSolved()) {
            displayPuzzle();
            System.out.println("Move (u=up, d=down, l=left, r=right): ");
            char move = scanner.next().charAt(0);
            moveTile(move);
        }
        displayPuzzle();
        System.out.println("Congratulations! You solved the puzzle.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter puzzle size (e.g., 4 for 4x4 puzzle): ");
        int size = scanner.nextInt();

        PuzzleGame game = new PuzzleGame(size);
        game.play();
    }
}
