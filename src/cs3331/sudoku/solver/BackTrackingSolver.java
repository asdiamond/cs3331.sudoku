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
        return backtrackingSolve(clone);
    }

    @Override
    public void solve(Board board) {
        if (isSolvable(board)) {
            backtrackingSolve(board);
        }
    }

    private boolean backtrackingSolve(Board board) {
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if (board.getSquare(row, col).getVal() == Board.UNASSIGNED) {
                    for (int val = 1; val <= board.getSize(); val++) {
                        Square move = new Square(row, col, val);
                        if (board.isValidMove(move)) {
                            board.updateBoard(move);
                            if (backtrackingSolve(board)) {
                                return true;
                            } else {
                                //undo move if it is invalid
                                Square undo = new Square(row, col, Board.UNASSIGNED);
                                board.updateBoard(undo);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
