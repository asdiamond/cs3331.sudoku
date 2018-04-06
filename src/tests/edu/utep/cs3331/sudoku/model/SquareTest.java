package edu.utep.cs3331.sudoku.model;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Aleksandr Diamond on 3/19/2018
 * Assignment: sudoku
 * Purpose:
 */
class SquareTest {

    Square square;
    int row = 1;
    int col = 1;
    int val = 1;

    @BeforeEach
    void setUp() {
        square = new Square(row, col, val);
    }

    @Test
    void getRow() {
        assertEquals(row, square.getRow());
    }

    @Test
    void getCol() {
        assertEquals(col, square.getCol());
    }

    @Test
    void getVal() {
        assertEquals(val, square.getVal());
    }

    @Test
    void setVal() {
        int val = 100;
        square.setVal(val);
        assertEquals(val, square.getVal());
    }

    @Test
    void isSelected() {
        assertEquals(false, square.isSelected());
    }

    @Test
    void setSelected() {
        boolean selected = true;
        square.setSelected(selected);
        assertEquals(selected, square.isSelected());
    }
}