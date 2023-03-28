import java.awt.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class grid extends JPanel{
  public int [][] maze;
  public int pathX=0;
  public int pathY=0;
  public int x=0;
  public int y=0;
  public Timer tm;
  //public int delay=100;
  //public boolean changeTimer=false;
  public ArrayList<Integer> path=new ArrayList<Integer>();
  private final List<Integer> path_two = new ArrayList<Integer>();

  public int [][] pathMaze;
  public int [][] pathMaze_two;

  public int i=0;
  public int count=200;
  public String st;
  public String st2;
  public String kisaYolSt;
  public String kisaYolSt2;
  public int sayac=0;

public grid(String urlSt){
    //READ THE URL
     //String urlString="http://bilgisayar.kocaeli.edu.tr/prolab2/url1.txt";
    String urlString=urlSt;

    ArrayList<String> arr = new ArrayList<String>();    

    URL url;
    try {
      url = new URL(urlString);
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));  

      String line;
      try {
        while ((line = reader.readLine()) != null)
        {
          arr.add(line);
        }
      } catch (IOException e) {
        e.printStackTrace();
      } 

      reader.close();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    char [][] mazeCh=new char[arr.size()][arr.size()];
    int [][] mazeInt=new int[arr.size()][arr.size()];
    int [][] mazeTemp=new int[arr.size()][arr.size()];
    for(int i=0;i<arr.size();i++){
      for(int j=0;j<arr.get(i).length();j++){
        mazeCh[i][j]=arr.get(i).charAt(j);
        mazeInt[i][j]=Character.getNumericValue(mazeCh[i][j]);
      }
    }
/////////başlangıç noktasını random atama
     int max=mazeInt.length;
     int random_iBaslangic=(int)Math.floor(Math.random() * (max - + 1) );
     int random_jBaslangic=(int)Math.floor(Math.random() * (max - + 1) );
     while(mazeInt[random_iBaslangic][random_jBaslangic]==1 ||mazeInt[random_iBaslangic][random_jBaslangic]==2 ||mazeInt[random_iBaslangic][random_jBaslangic]==3){
      random_iBaslangic=(int)Math.floor(Math.random() * (max - + 1) );
      random_jBaslangic=(int)Math.floor(Math.random() * (max - + 1) );
     }

    //////////bitiş noktasını random atama
    int random_iBitis=(int)Math.floor(Math.random() * (max -  1) );
    int random_jBitis=(int)Math.floor(Math.random() * (max -  1) );
    while(mazeInt[random_iBitis][random_jBitis]==1 ||mazeInt[random_iBitis][random_jBitis]==2 ||mazeInt[random_iBitis][random_jBitis]==3){
      random_iBitis=(int)Math.floor(Math.random() * (max - + 1) );
      random_jBitis=(int)Math.floor(Math.random() * (max - + 1) );
    }

    this.pathX=random_iBaslangic;
    this.pathY=random_jBaslangic;
    //matrisi public olan maze arrayine atama
    maze=mazeInt;
    
    //başlangıç ve bitis noktalarına neğer verme
    maze[random_iBaslangic][random_jBaslangic]=8;
    maze[random_iBitis][random_jBitis]=9;

    //ROBOT HAREKETİ İÇİN FONKSİYON ÇAĞIR
    robot robot=new robot();
    robot.p1_robot(maze,random_iBaslangic,random_jBaslangic,path,0,0,0);
    int [][] pathMaze=new int[path.size()/2][2];
    int x=0;
    int y=1;
    for(int i=0;i<pathMaze.length;i++){
        pathMaze[i][0]=path.get(x);
        pathMaze[i][1]=path.get(y);
        x+=2;
        y+=2;
    }
    this.pathMaze=pathMaze;


    //EN KISA YOL İÇİN FONKSİYON ÇAĞIR
    for(int i=0;i<mazeInt.length;i++){
      for(int j=0;j<mazeInt[0].length;j++){
          mazeTemp[i][j]=100;
      }
    }

    for(int i=0;i<mazeInt.length;i++){
      for(int j=0;j<mazeInt[0].length;j++){
        if(maze[i][j]==4){
          mazeTemp[i][j]=0;
          if(i-1>=0  && mazeTemp[i-1][j]!=1  && mazeTemp[i-1][j]!=2  && mazeTemp[i-1][j]!=3 )                    mazeTemp[i-1][j]=0;
          if(i+1<mazeTemp.length  && mazeTemp[i+1][j]!=1  && mazeTemp[i+1][j]!=2  && mazeTemp[i+1][j]!=3)        mazeTemp[i+1][j]=0;
          if(j-1>=0   && mazeTemp[i][j-1]-1!=1  && mazeTemp[i][j-1]!=2  && mazeTemp[i][j-1]!=3)                mazeTemp[i][j-1]=0;
          if(j+1<mazeTemp[i].length   && mazeTemp[i][j+1]!=1  && mazeTemp[i][j+1]!=2  && mazeTemp[i][j+1]!=3)   mazeTemp[i][j+1]=0;
        }
      }
    }
    for(int i=0;i<mazeInt.length;i++){
      for(int j=0;j<mazeInt[0].length;j++){
        if(maze[i][j]==1 || maze[i][j]==2 || maze[i][j]==3){
          mazeTemp[i][j]=100;
        }
      }
    }


    int [][] start= new int[mazeTemp.length][mazeTemp[i].length];
    int [][] finish= new int[mazeTemp.length][mazeTemp[i].length];
    aStar algorithm=new aStar();
    path_two.add(random_iBaslangic);
    path_two.add(random_jBaslangic);

    algorithm.algorithm(mazeTemp, random_iBaslangic, random_jBaslangic, random_iBitis, random_jBitis,path_two,start,finish);
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    //int sayac=0;
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

      ////////////////////////

      setSize(400, 400);
      setOpaque(true);
      setVisible( true );   
      setLayout(null);
  }

  //paint the grid function
    public void paint( Graphics g )    
    { 
       for(int row=0;row<maze.length;row++){
          for(int col=0;col<maze[0].length;col++){
            Color color;
            switch (this.maze[row][col]){
              case 1: color=Color.BLACK;break;
              case 2: color=Color.BLACK;break;
              case 3: color=Color.black;break;
              //case 4: color=Color.gray;break;
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