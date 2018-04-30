package cs3331.sudoku.network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

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
        JMenuItem network = new JMenuItem("Network Operations",
                KeyEvent.VK_A);
        network.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_MASK));
        network.getAccessibleContext().setAccessibleDescription("Network Operations");
        network.addActionListener(e -> {
            new NetworkDialog("A hostname", "an ip address");
        });
        menu.add(network);
        return menu;
    }

    public static void main(String[] args) {
        //Start the gui asynchronously
        new Thread(SudokuDialog::new).start();

        /*
        int serverPort = 8000;
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(serverPort)) {
            System.out.println("Server started on port: " + serverPort);
            socket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not start server on port " + serverPort);
        }

        try {
            NetworkAdapter network = new NetworkAdapter(socket, System.out);
            network.setMessageListener((type, x, y, z, others) -> {
                System.out.println("type = " + type);
                switch (type){
                    case QUIT:
                        break;
                    case JOIN:
                        break;
                    case JOIN_ACK:
                        break;
                    case NEW:
                        break;
                    case NEW_ACK:
                        break;
                    case FILL:
                        break;
                    case FILL_ACK:
                        break;
                    case CLOSE:
                        break;
                    case UNKNOWN:
                        break;
                }
            });
            network.receiveMessagesAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}
