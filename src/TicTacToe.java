import java.util.Scanner;
/**
 * This class manages all the logics BEFORE, DURING, and AFTER the game of Tic Tac Toe.
 */
final public class TicTacToe {

    private static String AIBotName = "TicTacToe Master";

    private static String AIBotName2 = "The Grid Grandmaster";

    private static Scanner sc;
    private static String nameOfPlayer1;
    private static String nameOfPlayer2;
    private static boolean selectFirstPlayerAsX;
    private static boolean playerPlaysFirst; // For Multi player game
    private static int boardSize;
    private static int rowPlacement; // Row index of a single placement
    private static int colPlacement; // Column index of a single placement
    private static int numberOfPlacements; // Total number of placements during the game

    private Player player1;
    private Player player2;
    private GameBoard gameBoard;

    private int code = 0;

    /*
        "The value x of rows[1][1]" indicates:
            - Player 1 has made x placements on row 1
            - So if rows[i][j] = boardSize, we know that player i has won
            - Columns and diagonals have just the same logics
     */
    private int[][] rows;
    private int[][] columns;
    private int[][] diagonals;


    public TicTacToe() {
        sc = new Scanner(System.in);

        nameOfPlayer1 = "";
        nameOfPlayer2 = "";
        selectFirstPlayerAsX = false;
        boardSize = 3;
        rowPlacement = -1;
        colPlacement = -1;
        numberOfPlacements = 0;
        playerPlaysFirst = false;


        this.prepare();
        this.getPlayerNames();

        this.player1 = new Player(nameOfPlayer1);
        this.player2 = new Player(nameOfPlayer2);
        this.gameBoard = new GameBoard(boardSize);

        this.rows = new int[3][boardSize];
        this.columns = new int[3][boardSize];
        this.diagonals = new int[3][2];
    }

    /*
        This is the chief method of the class
     */
    public void play() {
        int playerID = 0, winnerID = 0;

        while (true) {
            // Only when we start a new game, we make sure first player is X
            /*
            DURING the game -> Choose a player 1 to play as a 'X'
            */
            if (selectFirstPlayerAsX) {
                playerID = 1;
                selectFirstPlayerAsX = false;
            }

            if(code == 1){

                playerID = this.playerTurn(playerID); // Players take turn to play
            }
            else if (code ==2) ///Player Vs Computer code goes here
            {

                playerID = this.playerOrComputerTurn(playerID,this.playerPlaysFirst); // Players take turn to play

            }
            else if (code ==3 ) ///Player Vs Computer code goes here
            {

                playerID = this.ComputerorComputerplayerTurn(playerID); // Players take turn to play

            }

//            playerID = this.playerTurn(playerID ); // Players take turn to play
            ++numberOfPlacements;
            winnerID = this.checkWinner(rowPlacement, colPlacement, playerID);

            if (winnerID == 1) {
                NotificationCenter.winnerCongratulations(player2.getName());

            } else if (winnerID == 2) {
                NotificationCenter.winnerCongratulations(player1.getName());

            } else if ((numberOfPlacements == 9) && (winnerID == -1)) {
                NotificationCenter.stalemateAnnouncement();
            } else {
                continue; // IMPORTANT! If no winner generated, skip the code below.
            }

            this.gameBoard.printBoard(); // Update and print the current game board

            // Close all the Scanners
            sc.close();
            this.player1.getSC().close();
            this.player2.getSC().close();
            System.exit(0);

        }
    }

