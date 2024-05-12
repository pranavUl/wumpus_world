import javax.swing.JFrame;

public class WumpusFrame extends JFrame {
    
    public WumpusFrame() {
        super();
        this.setTitle("Wumpus World");
        this.setSize(625, 650);
        WumpusPanel panel = new WumpusPanel(700, 700);
        panel.setLocation(50, 50);
        this.getContentPane().add(panel);
    }

}
