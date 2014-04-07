
package ant.g2048.game;

import java.util.Arrays;
import java.util.Random;

public class Board {

    public enum Direction {
        UP, LEFT, DOWN, RIGHT
    };

    private static final Random random = new Random();

    private final int rows;

    private final int cols;

    private final int[][] board;

    private final int free;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new int[rows][cols];
        free = rows * cols;
    }

    public Board(int[][] board, int free) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;
        this.free = free;
    }

    public boolean isFull() {
        return free == 0;
    }

    public Board move(Direction direction) {
        int[][] newBoard = deepCopyBoard();
        int growths = 0;
        switch (direction) {
            case UP:
                growths =moveVertical(newBoard, -1);
                break;
            case LEFT:
                growths =moveHorizontal(newBoard, -1);
                break;
            case DOWN:
                growths =moveVertical(newBoard, 1);
                break;
            case RIGHT:
                growths =moveHorizontal(newBoard, 1);
                break;
        }
        return new Board(newBoard, free + growths);
    }

    private int moveVertical(int[][] board, int dRow) {
        int growths = 0;
        for (int col = 0; col < cols; col++) {
            growths += moveVertical(board, col, dRow);
        }
        return growths;
    }

    private int moveHorizontal(int[][] board, int dCol) {
        int growths = 0;
        for (int row = 0; row < rows; row++) {
            growths += moveHorizontal(board, row, dCol);
        }
        return growths;
    }

    private int moveVertical(int[][] board, int col, int dRow) {
        int growths = 0;
        for (int row = (dRow == -1) ? 0 : (rows-1); validRow(row); row -= dRow) {
            int val = board[row][col];
            if (val > 0) {
                board[row][col] = 0;
                int r = row + dRow;
                while (validRow(r) && board[r][col] == 0) {
                    r += dRow;
                }
                if (validRow(r) && board[r][col] == val) {
                    board[r][col] = val +1;
                    growths++;
                } else {
                    board[r-dRow][col] = val;
                }

            }
        }
        return growths;
    }

    private int moveHorizontal(int[][] board, int row, int dCol) {
        int growths = 0;
        for (int col = (dCol == -1) ? 0 : (cols-1); validCol(col); col -= dCol) {
            int val = board[row][col];
            if (val > 0) {
                board[row][col] = 0;
                int c = col + dCol;
                while (validCol(c) && board[row][c] == 0) {
                    c += dCol;
                }
                if (validCol(c) && board[row][c] == val) {
                    board[row][c] = val +1;
                    growths++;
                } else {
                    board[row][c-dCol] = val;
                }

            }
        }
        return growths;
    }

    private boolean validRow(int row) {
        return row < rows && row >= 0;
    }

    private boolean validCol(int col) {
        return col < cols && col >= 0;
    }

    public Board placeRandom() {
        int[][] newBoard = deepCopyBoard();
        placeRandom(newBoard);
        return new Board(newBoard, free-1);
    }

    private void placeRandom(int[][] newBoard) {
        int p = random.nextInt(free);
        int val = (random.nextInt(2)) +1; // 1 or 2
        int freeCount = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (newBoard[r][c] == 0) {
                    if (freeCount == p) {
                        newBoard[r][c] = val;
                        return;
                    } else {
                        freeCount++;
                    }
                }
            }
        }
    }

    private int[][] deepCopyBoard() {
        int[][] newBoard = new int[rows][];
        for (int r = 0; r < rows; r++) {
            newBoard[r] = Arrays.copyOf(board[r], board.length);
        }
        return newBoard;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(rows * (cols + 2));
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                sb.append(board[r][c] == 0 ? "." : board[r][c]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
