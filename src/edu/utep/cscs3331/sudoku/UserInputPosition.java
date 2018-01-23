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
    private int x, y, val;

    public UserInputPosition(int x, int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVal() {
        return val;
    }
}
