package edu.utep.cscs3331.sudoku;

public class SudokuInvalidPositionException extends Exception{
    public SudokuInvalidPositionException(String message){
        super(message);
    }
}
