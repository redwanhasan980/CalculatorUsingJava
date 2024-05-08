import javax.swing.*;

public class Calculator {

    private static final int POINT_LENGTH = 10;

    /**
     * REACTIVE PROPERTIES
     */

    private static int mode = 0;
    private static boolean isOn = false;

    /**
     * Convert decimal number to binary
     *
     * > Using my own original algorithm
     *
     * @param dec
     * @return String
     */
    public static String convertDecimalToBinary(String dec) {
        // Check if the decimal is not empty
        if (dec.length() > 0) {
            String leftNum = "";
            String rightNum = "";

            // ==== COMPUTATION ==== //

            try {
                // Split the text by dots
                final String[] splits = dec.split("\\.");

                // Convert the string to long
                long num = Long.parseLong(splits.length > 1 ? splits[0] : dec);

                // While num is greater than 0
                while (num > 0) {
                    // Get the remainder and append it to the output
                    leftNum += num % 2;
                    // Divide the num by 2 without floating point
                    num = Math.floorDiv(num, 2);
                }

                // Splits is equal to 2 if there is a dot or point
                if (splits.length == 2) {
                    /**
                     * Convert decimal point to binary
                     */

                    // Has floating point
                    // Convert the decimal point to double
                    // For intance, the value of splits[1] is 625 
                    //
                    // Then pt would be 0.625
                    double pt = Double.parseDouble("0." + splits[1]);

                    // Length tracker
                    int ptLength = 0;

                    /**
                     * Converting floating point number to binary
                     *
                     * 0.625 x 2 = 1.25  – 1
                     * 0.25 x 2 = 0.5    – 0
                     * 0.5 x 2 = 1.0     – 1
                     *
                     * Reset the whole number to 0 if it's 1
                     */

                    // While pt is not 0 and pt is less than 0
                    while (pt != 0 && pt < 1) {
                        // Multiply the point by 2
                        pt *= 2;

                        //  If the pt is greater than or equal to 1, add 1 else 0
                        rightNum += (pt >= 1) ? "1" : "0";

                        // If pt is greater than 1,
                        // then subtract it by it's whole number
                        if (pt > 1) {
                            pt -= Math.floor(pt);
                        }

                        // Increase the length counter
                        ptLength++;

                        // If pt is equal to 1.0, then break the loop
                        if (pt == 1.0 || ptLength >= POINT_LENGTH) {
                            break;
                        }
                    }
                }
                else if (splits.length > 2) {
                    // Multiple points
                    return "-3"; // Please input only one dot
                }
            }
            catch (NumberFormatException e) {
                return "-2"; // Input numbers only
            }

            // ===================== //

            // Check if it's floating point
            if (leftNum.length() > 0 && rightNum.length() > 0) {
                return CalProperty.reverseString(leftNum) + "." + rightNum;
            }

            return CalProperty.reverseString(leftNum);
        }

        return "-1"; // Please fill the decimal field
    }

    /**
     * Convert binary to decimal
     *
     * > Using my own original algorithm
     *
     * @param bin
     * @return
     */
    public static String convertBinaryToDecimal(String bin) {
        // Check if the text is not empty
        if (bin.length() > 0) {
            // Output bin
            int leftBin = 0;
            double rightBin = 0;

            // Check if the binary contains only 0 and 1
            for (char b : bin.toCharArray()) {
                if (b != '0' && b != '1' && b != '.') {
                    return "-4"; // Please input only 0 and 1"
                }
            }

            // Split the text by dots
            final String[] SPLITS = bin.split("\\.");
            final boolean hasFloating = SPLITS.length == 2;

            if (SPLITS.length <= 2) {
                try {
                    // Get the length of the whole number
                    final int LENGTH = hasFloating ? SPLITS[0].length() : bin.length();
                    final String chToCompute = hasFloating ? SPLITS[0] : bin;

                    // For every character
                    for (int i = 1; i <= LENGTH; i++) {
                        // If the character is 1, then add it's corresponding perfect square
                        if (chToCompute.charAt(i - 1) == '1') {
                            /**
                             * Using the binary conversion table
                             * If last element, add only 1
                             *
                             * For instance, 101
                             *
                             * 1st element = 1 = (2 ^ length - 1) = (2 ^ 3 - 1) = (2 ^ 2)
                             * 2nd element = 0, so skip
                             * 3rd element = 1 = (last element) = 1
                             *
                             * Subtotal:
                             *
                             * 1st = 4
                             * 2nd = 0
                             * 3rd = 1
                             *
                             * Total: 5
                             */
                            leftBin += (i == LENGTH) ? 1 : (int)Math.pow(2, LENGTH - i);
                        }
                    }

                    // If the binary has a decimal point
                    if (hasFloating) {
                        final String pt = SPLITS[1];

                        for (int i = 1; i <= pt.length(); i++) {
                            final String ch = String.valueOf(pt.charAt(i - 1));
                            final double ans = Integer.parseInt(ch) * Math.pow(2, -i);

                            rightBin += ans;
                        }

                        String finalPoint = String.valueOf(rightBin).replace("0.", "");

                        return String.valueOf(leftBin) + "." + finalPoint;
                    }

                    return String.valueOf(leftBin);
                }
                catch (Exception e) {
                    return "-2"; // An error has occured
                }
            }
            else {
                // Multiple points
                return "-3"; // Please input only one dot
            }
        }

        return "-1"; // Please fill the binary field
    }

    /**
     * Invoking my own algorithm for evaluating expressions with MDAS
     *
     * @param label
     * @param inputPrevious
     * @param input
     */
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

    /**
     * Switch between modes
     *
     *  Normal Mode        = 0
     *  Decimal to Binary  = 1
     *  Binary to Decimal  = 2
     * ```
     */
    public static int switchMode(JLabel normal, JLabel db, JLabel bd) {
        normal.setForeground(CalProperty.FORE_COLOR);
        db.setForeground(CalProperty.FORE_COLOR);
        bd.setForeground(CalProperty.FORE_COLOR);

        // Increment mode
        mode++;

        switch (mode % 3) {
            // Normal mode
            case 0:
                normal.setForeground(CalProperty.YELLOW_COLOR);
                break;
            // Decimal to Binary mode
            case 1:
                db.setForeground(CalProperty.YELLOW_COLOR);
                break;
            // Binary to Decimal mode
            case 2:
                bd.setForeground(CalProperty.YELLOW_COLOR);
                break;
        }

        return mode % 3;
    }


}
