package cs3331.sudoku.network;

import cs3331.sudoku.model.Board;
import cs3331.sudoku.model.Square;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SudokuDialog extends cs3331.sudoku.advancedgraphics2d.SudokuDialog{

    protected SudokuDialog() {
        super();
    }

    protected SudokuDialog(Dimension dim) {
        super(dim);
    }

    Board getBoard() {
        return board;
    }

    void setBoard(Board board) {
        this.board = board;
        boardPanel.setBoard(this.board);
    }

    @Override
    protected JMenu createMenu() {
        JMenu menu = super.createMenu();
        JMenuItem network = new JMenuItem("Network Operations",
                KeyEvent.VK_A);
        network.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_MASK));
        network.getAccessibleContext().setAccessibleDescription("Network Operations");

        String hostname = null;
        String an_ip_address = null;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
            an_ip_address = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            hostname = "Couldn't get hostname";
            e.printStackTrace();
        }

        String a_port_number = "8000";

        //final because lambdas...
        String finalHostname = hostname;
        String finalAn_ip_address = an_ip_address;
        network.addActionListener(e -> new NetworkDialog(finalHostname, finalAn_ip_address, a_port_number));
        menu.add(network);
        return menu;
    }

    @Override
    protected void numberClicked(int number) {
        super.numberClicked(number);
        Square s = new Square(boardPanel.selected.getRow(), boardPanel.selected.getCol(), number);
//        if(getBoard().isValidMove(s)){
        Main.networkAdapter.writeFill(s.getRow(), s.getCol(), number);
//        }
    }
}
