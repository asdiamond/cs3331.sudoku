package cs3331.sudoku.network;

import cs3331.sudoku.model.Board;

public class BoardPanel extends cs3331.sudoku.advancedgraphics2d.BoardPanel{
    /**
     * Create a new board panel to display the given board.
     *
     * @param board
     * @param listener
     */
    public BoardPanel(Board board, ClickListener listener) {
        super(board, listener);
    }


}
