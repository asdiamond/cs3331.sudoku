package edu.utep.cscs3331.sudoku;
import java.util.Stack;

/*
 * Generate solved board.
 * start with blank and fill sqrtn by sqrtn box
 * with permutation, add 1 to each permutation
 * and fill subsequent
 */


//to check box
//subtract one from positions, divide both by sqrt and multiply by square root

public class Board {
    private int size;
    private int[][] internalBoard;
    private int movesLeft;//number of valid moves left. determines if board is solved

    //defaults to 4
    public Board() throws SudokuSizeInputException {
        this(4);
    }

    public Board(int size) throws SudokuSizeInputException {
        validateSize(size);
        this.size = size;
        internalBoard = new int[size][size];
        movesLeft = size * size;
    }

    public Stack getUniqueRandomNumbers(int size){
        Stack stack = new Stack();
        for (int i = 0; i <= size; i++) {

        }
        return null;
    }

    private void validateSize(int size) throws SudokuSizeInputException {
        if(size == 4 || size == 9){
            return;
        }
        else {
            throw new SudokuSizeInputException("Invalid input");
        }
    }

    //just for testing
    public boolean isSolved(){
        return movesLeft == 0;
    }

    //TODO main part of the program. Checks valid input
    //@param position is not guranteed to be safe in any way other than
    //having all values. So bounds checking is necessary
    public void updateBoard(UserInputPosition position) throws SudokuInvalidPositionException {
        //check bounds
        if(position.getRow() > size || position.getCol() > size || position.getVal() > size){
            throw new SudokuInvalidPositionException("row, col, or val is out of bounds.");
        }
        if(
                validBox(position.getRow(), position.getCol(), position.getVal()) &&
                validCol(position.getCol(), position.getVal()) &&
                validRow(position.getRow(), position.getVal()))
        {//if
            internalBoard[position.getRow()][position.getCol()] = position.getVal();
            movesLeft--;//update the amount of valid moves left
        }//end if
        else{//not a valid move
            throw new SudokuInvalidPositionException("in bounds but invalid move.");
        }
    }

    private boolean validBox(int row, int col, int entry){
        int rowBound = (int) ((row - 1) / Math.sqrt(size));
        rowBound = (int) (rowBound * Math.sqrt(size));

        int colBound = (int) ((col - 1) / Math.sqrt(size));
        colBound = (int) (colBound * Math.sqrt(size));

        for(int i = rowBound; i < rowBound + (int) Math.sqrt(size); i++){
            for(int j = colBound; j < colBound + (int) Math.sqrt(size); j++){
                if(entry == internalBoard[i][j])
                    return false;
            }
        }

        return true;
    }

    private boolean validRow(int row, int entry){
        for (int i = 0; i < size; i++)
            if(internalBoard[row][i] == entry)
                return false;
        return true;
    }

    private boolean validCol(int col, int entry){
        for (int i = 0; i < size; i++)
            if(internalBoard[i][col] == entry)
                return false;
        return true;
    }

    public int getIndex(int x, int y){
        //TODO implement safety here.
        return internalBoard[x][y];
    }

    public int getSize(){
        return size;
    }

    //decent test code..

    /*
    public static void main(String[] args) {
        try {
            Board b = new Board();
            b.internalBoard = new int[][]{
                    {1, 2, 3, 0},
                    {3, 4, 1, 2},
                    {2, 3, 4, 1},
                    {4, 1, 2, 3}
            };
            b.updateBoard(new UserInputPosition(0, 3, 4));
            ConsoleUI ui = new ConsoleUI();
            ui.displayBoard(b);
        } catch (SudokuSizeInputException | SudokuInvalidPositionException e) {
            e.printStackTrace();
        }
    }
    */

}
