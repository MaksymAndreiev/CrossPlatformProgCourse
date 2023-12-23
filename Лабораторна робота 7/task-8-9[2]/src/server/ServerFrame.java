package server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

public class ServerFrame {
    JFrame frame = new JFrame("TCP Server");
    private int serverPort = -1;
    private JPanel panel1;
    public static JTextArea textArea;
    private JTextField textField;
    private JButton startServerButton;
    private JButton stopServerButton;
    private JButton exitServerButton;
    private Server server;

    public ServerFrame() {
        textArea = new JTextArea();
        panel1.add(textArea, BorderLayout.CENTER);
        stopServerButton.setEnabled(false);
        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("") || textField.getText() != null) {
                    serverPort = Integer.parseInt(textField.getText());
                    run();
                    stopServerButton.setEnabled(true);
                    startServerButton.setEnabled(false);
                }
            }
        });
        stopServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.flag = false;
                server.stopServer();
                startServerButton.setEnabled(true);
                stopServerButton.setEnabled(false);
                showText("\nThe server stops working...");
            }
        });
        exitServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server != null) {
                    server.stopServer();
                    showText("\nThe server stops working...");
                }
                frame.dispose();
            }
        });
        frame.getContentPane().add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            server = new Server(serverSocket);
            server.start();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void showText(String s) {
        textArea.append(s);
    }

    public static void main(String[] args) {
        ServerFrame server = new ServerFrame();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel1.setMinimumSize(new Dimension(600, 400));
        panel1.setPreferredSize(new Dimension(600, 400));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel2, BorderLayout.NORTH);
        final JLabel label1 = new JLabel();
        label1.setText("Working Port:");
        panel2.add(label1);
        textField = new JTextField();
        textField.setColumns(6);
        panel2.add(textField);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel3, BorderLayout.SOUTH);
        startServerButton = new JButton();
        startServerButton.setText("Start ServerFrame");
        panel3.add(startServerButton);
        stopServerButton = new JButton();
        stopServerButton.setText("Stop ServerFrame");
        panel3.add(stopServerButton);
        exitServerButton = new JButton();
        exitServerButton.setText("Exit ServerFrame");
        panel3.add(exitServerButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
