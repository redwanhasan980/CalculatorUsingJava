import javax.swing.*;
import java.awt.*;

class CalButton extends JButton {
    private Color fgColor = CalProperty.FORE_COLOR;

    public CalButton(String text) {
        setText(text);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(CalProperty.createFont(20));
        setCustomEnabled(false);
    }

    public CalButton(String text, Color fgColor) {
        this(text);
        this.fgColor = fgColor;
    }

    /**
     * Custom enabling and disabling button
     *

     */
    public void setCustomEnabled(boolean b) {
        setEnabled(b);

        if (b) {
            setBackground(CalProperty.DARK_COLOR);
            setForeground(this.fgColor);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            setBackground(CalProperty.DISABLED_COLOR);
        }
    }
}
