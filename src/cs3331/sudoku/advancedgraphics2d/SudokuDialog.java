package cs3331.sudoku.advancedgraphics2d;

import cs3331.sudoku.model.Board;
import cs3331.sudoku.solver.BackTrackingSolver;
import cs3331.sudoku.solver.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Author: Aleksandr Diamond on 4/12/2018
 * Assignment: sudoku
 * Purpose:
 */
public class SudokuDialog extends cs3331.sudoku.graphics2d.SudokuDialog {

    Solver solver;

    protected SudokuDialog() {
        this(DEFAULT_SIZE);
    }

    protected SudokuDialog(Dimension dim) {
        super(dim, "advanced sudoku");
    }

    @Override
    protected void initVars() {
        this.solver = new BackTrackingSolver();
        //order matters here.
        this.board = new Board(9, false);
        this.boardPanel = new BoardPanel(board, this::boardClicked);
    }

    @Override
    protected void configureUI() {
//        super.configureUI();
        setLayout(new BorderLayout());
        createMenu();

        //for toolbar
        JToolBar toolBar = new JToolBar("Toolbar");
        createNumberButtons(toolBar);

        add(toolBar, BorderLayout.NORTH);

        createBoard();
        createMessageBar();
    }

    @Override
    protected JPanel makeControlPanel() {
        return super.makeControlPanel();
    }

    /**
     * Creates Menu with new 4x4 and 9x9 options.
     */
    private void createMenu() {
        //create menubar and menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("The Game menu");
        menuBar.add(menu);

        //jmenu items
        //new 4x4 menu item
        JMenuItem menuItem4x4 = new JMenuItem("New 4X4 Game",
                KeyEvent.VK_A);
        menuItem4x4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
        menuItem4x4.getAccessibleContext().setAccessibleDescription("New 4X4 Game");
        menuItem4x4.addActionListener(e -> newClicked(4));
        menu.add(menuItem4x4);


        //new 4x4 menu item
        JMenuItem menuItem9x9 = new JMenuItem("New 9X9 Game",
                KeyEvent.VK_A);
        menuItem9x9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
        menuItem9x9.getAccessibleContext().setAccessibleDescription("New 9X9 Game");
        menuItem9x9.addActionListener(e -> newClicked(9));
        menu.add(menuItem9x9);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        new SudokuDialog();
    }
}
