import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class gameplay extends JPanel implements ActionListener,KeyListener{
    private boolean play =false;
    private int totalbrick=81;
    private Timer timer;
    private int delay=8;
    private int ballXdir=-2;
    private int ballYdir=-4;
    private int ballpositionX=150;
    private int ballpositionY=400;
    private int playerX=320;
    private int score =0;
    private mapg map;

    public gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer=new Timer(delay, this);
        timer.start();

        map=new mapg(5, 9);
        
    }
    public void paint(Graphics a){
        //FOR BACKGROUND
        a.setColor(Color.black);
        a.fillRect(1, 1, 792, 642);
        //FOR BORDER
        a.setColor(Color.white);
        a.fillRect(0, 0, 792, 6);
        a.fillRect(0, 4, 3, 642);
        a.fillRect(784, 4, 3, 642);
        a.fillRect(3, 610, 786, 3);
        //PADDLE
        a.setColor(Color.cyan);
        a.fillRect(playerX, 580, 120,8);
        //BRICKS
        map.draw((Graphics2D)a);
        //BALL
        a.setColor(Color.white);
        a.fillOval(ballpositionX, ballpositionY, 13, 13);
        //SCORE
        a.setColor(Color.green);
        a.setFont(new Font("serif",Font.BOLD,20));
        a.drawString("SCORE : "+score, 650, 30);

       
         if(play==false&&ballpositionY<620&&totalbrick>0){
           a.setColor(Color.yellow);
           a.setFont(new Font("Times new roman",Font.BOLD,30));
            a.drawString("PRESS ARROW KEY TO START THE GAME ", 80, 500);
        }

       //GAMEOVER
        if(ballpositionY>=620){
            // if(lives!=0){
            //     ballpositionX=120;
            //     ballpositionY=350;
            // }
            play=false;
            ballXdir=0;
            ballYdir=0;
            a.setColor(Color.white);
            a.setFont(new Font("Times new roman",Font.ITALIC,45));
            a.drawString("GAMEOVER!! ", 250, 330);
            a.drawString("Better luck next time ", 200, 390);
            a.setColor(Color.cyan);
            a.setFont(new Font("Times new roman",Font.ITALIC,45));
            a.drawString("Your Score is  "+score, 220, 470);
            a.setColor(Color.red);
            a.setFont(new Font("Times new roman",Font.BOLD,40));
            a.drawString("PRESS:ENTER TO RESTART", 150, 580);
        }
        if(totalbrick<=0){
            play=false;
            ballXdir=0;
            ballYdir=0;
            a.setColor(Color.white);
            a.setFont(new Font("Times new roman",Font.ITALIC,60));
            a.drawString("YOU WON!! ", 210, 350);
           // a.drawString("Better luck next time ", 150, 340);
           a.setColor(Color.cyan);
            a.setFont(new Font("Times new roman",Font.ITALIC,40));
            a.drawString("Your Score is  " +score, 260, 410);
            a.setColor(Color.red);
            a.setFont(new Font("Times new roman",Font.BOLD,40));
            a.drawString("PRESS:ENTER to restart", 180, 550);
        }
        }
    
    private void moveLeft(){
        play=true;
        playerX=playerX-20;
    }
    private void moveRight(){
        play=true;
        playerX=playerX+20;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerX<=0){
                playerX=0;
            }
            else{
            moveLeft();
        }
    }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerX>=700){
                playerX=700;
            }
            else{
            moveRight();
        }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                score=0;
                totalbrick=81;
                ballpositionX=150;
                ballpositionY=400;
                ballXdir=-2;
                ballYdir=-4;
                playerX=320;
                map=new mapg(5, 9);
            }
        }
        repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(play){
            if(ballpositionX<=0){
                ballXdir=-ballXdir;
            }
            if(ballpositionX>=772){
                ballXdir=-ballXdir;
            }
            if(ballpositionY<=0){
                ballYdir=-ballYdir;
            }
            Rectangle ballrec=new Rectangle(ballpositionX,ballpositionY,13,13);
            Rectangle paddlerec=new Rectangle(playerX,580,120,8);
            if(ballrec.intersects(paddlerec)) {
                ballYdir= -ballYdir;
            }  

           A: for(int i=0;i<map.map.length;i++){// TWO MAP BECAUSE ONE IS VARIABLE THIS CLASS VARIABLE ANOTHER ONE MAPG CLASS MAP VARIABLE.MEANS MAP IS VARIABLE OF CLASS MAPG AND IN THIS CLASS WE HAVE MADE MAP AS AN OBJECT OF CLASS MAPG SO BASIC OOPS cONCEPT TO CALL THe MEMEBER IN THE CLASS FROm MAIN
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int width=map.brickWidth;
                        int height=map.brickHeight;
                        int brickXpos=90+j*width;
                        int brickypos=50+i*height;

                        Rectangle brickRect=new Rectangle(brickXpos,brickypos,width,height);
                        if(ballrec.intersects(brickRect)){
                            map.hitbrick(i, j);
                            totalbrick--;
                            if(map.map[i][j]==0&&(int)i==0)
                            score=score+20;
                            else if(map.map[i][j]==0&&((int)i==1||(int)i==2))
                            score=score+10;
                            else if(map.map[i][j]==0&&((int)i==3||(int)i==4))
                            score=score+5;
                            if(ballpositionX+11<=brickXpos||ballpositionX+1>=brickXpos+width){
                                ballXdir=-ballXdir;
                            }
                            else{
                                ballYdir=-ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }


            ballpositionX+=ballXdir;
            ballpositionY+=ballYdir;
           
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
}
