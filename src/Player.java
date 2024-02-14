import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class maintains all the operations that a player can perform.
 */
public class Player {
    private String name;
    private Scanner sc;
    private int rowIndex; // Row index of a single placement
    private int colIndex; // Column index of a single placement

    private Player() {}

    private Random random = new Random();

    public Player(String name) {
        this.name = name;
        sc = new Scanner(System.in);
        this.rowIndex = -1;
        this.colIndex = -1;
    }

    public String getName() {
        return this.name;
    }

    public Scanner getSC() {
        return this.sc;
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public int getColIndex() {
        return this.colIndex;
    }

    /*
        A player can move a checker to place it on the board
     */


    public void move(GameBoard board, Cell cell) {
        int movePos;
        while (true) {
            NotificationCenter.boardPlacement(1, this.name,board.getBoardSize());
            board.printBoard();

            if (sc.hasNextInt()) {
                movePos = sc.nextInt();

                if (!board.isValidPosition(movePos)) {
                    // Input is an integer, but it leads to an illegal position on the board
                    NotificationCenter.boardPlacement(3, this.name, board.getBoardSize());
                    continue;
                }

                if (!board.isValidPosition(movePos)) {
                    // Input is an integer, but it leads to an illegal position on the board
                    NotificationCenter.boardPlacement(3, this.name, board.getBoardSize());
                    continue;
                } else {
                    // Input is a valid integer which points to an open vacancy on the board
                    break;
                }
            } else {
                // Input is not an integer
                sc.next();
                NotificationCenter.boardPlacement(2, this.name, board.getBoardSize());
            }
        }

        // Place the move to the board
        placeTheMove(board, cell, movePos);
    }

    /*
        Place the move that a player made to the game board
     */


    public void placeTheMove(GameBoard gameBoard, Cell cell, int movePosition) {

        int count = 1, i = 0, j = 0;
        boolean flag = true;

        for (i = 0; i < gameBoard.getBoard().length; ++i) {
            for (j = 0; j < gameBoard.getBoard()[i].length; ++j) {
                if (count == movePosition) {
                    gameBoard.getBoard()[i][j] = cell;
                    flag = false; break;
                }
                ++count;
            }
            if (!flag) {break;}
        }

        this.rowIndex = i;
        this.colIndex = j;
    }



    public void computerMove(GameBoard board, Cell cell) {
        board.printBoard();
        List<Integer> availableMoves = new ArrayList<>();
        int count = 0;
        for (int row = 0; row < board.getBoardSize(); row++) {
            for (int col = 0; col < board.getBoardSize(); col++) {
                count++;
                if ( board.getBoard()[row][col]  == Cell.EMPTY) {
                    availableMoves.add(count);
                }
            }
        }
        int movePos = new Random().nextInt(availableMoves.size());
        movePos = availableMoves.get(movePos);
        NotificationCenter.computerMoves(1, this.name,board.getBoardSize(),movePos);
        // Place the move to the board
        placeTheMove(board, cell, movePos);
    }



}