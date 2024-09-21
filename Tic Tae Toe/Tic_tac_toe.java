
import java.util.Scanner;

public class Tic_tac_toe
{
    static char[] board={'0','1','2','3','4','5','6','7','8','9'};
    static void designBoard()
    {
        System.out.println("\n--->TIC TAC TOE GAME<<--\n");
        System.out.println("\t____|____|____|");
        System.out.println("\t    |    |    ");
        System.out.printf("\t  %c |  %c |  %c  \n",board[1],board[2],board[3]);
        System.out.println("\t____|____|____|");
        System.out.println("\t    |    |    ");
        System.out.printf( "\t  %c |  %c |  %c  \n",board[4],board[5],board[6]);
        System.out.println("\t____|____|____|");

        System.out.println("\t    |    |    ");
        System.out.printf("\t  %c |  %c |  %c  \n",board[7],board[8],board[9]);
        System.out.println("\t____|____|____|");
    
    } 
    static int checkWin()
    {
        if(board[1]==board[2]&&board[2]==board[3])
        {
            return 1;
        }
        else if(board[4]==board[5]&&board[5]==board[6])
        {
            return 1;
        }
        else if(board[7]==board[8]&&board[8]==board[9])
        {
            return 1;
        }
        else if(board[1]==board[4]&&board[4]==board[7])
        {
            return 1;
        }
        else if(board[2]==board[5]&&board[5]==board[8])
        {
            return 1;
        }
        else if(board[3]==board[6]&&board[6]==board[9])
        {
            return 1;
        }
        else if(board[1]==board[5]&&board[5]==board[9])
        {
            return 1;
        }
        else if(board[3]==board[5]&&board[5]==board[7])
        {
            return 1;
        }
        for(int i=1;i<=9;i++)
        {
            if(board[i]!='X'&&board[i]!='O')
            {
                return -1;
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println("\n TIC TAC TOE");
    
        int input;
        int player=1;
        Scanner sc=new Scanner(System.in);
        while (true) { 
            player=(player%2==0)?2:1;
            designBoard();
            System.out.printf("\nEnter no. for player %d:",player);
        
        if(!sc.hasNext())
{
    System.out.println("Invalid input.Ennter a number.");
    continue;
}
        input=sc.nextInt();
        if(input<1||input>9||(board[input]=='X'||board[input]=='O'))
        {
            System.out.println("Invalid input.Ennter a valid position");
           continue;
        }
        char mark=(player==1)?'X':'O';
        board[input]=mark;
        int result=checkWin();
        if(result==1)
        {
            designBoard();
            System.out.printf("\t\n Player %d Wins \n",player);
            break;
        }
        else if(result==0){
            designBoard();
            System.out.println("Match draw !!!");
            break;
        }
        player++;
    }  
 
}
}