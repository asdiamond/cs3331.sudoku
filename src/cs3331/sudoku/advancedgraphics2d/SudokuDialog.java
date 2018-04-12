package cs3331.sudoku.advancedgraphics2d;

import cs3331.sudoku.model.Board;

import java.awt.*;

/**
 * Author: Aleksandr Diamond on 4/12/2018
 * Assignment: sudoku
 * Purpose:
 */
public class SudokuDialog extends cs3331.sudoku.graphics2d.SudokuDialog {

    protected SudokuDialog() {
        this(DEFAULT_SIZE);
    }

    protected SudokuDialog(Dimension dim) {
        super(dim, "advanced sudoku");
    }

    @Override
    protected void initVars() {
        System.out.println("Right initVars");
        this.board = new Board(9);
        this.boardPanel = new BoardPanel(board, this::boardClicked);
    }

    public static void main(String[] args) {
        new SudokuDialog();
    }
}
