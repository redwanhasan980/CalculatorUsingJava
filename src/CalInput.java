import java.util.HashMap;

class CalInput {
    private String exp = "";
    private char[] ops = { 'x', '/', '+', '-','√' };

    public CalInput(String input) {
        this.exp = input;
    }


     //Check if the character passed is an operator

    private boolean checkOperation(char ch) {
        for (int i = 0; i < this.ops.length; i++) {
            if (ch == this.ops[i]) {
                System.out.println(ch);
                return true;
            }
        }

        return false;
    }


     //Check if a string has operator

    private boolean hasOperator(String input) {
        // Ignore "-" operator at first number
        for (int i = 0; i < input.length(); i++) {
              if(i==0)
              {
                  if(input.charAt(i)=='√')
                      return true;
              }
              else {
                  if (checkOperation(input.charAt(i))) {
                      System.out.println("r");
                      return true;
                  }
              }
        }

        return false;
    }


     // Get the highest precidence operator in the input

    private char getHighestOperator(String input) {
        final int mulIndex = input.indexOf("x", 1);
        final int divIndex = input.indexOf("/", 1);
        final int addIndex = input.indexOf("+", 1);
        final int subIndex = input.indexOf("-", 1);
        final int rootIndex = input.indexOf("√",0);

        if (mulIndex != -1 && divIndex != -1) {
            return mulIndex < divIndex ? 'x' : '/';
        }

        if (mulIndex != -1 && (divIndex == -1 || addIndex == -1 || subIndex == -1)) {
            return 'x';
        }

        if (divIndex != -1 && (mulIndex == -1 || addIndex == -1 || subIndex == -1)) {
            return '/';
        }

        if (addIndex != -1 && subIndex != -1) {
            return addIndex < subIndex ? '+' : '-';
        }

        if (addIndex != -1 && (mulIndex == -1 || divIndex == -1 || subIndex == -1)) {
            return '+';
        }

        if (subIndex != -1 && (mulIndex == -1 || divIndex == -1 || addIndex == -1)) {
            return '-';
        }
        if(rootIndex != -1) {
            return '√';
        }


        return ' ';
    }


    //Get the left and right operand of a specific expression with start index

    private HashMap<String, Object> getLROperand(String input, int start) {
        String leftOperand = "";
        String rightOperand = "";

        int startIndex = 0;
        int endIndex = 0;

        // Get left operand
        for (int j = start - 1; j >= 0; j--) {
            if (j - 1 < 0) {
                startIndex = 0;
            } else {
                startIndex = j + 1;
            }

            char chp = input.charAt(j);

            if (checkOperation(chp)) {
                if (chp == '-' || chp == '+') {
                    char cha = j - 1 >= 0 ? input.charAt(j - 1) : ' ';

                    if (!Character.isDigit(cha)) {
                        leftOperand += chp;
                    }
                }

                break;
            }

            leftOperand += chp;
        }

        // Get right operand
        for (int j = start + 1; j < input.length(); j++) {
            if (j + 1 >= input.length()) {
                endIndex = input.length();
            } else {
                endIndex = j;
            }

            char chp = input.charAt(j);

            if ((chp == '-' || chp == '+') && j == start + 1) {
                rightOperand += chp;
                continue;
            } else  if (checkOperation(chp)) {
                break;
            }

            rightOperand += chp;
        }

        leftOperand = CalProperty.reverseString(leftOperand);

        HashMap<String, Object> output = new HashMap<String, Object>();

        output.put("leftOperand", leftOperand);
        output.put("rightOperand", rightOperand);
        output.put("startIndex", startIndex);
        output.put("endIndex", endIndex);

        return output;
    }


     //Handling Evaluation of the expression

    private String evaluate(String input) {
        // Check if it has operation
        // If it has no operator, then return the input
        // This can be used when evaluating is done
        if (!hasOperator(input)) {
            System.out.printf("no op");
            return input;
        }

        while (hasOperator(input)) {
            char op = getHighestOperator(input);

            for (int j = 0; j < input.length(); j++) {
                char ch = input.charAt(j);

                // Skip number sign on first number
                if ((ch == '-' || ch == '+') && j == 0) {
                    continue;
                }

                if (checkOperation(ch) && ch == op) {
                    HashMap<String, Object> val = getLROperand(input, j);

                    final String LEFT_OPERAND = (String)val.get("leftOperand");
                    final String RIGHT_OPERAND = (String)val.get("rightOperand");
                    final int START_INDEX = (int)val.get("startIndex");
                    final int END_INDEX = (int)val.get("endIndex");

                    /**
                     * See evalutation steps
                     */
                    if (CalProperty.SHOW_STEPS) {
                        System.out.printf("Operator: %-3c Input: %-20s  Left: %-10s Right: %-10s Evaluated: %s\n", op, input, LEFT_OPERAND, RIGHT_OPERAND, input.substring(START_INDEX, END_INDEX));
                    }

                    // Malformed Expression occured
                    if(LEFT_OPERAND.length() == 0 &&op!='√') {
                        return "E: MALFORMED_EXPRESSION";
                    }

                    else if ( RIGHT_OPERAND.length() == 0) {
                        return "E: MALFORMED_EXPRESSION";
                    }

                    try {
                        double leftOperand =0.0;
                        if(LEFT_OPERAND.length() != 0)
                              leftOperand = Double.parseDouble(LEFT_OPERAND);
                        double rightOperand = Double.parseDouble(RIGHT_OPERAND);
                        double answer = 0.0;
                       // System.out.printf("erer");
                        switch (op) {
                            case 'x':
                                answer = leftOperand * rightOperand;
                                break;
                            case '/':
                                answer = leftOperand / rightOperand;
                                break;
                            case '+':
                                answer = leftOperand + rightOperand;
                                break;
                            case '-':
                                answer = leftOperand - rightOperand;
                                break;
                            case '√':
                                answer = Math.sqrt(rightOperand);
                                System.out.print("e");
                        }

                        final String evaluatedInput = input.replace(input.substring(START_INDEX, END_INDEX), String.valueOf(answer));

                        /**
                         * Recursive method
                         */
                        return evaluate(evaluatedInput);
                    }
                    catch (NumberFormatException e) {
                       // System.out.printf("no rt");
                        return "E: " + e.getMessage();
                    }
                }
            }
        }

        return input;
    }


     //Main calculation method

    public String calculate() {
        String FINAL_ANSWER = evaluate(this.exp);

        if (FINAL_ANSWER.endsWith(".0")) {
            FINAL_ANSWER = FINAL_ANSWER.substring(0, FINAL_ANSWER.length() - 2);
        }

        return FINAL_ANSWER;
    }
}

