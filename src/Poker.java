import java.util.Scanner;
public class Poker {
    public static void main(String args[]){
        Rules rules = new Rules();
        int ante = 2;
        Scanner reader = new Scanner (System.in);
        String instructions = "*\t At the beginning of every round an ante is collects from each player before cards are dealt.\n" +
                "*\t Each player is dealt 5 cards and they the first player can set a bet or fold and all others fold or match or raise, this continues until everyone has matched the same bet (all players start with 20 chips).\n" +
                "*\t After the round of betting players have the chance to trade in up to 5 cards for new ones trying to get one of the 10 card combinations and then another round of betting happens.\n" +
                "*\t Finally after the betting cards are showm and the hand with the highest rank is awarded the money in the kitty.";
        Kitty kitty = new Kitty();
        Deck d = new Deck();
        PokerPlayer p1;
        ComputerPokerPlayer cpu1;
        int bet = 0;
        int cpu1Bet;
        int first = 1;
        do{
            System.out.println("Welcome to 5 card draw poker. Here's how to play: \n" + instructions);
            System.out.println("Enter your name: ");
            String n = reader.nextLine();
            p1 = new PokerPlayer(n,5,20);
            System.out.println("Choose a number from 1-3");
            int a = reader.nextInt();
            cpu1 = new ComputerPokerPlayer("George", 5, 20, a);
            kitty.update(ante*2);
            p1.deduct(ante);
            cpu1.deduct(ante);
            System.out.println("An ante of " + ante + " coins were deducted you now have " + p1.getMoney() + " coins");
            d.shuffle();

            for(int i=0; i<5; i++)
            {
                p1.setCard(d.deal());
                cpu1.setCard(d.deal());
            }
            p1.sortCards();
            cpu1.sortCards();
            System.out.println(p1.showHand());
            rules.scoreCards(p1.getHand());
            cpu1Bet = cpu1.getStrat().chooseBet(cpu1.getMoney(),cpu1.getHand(),1);
            cpu1.deduct(cpu1Bet +1);
            kitty.update(cpu1Bet);
            if(cpu1Bet>=0) {
                System.out.println("You can fold (-1), match the bet of " + (cpu1Bet+1) + " (0), or raise by an amount(amt)");
                bet = reader.nextInt();
                p1.deduct(bet+cpu1Bet+1);
                kitty.update(bet+cpu1Bet+1);
                if(bet>=0){
                    cpu1Bet = cpu1.getStrat().match(cpu1.getMoney(),cpu1.getHand(),bet);
                    if(cpu1Bet>=0){cpu1.deduct(cpu1Bet +bet);}
                }
                else{
                    System.out.println("You folded so the computer won");
                    break;
                }
                if(cpu1Bet<0){
                    System.out.println("The computer folded you won.");
                    break;
                }
            }
            else{
                System.out.println("The computer folded you won");

                break;
            }

            Card[] discardPile= new Card[52]; //Create discard pile so that we have a place to put discarded cards
            int sizeOfDiscard=0; //starts at 0
            int discardAmt=0;
            do{
                try{

                    System.out.println("P1: How many cards would you like to trade? (0-5)");
                    discardAmt = reader.nextInt();
                    reader.nextLine(); //to clean up new line character not picked up by nextInt()
                }
                catch(Exception e)
                {
                    System.out.println("Invalid response, try again");
                    reader.nextLine();
                    discardAmt=6;
                }
            }while(discardAmt>5||discardAmt<0); //Stay in this loop if invalid discardAmt entered
            int cardsToDiscardInts[] = new int[discardAmt];
            if(discardAmt>0)
            {
                //try{


                System.out.println("Enter card numbers, separated by space");

                int j=0;

                while(j<discardAmt){
                    cardsToDiscardInts[j] = reader.nextInt();
                    j++;
                }
                System.out.println();
                System.out.println("Discarded:");
                for(int i=0; i<discardAmt; i++)
                {

                    discardPile[sizeOfDiscard]=p1.discard(cardsToDiscardInts[i]);
                    System.out.println(discardPile[sizeOfDiscard]);
                    sizeOfDiscard++;
                }
                reader.nextLine();

                p1.fixCards(); //Remove the nulls

                System.out.println();
                System.out.println("Here are your new cards");

                //Give p1 new cards to replace the discarded cards.
                for(int i=0; i<discardAmt; i++)
                {
                    p1.setCard(d.deal());

                }
                p1.sortCards();
                System.out.println(p1.showHand());
            }
            System.out.println("---------------------");
            System.out.println("CPU trade");
            System.out.println("---------------------");
            sizeOfDiscard=0; //starts at 0
            discardAmt=0;
            cardsToDiscardInts = cpu1.getStrat().chooseTrade(cpu1.getHand());
            for(int i = 0; i<cardsToDiscardInts.length; i++){
                if(cardsToDiscardInts[i]!= 0){
                    discardAmt++;
                }
            }

            for(int i=0; i<discardAmt; i++)
            {
                if(cardsToDiscardInts[i]>=1) {
                    discardPile[sizeOfDiscard] = cpu1.discard(cardsToDiscardInts[i] - 1);
                    sizeOfDiscard++;
                }
            }
            cpu1.fixCards(); //Remove the nulls


            //Give cpu new cards to replace the discarded cards.
            for(int i=0; i<discardAmt; i++)
            {
                cpu1.setCard(d.deal());

            }
            cpu1.sortCards();


            cpu1Bet = cpu1.getStrat().chooseBet(cpu1.getMoney(),cpu1.getHand(),1);
            cpu1.deduct(cpu1Bet +1);
            kitty.update(cpu1Bet);
            if(cpu1Bet>=0) {
                System.out.println("You can fold (-1), match the bet of " + (cpu1Bet+1) + " (0), or raise by an amount(amt)");
                bet = reader.nextInt();
                p1.deduct(bet+cpu1Bet+1);
                kitty.update(bet+cpu1Bet+1);
                if(bet>=0){
                    cpu1Bet = cpu1.getStrat().match(cpu1.getMoney(),cpu1.getHand(),bet);
                    if(cpu1Bet>=0){cpu1.deduct(cpu1Bet +bet);}
                }
                else{
                    System.out.println("You folded so the computer won");
                    break;
                }
                if(cpu1Bet<0){
                    System.out.println("The computer folded you won.");
                    break;
                }
            }
            else{
                System.out.println("The computer folded you won");

                break;
            }
            int[] cpuScore = rules.scoreCards(cpu1.getHand());
            int[] p1Score = rules.scoreCards(p1.getHand());
            for(int i = 0; i<p1Score.length ; i++){
                if(p1Score[i] >cpuScore[i]){

                    System.out.println("You won (" + p1Score[i] + " - " + cpuScore[i] + ")");
                    p1.increase(kitty.payout());
                    break;
                }
                else if(p1Score[i] < cpuScore[i]){
                    System.out.println("The CPU won (" + cpuScore[i] + " - " + p1Score[i] + ")");
                    cpu1.increase(kitty.payout());
                    break;
                }
            }
            break;
        }while(false);
    }

}
