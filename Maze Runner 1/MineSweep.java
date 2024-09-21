import java.util.Scanner;
import java.util.Random;
class mine
{
    private final int size;
    private final int mineCount;
    private final char[][] board;
    private final char[][] field;
    

    private int flagCount=0;
    public mine(int size,int mineCount)
    {
        this.mineCount=mineCount;
        this.size=size;
        this.board=new board[size][size];
        this.field=new field[size][size];
        fillArray(board,'?');
        fillArray(board,' ');
        setUpField();
    }
    private void fillArray(char[][] matrix,char fill)
    {
        for (int i = 0; i < size; i++)
         {
            for (int j = 0; j < size; i++)
            {
             matrix[i][j]=fill
               
            }
            
        }
    }
    private void setUpField()
    {
        Random rand=new Random();
        
        count=0;
        while(count<mineCount)
        {
            int row=rand.nextInt(size);
            int col=rand.nextInt(size);
            if(field[row][col]==' ')
            {
                field[row][col]='M';
                fillAdjacencyMatrix(row,col);
                count++;
            }
        }
    }
    private void fillAdjacencyMatrix(int row,int col)
    {
      for(i=Math.max(0,row-1);i<Math.min(row+1,size-1);i++)
      {
        for(j=Math.max(0,col-1);i<Math.min(col+1,size-1);j++)
      {
           field[i][j]= field[i][j]==' '?'1':(char)field[i][j]+1;
           

      }




    }


    
}







public class MineSweep {
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter n for n*n minesweeper game:");
        int n =sc.nextInt();

      



    
    }
}
