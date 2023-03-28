import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mazeCreate extends JPanel{
    /*public int row;
    public int col;*/
    public int maze[][];
    public int pathMaze[][];
    public ArrayList<Integer> path=new ArrayList<Integer>();
    private final List<Integer> path_two = new ArrayList<Integer>();
    public int count=200;
    public Timer tm;
    public int i=0;
    public String st;
    public String st2;
    public String kisaYolSt;
    public String kisaYolSt2;

    //dış halkasını çiz
    public mazeCreate(int row,int col) {
        int [][]maze= new int [row][col];
        int [][]mazeTemp=new int[row][col];
        this.maze=maze;
        int j=0,p=2;
        for(i=0;i<this.maze.length;i++){
            for(j=0;j<maze[i].length;j++){
                if(i==0) this.maze[i][j]=1;
                if(j==0) this.maze[i][j]=1;
                if(i==maze.length-1) this.maze[i][j]=1;
                if(j==maze[i].length-1) this.maze[i][j]=1;
            }
        }
        maze[1][0]=9;
        maze[maze.length-2][maze[0].length-1]=8;

        int boyut=0;
        if(maze.length<maze[0].length) boyut=maze.length;
        else boyut=maze[0].length;

        //iç halkaları oluştur
        while(p<=boyut){
                for(i=0;i<this.maze.length;i++){
                    for(j=0;j<maze[i].length;j++){
                        if(i>=p && j>=p && i<=maze.length-(p+1) && j<=maze[i].length-(p+1)){
                        if(i==p) this.maze[i][j]=1;
                        if(j==p) this.maze[i][j]=1;
                        if(i==maze.length-(p+1)) this.maze[i][j]=1;
                        if(j==maze[i].length-(p+1)) this.maze[i][j]=1;                      
                        }    
                    }
                }
                p+=2;    
        }
        p=2;
        // halkalarda rastgele boşluklar aç
        ArrayList<Integer>boslukCount =new ArrayList<Integer>();
        while(p<=boyut/2-1){
            int count=0;
            for(i =0;i<(boyut-p-1);i++){
            Random r = new Random();
            int low = p;
            int highRow = maze.length-p;
            int highCol=maze[i].length-p;
            int resultRow = r.nextInt(highRow-low) + low;
            int resultCol = r.nextInt(highCol-low) + low;
            //4 kenardan birini seçsin
            int line=r.nextInt(4);
            if(line==0){
                if(maze[p][resultCol]==0)
                maze[p][resultCol]=0;
                else{
                    count++;
                    maze[p][resultCol]=0;
                }
            }
            if(line==1){
                if(maze[maze.length-p-1][resultCol]==0){
                    maze[maze.length-p-1][resultCol]=0;
                }else{
                    count++;
                    maze[maze.length-p-1][resultCol]=0;   
                }
            }
            if(line==2){
                if(maze[resultRow][p]==0){
                    maze[resultRow][p]=0;
                }else{
                    count++;
                    maze[resultRow][p]=0;
                }
            }
            if(line==3){
                if(maze[resultRow][maze[i].length-p-1]==0){
                    maze[resultRow][maze[i].length-p-1]=0;
                }else{
                    count++;
                    maze[resultRow][maze[i].length-p-1]=0;
                }
            }
        }
        p+=2;
        boslukCount.add(count);

    }

    Random r = new Random();
    int result = r.nextInt((maze[0].length)/2);
    maze[maze.length-2][result]=1;


    p=3;
    for(i=0;i<boslukCount.size()-1;i++){
        for(j=0;j<boslukCount.get(i)-3;j++){
            //Random r = new Random();
            int low = p;
            int highRow = maze.length-p;
            int highCol=maze[i].length-p;
            int resultRow = r.nextInt(highRow-low) + low;
            int resultCol = r.nextInt(highCol-low) + low;

            //4 kenardan birini seçsin
            int line=r.nextInt(4);
            if(line==0) maze[p][resultCol]=1;
            if(line==1) maze[maze.length-p-1][resultCol]=1;
            if(line==2) maze[resultRow][p]=1;
            if(line==3) maze[resultRow][maze[i].length-p-1]=1;

   
        }
        p+=2;
    }
    
    //şimdi robotu çağıralım
    robot rb=new robot();
    rb.p1_robot(maze,maze.length-2,maze[0].length-1,path, 0, 0,1);
    int [][] pathMaze=new int[path.size()/2][2];
    int x=0;
    int y=1;
    for(i=0;i<pathMaze.length;i++){
        pathMaze[i][0]=path.get(x);
        pathMaze[i][1]=path.get(y);
        x+=2;
        y+=2;
    }
    this.pathMaze=pathMaze;


    //en kısa yolu bulan fonksiyonu çağıralım
    for(int i=0;i<maze.length;i++){
      for( j=0;j<maze[0].length;j++){
          mazeTemp[i][j]=100;
      }
    }

    for(int i=0;i<maze.length;i++){
      for(j=0;j<maze[i].length;j++){
        if(maze[i][j]==4){
          mazeTemp[i][j]=0;
          
          if(i-1>=0 )                        mazeTemp[i-1][j]=0;
          if(i+1<mazeTemp.length  )          mazeTemp[i+1][j]=0;
          if(j-1>=0   )                      mazeTemp[i][j-1]=0;
          if(j+1<mazeTemp[i].length   )      mazeTemp[i][j+1]=0;
        }
      }
    }
    for(int i=0;i<maze.length;i++){
      for(j=0;j<maze[0].length;j++){
        if(maze[i][j]==1 ){
          mazeTemp[i][j]=100;
        }
      }
    }


    int [][] start= new int[row][col];
    int [][] finish= new int[row][col];
    aStar algorithm=new aStar();
    path_two.add(mazeTemp.length-2);
    path_two.add(mazeTemp[0].length-1);

    algorithm.algorithm(mazeTemp, mazeTemp.length-2, mazeTemp[0].length-1, 1, 0,path_two,start,finish);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    int sayac=0;
    int sayac2=0;
    for(int i=0;i<pathMaze.length-1;i++){
      sayac++;
      sayac2++;
    }
    this.st=Integer.toString(sayac+1);
    String demo=Float.toString((sayac2+1)*1/5);
    this.st2=demo+" saniye";
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int kisaYolCount=0;
    for(int i=0;i<path_two.size()/2-2;i++){
      kisaYolCount++;
    }
    this.kisaYolSt=Integer.toString(kisaYolCount);
    String demo2=Float.toString((kisaYolCount)/1/5);
    this.kisaYolSt2=demo2+" saniye";

    this.i=0;
    //timer çalıştıralım
    if(count==200){
        this.tm=new Timer(count,new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            maze[pathMaze[i][0]][pathMaze[i][1]]=7;
            if(i<pathMaze.length-1){
                repaint();
                i+=1;
            }else{
                tm.stop();
                int k=2;
                int m=3;
                for(int i=0;i<path_two.size()/2-2;i++){
                  maze[path_two.get(k)][path_two.get(m)]=5;
                  k+=2;
                  m+=2;
                }
                repaint();
            }
          }
        });
        tm.start();   
      }



    setSize(400, 400);
    setOpaque(true);
    setVisible( true );   
    setLayout(null);
}
public void paint( Graphics g )    
{ 
   for(int row=0;row<maze.length;row++){
      for(int col=0;col<maze[0].length;col++){
        Color color;
        switch (this.maze[row][col]){
          case 1: color=Color.BLACK;break;
          case 2: color=Color.BLACK;break;
          case 3: color=Color.black;break;
          case 5: color =Color.cyan;break;
          case 7: color=Color.orange; break;
          case 8: color=Color.GREEN ;break;
          case 9: color=Color.red; break;
          default: color=Color.white;
        }   
        g.setColor(color);
        g.fillRect(20*col, 20*row, 20, 20);
        g.setColor(Color.black);
        g.drawRect(20*col, 20*row, 20, 20);
      }
    }
    g.setColor(Color.MAGENTA);
    if(pathMaze.length!=0){
      g.fillOval(pathMaze[i][1]*20,pathMaze[i][0]*20, 20,20);
    }
}


}
