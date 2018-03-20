package edu.utep.cscs3331.sudoku.graphics2d.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Aleksandr Diamond on 3/19/2018
 * Assignment: sudoku
 * Purpose:
 */
class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        System.out.println("BoardTest.setUp");
        board = new Board();//size 4

        board.updateBoard(new Square(0, 0, 1));
        board.updateBoard(new Square(0, 1, 2));
        board.updateBoard(new Square(0, 2, 3));
        board.updateBoard(new Square(0, 3, 4));

        /*
        1 2  3 4
        */

        /*
        board.updateBoard(new Square(1, 0, 3));
        board.updateBoard(new Square(1, 1, 4));
        board.updateBoard(new Square(1, 2, 1));
        board.updateBoard(new Square(1, 3, 2));

        board.updateBoard(new Square(2, 0, 2));
        board.updateBoard(new Square(2, 1, 3));
        board.updateBoard(new Square(2, 2, 4));
        board.updateBoard(new Square(2, 3, 1));

        board.updateBoard(new Square(3, 0, 4));
        board.updateBoard(new Square(3, 1, 1));
        board.updateBoard(new Square(3, 2, 2));
        board.updateBoard(new Square(3, 3, 3));
        */

        /*
        1 2  3 4
        3 4  1 2

        2 3  4 1
        4 1  2 3
        */
    }

    @AfterEach
    void tearDown() {
        System.out.println("BoardTest.tearDown");
    }

    @Test
    void isSolved() {
        System.out.println("BoardTest.isSolved");
        assertFalse(board.isSolved());

        board.updateBoard(new Square(1, 0, 3));
        board.updateBoard(new Square(1, 1, 4));
        board.updateBoard(new Square(1, 2, 1));
        board.updateBoard(new Square(1, 3, 2));

        assertFalse(board.isSolved());

        board.updateBoard(new Square(2, 0, 2));
        board.updateBoard(new Square(2, 1, 3));
        board.updateBoard(new Square(2, 2, 4));
        board.updateBoard(new Square(2, 3, 1));

        assertFalse(board.isSolved());

        board.updateBoard(new Square(3, 0, 4));
        board.updateBoard(new Square(3, 1, 1));
        board.updateBoard(new Square(3, 2, 2));
        board.updateBoard(new Square(3, 3, 3));

        /*
        1 2  3 4
        3 4  1 2

        2 3  4 1
        4 1  2 3
        */

        assertTrue(board.isSolved());
    }

    @Test
    void getSquare() {
        System.out.println("BoardTest.getSquare");
        assertEquals(board.getSquare(0,0), new Square(0, 0, 1));
        assertEquals(board.getSquare(0, 1), new Square(0, 1, 2));
        assertEquals(board.getSquare(0, 2), new Square(0, 2, 3));
        assertEquals(board.getSquare(0, 3), new Square(0, 3, 4));
    }

    @Test
    void updateBoard() {
        System.out.println("BoardTest.updateBoard");

        Square s1 = new Square(1, 0, 3);
        board.updateBoard(s1);
        assertEquals(s1, board.getSquare(1, 0));

        Square s2 = new Square(1, 1, 4);
        board.updateBoard(s2);
        assertEquals(s2, board.getSquare(1, 1));

        Square s3 = new Square(1, 2, 1);
        board.updateBoard(s3);
        assertEquals(s3, board.getSquare(1, 2));

        Square s4 = new Square(1, 3, 2);
        board.updateBoard(s4);
        assertEquals(s4, board.getSquare(1, 3));
    }

    @Test
    void isValidMove() {
        /*
        row 1
        1 2  3 4
        */
        System.out.println("BoardTest.isValidMove");

        //all valid pos, valid val
        Square s1 = new Square(1, 0, 3);
        assertTrue(board.isValidMove(s1));

        Square s2 = new Square(1, 1, 4);
        assertTrue(board.isValidMove(s2));

        Square s3 = new Square(1, 2, 1);
        assertTrue(board.isValidMove(s3));

        Square s4 = new Square(1, 3, 2);
        assertTrue(board.isValidMove(s4));

        //valid pos, invalid val
        Square s5 = new Square(2, 0, -1);
        assertFalse(board.isValidMove(s5));

        //invalid pos, valid val
        Square s6 = new Square(-1, -1, 2);
        assertFalse(board.isValidMove(s6));

        //both invalid
        Square s7 = new Square(-1, -1, -1);
        assertFalse(board.isValidMove(s7));
    }

    @Test
    void getIndex() {
        System.out.println("BoardTest.getIndex");
        //from setUp...
        //valid squares in place
        assertEquals(board.getIndex(0, 0), 1);
        assertEquals(board.getIndex(0, 1), 2);
        assertEquals(board.getIndex(0, 2), 3);
        assertEquals(board.getIndex(0, 3), 4);

        //nonexistant square
//        assertEquals(board.getIndex(-1, -1), NullPointerException.class);
    }

    @Test
    void getSize() {
        System.out.println("BoardTest.getSize");
        int size = 4;
        assertEquals(board.getSize(), size);

        size = 9;
        board = new Board(size);
        assertEquals(size, board.getSize());
    }

    @Test
    void getSelectedSquare() {
        System.out.println("BoardTest.getSelectedSquare");

        assertNull(board.getSelectedSquare());
        Square s1 = new Square(1, 1, 4);
        s1.setSelected(true);
        board.updateBoard(s1);
        assertEquals(s1, board.getSelectedSquare());
    }
}