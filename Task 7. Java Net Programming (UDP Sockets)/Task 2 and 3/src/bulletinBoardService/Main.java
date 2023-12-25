package bulletinBoardService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main extends JFrame{

    private JPanel panel;
    private JTextArea textArea;
    private JTextField textFieldMsg;
    private JButton sendButton;
    private JTextField addressField;
    private JTextField portField;
    private JTextField nameField;
    private JButton finishButton;
    private JButton clearButton;
    private JButton disconnectButton;
    private JButton connectButton;

    private Messenger messenger = null;
    private InetAddress address = null;
    private int port;
    private String name;

    public Main() {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    address = InetAddress.getByName(addressField.getText());
                } catch (UnknownHostException unknownHostException) {
                    unknownHostException.printStackTrace();
                }
                port = Integer.parseInt(portField.getText());
                name = nameField.getText();
                UITasks ui = (UITasks) Proxy.newProxyInstance(getClass().getClassLoader(),
                        new Class[]{UITasks.class}, new EDTInvocationHandler(new UITaskImpl()));
                messenger = new MessengerImpl(ui, address, port, name);
                messenger.start();
                connectButton.setEnabled(false);
                disconnectButton.setEnabled(true);
                addressField.setEnabled(false);
                portField.setEnabled(false);
                nameField.setEnabled(false);
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (messenger!=null) {
                    messenger.send();
                }
            }
        });
        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (messenger!=null) {
                    messenger.stop();
                }
                connectButton.setEnabled(true);
                disconnectButton.setEnabled(false);
                addressField.setEnabled(true);
                portField.setEnabled(true);
                nameField.setEnabled(true);
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                textFieldMsg.setText("");
            }
        });
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (messenger!=null) {
                    messenger.stop();
                }
                System.exit(0);
            }
        });
        setContentPane(panel);
        setTitle("Text Conference");
        setPreferredSize(new Dimension(612, 400));
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {
	    new Main();
    }

    private class UITaskImpl implements UITasks{

        @Override
        public String getMessage() {
            String res = textFieldMsg.getText();
            textFieldMsg.setText("");
            return res;
        }

        @Override
        public void setText(String text) {
            textArea.append(text + "\n");
        }
    }
}
