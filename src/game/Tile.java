package game;

import com.sun.deploy.security.ValidationState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public static final int width = 80;
    public static final int height = 80;
    public static final int sliteSpeed = 20;
    public static final int ARC_width = 15;
    public static final int ARC_height = 15;


    private int value;
    private BufferedImage tileImage;
    private Color background;
    private Color text;
    private Font font;
    private int x;
    private int y;
    public Tile(int x, int y, int value){
        this.value = value;
        this.x = x;
        this.y= y;
        tileImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        drawImage();

    }

    private void drawImage(){
        Graphics2D g = (Graphics2D)tileImage.getGraphics();

        if(value == 2 ){
            background = new Color(0xe9e9e9);
            text = new Color(0x000000);
        }

        else if(value ==4){
            background = new Color(0xe6daab);
            text = new Color(0x000000);
        }

        else if(value ==8){
            background = new Color(0xf79d3d);
            text = new Color(0xffffff);
    }
        else if(value ==16){
            background = new Color(0xf28007);
            text = new Color(0xffffff);
    }
        else if(value ==32){
            background = new Color(0xf55e3b);
            text = new Color(0xffffff);
        }
        else if(value ==64){
            background = new Color(0xf78c4);
            text = new Color(0xffffff);
        }
        else if(value ==128){
            background = new Color(0xe9e9);
            text = new Color(0xffffff);
        }
        else if(value ==256){
            background = new Color(0xf6e873);
            text = new Color(0xffffff);
        }
        else if(value ==512){
            background = new Color(0xf5e455);
            text = new Color(0xffffff);
        }
        else if(value ==1024){
            background = new Color(0xf7e12c);
            text = new Color(0xffffff);
        }
        else if(value ==2048){
            background = new Color(0xffe400);
            text = new Color(0xffffff);
        }
    else{
        background = Color.black;
        text = Color.white;

        }

        g.setColor(new Color(0,0,0,0));
        g.fillRect(0,0,width,height);

        g.setColor(background);
        g.fillRoundRect(0,0,width,height,ARC_width,ARC_height);

        g.setColor(text);
        if(value<=64){
            font = Game.main.deriveFont(36f);
        }else{
            font = Game.main;
        }
        g.setFont(font);

        int drawX = width /2 - DrawUtils.getMessageWidth(""+value,font,g)/2;
        int drawY = height /2 - DrawUtils.getMessageHeight(""+ value,font ,g )/2;
        g.drawString(""+ value,drawX,drawY);

    }
}
