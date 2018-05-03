package cs3331.sudoku.network;

import cs3331.sudoku.model.Board;
import cs3331.sudoku.model.Square;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aleksandr Diamond on 4/30/2018
 * Assignment: sudoku
 * Purpose:
 */
public class Main {
    public static NetworkAdapter networkAdapter;
    public static SudokuDialog sudokuDialog;


    public static void main(String[] args) {
        //Start the gui asynchronously
        Thread guiThread = new Thread(() -> sudokuDialog = new SudokuDialog());

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
                handleJoin();
                break;
            case JOIN_ACK:
                handleJoinAck(x, y, others);
                break;
            case NEW:
                sudokuDialog.setBoard(new Board(x));
                List<Square> squareList = decodeSquares(others);
                for (Square s : squareList) {
                    sudokuDialog.getBoard().updateBoard(s);
                }
                sudokuDialog.repaint();
                networkAdapter.writeNewAck(true);
                break;
            case NEW_ACK:
                break;
            case FILL:
                Square square = new Square(x, y, z);
                sudokuDialog.getBoard().updateBoard(square);
                sudokuDialog.repaint();
                networkAdapter.writeFillAck(x, y, z);
                break;
            case FILL_ACK:
                break;
            case CLOSE:
                break;
            case UNKNOWN:
                break;
        }
    }

    /**
     * to send a join_ack, effectively shares the game
     */
    private static void handleJoin() {
        Board board = sudokuDialog.getBoard();
        List<Square> squareList = board.getInternalBoard();

        int[] squares = new int[squareList.size() * 4];
        for (int i = 0; i < squares.length / 4; i++) {
            Square square = squareList.get(i);
            squares[i] = square.getCol();
            squares[i + 1] = square.getRow();
            squares[i + 2] = square.getVal();
            squares[i + 3] = square.isConstant() ? 1 : 0;
        }


        networkAdapter.writeJoinAck(board.getSize(), squares);
    }

    /**
     * @param n       Response, either 0 declined or 1 accepted
     * @param s       Size of board
     * @param squares Array of squares encoded
     */
    static void handleJoinAck(int n, int s, int[] squares) {
        if (n == 1) {
            System.out.println("accepted join");
            List<Square> squareList = decodeSquares(squares);
            for (Square square : squareList) {
                sudokuDialog.getBoard().updateBoard(square);
            }
            sudokuDialog.repaint();
        } else {
            System.out.println("rejected join");
        }
    }

    static List<Square> decodeSquares(int[] squares) {
        ArrayList<Square> list = new ArrayList<>();
        int[][] squareChunks = chunkArray(squares, 4);
        for (int[] squareChunk : squareChunks) {
            Square square = new Square(squareChunk[0], squareChunk[1],
                    squareChunk[2],
                    squareChunk[3] == 1);
            list.add(square);
        }
        return list;
    }

    /**
     * Splits an array into chunks.
     * reference https://gist.github.com/lesleh/7724554
     *
     * @param array     array to be split
     * @param chunkSize size of chunks. if array isnt big enough it will cut them off
     * @return 2d array of chunks of given array.
     */
    private static int[][] chunkArray(int[] array, int chunkSize) {
        int numOfChunks = (int) Math.ceil((double) array.length / chunkSize);
        int[][] output = new int[numOfChunks][];

        for (int i = 0; i < numOfChunks; ++i) {
            int start = i * chunkSize;
            int length = Math.min(array.length - start, chunkSize);

            int[] temp = new int[length];
            System.arraycopy(array, start, temp, 0, length);
            output[i] = temp;
        }

        return output;
    }
}
