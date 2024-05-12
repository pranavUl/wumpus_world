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

        this.status = 0;
        this.player = new WumpusPlayer();
        this.map = new WumpusMap();
        this.player.setRowPosition(map.getLadderR());
        this.player.setColPosition(map.getLadderC());

        setSize(panelWidth, panelHeight);

        //System.out.println("Current directory: " + new File(".").getAbsolutePath()); 

        try {

            File imgFile = new File("wumpus_world\\src\\images\\Floor.gif");
            if (!imgFile.exists()) {
                System.out.println("Image file NOT FOUND at: " + imgFile.getAbsolutePath());
            }
            else if (!imgFile.canRead()) {
                System.out.println("No READ perms for file at: " + imgFile.getAbsolutePath());
            } else {
                System.out.println("Loading valid image file from: " + imgFile.getAbsolutePath());
            }

            floor = ImageIO.read((new File("wumpus_world\\src\\images\\Floor.gif")));
            arrow = ImageIO.read((new File("wumpus_world\\src\\images\\arrow.gif")));
            fog = ImageIO.read((new File("wumpus_world\\src\\images\\black.GIF")));
            gold = ImageIO.read((new File("wumpus_world\\src\\images\\gold.gif")));
            ladder = ImageIO.read((new File("wumpus_world\\src\\images\\ladder.gif")));
            pit = ImageIO.read((new File("wumpus_world\\src\\images\\pit.gif")));
            breeze = ImageIO.read((new File("wumpus_world\\src\\images\\breeze.gif")));
            wumpus = ImageIO.read((new File("wumpus_world\\src\\images\\wumpus.gif")));
            deadWumpus = ImageIO.read((new File("wumpus_world\\src\\images\\deadWumpus.GIF")));
            stench = ImageIO.read((new File("wumpus_world\\src\\images\\stench.gif")));
            playerUp = ImageIO.read((new File("wumpus_world\\src\\images\\playerUp.png")));
            playerDown = ImageIO.read((new File("wumpus_world\\src\\images\\playerDown.png")));
            playerLeft = ImageIO.read((new File("wumpus_world\\src\\images\\playerLeft.png")));
            playerRight = ImageIO.read((new File("wumpus_world\\src\\images\\playerRight.png")));
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

        for (int i = 0; i < WumpusMap.NUM_ROWS; i++) {
            for (int k = 0; k < WumpusMap.NUM_COLUMNS; k++) {
                g.drawImage(floor, 50 + i*50, 50 + k*50, null);
                /*if (!map.getSquare(i, k).isVisited()) {
                    g.drawImage(floor, 50 + i*50, 50 + k*50, null);
                    continue;
                }
                else if (map.getSquare(i, k).isVisited()) {
                    g.drawImage(fog, 50 + i*50, 50 + k*50, null);
                }*/
                if (map.getSquare(i, k).isGold()) {
                    g.drawImage(gold, 50 + i*50, 50 + k*50, null);
                }
                if (map.getSquare(i, k).isWumpus()) {
                    g.drawImage(wumpus, 50 + i*50, 50 + k*50, null);
                }
                if (map.getSquare(i, k).isLadder()) {
                    g.drawImage(ladder, 50 + i*50, 50 + k*50, null);
                }
                if (map.getSquare(i, k).isPit()) {
                    g.drawImage(pit, 50 + i*50, 50 + k*50, null);
                }
                if (map.getSquare(i, k).isDeadWumpus()) {
                    g.drawImage(deadWumpus, 50 + i*50, 50 + k*50, null);
                }
                if (map.getSquare(i, k).isBreeze()) {
                    g.drawImage(breeze, 50 + i*50, 50 + k*50, null);
                }
                if (map.getSquare(i, k).isStench()) {
                    g.drawImage(stench, 50 + i*50, 50 + k*50, null);
                }
                
            }
        }

        if (player.getDirection() == 0) {
            g.drawImage(playerUp, 50 + player.getRowPosition()*50, 50 + player.getColPosition()*50, null);
        }
        else if (player.getDirection() == 1) {
            g.drawImage(playerRight, 50 + player.getRowPosition()*50, 50 + player.getColPosition()*50, null);
        }
        else if (player.getDirection() == 2) {
            g.drawImage(playerDown, 50 + player.getRowPosition()*50, 50 + player.getColPosition()*50, null);
        }
        else if (player.getDirection() == 3) {
            g.drawImage(playerLeft, 50 + player.getRowPosition()*50, 50 + player.getColPosition()*50, null);
        }

        //g.drawImage(ladder, 50, 50, null);
        
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
        this.map.createMap();
        this.player.setRowPosition(map.getLadderR());
        this.player.setColPosition(map.getLadderC());

    }


    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
}
