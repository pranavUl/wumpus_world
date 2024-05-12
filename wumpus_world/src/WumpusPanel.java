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

        addKeyListener(this);
    }

    public void paint (Graphics g) {

        //Paints the game world to the screen, with the appropriate messages.

        try {
            floor = ImageIO.read(new File("Floor.gif"));
            System.out.println("Images loaded successfully");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        
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
