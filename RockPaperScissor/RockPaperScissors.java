import java.util.Random;
import java.util.Scanner;
public class RockPaperScissors {

public static void main(String[] args) {
    String rps[]={"r","p","s"};
    String computerMove=rps[new Random().nextInt(rps.length)];
    Scanner sc=new Scanner(System.in);
    while (true) { 
        System.out.println("Enter your move (r,p,s)");
        String playerMove=sc.nextLine();
         
        if(!(playerMove.equals("r")||playerMove.equals("p")||playerMove.equals("s")))
        {
            System.out.println(playerMove+"is not a valid move");
            break;
        }
        

    
    System.out.println("Computer played:"+computerMove);
    if(playerMove.equals(computerMove))
    {
        System.out.println("The game was tie");
    }
    else if(playerMove.equals("r"))
    {
        if(computerMove.equals("p"))
        {
            System.out.println("you lose as paper beats rock");
        }
        else if(computerMove.equals("s"))
         {
            System.out.println("you win as  rock beats scissor");
         }
    }
    else if(playerMove.equals("s"))
    {
        if(computerMove.equals("p"))
        {
            System.out.println("you win as scissor beats paper");
        }
        else if(computerMove.equals("r"))
         {
            System.out.println("you lose as rock beats scissor");
         }
    }
    else if(playerMove.equals("p"))
    {
        if(computerMove.equals("r"))
        {
            System.out.println("you win as paper beats rock");
        }
        else if(computerMove.equals("s"))
         {
            System.out.println("you lose as scissor beats paper");
         }
    }
    System.out.println("Play Again? (Y/N)");
    String playAgain=sc.nextLine();
    if(!playAgain.equals("y"))
    {
        break;
    }

    }

}
}



