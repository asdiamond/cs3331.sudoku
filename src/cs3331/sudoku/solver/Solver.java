package cs3331.sudoku.solver;

import cs3331.sudoku.model.Board;

/**
 * Author: Aleksandr Diamond on 4/14/2018
 * Assignment: sudoku
 * Purpose:
 */
public interface Solver {
    /**
     * @param board the board to check if is solvable
     * @return if the board is solvable
     */
    boolean isSolvable(Board board);

    /**
     * @param board the board to be solved
     */
    boolean solve(Board board);
}
