package edu.utep.cscs3331.sudoku.console;

/**
 * Course: CS 2302
 * Author: Aleksandr Diamond on 8/28/2017
 * Assignment: sudoku
 * Instructor: Dr. Fuentes
 * T.A.: TBD
 * Last Date of Modification:
 * Purpose:
 */
public class Square {
    //keep track of variables internally
    private int row, col, val;
    private boolean selected;

    public Square(int row, int col, int val){
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

    public void setVal(int val){this.val = val;}

    public boolean isSelected(){return selected;}

    public void setSelected(boolean selected){this.selected = selected;}
}
