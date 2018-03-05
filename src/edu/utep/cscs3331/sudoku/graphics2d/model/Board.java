package edu.utep.cscs3331.sudoku.graphics2d.model;
import edu.utep.cscs3331.sudoku.console.SudokuSizeInputException;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a sudoku board.
 * An aggregation of Squares.
 */

public class Board {
    private int size;
    private List<Square> internalBoard;

    /**
     * Default constructor with size 4
     */
    public Board() {
        this(4);
    }

    /**
     *
     * @param size the size of the board. 4 or 9 are valid.
     */
    public Board(int size) {
//        validateSize(size);
        this.size = size;
        internalBoard = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //so it is non-null
                internalBoard.add(new Square(i, j, 0));
            }
        }
    }

    /**
     *
     * @return if the board is in a solved state
     */
    public boolean isSolved(){
        int sum = (size * (size + 1)) / 2;//sum of all numbers up to size
        for (int r = 0; r < size; r++) {
            int rowSum = sum;
            for (int c = 0; c < size; c++) {
                rowSum -= getSquare(r, c).getVal();
            }
            if (rowSum != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param row Row address of square
     * @param col Column address of square
     * @return the square at those addresses
     */
    public Square getSquare(int row, int col){
        for (Square s : internalBoard) {
            if ((s.getRow() == row) && (s.getCol() == col)){
                return s;
            }
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

    /**
     * Adds given square to board.
     * @param position The square given.
     */
    public void updateBoard(Square position) {
        //must be a valid move
        if(!isValidMove(position)) return;
        //out with the old..
        internalBoard.remove(getSquare(position.getRow(), position.getCol()));
        //and in with the new
        internalBoard.add(position);
    }

    /**
     * Checks if move is valid sudoku move.
     * @param move The change you are validating
     * @return true if a valid move.
     */
    public boolean isValidMove(Square move){
        return validBox(move) && validRow(move) && validCol(move);
    }

    /**
     * Checks if move is valid in its sudoku sub-box
     * @param in The move to be checked.
     * @return True if valid.
     */
    private boolean validBox(Square in) {
        int rowBound = (int) ((in.getRow()) / Math.sqrt(size));
        rowBound = (int) (rowBound * Math.sqrt(size));

        int colBound = (int) ((in.getCol()) / Math.sqrt(size));
        colBound = (int) (colBound * Math.sqrt(size));

        for(int i = rowBound; i < rowBound + (int) Math.sqrt(size); i++){
            for(int j = colBound; j < colBound + (int) Math.sqrt(size); j++){
                Square s = getSquare(i, j);
                if(s.getVal() == in.getVal()) return false;
            }
        }
        return true;
    }

    /**
     * Checks if move is valid in given column
     * @param in Move to be checked.
     * @return True if valid
     */
    private boolean validCol(Square in) {
        for (int i = 0; i < size; i++) {
            if((getSquare(in.getRow(), i).getVal()) == in.getVal()) return false;
        }
        return true;
    }

    /**
     * Checks if move is valid in given row
     * @param in Move to be checked.
     * @return True if valid
     */
    private boolean validRow(Square in) {
        for (int i = 0; i < size; i++) {
            if(getSquare(i, in.getCol()).getVal() == in.getVal()) return false;
        }
        return true;
    }

    /**
     * Gives value at boards index
     * @param row Row address of square
     * @param col Column address of square
     * @return value of square at address
     */
    public int getIndex(int row, int col){
        return getSquare(row, col).getVal();
    }

    /**
     * Gives size that object was instantiated with.
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * Gives first square with a selected attribute
     * @return
     */
    public Square getSelectedSquare(){
        for (Square s : internalBoard) {
            if (s.isSelected()) return s;
        }
        return null;
    }


    //decent test code..

    /*
    public static void main(String[] args) {
        Board b = new Board();
        ConsoleUI ui = new ConsoleUI();
        int[][] practiceBoard = new int[][]{
        {1, 2, 3, 0},
        {3, 4, 1, 2},
        {2, 3, 4, 1},
        {4, 1, 2, 3}
        };
        for (int i = 0; i < practiceBoard.length; i++) {
            for (int j = 0; j < practiceBoard.length; j++) {
                b.updateBoard(new Square(i, j, practiceBoard[i][j]));
            }
        }

        ui.displayBoard(b);
        b.updateBoard(new Square(0, 3, 4));
        ui.displayBoard(b);
    }
    */

}