    /*
        BEFORE the game -> Display welcome messages and other important notifications
     */
    private void prepare() {
        NotificationCenter.welcome();
        final String DECISION = sc.nextLine();
        this.code = NotificationCenter.startOrExit(DECISION);

        if (this.code == 0) {System.exit(0);}
    }
    /*
        DURING the game -> Ask for the names of the two players and their preferred board size
     */
    private void getPlayerNames() {
        if (this.code == 1) {
            NotificationCenter.namesAndSize(1);
            nameOfPlayer1 = sc.nextLine();
            NotificationCenter.namesAndSize(2);
            nameOfPlayer2 = sc.nextLine();
            selectFirstPlayerAsX = true;
        }
        else if (this.code == 2) {
            NotificationCenter.namesAndSize(3);
            String playerMoveChoice = sc.nextLine();
            if (playerMoveChoice.equalsIgnoreCase("Y")) {
                NotificationCenter.namesAndSize(4);
                nameOfPlayer1 = sc.nextLine();
                nameOfPlayer2 = this.AIBotName;
                selectFirstPlayerAsX = true; // Selecting Human as a Player 1
                playerPlaysFirst = true;
            }

            if (playerMoveChoice.equalsIgnoreCase("N")) {
                nameOfPlayer1 = this.AIBotName;
                NotificationCenter.namesAndSize(4);
                nameOfPlayer2 = sc.nextLine();
                selectFirstPlayerAsX = true; // Selecting Computer as a Player 1
                playerPlaysFirst = false;


            }
        }
        else if (this.code == 3) {

//            NotificationCenter.namesAndSize(1);
            nameOfPlayer1 = this.AIBotName;
            nameOfPlayer2 = this.AIBotName2;
            selectFirstPlayerAsX = true;
        }

    }

    /*
        DURING the game -> Let the designated player play
     */
    private int playerTurn(int playerID) {
        if (playerID == 1) {

            this.player1.move(this.gameBoard, Cell.X);
            rowPlacement = this.player1.getRowIndex();
            colPlacement = this.player1.getColIndex();
            playerID = 2; // Player should take turns to play
        } else {
            this.player2.move(this.gameBoard, Cell.O);
            rowPlacement = this.player2.getRowIndex();
            colPlacement = this.player2.getColIndex();
            playerID = 1; // Player should take turns to play
        }

        return playerID;
    }
    private int ComputerorComputerplayerTurn(int playerID) {
        if (playerID == 1) {

//            this.player1.move(this.gameBoard, Cell.X);
            this.player1.computerMove(this.gameBoard, Cell.X);
            rowPlacement = this.player1.getRowIndex();
            colPlacement = this.player1.getColIndex();
            playerID = 2; // Player should take turns to play
        } else {
//            this.player2.move(this.gameBoard, Cell.O);
            this.player2.computerMove(this.gameBoard, Cell.O);
            rowPlacement = this.player2.getRowIndex();
            colPlacement = this.player2.getColIndex();
            playerID = 1; // Player should take turns to play
        }

        return playerID;
    }

    private int playerOrComputerTurn(int playerID, boolean playerPlaysFirst) {


        // playerfirst true then player 1 is User and player 2 is computer
        // playerfirst false then player 1 is Computer and player 1 is User
        if (playerPlaysFirst == true) {
            if (playerID == 1) {

                this.player1.move(this.gameBoard, Cell.X);
                rowPlacement = this.player1.getRowIndex();
                colPlacement = this.player1.getColIndex();
                playerID = 2; // Player should take turns to play
            } else {


                this.player2.computerMove(this.gameBoard, Cell.O);
                rowPlacement = this.player2.getRowIndex();
                colPlacement = this.player2.getColIndex();
                playerID = 1; // Player should take turns to play
            }
        }
        else if (playerPlaysFirst == false) {
            if (playerID == 1) {

                this.player1.computerMove(this.gameBoard, Cell.X);
//                this.player1.move(this.gameBoard, Cell.X);
                rowPlacement = this.player1.getRowIndex();
                colPlacement = this.player1.getColIndex();
                playerID = 2; // Player should take turns to play
            } else {

                this.player2.move(this.gameBoard, Cell.O);
                rowPlacement = this.player2.getRowIndex();
                colPlacement = this.player2.getColIndex();
                playerID = 1; // Player should take turns to play
            }
        }
//
        return playerID;
    }

    /*
        DURING the game -> Check if there is a winner generated
     */
    private int checkWinner(int row, int col, int playerID) {
        if (++this.rows[playerID][row] == boardSize) {
            return playerID;
        }
        if (++this.columns[playerID][col] == boardSize) {
            return playerID;
        }
        if (row == col && ++this.diagonals[playerID][0] == boardSize) {
            return playerID;
        }
        if ((row + col == boardSize - 1) && ++this.diagonals[playerID][1] == boardSize) {
            return playerID;
        }
        return -1;
    }

}