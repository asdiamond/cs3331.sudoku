package cs3331.sudoku.advancedgraphics2d;

import cs3331.sudoku.model.Board;
import cs3331.sudoku.model.Square;
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
        this.board = new Board(9, true);
        this.boardPanel = new BoardPanel(board, this::boardClicked);
    }

    @Override
    protected void configureUI() {
//        super.configureUI();
        //for dropdown jmenu
        setLayout(new BorderLayout());
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createMenu());
        setJMenuBar(jMenuBar);

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

    @Override
    protected void newClicked(int size) {
        int result = JOptionPane.showConfirmDialog(null, "Start a new game?", "Warning", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            board = new Board(size, true);
            boardPanel.setBoard(this.board);
            repaint();//from Jframe documentation, tell this to repaint itself.
        }
    }

    /**
     * Creates Menu with new 4x4 and 9x9 options.
     */
    protected JMenu createMenu() {
        //create menubar and menu
        JMenu menu = new JMenu("Game");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("The Game menu");

        //jmenu items
        //new 4x4 menu item
        JMenuItem menuItem4x4 = new JMenuItem("New 4X4 Game",
                KeyEvent.VK_A);
        menuItem4x4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
        menuItem4x4.getAccessibleContext().setAccessibleDescription("New 4X4 Game");
        menuItem4x4.addActionListener(e -> newClicked(4));
        menu.add(menuItem4x4);


        //new 9x9 menu item
        JMenuItem menuItem9x9 = new JMenuItem("New 9X9 Game",
                KeyEvent.VK_A);
        menuItem9x9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_MASK));
        menuItem9x9.getAccessibleContext().setAccessibleDescription("New 9X9 Game");
        menuItem9x9.addActionListener(e -> newClicked(9));
        menu.add(menuItem9x9);

        //check if solvable menu item
        JMenuItem checkSolvable = new JMenuItem("Check if Solvable",
                KeyEvent.VK_A);
        checkSolvable.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.ALT_MASK));
        checkSolvable.getAccessibleContext().setAccessibleDescription("Check if Solvable");
        checkSolvable.addActionListener(e -> showMessage(
                solver.isSolvable(board) ? "Solvable" : "Not Solvable"
                )
        );
        menu.add(checkSolvable);

        //solve menu item
        JMenuItem solve = new JMenuItem("Solve",
                KeyEvent.VK_A);
        solve.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_MASK));
        solve.getAccessibleContext().setAccessibleDescription("Solve");
        solve.addActionListener(e -> {
            solver.solve(board);
            repaint();
        });
        menu.add(solve);

        return menu;
    }

    public static void main(String[] args) {
        new SudokuDialog();
    }
}
