package game;

import java.awt.image.BufferedImage;

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

    private static int SPACING = 10;
    public static int Board_width = (COLS+1)*SPACING + COLS * Tile.width;
    public static int Board_height = (ROWS+1)*SPACING + ROWS *Tile.height;

}
