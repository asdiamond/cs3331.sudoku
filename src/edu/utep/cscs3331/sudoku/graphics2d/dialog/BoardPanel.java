package edu.utep.cscs3331.sudoku.graphics2d.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import edu.utep.cscs3331.sudoku.graphics2d.model.Square;
import edu.utep.cscs3331.sudoku.graphics2d.model.Board;

/**
 * A special panel class to display a Sudoku board modeled by the
 * {@link edu.utep.cscs3331.sudoku.graphics2d.model.Board} class. You need to write code for
 * the paint() method.
 *
 * @see edu.utep.cscs3331.sudoku.graphics2d.model.Board
 * @author Yoonsik Cheon
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

    //the square that is currently selected
    //null if none
    public Square selected;

	public interface ClickListener {
		
		/** Callback to notify clicking of a square. 
		 * 
		 * @param x 0-based column index of the clicked square
		 * @param y 0-based row index of the clicked square
		 */
		void clicked(int x, int y);
	}
	
    /** Background color of the board. */
	private static final Color boardColor = new Color(247, 223, 150);

    /** Board to be displayed. */
    private Board board;

    /** Width and height of a square in pixels. */
    private int squareSize;

    /** Create a new board panel to display the given board. */
    public BoardPanel(Board board, ClickListener listener) {
        this.board = board;
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	int xy = locateSquaree(e.getX(), e.getY());
            	if (xy >= 0) {
            		listener.clicked(xy / 100, xy % 100);
            	}
            }
        });
    }

    /** Set the board to be displayed. */
    public void setBoard(Board board) {
    	this.board = board;
    }
    
    /**
     * Given a screen coordinate, return the indexes of the corresponding square
     * or -1 if there is no square.
     * The indexes are encoded and returned as x*100 + y, 
     * where x and y are 0-based column/row indexes.
     */
    private int locateSquaree(int x, int y) {
    	if (x < 0 || x > board.getSize() * squareSize
    			|| y < 0 || y > board.getSize() * squareSize) {
    		return -1;
    	}
    	int xx = x / squareSize;
    	int yy = y / squareSize;
    	return xx * 100 + yy;
    }

    /** Draw the associated board. */
    @Override
    public void paint(Graphics g) {
        super.paint(g); 

        // determine the square size
        Dimension dim = getSize();
        squareSize = Math.min(dim.width, dim.height) / board.getSize();

        // draw background
        final Color oldColor = g.getColor();
        g.setColor(boardColor);
        g.fillRect(0, 0, squareSize * board.getSize(), squareSize * board.getSize());


        //for drawing grid
        g.setColor(Color.WHITE);
        for(int i = 0; i < board.getSize(); i++){
            for(int j = 0; j < board.getSize(); j++){
                g.drawRect(i * squareSize, j * squareSize, squareSize, squareSize);
            }
        }

        //for drawing squares
        g.setColor(Color.BLACK);
        int largeSquareSize =  Math.min(dim.width, dim.height) / (int)Math.sqrt(board.getSize());
        for(int i = 0; i < (int)Math.sqrt(board.getSize()); i++){
            for (int j = 0; j < (int) Math.sqrt(board.getSize()); j++) {
                g.drawRect(i * largeSquareSize, j * largeSquareSize, largeSquareSize, largeSquareSize);
            }
        }

        //for coloring the currently selected box
        //the order here matters, you want to draw the
        //values on top of the color
        g.setColor(Color.PINK);
        selected = board.getSelectedSquare();
        if(selected != null){
            g.fillRect(selected.getRow() * squareSize, selected.getCol() * squareSize, squareSize, squareSize);
        }

        //for drawing board values onto grid
        g.setColor(Color.BLUE);
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                //i is x, j is y according to board
                if(board.getIndex(i, j) == 0){
                    continue;
                }
                g.drawString(board.getIndex(i, j) + "", (i * squareSize), ((j + 1) * squareSize));
            }
        }

    }

}
