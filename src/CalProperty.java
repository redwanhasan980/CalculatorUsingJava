import java.awt.*;

public class CalProperty {
    public static final Color DARK_COLOR = new Color(0x272727);
    public static final Color SURFACE_COLOR = new Color(0xFF939393, true);
    public static final Color FORE_COLOR = new Color(0x2C3531);
    public static final Color DISABLED_COLOR = new Color(0xAFAFAF);
    public static final Color RED_COLOR = new Color(0xFF652F);
    public static final Color YELLOW_COLOR = new Color(0x0B0C10);
    public static final Color GREEN_COLOR = new Color(0xFF5EC479);
    public static final Color BLUE_COLOR = new Color(0x5A59EA);


     // Set whether to see the steps on how the computer executes my algorithm

    public static final boolean SHOW_STEPS = false;


     // Create custom font with size

    public static Font createFont(int size) {
        return new Font("Arial", Font.BOLD, size);
    }
    public static Font createFont(String name ,int size) {
        return new Font(name, Font.BOLD, size);
    }


     // Force capitalized strings

    public static String forceCapitalize(String text) {
        String output = "";

        final String[] WORDS = text.split(" ");

        for (int i = 0; i < WORDS.length; i++) {
            output += String.valueOf(WORDS[i].charAt(0)).toUpperCase() + WORDS[i].substring(1, WORDS[i].length()).toLowerCase();

            if (i != WORDS.length - 1) {
                output += " ";
            }
        }

        return output;
    }


     //Reverse a string

    public static String reverseString(String text) {
        return new StringBuilder(text).reverse().toString();
    }

}
