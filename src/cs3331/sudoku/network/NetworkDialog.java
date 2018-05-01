package cs3331.sudoku.network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

public class NetworkDialog extends JDialog {
    final static Dimension DEFAULT_DIM = new Dimension(310, 430);

    private JPanel contentPane;
    private JPanel HostInfo;
    private JPanel PeerInfo;
    private JLabel hostNameLabel;
    private JLabel IPNumberLabel;
    private JTextField hostnameTF;
    private JTextField portnumberTF;
    private JButton connectButton;
    private JButton disconnectButton;
    private JLabel portLabel;

    private JDialog connectionProgress;

    public NetworkDialog(String hostname, String ipAddress, String portNumber) {
        this(DEFAULT_DIM, hostname, ipAddress, portNumber);
    }

    public NetworkDialog(Dimension dim, String hostname, String ipAddress, String portNumber) {
        setTitle("Network Operations");

        setContentPane(contentPane);
        setModal(true);

        setSize(dim);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        hostNameLabel.setText("Host Name: " + hostname);
        IPNumberLabel.setText("IP Address: " + ipAddress);
        portLabel.setText("Port Number: " + portNumber);

        connectionProgress = new JDialog(this, "Connecting...", true);
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setIndeterminate(true);
        connectionProgress.add(progressBar);
        connectionProgress.pack();

        connectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String hostname = hostnameTF.getText().trim();
                String portNumber = portnumberTF.getText().trim();
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        var socket = new Socket(hostname, Integer.parseInt(portNumber));
                        Main.networkAdapter = new NetworkAdapter(socket, System.out);
                        Main.networkAdapter.setMessageListener(Main::messageReceived);
                        Main.networkAdapter.receiveMessagesAsync();
                        Main.networkAdapter.writeJoin();
                        return null;
                    }

                    @Override
                    protected void done() {
                        connectionProgress.dispose();
                    }
                }.execute();

                connectionProgress.setVisible(true);
            }
        });
        setVisible(true);
    }
}
