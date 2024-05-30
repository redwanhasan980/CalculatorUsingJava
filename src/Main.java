import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {
    private static boolean isOn = false;
    private static int mode = 0;
    private static final Dimension WIN_SIZE = new Dimension(800, 650);

    private static final Border PADDING = BorderFactory.createEmptyBorder(16, 16, 16, 16);
    public static void main(String[] args) {
        // Main frame

        JFrame frame = new JFrame("Calculator by Redwan");
        frame.setSize(WIN_SIZE);
        frame.setMinimumSize(WIN_SIZE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        /*LAYOUTS  */

        BorderLayout panelLayout = new BorderLayout();
        panelLayout.setVgap(16);

        GridLayout inputPanelLayout = new GridLayout(3, 1);
        inputPanelLayout.setVgap(8);

        GridLayout input1PanelLayout = new GridLayout(1, 1);
        GridBagLayout buttonsPanelLayout = new GridBagLayout();



        JPanel panel = new JPanel(panelLayout);
        JPanel inputPanel = new JPanel(inputPanelLayout);
        JPanel buttonsPanel = new JPanel(buttonsPanelLayout);
        JPanel input1Panel = new JPanel(input1PanelLayout);
        JPanel input2Panel = new JPanel(new GridLayout(1, 1));
        JPanel input3Panel = new JPanel(new GridLayout(1, 1));

        Random random=new Random();
        int index=random.nextInt();
        if(index<0)index*=-1;

        JLabel mode3 = new JLabel(Quotes.quotes[(index%24)], SwingConstants.CENTER);

        // input2Panel's componenets
        JLabel inputLabel = new JLabel(" ", SwingConstants.RIGHT);

        // input3Panel's components
        JLabel inputPrevious = new JLabel(" ", SwingConstants.RIGHT);

        // 1st row
        CalButton addButton = new CalButton("+", CalProperty.BLUE_COLOR);
        CalButton subButton = new CalButton("-", CalProperty.BLUE_COLOR);
        CalButton mulButton = new CalButton("x", CalProperty.BLUE_COLOR);
        CalButton divButton = new CalButton("/", CalProperty.BLUE_COLOR);

        // 2nd row
        CalButton num7Button = new CalButton("7", CalProperty.GREEN_COLOR);
        CalButton num8Button = new CalButton("8", CalProperty.GREEN_COLOR);
        CalButton num9Button = new CalButton("9", CalProperty.GREEN_COLOR);
        CalButton rootOver = new CalButton("√", CalProperty.BLUE_COLOR);

        // 3rd row
        CalButton num4Button = new CalButton("4", CalProperty.GREEN_COLOR);
        CalButton num5Button = new CalButton("5", CalProperty.GREEN_COLOR);
        CalButton num6Button = new CalButton("6", CalProperty.GREEN_COLOR);
        CalButton clearButton = new CalButton("C", CalProperty.RED_COLOR);

        // 4th row
        CalButton num1Button = new CalButton("1", CalProperty.GREEN_COLOR);
        CalButton num2Button = new CalButton("2", CalProperty.GREEN_COLOR);
        CalButton num3Button = new CalButton("3", CalProperty.GREEN_COLOR);
        CalButton powerButton = new CalButton("On");

        // 5th row
        CalButton num0Button = new CalButton("0", CalProperty.GREEN_COLOR);
        CalButton dotButton = new CalButton(".", CalProperty.BLUE_COLOR);
        CalButton equalsButton = new CalButton("=", CalProperty.BLUE_COLOR);



        panel.setBorder(PADDING);
        panel.setBackground(CalProperty.DARK_COLOR);

        mode3.setForeground(CalProperty.FORE_COLOR);
        mode3.setFont(CalProperty.createFont("Arial Rounded MT Bold",14));


        inputPanel.setBorder(PADDING);
        inputPanel.setBackground(CalProperty.SURFACE_COLOR);

        inputLabel.setFont(CalProperty.createFont(28));
        inputLabel.setForeground(CalProperty.FORE_COLOR);

        inputPrevious.setFont(CalProperty.createFont(18));
        inputPrevious.setForeground(CalProperty.FORE_COLOR);

        input1Panel.setBackground(CalProperty.SURFACE_COLOR);
        input2Panel.setBackground(CalProperty.SURFACE_COLOR);
        input3Panel.setBackground(CalProperty.SURFACE_COLOR);

        buttonsPanel.setBorder(PADDING);
        buttonsPanel.setBackground(CalProperty.SURFACE_COLOR);

        powerButton.setCustomEnabled(true);
        powerButton.setForeground(CalProperty.GREEN_COLOR);

        /** =============== EVENT LISTENERS ================== */

        final ActionListener numListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final Object src = e.getSource();

                String toAdd = "";

                if (src.equals(num0Button)) {
                    toAdd = "0";
                } else if (src.equals(num1Button)) {
                    toAdd = "1";
                } else if (src.equals(num2Button)) {
                    toAdd = "2";
                }  else if (src.equals(num3Button)) {
                    toAdd = "3";
                } else if (src.equals(num4Button)) {
                    toAdd = "4";
                } else if (src.equals(num5Button)) {
                    toAdd = "5";
                } else if (src.equals(num6Button)) {
                    toAdd = "6";
                } else if (src.equals(num7Button)) {
                    toAdd = "7";
                } else if (src.equals(num8Button)) {
                    toAdd = "8";
                } else if (src.equals(num9Button)) {
                    toAdd = "9";
                } else if (src.equals(dotButton)) {
                    toAdd = ".";
                } else if (src.equals(addButton)) {
                    toAdd = " + ";
                } else if (src.equals(subButton)) {
                    toAdd = " - ";
                } else if (src.equals(mulButton)) {
                    toAdd = " x ";
                } else if (src.equals(divButton)) {
                    toAdd = " / ";
                } else if (src.equals(rootOver)) {
                    toAdd = " √ ";
                }
                inputLabel.setText(inputLabel.getText() + toAdd);
            }
        };


        num0Button.addActionListener(numListener);
        num1Button.addActionListener(numListener);
        num2Button.addActionListener(numListener);
        num3Button.addActionListener(numListener);
        num4Button.addActionListener(numListener);
        num5Button.addActionListener(numListener);
        num6Button.addActionListener(numListener);
        num7Button.addActionListener(numListener);
        num8Button.addActionListener(numListener);
        num9Button.addActionListener(numListener);
        dotButton.addActionListener(numListener);
        addButton.addActionListener(numListener);
        subButton.addActionListener(numListener);
        mulButton.addActionListener(numListener);
        divButton.addActionListener(numListener);
        rootOver.addActionListener(numListener);




        equalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String EXPRESSION = inputLabel.getText().trim();
                    // Normal mode
                        inputPrevious.setText(EXPRESSION);
                        Calculator.calculate(inputLabel, inputPrevious, EXPRESSION);
            }
        });


        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputLabel.setText(" ");
                inputPrevious.setText(" ");
            }
        });




        powerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isOn = !isOn;

                if (!isOn) {
                    inputLabel.setText(" ");
                    inputPrevious.setText(" ");
                    mode = -1;
//                    Calculator.switchMode(mode3, mode1, mode2);
                    mode3.setForeground(CalProperty.FORE_COLOR);
                } else {
                    mode3.setForeground(CalProperty.YELLOW_COLOR);
                    num0Button.setForeground(CalProperty.GREEN_COLOR);
                    num1Button.setForeground(CalProperty.GREEN_COLOR);
                    num2Button.setForeground(CalProperty.GREEN_COLOR);
                    num3Button.setForeground(CalProperty.GREEN_COLOR);
                    num4Button.setForeground(CalProperty.GREEN_COLOR);
                    num5Button.setForeground(CalProperty.GREEN_COLOR);
                    num6Button.setForeground(CalProperty.GREEN_COLOR);
                    num7Button.setForeground(CalProperty.GREEN_COLOR);
                    num8Button.setForeground(CalProperty.GREEN_COLOR);
                    num9Button.setForeground(CalProperty.GREEN_COLOR);
                }


                num0Button.setCustomEnabled(isOn);
                num1Button.setCustomEnabled(isOn);
                num2Button.setCustomEnabled(isOn);
                num3Button.setCustomEnabled(isOn);
                num4Button.setCustomEnabled(isOn);
                num5Button.setCustomEnabled(isOn);
                num6Button.setCustomEnabled(isOn);
                num7Button.setCustomEnabled(isOn);
                num8Button.setCustomEnabled(isOn);
                num9Button.setCustomEnabled(isOn);

                addButton.setCustomEnabled(isOn);
                subButton.setCustomEnabled(isOn);
                mulButton.setCustomEnabled(isOn);
                divButton.setCustomEnabled(isOn);

                rootOver.setCustomEnabled(isOn);
                clearButton.setCustomEnabled(isOn);
                dotButton.setCustomEnabled(isOn);
                equalsButton.setCustomEnabled(isOn);

                powerButton.setText(isOn ? "Off" : "On");
                powerButton.setForeground(isOn ? CalProperty.RED_COLOR : CalProperty.GREEN_COLOR);
            }
        });




        input1Panel.add(mode3);

        input2Panel.add(inputLabel);
        input3Panel.add(inputPrevious);

        inputPanel.add(input1Panel);
        inputPanel.add(input2Panel);
        inputPanel.add(input3Panel);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(16, 8, 16, 8);

        // 1st row
        buttonsPanel.add(addButton, gbc);

        gbc.gridx++;
        buttonsPanel.add(subButton, gbc);

        gbc.gridx++;
        buttonsPanel.add(mulButton, gbc);

        gbc.gridx++;
        buttonsPanel.add(divButton, gbc);

        // 2nd row
        gbc.gridx = 0;
        gbc.gridy++;

        buttonsPanel.add(num7Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num8Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num9Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(rootOver, gbc);

        // 3rd row
        gbc.gridx = 0;
        gbc.gridy++;

        buttonsPanel.add(num4Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num5Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num6Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(clearButton, gbc);

        // 4th row
        gbc.gridx = 0;
        gbc.gridy++;

        buttonsPanel.add(num1Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num2Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(num3Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(powerButton, gbc);

        // 5th row
        gbc.gridx = 0;
        gbc.gridy++;

        buttonsPanel.add(num0Button, gbc);

        gbc.gridx++;
        buttonsPanel.add(dotButton, gbc);

        gbc.gridx++;
        gbc.gridwidth = 2;

        buttonsPanel.add(equalsButton, gbc);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }
}
