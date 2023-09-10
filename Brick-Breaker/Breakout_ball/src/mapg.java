
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
public class mapg {
    public int map[][];
    public int brickWidth;
    public int brickHeight;

    public mapg(int row, int col){
        map=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(i==0)
                map[i][j]=3;
                else if(i==1||i==2){
                    map[i][j]=2;
                }
                else
                map[i][j]=1;
            }
        }
        brickWidth=610/col;
        brickHeight=140/row;
    }
    public void setbrick(int value , int r, int c){
        map[r][c]=value;
    }
    public void draw(Graphics2D a){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]>0){
                    if((int)i==0){
                    if(map[i][j]==3)
                    a.setColor(Color.red);
                    if(map[i][j]==2)
                    a.setColor(Color.yellow);
                    if(map[i][j]==1)
                    a.setColor(Color.blue);
                    }
                    else if((int)i==1){
                    if(map[i][j]==2)
                    a.setColor(Color.orange);
                    if(map[i][j]==1)
                    a.setColor(Color.blue);
                    }
                    else if((int)i==2){
                    if(map[i][j]==2)
                    a.setColor(Color.yellow);
                    if(map[i][j]==1)
                    a.setColor(Color.blue);
                    }
                    else if((int)i==3)
                    a.setColor(Color.green);
                    else
                    a.setColor(Color.blue);
                    a.fillRect(j*brickWidth+90, i*brickHeight+50, brickWidth, brickHeight);
                    a.setColor(Color.darkGray);
                    a.setStroke(new BasicStroke(3));
                    a.drawRect(j*brickWidth+90, i*brickHeight+50, brickWidth, brickHeight);
                }
            }
        }
    }
    public void hitbrick(int i,int j){
        map[i][j]-=1;
        if(map[i][j]<0){
            map[i][j]=0;
        }
    }
}
