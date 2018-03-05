package edu.utep.cscs3331.sudoku.graphics2d.model;
import edu.utep.cscs3331.sudoku.console.ConsoleUI;
import edu.utep.cscs3331.sudoku.console.SudokuSizeInputException;
import edu.utep.cscs3331.sudoku.console.Square;

import java.util.ArrayList;
import java.util.List;

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
//    private int[][] internalBoard;
    private List<Square> internalBoard;

    //defaults to 4
    public Board() {
        this(4);
    }

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

    private Square getSquare(int row, int col){
        for (Square s : internalBoard) {
            if (s.getRow() == row && s.getCol() == col){
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

    //just for testing
    //need to update so this method actually looks to
    //see if board is in solved state.
    public boolean isSolved(){
        return true;
    }

    //TODO main part of the program. Checks valid input
    //@param position is not guranteed to be safe in any way other than
    //having all values. So bounds checking is necessary
    public void updateBoard(Square position) {
        //check bounds
//        if(position.getRow() > size || position.getCol() > size || position.getVal() > size){
//            throw new SudokuInvalidPositionException("row, col, or val is out of bounds.");
//        }
        System.out.println("validBox(position) = " + validBox(position));
        System.out.println("validCol(position) = " + validCol(position));
        System.out.println("validRow(position) = " + validRow(position));
        //out with the old..
        internalBoard.remove(getSquare(position.getRow(), position.getCol()));
        //and in with the new
        internalBoard.add(position);
    }

    private boolean validBox(Square in) {
        int rowBound = (int) ((in.getRow() - 1) / Math.sqrt(size));
        rowBound = (int) (rowBound * Math.sqrt(size));

        int colBound = (int) ((in.getCol() - 1) / Math.sqrt(size));
        colBound = (int) (colBound * Math.sqrt(size));

//        for (Square square : internalBoard) {
//            if((square.getRow() >= rowBound) && (square.getRow() < (rowBound + (int)Math.sqrt(size)))) {
//                if ((square.getCol() >= colBound) && (square.getCol() < (colBound + (int) Math.sqrt(size)))) {
//                    System.out.println("i = " + square.getCol());
//                    System.out.println("j = " + square.getRow());
//                    System.out.println("val = " + square.getVal());
//                    if(square.getVal() == in.getVal()) return false;
//                }
//            }
//        }

        for(int i = rowBound; i < rowBound + (int) Math.sqrt(size); i++){
            for(int j = colBound; j < colBound + (int) Math.sqrt(size); j++){
                Square s = getSquare(i, j);
                if(s.getVal() == in.getVal()) return false;
            }
        }
        return true;
    }

    private boolean validRow(Square in) {
        for (int i = 0; i < size; i++) {
            if(getSquare(in.getRow(), i).getVal() == in.getVal()) return false;
        }
        return true;
    }

    private boolean validCol(Square in) {
        for (int i = 0; i < size; i++) {
            if(getSquare(i, in.getCol()).getVal() == in.getVal()) return false;
        }
        return true;
    }

    public int getIndex(int row, int col){
        return getSquare(row, col).getVal();
    }

    public int getSize(){
        return size;
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
