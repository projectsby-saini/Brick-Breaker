import javax.swing.JFrame;
public class App {
    public static void main(String[] args){
        JFrame a=new JFrame();
        a.setTitle("BREAKOUT BALL GAME");
        a.setSize(800,650);
        a.setLocationRelativeTo(null);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setVisible(true);
        a.setResizable(false);

        gameplay gp=new gameplay();
        a.add(gp);
    }

}

