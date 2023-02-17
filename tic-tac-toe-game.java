import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        char[][] board = new char[3][3];
        char currentPlayer = 'X';
        boolean isGameFinished = false;

        // Initialize the board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }

        // Game loop
        while (!isGameFinished) {
            // Print the board
            System.out.println("-------------");
            for (int row = 0; row < 3; row++) {
                System.out.print("| ");
                for (int col = 0; col < 3; col++) {
                    System.out.print(board[row][col] + " | ");
                }
                System.out.println();
                System.out.println("-------------");
            }

            // Get the player's move
            System.out.print("Player " + currentPlayer + ", enter row (1-3) and column (1-3): ");
            Scanner scanner = new Scanner(System.in);
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            // Check if the move is valid
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid move. Try again.");
            } else if (board[row][col] != ' ') {
                System.out.println("That square is already occupied. Try again.");
            } else {
                board[row][col] = currentPlayer;

                // Check if the game is over
                boolean hasWinner = checkWin(board, currentPlayer);
                boolean hasEmptySquare = false;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == ' ') {
                            hasEmptySquare = true;
                        }
                    }
                }
                if (hasWinner) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    isGameFinished = true;
                } else if (!hasEmptySquare) {
                    System.out.println("The game is a tie.");
                    isGameFinished = true;
                } else {
                    currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
                }
            }
        }
    }

    private static boolean checkWin(char[][] board, char player) {
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }
}
