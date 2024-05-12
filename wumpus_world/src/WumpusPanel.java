import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.*;

public class WumpusPanel extends JPanel implements KeyListener {
    
    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int WON = 2;

    private int status;
    private WumpusPlayer player;
    private WumpusMap map;

    private BufferedImage floor;
    private BufferedImage arrow;
    private BufferedImage fog;
    private BufferedImage gold;
    private BufferedImage ladder;
    private BufferedImage pit;
    private BufferedImage breeze;
    private BufferedImage wumpus;
    private BufferedImage deadWumpus;
    private BufferedImage stench;
    private BufferedImage playerUp;
    private BufferedImage playerDown;
    private BufferedImage playerLeft;
    private BufferedImage playerRight;

    private BufferedImage buffer;

    public WumpusPanel(int panelWidth, int panelHeight) {
        super();

        setSize(panelWidth, panelHeight);

        System.out.println("Current directory: " + new File(".").getAbsolutePath()); 

        try {

            File imgFile = new File("images\\Floor.gif");
            if (!imgFile.exists()) {
                System.out.println("Image file NOT FOUND at: " + imgFile.getAbsolutePath());
            }
            else if (!imgFile.canRead()) {
                System.out.println("No READ perms for file at: " + imgFile.getAbsolutePath());
            } else {
                System.out.println("Loading valid image file from: " + imgFile.getAbsolutePath());
            }

            floor = ImageIO.read((new File("images\\Floor.gif")));
            ladder = ImageIO.read((new File("images\\ladder.gif")));
            System.out.println("Images loaded successfully");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        addKeyListener(this);
    }

    public void paint (Graphics g) {

        //Paints the game world to the screen, with the appropriate messages.

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.drawImage(floor, 50, 50, null);
        g.drawImage(ladder, 50, 50, null);
        
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

        //HANDLES ALL PLAYER INPUTS

        /*char dir = e.getKeyChar();

        if (dir == 'w') {
            y-=2;
        }
        else if (dir == 's') {
            y+=2;
        }
        else if (dir == 'a') {
            x-=2;
        }
        else if (dir == 'd') {
            x+=2;
        }*/

        repaint();
    }

    public void reset() {
        this.status = 0;
        map = new WumpusMap();
        this.player.setRowPosition(map.getLadderR());
        this.player.setColPosition(map.getLadderC());
    }


    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}
