package game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tile {
    public static final int width = 100;
    public static final int height = 100;
   // public static final int sliteSpeed = 20;//raczej usunąć
    public static final int ARC_width = 15;
    public static final int ARC_height = 15;
    public static final int SLIDE_SPEED = 20;


    private int value;
    private BufferedImage tileImage;
    private Color background;
    private Color text;
    private Font font;
    private Point slideTo;
    private int x;
    private int y;

    private boolean startAnimation = true;
    private double scaleFirst = 0.1;
    private BufferedImage startImage;

    private  boolean combineAnimation = false;
    private double scaleCombine = 1.2;
    private BufferedImage combineImage;


    private boolean canCombine = true;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tile(int x, int y, int value){
        this.value = value;
        this.x = x;
        this.y= y;
        slideTo = new Point(x,y);
        tileImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        startImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        combineImage = new BufferedImage(width*2,height*2,BufferedImage.TYPE_INT_ARGB);
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
        int drawY = height /2 + DrawUtils.getMessageHeight(""+ value,font ,g )/2;
        g.drawString(""+ value,drawX,drawY);
        g.dispose();
    }
    public void update(){
        if (startAnimation){
            AffineTransform at = new AffineTransform();
            at.translate(width/2 - scaleFirst * width/2,height/2 -scaleFirst *height/2);
            at.scale(scaleFirst,scaleFirst);
            Graphics2D g2d = (Graphics2D)startImage.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setColor(new Color(0,0,0,0));
            g2d.fillRect(0,0,width,height);
            g2d.drawImage(tileImage,at,null);
            scaleFirst += 0.1;
            g2d.dispose();
            if (scaleFirst>= 1)
                startAnimation = false;
        }
        else if(combineAnimation){
            AffineTransform at = new AffineTransform();
            at.translate(width/2 - scaleCombine * width/2,height/2 -scaleCombine *height/2);
            at.scale(scaleCombine,scaleCombine);
            Graphics2D g2d = (Graphics2D)combineImage.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setColor(new Color(0,0,0,0));
            g2d.fillRect(0,0,width,height);
            g2d.drawImage(tileImage,at,null);
            scaleCombine -= 0.05;
            g2d.dispose();
            if (scaleCombine<= 1)
                combineAnimation = false;
        }
    }

    public void render(Graphics2D g ){
        if (startAnimation){
            g.drawImage(startImage,x,y,null );
        }
        else if (combineAnimation){
            g.drawImage(combineImage,(int)(x+width / 2 -scaleCombine * width / 2)
                                    ,(int)(y+height / 2 -scaleCombine * height / 2),null);
        }else {
            g.drawImage(tileImage, x, y, null);
        }
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
        drawImage();
    }
    public boolean CanCombine()
    {
        return canCombine;
    }

    public void setCanCombine(boolean canCombine)
    {
        this.canCombine = canCombine;
    }

    public Point getSlideTo()
    {
        return slideTo;
    }

    public void setSlideTo(Point slideTo) {
        this.slideTo = slideTo;
    }

    public boolean isCombineAnimation() {
        return combineAnimation;
    }

    public void setCombineAnimation(boolean combineAnimation) {
        this.combineAnimation = combineAnimation;
    }
}





