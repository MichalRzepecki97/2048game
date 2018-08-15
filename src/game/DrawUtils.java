package game;

import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class DrawUtils {
    private DrawUtils(){}

    public static int getMessageWidth(String Message, Font font, Graphics2D g){
        g.setFont(font);
        Rectangle2D bounds = g.getFontMetrics().getStringBounds(Message ,g);
        return (int)bounds.getWidth();
    }
    public static int getMessageHeight(String Message, Font font, Graphics2D g) {
        g.setFont(font);
        if (Message.length() == 0)
            return 0;
        TextLayout tl = new TextLayout(Message, font, g.getFontRenderContext());
        return (int) tl.getBounds().getHeight();

    }
}
