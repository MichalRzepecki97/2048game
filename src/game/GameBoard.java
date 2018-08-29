package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameBoard {

    public static final int ROWS = 4;
    public static final int COLS = 4;

    private final int startingTiles = 2;
    private Tile[][] board;
    private boolean dead;
    private boolean won;
    private BufferedImage gameBoard;
    private BufferedImage finalboard;
    private int x;
    private int y;

    private boolean hasStarted;
    private static int SPACING = 10;
    public static int Board_width = (COLS + 1) * SPACING + COLS * Tile.width;
    public static int Board_height = (ROWS + 1) * SPACING + ROWS * Tile.height;

    public GameBoard(int x, int y) {
        this.x = x;
        this.y = y;
        board = new Tile[ROWS][COLS];
        gameBoard = new BufferedImage(Board_width, Board_height, BufferedImage.TYPE_INT_RGB);
        finalboard = new BufferedImage(Board_width, Board_height, BufferedImage.TYPE_INT_RGB);

        createBoardImage();
        start();

    }

    private void createBoardImage() {
        Graphics2D g = (Graphics2D) gameBoard.getGraphics();
        //pobieranie grafiki
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, Board_width, Board_height);
        g.setColor(Color.cyan);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int x = SPACING + SPACING * col + Tile.width * col;
                int y = SPACING + SPACING * row + Tile.height * row;
                g.fillRoundRect(x, y, Tile.width, Tile.height, Tile.ARC_width, Tile.ARC_height);
            }
        }
    }

    private void start(){
        for (int i = 0; i <startingTiles ; i++) {
            spawnRandom();
        }
    }

    private void spawnRandom(){
        Random random = new Random();
        boolean notValid = true;

        while (notValid){
            int location = random.nextInt(ROWS*COLS);
            int row = location /ROWS;
            int col = location % COLS;
            Tile current = board[row][col];
            if (current == null){
                int value = random.nextInt(10)< 9?2:4;
                Tile tile = new Tile(value, getTileX(col),getTileY(row));
                board[row][col] = tile;
                notValid = false;
            }
        }
    }
    public int getTileX(int col){
        return SPACING + col*Tile.width + col*SPACING;
    }

    public int getTileY(int row ){
        return SPACING +row*Tile.height + row* SPACING;
    }

    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) finalboard.getGraphics();
        g2d.drawImage(gameBoard, 0, 0, null);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <COLS ; col++) {
                Tile current = board [row][col];
                if(current == null) continue;
                current.render(g2d);
            }
        }

        g.drawImage(finalboard, x, y, null);
        g2d.dispose();
    }

    public void update() {
        checkKeys();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <COLS ; col++) {
                Tile current = board[row][col];
                if (current == null) continue;
                current.update();
                //reset position
                if(current.getValue() == 2048){
                    won = true;
                }
            }
        }
    }

    private void checkKeys() {
        if (Keyboard.typed(KeyEvent.VK_LEFT)) {
            //ruch w lewo
            if (!hasStarted) hasStarted = true;
        }
        if (Keyboard.typed(KeyEvent.VK_RIGHT)) {
            //ruch w prawo
            if (!hasStarted) hasStarted = true;
        }
        if (Keyboard.typed(KeyEvent.VK_UP)) {
            //ruch do góry
            if (!hasStarted) hasStarted = true;
        }
        if (Keyboard.typed(KeyEvent.VK_DOWN)) {
            //ruch do dół
            if (!hasStarted) hasStarted = true;
        }
    }
}

