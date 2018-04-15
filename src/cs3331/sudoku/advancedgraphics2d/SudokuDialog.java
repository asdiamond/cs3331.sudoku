package cs3331.sudoku.advancedgraphics2d;

import cs3331.sudoku.model.Board;
import cs3331.sudoku.solver.BackTrackingSolver;
import cs3331.sudoku.solver.Solver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
        this.board = new Board(9, true);
        this.boardPanel = new BoardPanel(board, this::boardClicked);
    }

    @Override
    protected void configureUI() {
        super.configureUI();
        //for menus
        //create menubar and menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("The Game menu");
        menuBar.add(menu);

        //jmenu items
        JMenuItem menuItem = new JMenuItem("Text Menu Item",
                KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Doesnt do anything rn");
        menu.add(menuItem);

        setJMenuBar(menuBar);

        //for toolbar
        JToolBar toolBar = new JToolBar("Draggable");
        toolBar.add(new Button("A Button"));
        northJPanel.add(toolBar, 0);
    }

    public static void main(String[] args) {
        new SudokuDialog();
    }
}
