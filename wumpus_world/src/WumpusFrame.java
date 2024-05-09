import javax.swing.JFrame;

public class WumpusFrame extends JFrame {
    
    public WumpusFrame() {
        super();
        this.setTitle("Wumpus World");
        this.setSize(600, 600);
        WumpusPanel panel = new WumpusPanel(500, 500);
        this.getContentPane().add(panel);
    }

}
