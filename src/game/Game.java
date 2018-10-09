package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements KeyListener, Runnable {

    private static final long serialVersionUID = 1L;

    public static final int width = 500;
    public static final int height = 630;
    public static final Font main = new Font("Babes Neue Regular", Font.PLAIN,28);
    private static Thread game;
    private static boolean running;
    private BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);// obcza
    private GameBoard board;

    private long startTime;
    private long elapsed;
    private boolean set;

    public Game(){
        setFocusable(true);
        setPreferredSize(new Dimension(width, height));
        addKeyListener(this);

        board = new GameBoard(width/2 - GameBoard.Board_width / 2,HEIGHT - GameBoard.Board_height - 10 );
    }
    public void update(){
        if(Keyboard.pressed[KeyEvent.VK_SPACE]){
            System.out.println("hit space");
        }
            if(Keyboard.typed(KeyEvent.VK_RIGHT)){
                System.out.println("hit right");
    }
    board.update();
    Keyboard.update();


    }
    public void render(){
        Graphics2D g = (Graphics2D)image.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0,0,width,height);
        board.render(g);
        //tworzy plansze
        g.dispose();

        Graphics2D g2d =(Graphics2D)getGraphics();
        g2d.drawImage(image,0,0,null);
        g2d.dispose();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
     Keyboard.keyRelesed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

        int fps = 0, update = 0;
        long fpsTimer = System.currentTimeMillis();                  //obczaj
        double nsPerUpdate = 1000000000.0 / 60;
        double then = System.nanoTime();
        double unprocessed = 0;


        while(running) {
            boolean shouldRender = false;
            double now = System.nanoTime();//!!obczaj!!
            unprocessed +=(now -then)/nsPerUpdate;
            then = now;



            while (unprocessed >= 1) {
                update++;
                update();
                unprocessed--;
                shouldRender = true;
            }
            if (shouldRender) {
                fps++;
                render();
                shouldRender = false;
            } else {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if(System.currentTimeMillis()- fpsTimer > 1000){
            System.out.printf("%d fps %d updates",fps,update);
            System.out.println();
            fps = 0;
            update = 0;
            fpsTimer += 1000;
    }

    }
    public static synchronized void start(){
        if(running) return;;
        running = true;
        //game  = new Thread(this,"game");
         start();
    }
    public synchronized void stop(){
        if(!running)return;
        running = false;
        System.exit(0);
    }
}
