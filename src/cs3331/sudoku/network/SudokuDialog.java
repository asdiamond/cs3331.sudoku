package cs3331.sudoku.network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SudokuDialog extends cs3331.sudoku.advancedgraphics2d.SudokuDialog{

    protected SudokuDialog() {
        super();
    }

    protected SudokuDialog(Dimension dim) {
        super(dim);
    }

    @Override
    protected JMenu createMenu() {
        JMenu menu = super.createMenu();
        JMenuItem network = new JMenuItem("Pair",
                KeyEvent.VK_A);
        network.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_MASK));
        network.getAccessibleContext().setAccessibleDescription("Pair");
        network.addActionListener(e -> {
            new NetworkDialog();
        });
        menu.add(network);
        return menu;
    }

    public static void main(String[] args) {
        new SudokuDialog();
    }
}
