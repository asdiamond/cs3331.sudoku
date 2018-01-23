package edu.utep.cscs3331.sudoku;
import java.util.Random;

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

    public void updateBoard(UserInputPosition position){
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
