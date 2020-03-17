import java.util.Scanner;
public class Player {
    private Card[] hand;
    private final int MAX_SIZE = 5;
    private int currentSize;
    private String name;
    public Player(String n, int s) {
        name = n;
        hand = new Card[s];
        currentSize = 0;
    }
    public void setCard(Card c){
        if(currentSize<MAX_SIZE) {
            for (int i = 0; i < MAX_SIZE; i++) {
                if (hand[i] == null) {
                    hand[i] = c;
                    currentSize++;
                    break;
                }
            }
        }
    }
    public Card[] getHand(){
        return hand;
    }
    public Card discard(int i) {
        currentSize--;
        Card temp = hand[i];
        hand[i] = null;
        return temp;
    }
    public Card[] discard(){
        currentSize = 0;
        Card[] temp = new Card[hand.length];
        for(int i = 0; i<hand.length ; i++){
            temp[i] = hand[i];
            hand[i] = null;
        }
        return temp;
    }
    public  void sortCards(){
        Card max;
        for(int i = 0 ; i<hand.length ; i++){
            max = new Card("spades", "one", 1);
            for(int a = hand.length-1-i ; a>=0 ; a--){
                if(hand[a] !=null && hand[a].getFaceValue()>max.getFaceValue()){
                    max = hand[a];
                    hand[a] = hand[hand.length-1-i];
                    hand[hand.length-1-i] = max;
                }
                else if(hand[a] == null){
                    fixCards();
                }
            }
        }
    }
    public  void fixCards(){
        for(int i = 0; i<hand.length ; i++){
            if(hand[i] == null){
                for(int a = i ; a<hand.length-1 ; a++){
                    hand[a] = hand[a+1];
                }
                hand[hand.length-1] = null;
            }
        }
    }
    public String showHand(){
        String ret = "";
        for(int i = 0 ; i<currentSize ; i++){
            if(hand[i] != null) {
                ret += hand[i].toString() + " \n";
            }
        }
        return ret;
    }
    public static void main(String[] args){
        Scanner reader = new Scanner(System.in);
        /**
        Deck d = new Deck();
        d.shuffle();
        Player p1 = new Player("Nalin", 5);
        Player p2 = new Player("Justin", 5);
        for(int i = 0 ; i<5 ; i++){
            p1.setCard(d.deal());
            p2.setCard(d.deal());
        }
        System.out.println(p1.showHand());
        System.out.println(p2.showHand() + "\n");

        System.out.println("P1: How many cards would you like to trade? (0-5)");
        int discardAmt = reader.nextInt();
        reader.nextLine(); //to clean up new line character not picked up by nextInt()


        Card[] discardPile= new Card[52]; //Create discard pile so that we have a place to put discarded cards
        int sizeOfDiscard=0; //starts at 0

        Deck myDeck = new Deck();
        System.out.println(myDeck);

        myDeck.shuffle();
        Player p1 = new Player("fred", 5); //sets player name and max number cards
        Player p2 = new Player("wilma", 5);

        //Deal out 5 cards to each player
        for(int i=0; i<5; i++)
        {
            p1.setCard(myDeck.deal());
            p2.setCard(myDeck.deal());
        }
        System.out.println("player 1: " + p1.showHand() + "\nPress enter when ready"); //Show user player 1 hand
        reader.nextLine();

        System.out.println("player 2: " + p2.showHand() + "\nPress enter when ready"); //Show user player 1 hand
        reader.nextLine();

        System.out.println("Remaining Deck\n: " + myDeck.checkDeck() + "\nVerify p1 and p2 cards missing, press enter when ready");
        reader.nextLine();

        System.out.println("player 1: " + p1.showHand()); //Show user player 1 hand

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

        if(discardAmt>0)
        {
            //try{


                System.out.println("Enter card numbers, separated by space");
                int cardsToDiscardInts[] = new int[discardAmt];
                int j=0;

                while(j<discardAmt){
                    cardsToDiscardInts[j] = reader.nextInt();
                    j++;
                }
                for(int i=0; i<discardAmt; i++)
                {

                    discardPile[sizeOfDiscard]=p1.discard(cardsToDiscardInts[i]);

                    sizeOfDiscard++;
                }


                myDeck.fixCards(p1.getHand()); //Remove the nulls
                System.out.println("**Verify Player 1 Cards now ** Press enter when ready");
                reader.nextLine();
                System.out.println(p1.showHand());

                //Give p1 new cards to replace the discarded cards.
                for(int i=0; i<discardAmt; i++)
                {
                    p1.setCard(myDeck.deal());

                }
            //}
            //catch(Exception e)
            //{
              //  System.out.println("An exception occurred, make sure you are entering numbers of cards to delete");
               // return;
            //}



        }

        System.out.println("player 2: " + p2.showHand());
        System.out.println("player 1: " + p1.showHand());
        System.out.println("press enter when ready");
        reader.nextLine();

        for(int i = 0 ; i<discardPile.length ; i++){
            System.out.println(discardPile[i]);
        }
        System.out.println("*******Verify Deck is missing p1 and p2 cards plus the cards p1 discarded******");
        myDeck.checkDeck();
        System.out.println("Press enter when ready");
        reader.nextLine();


        System.out.println( myDeck.returnToDeck(p1.discard()));
        System.out.println(myDeck.returnToDeck(p2.discard()));


        System.out.println(myDeck.returnToDeck(discardPile));
        System.out.println("*******Verify all cards have been returned******");

        System.out.println(myDeck); //Verify all cards made it back in deck.
        **/
        Deck myDeck = new Deck();
        myDeck.shuffle();
        Player p1 = new Player("fred", 5); //sets player name and max number cards
        Player p2 = new Player("wilma", 5);

        //Deal out 5 cards to each player
        for(int i=0; i<5; i++)
        {
            p1.setCard(myDeck.deal());
            p2.setCard(myDeck.deal());
        }
        System.out.println(p1.showHand());

    }
}
