public class practice {

    static final int N = 4; // Change N to the desired board size

    static int solveNQueens(int[][] board, int col) {
        if (col == N) {
            printSolution(board);
            return 1;
        }
        int count=0;
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;
                count+=solveNQueens(board, col + 1);
                board[i][col] = 0; // backtrack
            }
        }
        return count;
    }

    static boolean isSafe(int[][] board, int row, int col) {
        // Check this row on left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal on left side
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    static void printSolution(int[][] board) {
        System.out.println("Solution:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[][] board = new int[N][N];
        int count=solveNQueens(board, 0);
        System.out.println("Number of possible solutions: "+count);
    }
}
