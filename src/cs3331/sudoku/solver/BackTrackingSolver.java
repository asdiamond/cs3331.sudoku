package cs3331.sudoku.solver;

import cs3331.sudoku.model.Board;
import cs3331.sudoku.model.Square;

/**
 * Author: Aleksandr Diamond on 4/14/2018
 * Assignment: sudoku
 * Purpose:
 */
public class BackTrackingSolver implements Solver {


    @Override
    public boolean isSolvable(Board board) {
        Board clone = board.clone();
        return backtrackingSolve(0, 0, clone);
    }

    @Override
    public void solve(Board board) {
        if (isSolvable(board)) {
            backtrackingSolve(0, 0, board);
        }
    }

    protected boolean backtrackingSolve(int i, int j, Board board) {
        if (i == board.getSize()) {
            i = 0;
            if (++j == board.getSize()) return true;
        }
        if (board.getIndex(i, j) != Board.UNASSIGNED) {
            return backtrackingSolve(i + 1, j, board);
        }

        for (int val = 1; val <= board.getSize(); ++val) {
            var move = new Square(i, j, val);
            if (board.isValidMove(move)) {
                board.updateBoard(move);
                if (backtrackingSolve(i + 1, j, board)) {
                    return true;
                }
            }
        }
        board.getSquare(i, j).setVal(Board.UNASSIGNED);
        return false;

    }
}
