package edu.utep.cscs3331.sudoku;

/**
 * Course: CS 2302
 * Author: Aleksandr Diamond on 8/28/2017
 * Assignment: sudoku
 * Instructor: Dr. Fuentes
 * T.A.: TBD
 * Last Date of Modification:
 * Purpose:
 */
public class UserInputPosition {
    //keep track of variables internally
    private int row, col, val;

    public UserInputPosition(int row, int col, int val){
        this.row = row;
        this.col = col;
        this.val = val;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getVal() {
        return val;
    }
}
