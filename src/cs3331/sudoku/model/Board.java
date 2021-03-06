package cs3331.sudoku.model;

import cs3331.sudoku.solver.BackTrackingSolver;
import cs3331.sudoku.solver.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a sudoku board.
 * An aggregation of Squares.
 */

public class Board {
    private int size;
    private List<Square> internalBoard;
    public static final int UNASSIGNED = 0;

    public List<Square> getInternalBoard() {
        return internalBoard;
    }

    /**
     * Default constructor with size 4
     */
    public Board() {
        this(4);
    }

    /**
     *
     * @param size the size of the board. 4 or 9 are valid.
     */
    public Board(int size) throws IllegalArgumentException {
        validateSize(size);
        this.size = size;
        internalBoard = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //so it is non-null
                Square square = new Square(i, j, UNASSIGNED, false);
                internalBoard.add(square);
            }
        }
    }


    /**
     * @param size            the size of the board.
     * @param partiallyFilled true to return partially filled board
     */
    public Board(int size, boolean partiallyFilled) {
        this(size);
        if (partiallyFilled) {
            Solver solver = new BackTrackingSolver();
            solver.solve(this);
            int minNumberOfClues = size == 4 ? 4 : 17;
            //remove until there are only minNumberOfClues left
            for (int i = 0; i < (size * size) - minNumberOfClues; i++) {
                int ranRow = new Random().nextInt(size);
                int ranCol = new Random().nextInt(size);
                getSquare(ranRow, ranCol).setVal(UNASSIGNED);
            }
            for (Square s : internalBoard) {
                if (s.getVal() != UNASSIGNED) s.setConstant(true);
            }
        }
    }

    /**
     *
     * @return if the board is in a solved state
     */
    public boolean isSolved(){
        /*
        int sum = (size * (size + 1)) / 2;//sum of all numbers up to size
        for (int r = 0; r < size; r++) {
            int rowSum = sum;
            for (int c = 0; c < size; c++) {
                rowSum -= getSquare(r, c).getVal();
            }
            if (rowSum != 0) {
                return false;
            }
        }
        return true;
        */
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!isValidMove(getSquare(i, j))) return false;
            }
        }
        return true;
    }

    /**
     *
     * @param row Row address of square
     * @param col Column address of square
     * @return the square at those addresses
     */
    public Square getSquare(int row, int col){
        for (Square s : internalBoard) {
            if ((s.getRow() == row) && (s.getCol() == col)){
                return s;
            }
        }
        return null;
    }

    /**
     * @param size size to be validated. Called from constructor.
     * @throws IllegalArgumentException If size is invalid.
     */
    private void validateSize(int size) throws IllegalArgumentException {
        if (!(size == 4 || size == 9)) {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    /**
     * Adds given square to board.
     * @param position The square given.
     */
    public void updateBoard(Square position) {
        //must be a valid move
        if (!isValidMove(position))
            throw new IllegalArgumentException("updateBoard(Square) cannot be called with an invalid move:\n" + position.toString());
        //out with the old..
        internalBoard.remove(getSquare(position.getRow(), position.getCol()));
        //and in with the new
        internalBoard.add(position);
    }

    /**
     * Checks if move is valid sudoku move.
     * @param move The change you are validating
     * @return true if a valid move.
     */
    public boolean isValidMove(Square move){
        //bounds
        int row = move.getRow();
        int col = move.getCol();
        int val = move.getVal();

//        if ((row >= this.size) || (row < 0) || (col >= this.size) || (col < 0) || (val > this.size) || (val < 0)) {
//            return false;
//        }
//        if(val == UNASSIGNED) return true;//removing is always a valid move
        return !getSquare(row, col).isConstant() && validBox(move) && validRow(move) && validCol(move);
    }

    /**
     * Checks if move is valid in its sudoku sub-box
     * @param in The move to be checked.
     * @return True if valid.
     */
    private boolean validBox(Square in) {
        int boxSize = (int) Math.sqrt(getSize());
        int row = in.getRow() - (in.getRow() % boxSize);
        int col = in.getCol() - (in.getCol() % boxSize);

        for (int i = row; i < row + boxSize; i++) {
            for (int j = col; j < col + boxSize; j++) {
                if (getSquare(row, col).getVal() == in.getVal()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if move is valid in given column
     * @param in Move to be checked.
     * @return True if valid
     */
    private boolean validCol(Square in) {
        for (int i = 0; i < size; i++) {
            if((getSquare(in.getRow(), i).getVal()) == in.getVal()) return false;
        }
        return true;
    }

    /**
     * Checks if move is valid in given row
     * @param in Move to be checked.
     * @return True if valid
     */
    private boolean validRow(Square in) {
        for (int i = 0; i < size; i++) {
            if(getSquare(i, in.getCol()).getVal() == in.getVal()) return false;
        }
        return true;
    }

    /**
     * Gives value at boards index
     * @param row Row address of square
     * @param col Column address of square
     * @return value of square at address
     */
    public int getIndex(int row, int col){
        Square s = getSquare(row, col);
        if(s == null) throw new NullPointerException("Square doesnt exist");
        return s.getVal();
    }

    /**
     * Gives size that object was instantiated with.
     * @return
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Gives first square with a selected attribute
     * @return
     */
    public Square getSelectedSquare(){
        for (Square s : internalBoard) {
            if (s.isSelected()) return s;
        }
        return null;
    }

    @Override
    public Board clone() {
        Board clone = new Board(this.size);
        ArrayList<Square> squares = new ArrayList<>(internalBoard.size());
        for (Square s : internalBoard) {
            squares.add(s.clone());
        }
        clone.internalBoard = squares;
        return clone;
    }
}
