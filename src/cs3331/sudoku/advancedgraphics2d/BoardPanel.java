package cs3331.sudoku.advancedgraphics2d;

import cs3331.sudoku.model.Board;

import java.awt.*;

/**
 * Author: Aleksandr Diamond on 4/12/2018
 * Assignment: sudoku
 * Purpose:
 */
public class BoardPanel extends cs3331.sudoku.graphics2d.BoardPanel {


    /**
     * Create a new board panel to display the given board.
     *
     * @param board
     * @param listener
     */
    public BoardPanel(Board board, ClickListener listener) {
        super(board, listener);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }


}
