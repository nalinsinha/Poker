import java.util.Random;
public class Deck {
    private Card[] cards;
    public static final int MAX_SIZE = 52;
    private int currentSize;
    public Deck() {
        String[] suits = {"Spades", "Clubs", "Hearts", "Diamonds"};
        String[] name = {"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queeen", "king", "ace"};
        cards = new Card[52];
        int count = 0;
        for(int i = 0; i<=3 ; i++){
            for(int a = 2 ; a<=14 ; a++){
                cards[count] = new Card(suits[i], name[a-2], a);
                count++;
            }

        }
        currentSize = 52;
    }
    public Card deal(){
        Card ret = cards[currentSize-1];
        cards[currentSize-1] = null;
        currentSize--;
        return ret;
    }
    public boolean returnToDeck(Card c){

        if(currentSize<52) {
            cards[currentSize] = c;
            currentSize++;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean returnToDeck(Card[] c){

        for(int i  = 0; i<c.length ; i++) {
            if(currentSize == 52){
                return false;
            }
            cards[currentSize] = c[i];
            currentSize++;
        }
        return true;

    }
    public void shuffle(){
        Random generator = new Random();
        for (int i = 0; i < currentSize; i++) {
            int randomValue = i + generator.nextInt(cards.length - i);
            // Swap the random element with the present element.
            Card randomElement = cards[randomValue];
            cards[randomValue] = cards[i];
            cards[i] = randomElement;
        }
    }


    public String toString(){
        String retVal = "Size: " + currentSize + " ";
        for (int i = 0; i < currentSize; i++) {
            if(cards[i] != null) {
                //retVal+= cards[i].toString() +" ";
                retVal += cards[i].getName() + " ";
            }
        }
        return retVal;
    }
    public boolean checkDeck()
    {
        int spadesCnt=0, diamondsCnt=0, clubsCnt=0, heartsCnt=0;
        int totalValue=0;
        Card[] spades = new Card[20];
        Card[] diamonds = new Card[20];
        Card[] hearts = new Card[20];
        Card[] clubs = new Card[20];
        System.out.println("*******Checking Deck **********/");
        for(int i=0; i<MAX_SIZE; i++)
        {
            if(cards[i]!=null && cards[i].getSuit().equals("Clubs"))
            {

                clubs[clubsCnt]=cards[i];
                clubsCnt++;
            }
            else if(cards[i]!=null && cards[i].getSuit().equals("Diamonds"))
            {
                diamonds[diamondsCnt]=cards[i];
                diamondsCnt++;
            }

            else if(cards[i]!=null && cards[i].getSuit().equals("Hearts"))
            {
                hearts[heartsCnt]=cards[i];
                heartsCnt++;
            }

            else if(cards[i]!=null && cards[i].getSuit().equals("Spades"))
            {
                spades[spadesCnt]=cards[i];
                spadesCnt++;
            }
            if(cards[i]!=null )
                totalValue+=cards[i].getFaceValue();

        }
        for(int i=0; i<clubsCnt; i++)
            if(clubs[i]!=null)
                System.out.println(clubs[i]);
        System.out.println();
        for(int i=0; i<diamondsCnt; i++)
            if(diamonds[i]!=null)
                System.out.println(diamonds[i]);
        System.out.println();
        for(int i=0; i<spadesCnt; i++)
            if(spades[i]!=null)
                System.out.println(spades[i]);
        System.out.println();
        for(int i=0; i<heartsCnt; i++)
            if(hearts[i]!=null)
                System.out.println(hearts[i]);



        System.out.println("Clubs: " + clubsCnt + " Spades: " + spadesCnt + " Diamonds: " + diamondsCnt + " Hearts: " + heartsCnt);
        System.out.println("Total: " + totalValue);

        if(clubsCnt==13 && spadesCnt == 13 && diamondsCnt==13 && heartsCnt==13 && totalValue==416)
            return true;
        return false;

    }
    public static void main(String[] args){
        Deck d = new Deck();
        System.out.println(d);
        d.shuffle();
        System.out.println(d);
        System.out.println();

        Card[] hand1 = new Card[7];
        Card[] hand2 = new Card[7];

        for(int i = 0; i<7 ; i++){
            hand1[i] = d.deal();
            hand2[i] = d.deal();
            System.out.print(hand1[i].getName() + " " + hand2[i].getName() + " ");
        }
        System.out.println();
        System.out.println(d);
        System.out.println();

        for(int i=0; i<hand1.length; i++)
        {
            if(hand1[i]!=null) //Ensure we do not try to access null reference
                System.out.print(" " + hand1[i]);
        }
        hand1[1] = null;
        hand1[3] = null;
        //fixCards(hand1);
        System.out.println("fixed");
        for(int i=0; i<hand1.length; i++)
        {
            if(hand1[i]!=null) //Ensure we do not try to access null reference
                System.out.print(" " + hand1[i]);
        }
        System.out.println();


        for(int i = 0; i<7 ; i++){
            d.returnToDeck(hand1[i]);
        }
        System.out.println(d);
        System.out.println();

        d.returnToDeck(hand2);
        System.out.println(d);








    }

}
