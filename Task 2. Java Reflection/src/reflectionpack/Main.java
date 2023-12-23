package reflectionpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;

public class Main {

    JFrame frame = new JFrame("Анализатор класса");
    private JPanel panel = new JPanel();
    private Dimension screenSize;

    private JPanel input_panel = new JPanel();
    private JLabel label;
    private JTextField input;

    private JPanel text_panel = new JPanel();
    private JTextArea text;

    private JPanel buttons_panel = new JPanel();
    private JButton analyse;
    private JButton clear;
    private JButton end;

    private String className;
    private Class object;


    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        screenSize = kit.getScreenSize();
        panel.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));

        panel.setLayout(new BorderLayout());
        frame.setResizable(false);

        label = new JLabel("Полное имя класса");
        input = new JTextField(15);
        input.addActionListener(new TextWritten());
        input_panel.add(label);
        input_panel.add(input);
        panel.add(input_panel, BorderLayout.NORTH);

        text = new JTextArea(30, 86);
        final JScrollPane scrollPane = new JScrollPane(text);
        text_panel.add(scrollPane);
        panel.add(text_panel, BorderLayout.CENTER);

        analyse = new JButton("Анализировать");
        clear = new JButton("Очистить");
        end = new JButton("Завершить");

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(null);
                input.setText(null);
            }
        });

        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttons_panel.add(analyse);
        buttons_panel.add(clear);
        buttons_panel.add(end);
        panel.add(buttons_panel, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public String setText(Class object) {
        String text = "";
        text += "Пакет: " + object.getPackage() + "\n";
        text += "Модифікатори: " + Modifier.toString(object.getModifiers()) + "\n";
        text += "Ім'я аналізующого класу: " + object.getName() + "\n";
        text += "Просте ім'я аналізующого класу: " + object.getSimpleName() + "\n";
        text += "Базовий клас: " + object.getSuperclass() + "\n";
        text += "Реалізуючі інтерфейси:" + "\n";
        for (Class cls : object.getInterfaces()) {
            text += cls.getSimpleName() + "\n";
        }
        text += "Поля класа:" + "\n";
        for (Field field :
                object.getDeclaredFields()) {
            text += field + "\n";
        }
        text += "Конструктори класа:" + "\n";
        for (Constructor cons :
                object.getDeclaredConstructors()) {
            text += cons + "\n";
        }
        text += "Методи класу:" + "\n";
        for (Method meth :
                object.getDeclaredMethods()) {
            text += meth + "\n";
        }
        return text;
    }

    class TextWritten implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            className = input.getText();
            try {
                object = Class.forName(className);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            text.setText(setText(object));
        }
    }

}
