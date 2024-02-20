# TicTacToe-Java


3 TicTacToe 

This project implements 1. Human Vs. Human
                        2. Human Vs. Computer 
                        3. Computer Vs. Computer

This games enbles option for which player will play first. (first player will be 'X')

Enum class is used for cell description for easy usability. 

There are 3 main function.

Pseudo Code
 
There will be 3 main functions
 
1. TicTacToe play Function
2. Check Player and Play Turn Function
3. Check winner function

         1. TicTacToe Play Function –
          play() {
             initializeGame();
             while (true) {
                 int currentPlayer = determineCurrentPlayer();
                 takeTurn(currentPlayer);
                 updateGameStatus();
                 if (checkForWinner()) {
                     declareWinner();
                     break;
                 } else if (checkForStalemate()) {
                     declareStalemate();
                     break;
                 }
             }
             printFinalGameBoard();
             closeScannersAndExit();
          }
           
          2. Check which player chance it is –
           
          playerTurn(playerID) {
             if (playerID == 1) {
                player1.move(this.gameBoard, Cell.X);
                 updatePlacement(player1.getRowIndex(), player1.getColIndex());
                 return 2;
             } else {
                 this.player2.move(this.gameBoard, Cell.O);
                 updatePlacement(player2.getRowIndex(), player2.getColIndex());
                 return 1;
             }
          }
           
          3. Check if the move made is the winner.
           
          checkWinner(row, col, playerID) {
          if (checkRowsForWin(playerID) || checkColumnsForWin(playerID) || checkDiagonalsForWin(playerID)) {
                 return playerID;
             } else {
                 return -1;
             }
          }

