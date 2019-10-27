import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*
        Для вывода используется GridLayout с тремя рядами:
        - FlowLayout с двумя элементами JTextArea для ввода чисел А и В
        - неизменяемое поле JTextArea для вывода результата, без особого Layout
        - FlowLayout с пятью кнопками вычислений
        */

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,0));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel resultPanel = new JPanel();

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Поля ввода
        JTextField inputs[] = new JTextField[2];
        inputs[0] = new JTextField(7);
        inputs[1] = new JTextField(7);

        for (int i = 0; i < inputs.length; i++)
            inputPanel.add(inputs[i]);

        // Поле результата
        JTextField result = new JTextField(15);
        result.setEditable(false);
        result.setText("result");
        resultPanel.add(result);

        // Кнопки вычислений
        JButton buttons[] = new JButton[5];
        buttons[0] = new JButton("+");
        buttons[1] = new JButton("-");
        buttons[2] = new JButton("*");
        buttons[3] = new JButton("/");
        buttons[4] = new JButton("^");

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Проверка на пустое поле и ввод нечисловых данных. Значение такого поля устанавливается равным нулю
                double value1 = inputs[0].getText().isEmpty() || inputs[0].getText().matches(".*\\D.*") ? 0 : Double.parseDouble(inputs[0].getText());
                double value2 = inputs[1].getText().isEmpty() || inputs[1].getText().matches(".*\\D.*") ? 0 : Double.parseDouble(inputs[1].getText());

                switch(e.getActionCommand()) {
                    case "+": result.setText(Double.toString(value1 + value2)); break;
                    case "-": result.setText(Double.toString(value1 - value2)); break;
                    case "/": if (value2 != 0) { result.setText(Double.toString(value1 / value2)); } else { result.setText("DIVISION BY ZERO"); }; break;
                    case "*": result.setText(Double.toString(value1 * value2)); break;
                    case "^": result.setText(Double.toString(Math.pow(value1, value2))); break;
                }
            }
        };

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setPreferredSize(new Dimension(30,30));
            buttons[i].addActionListener(listener);
            buttonsPanel.add(buttons[i]);
        }

        mainPanel.add(inputPanel);
        mainPanel.add(resultPanel);
        mainPanel.add(buttonsPanel);

        frame.add(mainPanel);
        frame.setSize(200, 140);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}

