/**
 * This class is mainly designed for storing and displaying messages BEFORE, DURING, and AFTER the game.
 */
final public class NotificationCenter {
    private NotificationCenter() {}
    public static void welcome() {
        System.out.println("Welcome to Tic Tac Toe! Please be noticed of the followings before our game starts:");

        System.out.println();
        System.out.println("1. Two Player Game!. (Human Vs Human) Player One will play as a X.");
        System.out.println("2. Play with Computer. (Human Vs Computer)");
        System.out.println("3. Watch Computer Playing. (Computer Vs Computer)");

        System.out.println();
        System.out.println("Hit 1 , 2 or 3 option to start the game. Or hit any other key to exit.");
    }
    public static int startOrExit(String message) {
        if (message.equalsIgnoreCase("1")) {
            System.out.println("*************************");
            System.out.println("Multi Player Game starts!:)");
            System.out.println("*************************");
            System.out.println();
            return 1;
        }
        else if (message.equalsIgnoreCase("2")) {
            System.out.println("*************************");
            System.out.println("Single Vs Computer Game starts!:)");
            System.out.println("*************************");
            System.out.println();
            return 2;
        }
        else if (message.equalsIgnoreCase("3")) {
            System.out.println("*************************");
            System.out.println("Computer Vs Computer Game starts!:)");
            System.out.println("*************************");
            System.out.println();
            return 3;
        }
        else {
            System.out.println("**********************************************");
            System.out.println("Game exits:( Take care and come back anytime!");
            System.out.println("**********************************************");
            return 0;
        }
    }
    public static void namesAndSize(int index) {
        switch (index) {
            case 1:
                System.out.print("Please enter Player ONE name: ");
                break;
            case 2:
                System.out.print("Please enter Player TWO name: ");
                break;
            case 3:
                System.out.print("Do you want to go First (Y/N)?: ");
                break;
            case 4:
                System.out.print("Please enter your name: ");
                break;

            default:
                System.out.println("Index can only be 1/2/3/4!");
        }
    }
    public static void boardPlacement(int index, String playerName, int boardSize) {
        switch (index) {
            case 1:
                System.out.println("***---------------------------------------------------------***");
                System.out.print("Player " + playerName + ", ");
                System.out.println("please enter your move. (enter a value from 1 - " + boardSize * boardSize + ")");
                System.out.println("Example: 1 (means: cell[1, 1]); 5 (means: cell[2, 2])");
                System.out.println("***---------------------------------------------------------***");
                System.out.println();
                break;
            case 2:
                System.out.println("----------------------------------");
                System.out.println("Invalid input! Must be an INTEGER!");
                System.out.println("----------------------------------");
                break;
            case 3:
                System.out.println();
                System.out.println("-------------------------------------------------------");
                System.out.println("Input out of range or position taken! Please try again.");
                System.out.println("-------------------------------------------------------");
                break;


            default:
                System.out.println("Index for boardPlacement() must be 1/2/3!");
        }
    }

    public static void computerMoves(int index, String playerName, int boardSize, int movepos) {
        switch (index) {
            case 1:
                System.out.println("***---------------------------------------------------------***");
                System.out.print("Player " + playerName + ", ");
                System.out.println("Made move at " +  movepos + " Position");
                System.out.println("***---------------------------------------------------------***");
                System.out.println();
                break;

            default:
                System.out.println("Index for computerMoves() must be 1!");
        }
    }

    public static void winnerCongratulations(String winnerName) {
        System.out.println();
        System.out.println("***********************************************");
        System.out.println("Congratulations " + winnerName + "! You have won the game!");
        System.out.println("***********************************************");
        System.out.println();
    }

    public static void stalemateAnnouncement() {
        System.out.println("*******************************************");
        System.out.println("Oopps! We have reached a stalemate, It's A Tie!!!!!");
        System.out.println("*******************************************");
    }

}