package cs3331.sudoku.graphics2d;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Aleksandr Diamond on 4/8/2018
 * Assignment: sudoku
 * Purpose:
 */
class SudokuDialogTest {

    SudokuDialog sudokuDialog;

    @BeforeEach
    void setUp() {
        sudokuDialog = new SudokuDialog();
    }

    @Test
    void playSound() {
        String errorSound = "error.wav";
        sudokuDialog.playSound(errorSound);

        String goodSound = "ta-da.wav";
        sudokuDialog.playSound(goodSound);
    }

}