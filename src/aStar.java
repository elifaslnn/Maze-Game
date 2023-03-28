import java.lang.constant.DynamicConstantDesc;
import java.util.ArrayList;
import java.util.List;

public class aStar {
    public int length;
    int count=1;
    int l=0;
    public void algorithm(int [][] maze,int startI,int startJ,int finishI,int finishJ,List<Integer> path_two, int [][]start,int [][] finish){

        start[startI][startJ]=1;
        finish[finishI][finishJ]=1;

        //start noktası için
        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[i].length;j++){
                if(j-1>=0 && start[i][j-1]==count && maze[i][j]!=100 && start[i][j]==0){
                    start[i][j]=count+1;
                }
                if(j+1<maze[i].length && start[i][j+1]==count && maze[i][j]!=100 && start[i][j]==0){
                    start[i][j]=count+1;
                }
                if(i-1>=0 && start[i-1][j]==count && maze[i][j]!=100 && start[i][j]==0){
                    start[i][j]=count+1;
                }
                if(i+1<maze.length && start[i+1][j]==count && maze[i][j]!=100 && start[i][j]==0){
                    start[i][j]=count+1;
                }
            }
        }
        //finish noktası için
        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[i].length;j++){
                if(j-1>=0 && finish[i][j-1]==count && maze[i][j]!=100 && finish[i][j]==0){
                    finish[i][j]=count+1;
                }
                if(j+1<maze[i].length && finish[i][j+1]==count && maze[i][j]!=100 && finish[i][j]==0){
                    finish[i][j]=count+1;
                }
                if(i-1>=0 && finish[i-1][j]==count && maze[i][j]!=100 && finish[i][j]==0){
                    finish[i][j]=count+1;
                }
                if(i+1<maze.length && finish[i+1][j]==count && maze[i][j]!=100 && finish[i][j]==0){
                    finish[i][j]=count+1;
                }
            }
        }
        int num=0;
        for(int i=0;i<maze.length;i++){
            for(int j=0;j<maze[0].length;j++){
                if(start[i][j]==0 && maze[i][j]!=100 && maze[i][j]!=8){
                   num++;
                }
                if(finish[i][j]==0 && maze[i][j]!=100 && maze[i][j]!=9){
                    num++;
                }
            }
        }

        
        if(count<maze.length*2+10){
            this.count++;
            algorithm(maze, path_two.get(path_two.size()-2),path_two.get(path_two.size()-1), finishI, finishJ,path_two,start,finish);
        }else{

            //toplamlarının verdiği en küçük sayıyı bulalım
            int min=1000;
            int sum=0;
            for(int i=0;i<start.length;i++){
                for(int j=0;j<start[i].length;j++){
                    sum=start[i][j]+finish[i][j];
                    if(sum<min && start[i][j]!=0){
                        int temp=sum;
                        sum=min;
                        min=temp;
                    }
                }
            }

            int z=0;

            for(int i=0;i<start.length;i++){
                for(int j=0;j<start[i].length;j++){
                    if(start[i][j]+finish[i][j]==min && z!=1){
                        if((i+1==(path_two.get(path_two.size()-2)) && j==(path_two.get(path_two.size()-1))) ||
                        (i-1==(path_two.get(path_two.size()-2))&& j==(path_two.get(path_two.size()-1))) ||
                        (i==(path_two.get(path_two.size()-2))&& j+1==(path_two.get(path_two.size()-1)))||
                        (i==(path_two.get(path_two.size()-2)) && j-1==(path_two.get(path_two.size()-1)))  ){
                            path_two.add(i);
                            path_two.add(j);
                            z=1;
                            break;
                        }         
                    }
                       
                    }
            }

            for(int i=0;i<start.length;i++){
                for(int j=0;j<start[i].length;j++){
                    if(start[i][j]!=0){
                        start[i][j]=0;
                        finish[i][j]=0;
                    }
                }
            }

            if(path_two.get(path_two.size()-2)==finishI && path_two.get(path_two.size()-1)==finishJ){
                    System.out.println("en kisa yol indeksleri:"+path_two);
            }
            else{
                this.count=1;
                algorithm(maze, path_two.get(path_two.size()-2), path_two.get(path_two.size()-1), finishI, finishJ, path_two, start, finish);
            }

        }
    }
    
}
