import javax.swing.*;

public class Calculator {

    private static final int POINT_LENGTH = 10;



    private static int mode = 0;
    private static boolean isOn = false;
    public static void calculate(JLabel label, JLabel inputPrevious, String input) {
        final String INPUT = input.replaceAll(" ", "");

        CalInput calculator = new CalInput(INPUT);
        final String ANSWER = calculator.calculate();

        if (ANSWER.startsWith("E: ")) {
            final String ERROR = CalProperty.forceCapitalize(ANSWER.replace("E: ", "").replaceAll("_", " "));

            if (ERROR.contains("For Input String")) {
                inputPrevious.setText("Malformed Expression");
            } else {
                inputPrevious.setText(ERROR);
            }

            inputPrevious.setForeground(CalProperty.RED_COLOR);
        }
        else {
            label.setText(ANSWER);
            inputPrevious.setForeground(CalProperty.FORE_COLOR);
        }
    }


}
