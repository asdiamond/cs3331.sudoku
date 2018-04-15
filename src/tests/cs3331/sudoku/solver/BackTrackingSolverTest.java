package cs3331.sudoku.solver;

import cs3331.sudoku.graphics2d.SudokuDialog;
import cs3331.sudoku.model.Board;
import cs3331.sudoku.model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Aleksandr Diamond on 4/14/2018
 * Assignment: sudoku
 * Purpose:
 */
class BackTrackingSolverTest extends SudokuDialog {

    Board board;
    Solver solver;

    @BeforeEach
    void setUp() {
        board = new Board(4);
        solver = new BackTrackingSolver();
        /*
        full board:
        1 2 3 4
        0 0 0 0
        2 3 4 1
        0 0 0 0
         */

        /* 1 2 3 4 */
        board.updateBoard(new Square(0, 0, 1));
        board.updateBoard(new Square(0, 1, 2));
        board.updateBoard(new Square(0, 2, 3));
        board.updateBoard(new Square(0, 3, 4));

        /* 2 3 4 1 */
        board.updateBoard(new Square(2, 0, 2));
        board.updateBoard(new Square(2, 1, 3));
        board.updateBoard(new Square(2, 2, 4));
        board.updateBoard(new Square(2, 3, 1));
    }

    @Test
    void isSolvable() {
        assertTrue(solver.isSolvable(board));
    }

    @Test
    void solve() {
        solver.solve(board);
        assertTrue(board.isSolved());
    }
}