package edu.utep.cscs3331.sudoku.console;

/**
 * Course: CS 2302
 * Author: Aleksandr Diamond on 8/28/2017
 * Assignment: sudoku
 * Instructor: Dr. Fuentes
 * T.A.: TBD
 * Last Date of Modification:
 * Purpose: This might seem like overkill, but it isnt.
 * I want to handle this exception properly as any other
 * exception could be thrown by libraries or other functions
 * such as invalidinputexception, assertionexception etc
 */
public class SudokuSizeInputException extends Exception{
    public SudokuSizeInputException(String message){
        super(message);
    }
}
