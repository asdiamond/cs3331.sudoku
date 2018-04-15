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

    private Board board4;
    private Board board9;
    private Solver solver;

    @BeforeEach
    void setUp() {
        board4 = new Board(4);
        solver = new BackTrackingSolver();
        /*
        full board4:
        1 2 3 4
        0 0 0 0
        2 3 4 1
        0 0 0 0
         */

        /* 1 2 3 4 */
        board4.updateBoard(new Square(0, 0, 1));
        board4.updateBoard(new Square(0, 1, 2));
        board4.updateBoard(new Square(0, 2, 3));
        board4.updateBoard(new Square(0, 3, 4));

        /* 2 3 4 1 */
        board4.updateBoard(new Square(2, 0, 2));
        board4.updateBoard(new Square(2, 1, 3));
        board4.updateBoard(new Square(2, 2, 4));
        board4.updateBoard(new Square(2, 3, 1));


        board9 = new Board(9);
        board9.updateBoard(new Square(0, 0, 1));
    }

    @Test
    void isSolvable4() {
        assertTrue(solver.isSolvable(board4));
    }

    @Test
    void isSolvable9() {
        assertTrue(solver.isSolvable(board9));
    }

    @Test
    void solve4() {
        solver.solve(board4);
        assertTrue(board4.isSolved());
    }

    @Test
    void solve9() {
        solver.solve(board9);
        assertTrue(board9.isSolved());
    }
}