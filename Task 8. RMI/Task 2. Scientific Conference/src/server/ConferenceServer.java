package server;

import registry.Participant;
import registry.RegImpl;
import xml.DataSheetToXML;
import xml.SAXRead;

import javax.swing.*;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ConferenceServer extends JFrame {
    private RegImpl regImpl = null;
    private Registry registry = null;
    private JPanel panel;
    public static JTextArea textArea;
    public static JTextField hostField;
    private JTextField portField;
    public static JTextField partField;
    private JButton startButton;
    private JButton stopButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton exitButton;
    private JScrollPane scrollPane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel hostLabel;
    private JLabel portLabel;
    private JLabel participantsLabel;
    private final JFileChooser fileChooser = new JFileChooser();

    public ConferenceServer() throws RemoteException {
        $$$setupUI$$$();
        createUIComponents();
        startButton.addActionListener(e -> {
            int port = Integer.parseInt(portField.getText());
            regImpl = new RegImpl();
            try {
                registry = LocateRegistry.createRegistry(port);
                textArea.append("The RMI registry is created.");
                Remote stub = UnicastRemoteObject.exportObject(regImpl, 0);
                registry.rebind("Registerable", stub);
                textArea.append("The stub is registered in RMI registry.");
                textArea.append("The conference server is ready to work.");
                partField.setText("0");
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            saveButton.setEnabled(true);
            portField.setEnabled(false);
        });
        stopButton.addActionListener(e -> {
            try {
                stop();
                textArea.append("The server is stopped.");
            } catch (RemoteException | NotBoundException remoteException) {
                remoteException.printStackTrace();
            }
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            portField.setEnabled(true);
        });
        loadButton.addActionListener(e -> {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
                String fileName = fileChooser.getSelectedFile().getPath();
                ArrayList<Participant> load = SAXRead.XMLReadData(fileName);
                regImpl.setRegParticipant(load);
            }
        });
        saveButton.addActionListener(e -> {
            if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                String fileName = fileChooser.getSelectedFile().getPath();
                DataSheetToXML.saveXMLDoc(DataSheetToXML.createDataSheetDOM(regImpl.getRegParticipant()), fileName);
                JOptionPane.showMessageDialog(null, "File " + fileName.trim() + " saved!",
                        "Результати збережені", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        exitButton.addActionListener(e -> System.exit(0));
        setContentPane(panel);
        setTitle("Conference Server");
        setPreferredSize(new Dimension(612, 400));
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public void stop() throws RemoteException, NotBoundException {
        registry.unbind("Registerable");
        if (UnicastRemoteObject.unexportObject(regImpl, true)) {
            textArea.append("The object is unexported.\n");
        } else {
            textArea.append("The object is not unexported.");
        }
        if (UnicastRemoteObject.unexportObject(registry, true)) {
            textArea.append("The registry is unexported.");
        } else {
            textArea.append("The registry is not unexported.");
        }
    }

    public static void main(String[] args) {
        try {
            new ConferenceServer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void createUIComponents() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.add(panel1, BorderLayout.NORTH);
        hostLabel = new JLabel();
        hostLabel.setText("host:");
        panel1.add(hostLabel);
        hostField = new JTextField();
        hostField.setColumns(20);
        hostField.setEnabled(false);
        panel1.add(hostField);
        portLabel = new JLabel();
        portLabel.setText("port:");
        panel1.add(portLabel);
        portField = new JTextField();
        portField.setColumns(5);
        panel1.add(portField);
        participantsLabel = new JLabel();
        participantsLabel.setText("participants:");
        panel1.add(participantsLabel);
        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel.add(panel3, BorderLayout.CENTER);
        textArea = new JTextArea();
        textArea.setColumns(70);
        textArea.setRows(36);
        panel3.add(textArea, BorderLayout.CENTER);
        scrollPane = new JScrollPane(textArea);
        panel3.add(scrollPane);
        partField = new JTextField();
        partField.setColumns(5);
        partField.setEnabled(false);
        panel1.add(partField);
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.add(panel2, BorderLayout.SOUTH);
        startButton = new JButton();
        startButton.setText("Start");
        panel2.add(startButton);
        stopButton = new JButton();
        stopButton.setEnabled(false);
        stopButton.setText("Stop");
        panel2.add(stopButton);
        saveButton = new JButton();
        saveButton.setEnabled(false);
        saveButton.setText("Save");
        panel2.add(saveButton);
        loadButton = new JButton();
        loadButton.setText("Load");
        panel2.add(loadButton);
        exitButton = new JButton();
        exitButton.setText("Exit");
        panel2.add(exitButton);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel.setLayout(new BorderLayout(0, 0));
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.add(panel1, BorderLayout.NORTH);
        hostLabel.setText("host:");
        panel1.add(hostLabel);
        portLabel.setText("port:");
        panel1.add(portLabel);
        portField.setColumns(5);
        panel1.add(portField);
        participantsLabel.setText("participants:");
        panel1.add(participantsLabel);
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel.add(panel2, BorderLayout.SOUTH);
        startButton.setText("Start");
        panel2.add(startButton);
        stopButton.setEnabled(false);
        stopButton.setText("Stop");
        panel2.add(stopButton);
        saveButton.setEnabled(false);
        saveButton.setText("Save");
        panel2.add(saveButton);
        loadButton.setText("Load");
        panel2.add(loadButton);
        exitButton.setText("Exit");
        panel2.add(exitButton);
        panel.add(panel3, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() { return panel; }

}

