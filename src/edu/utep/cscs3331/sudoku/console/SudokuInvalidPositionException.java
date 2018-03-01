package edu.utep.cscs3331.sudoku.console;

public class SudokuInvalidPositionException extends Exception{
    public SudokuInvalidPositionException(String message){
        super(message);
    }
}
