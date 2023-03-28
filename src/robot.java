import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class robot {
    public int dongu=0;
    public ArrayList<Integer> path = new ArrayList<Integer >();

    robot(){

    }
    public void p1_robot(int [][]maze,int i,int j,ArrayList<Integer>path_two,int endX,int endY,int count){
        ArrayList<ArrayList<Integer> > chooseList = new ArrayList<ArrayList<Integer> >(4);
        ArrayList<ArrayList<Integer> > mainList = new ArrayList<ArrayList<Integer> >();

        int number=0;

        int tempEndx=endX;
        int tempEndy=endY;

        for(int x=0;x<4;x++){
            chooseList.add(new ArrayList<Integer>());
        }

        int index=0;
        if(i>=0 && i<maze.length  && j-1>=0 && j-1<maze[i].length ){
            if(maze[i][j-1]==4){

            }else{
                if(maze[i][j-1]==8 ||maze[i][j-1]==1 || maze[i][j-1]==2 || maze[i][j-1]==3){

                }else{
                    chooseList.get(index).add(i);
                    chooseList.get(index).add(j-1);
                    index++;             
                }               
            }
        }
        if(i>=0 && i<maze.length  && j+1>=0 && j+1<maze[i].length ){
            if(maze[i][j+1]==4){

            }else{
                if(maze[i][j+1]==8 || maze[i][j+1]==1 || maze[i][j+1]==2 || maze[i][j+1]==3){

                }else{
                    chooseList.get(index).add(i);
                    chooseList.get(index).add(j+1);
                    index++;                  
                }                 
            }
        }
        if(i-1>=0 && i-1<maze.length  && j>=0 && j<maze[i].length  ){
            if(maze[i-1][j]==4){

            }else{
                if(maze[i-1][j]==8 || maze[i-1][j]==1 || maze[i-1][j]==2 || maze[i-1][j]==3 ){

                }else{
                    chooseList.get(index).add(i-1);
                    chooseList.get(index).add(j);
                    index++;
                }               
            }
        }
        if(i+1>=0 && i+1<maze.length  &&j>=0 && j<maze[i].length  ){
            if(maze[i+1][j]==4){

            }else{
                if(maze[i+1][j]==8 || maze[i+1][j]==1 || maze[i+1][j]==2 || maze[i+1][j]==3 ){

                }else{
                    chooseList.get(index).add(i+1);
                    chooseList.get(index).add(j);
                    index++;
                }               
            }
        }

        //gidebileceği yönlerde boyut sıfır ise ayıkla
        int size=0;
        for(int x=0;x<4;x++){
            if(chooseList.get(x).size()!=0){
                  size++;
            }
        }

        for(int x=0;x<size;x++){
            mainList.add(new ArrayList<Integer>());
        }

        for(int x=0;x<4;x++){
            if(chooseList.get(x).size()!=0){
                mainList.get(number).add(chooseList.get(x).get(0));
                mainList.get(number).add(chooseList.get(x).get(1));
                number++;
            }
        }
        //eğer bu yönlerden biri çıkışsa programı bitir
        int sayac=0;
        for(int x=0;x<mainList.size();x++){
            if(maze[mainList.get(x).get(0)][mainList.get(x).get(1)]==9){
                i=mainList.get(x).get(0);
                j=mainList.get(x).get(1);
                sayac++;
                System.out.println("cikis");
            }
        }
        //eğer mainList.size()==0 ise bir önceki noktaya gönder
        boolean check=false;
        if(sayac==0 && mainList.size()==0){
            if(path.size()!=0){
            path.remove(path.size()-1);
            path.remove(path.size()-1);
            check=true;               
            }
            i=path.get(path.size()-2);
            j=path.get(path.size()-1);
        }

        Random rastgele = new Random();
        int r = rastgele.nextInt(size+1);
        
        if(count==0){
            if(sayac==0 && mainList.size()!=0){
            do{
                //Random rastgele = new Random();
                r = rastgele.nextInt(size);            
            } while(maze[mainList.get(r).get(0)][mainList.get(r).get(1)]==1||
            maze[mainList.get(r).get(0)][mainList.get(r).get(1)]==2||
            maze[mainList.get(r).get(0)][mainList.get(r).get(1)]==3
            );
            i=mainList.get(r).get(0);
            j=mainList.get(r).get(1);
            }
        }
        if(count==1){
            if(sayac==0 && mainList.size()!=0){
                do{
                    //Random rastgele = new Random();
                    r = rastgele.nextInt(size);            
                } while(maze[mainList.get(r).get(0)][mainList.get(r).get(1)]==1
                );
                i=mainList.get(r).get(0);
                j=mainList.get(r).get(1);
                }
        }
        
        endX=i;
        endY=j;
        this.dongu++;
        if(maze[i][j]!=9){
            maze[i][j]=4;
            if(check==false){
  
            path.add(i);
            path.add(j);
            }
            path_two.add(i);
            path_two.add(j); 
            p1_robot(maze,i, j, path_two, endX, endY,count);
        }else{
            System.out.println("bitti");
            System.out.println("robotun yolu:"+path_two);
        }
  
    }

}
