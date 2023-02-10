public class Main {
    public static int[][] board = {
            {9,0,0,1,0,0,0,0,5},
            {0,0,5,0,9,0,2,0,1},
            {8,0,0,0,4,0,0,0,0},
            {0,0,0,0,8,0,0,0,0},
            {0,0,0,7,0,0,0,0,0},
            {0,0,0,0,2,6,0,0,9},
            {2,0,0,3,0,0,0,0,6},
            {0,0,0,2,0,0,9,0,0},
            {0,0,1,9,0,4,5,7,0},
    };
    final static int ROWS = board.length;
    final static int COLUMNS = board[0].length;
    public static void main(String[] args) {
        System.out.println("UNSOLVED SODUKU");
        printBoard();
        System.out.println();
        System.out.println();
        System.out.println();
        if (solve()) {
            System.out.println("SOLVED SODUKU");
            printBoard();
        }
        else {
            System.out.println("UNSOLVABLE");
        }
    }
    public static void printBoard() {
        for (int row = 0; row < ROWS; row++) {
            if (row == 0 || row == 3 || row == 6)
                System.out.println("+-------+-------+-------+");
            for (int col = 0; col < COLUMNS; col++) {
                if (col == 0 || col == 3 || col == 6)
                    System.out.print("| ");
                if (board[row][col] != 0)
                    System.out.print(board[row][col] + " ");
                else
                    System.out.print( "  ");
                if (col == 8)
                    System.out.println("|");
            }
        }
        System.out.println("+-------+-------+-------+");
    }
//recursive function
    public static boolean solve() {
        Location currentLoc = getNextZero();
        if (currentLoc == null)
            return true;
        //try all numbers
        //if a num works, add loc to stack, set num on board
        //if no nums work, backtrack
        for (int i = 1; i < 10; i++) {//numbers to try
            if (isValidNum(i,currentLoc)) {
                board[currentLoc.row][currentLoc.col] = i;
                if (solve()) {
                    return true;
                }
            }
            board[currentLoc.row][currentLoc.col] = 0;
        }
        return false;
    }
    //return null if no 0 left
    public static Location getNextZero() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == 0) {
                    return new Location(i,j);
                }
            }
        }
        return null;
    }
    //returns if num works inside of box, inside of long col, and insideof long row
    //gives loc of num
    public static boolean isValidNum(int num, Location loc) {
        if (loc.getRow() > ROWS || loc.getRow() < 0 ||loc.getCol() > COLUMNS || loc.getCol() < 0)
            return false;
        //rows and col check
        for (int i = 0; i < ROWS; i++) {
            if (board[loc.getRow()][i] == num)
                return false;
            if (board[i][loc.getCol()] == num)
                return false;
        }
        //check 3x3 grid
        int gridRow = loc.getRow()/3; //returns 0,1,2 index of little grid row
        int gridCol = loc.getCol()/3;//returns 0,1,2 index of little grid col
        int[][] box = new int[3][3];//make new box to store everything inside little grid

        int row = 0;
        for (int i = gridRow * 3; i < gridRow+3; i++) {
            int col = 0;
            for (int j = gridCol * 3; j < gridCol + 3; j++) {
                box[row][col]= board[i][j];
                col++;
            }
            row++;
        }
        //check if num is in box
        for (int[] ints : box) {
            for (int c = 0; c < box[0].length; c++) {
                if (num == ints[c])
                    return false;
            }
        }
        return true;
    }
}
