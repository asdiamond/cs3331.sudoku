package edu.utep.cscs3331.sudoku.webservice;

/**
 * Author: Aleksandr Diamond on 1/29/2018
 * Assignment: sudoku
 * Purpose:
 */
public class Square {
    int x;
    int y;
    int value;

    public String toString(){
        String tostr = "x: " + x + "\n";
        tostr += "y: " + y + "\n";
        tostr += "value: " + value + "\n";
        return tostr;
    }
}
