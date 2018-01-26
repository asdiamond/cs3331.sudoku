package edu.utep.cscs3331.sudoku;
import java.util.Random;
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
    private boolean solved;
    private int[][] internalBoard;

    //defaults to 4
    public Board() throws SudokuSizeInputException {
        this(4);
    }

    public Board(int size) throws SudokuSizeInputException {
        validateSize(size);
        this.size = size;
        internalBoard = new int[size][size];

    }

    public Stack getUniqueRandomNumbers(int size){
        Stack stack = new Stack();
        for (int i = 0; i <= size; i++) {

        }
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
    private static Random rand = new Random(100);
    public boolean isSolved(){
        return rand.nextBoolean();
    }

    //TODO main part of the program. Checks valid input
    //@param position is not guranteed to be safe in any way other than
    //having all values. So bounds checking is necessary
    public void updateBoard(UserInputPosition position) throws SudokuInvalidPositionException {
        //check bounds
        if(position.getX() >= size || position.getY() >= size || position.getVal() > size){
            throw new SudokuInvalidPositionException("invalid x, y or value.");
        }
//        if(position)
        internalBoard[position.getX()][position.getY()] = position.getVal();
    }

    public int getIndex(int x, int y){
        //TODO implement safety here.
        return internalBoard[x][y];
    }

    public int getSize(){
        return size;
    }
}
