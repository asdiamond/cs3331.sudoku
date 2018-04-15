package cs3331.sudoku.model;

/**
 * Author: Aleksandr Diamond on 8/28/2017
 * Assignment: sudoku
 * Purpose: Represents a single square of a sudoku game.
 */
public class Square {
    /**
     * Internally for storing row, column, and value
     * @row
     * @col
     * @val
     */
    private int row, col, val;
    /**
     * If the square has been selected
     */
    private boolean selected;

    /**
     * Constructs a square from params
     * @param row Row address of square
     * @param col Column address of square
     * @param val Value of square
     */
    public Square(int row, int col, int val){
        this.row = row;
        this.col = col;
        this.val = val;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val){this.val = val;}

    public boolean isSelected(){return selected;}

    public void setSelected(boolean selected){this.selected = selected;}

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != getClass()) return false;
        if (obj == this) return true;

        Square s = (Square)obj;
        return (s.col == this.col) && (s.row == this.row) &&
               (s.val == this.val) && (s.selected == this.selected);
    }

    @Override
    public String toString() {
        return "row=" + this.row + "\ncol=" + this.col + "\nval=" + this.val + "\nselected=" + this.selected;
    }

    @Override
    public Square clone() {
        return new Square(row, col, val);
    }
}
