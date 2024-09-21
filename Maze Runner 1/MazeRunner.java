import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Maze
{
    char maze[][];
    public Maze(int row,int column)
    {
        this.maze=new char[row][column];
        initializeMaze();

    }
   public void initializeMaze()
    {
        int i,j;
        for(i=0;i<maze.length;i++)
         {
           for(j=0;j<maze[0].length;j++)
            {
               maze[i][j]='0';
            }
         }
    }
    public void printMaze()
    {
       for(char row[]:maze)
       {
        for(char col:row)
        {
            System.out.print(col+" ");
        }
        System.out.println("");
       }
    }
    public boolean  putTreasure(int r,int c)
    {
     if(r<0||r>=maze.length||c<0||c>=maze[0].length)
     {
        return false;
     }
     maze[r][c]='T';
     return true;

    }
    
    private static class Node
    {
        int row,col,steps=0;
        Node(int row,int col,int steps)
        {
            this.row=row;
            this.col=col;
            this.steps=steps;
        }
    }
public  int shortestPath(int row,int col)
{
    row--;
    col--;
    if(row<0||row>=maze.length||col<0||col>=maze[0].length)
     {
        return -1;
     }

     if(maze[row][col]=='T')
       return 0;

    maze[row][col]='A';

    int rowLength=maze.length;
    int colLength=maze[0].length;
    
    boolean visited[][]=new boolean[rowLength][colLength];
    int directions[][]={{-1,0},{0,-1},{1,0},{0,1}};

    Queue<Node> q=new LinkedList<>();
    q.add(new Node(row,col,0));
    visited[row][col]=true;
    
    while(!q.isEmpty())
    {
      Node current=q.poll();
      for(int[] direction:directions)
      {
    
            int nextRow=current.row+direction[0];
            int nextCol=current.col+direction[1];
            if(nextRow>=0&&nextRow<rowLength&&nextCol<colLength&&nextCol>=0&&visited[nextRow][nextCol]!=true)
            {
                if(maze[nextRow][nextCol]=='T')
                  {
                    return current.steps+1;
                  }
                Node nextNode=new Node(nextRow,nextCol,current.steps+1);
                q.add(nextNode);
                visited[nextRow][nextCol]=true;
             }

       }

    }
    return -1;
}
}
public class MazeRunner{
public static void main(String[] args)
{
   
    Scanner sc=new Scanner(System.in);
        System.out.println("Enter no. of maze row:");
        int row=sc.nextInt();
        System.out.println("Enter no. of maze Columns:");
        int col=sc.nextInt();
        Maze maze=new Maze(row, col);

        System.out.println("Enter row for treasure:");
        int r=sc.nextInt();
        r--;
        System.out.println("Enter Column for treasure:");
        int c=sc.nextInt();
        c--;
   
        maze.putTreasure(r, c);
        maze.printMaze();
        System.out.println("Enter row for adventurer:");
        int arow=sc.nextInt();
       
        System.out.println("Enter Column for adventurer:");
        int acol=sc.nextInt();

       int result=maze.shortestPath(arow,acol);
       System.out.println("Minimum no. of steps:"+result);

}
}