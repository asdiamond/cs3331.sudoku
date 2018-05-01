package cs3331.sudoku.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: Aleksandr Diamond on 4/30/2018
 * Assignment: sudoku
 * Purpose:
 */
public class Main {
    public static NetworkAdapter networkAdapter;

    public static void main(String[] args) {
        //Start the gui asynchronously
        Thread guiThread = new Thread(SudokuDialog::new);

        //lambdas...
        final Socket[] socket = {null};
        int port = 8000;

        //make a new thread that blocks until it gets a socket
        Thread serverThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Server started on port: " + port);
                socket[0] = serverSocket.accept();
                System.out.println("Accepted a connection...");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Could not start server on port " + port);
            }
            //need a socket
            assert socket[0] != null;
            networkAdapter = new NetworkAdapter(socket[0]);
            networkAdapter.setMessageListener(Main::messageReceived);
            networkAdapter.receiveMessagesAsync();
        });

        guiThread.start();
        serverThread.start();
    }

    /**
     * Called when network adapter receives a message
     *
     * @param type
     * @param x
     * @param y
     * @param z
     * @param others
     */
    static void messageReceived(NetworkAdapter.MessageType type, int x, int y, int z, int[] others) {
        System.out.println("type = " + type);
        switch (type) {
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
    }
}
