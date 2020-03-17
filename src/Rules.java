public class Rules {

    public static int[] scoreCards(Card[] hand){

        int[] score = new int[7];
        //Each suit can be defined by 4 characteristics: suit, if consecutive, highest card, and the number of repeated cards
        Card[] temp = new Card[5];
        for(int z = 0; z<hand.length ; z++){
            temp[z] = hand[z];
        }
        boolean suit = false, consecutive = false;
        String s = temp[0].getSuit();
        int suitCount = 0, numCount = 0, num = temp[0].getFaceValue();
        for(Card a: temp){
            if(a.getSuit().equals(s)){
                suitCount++;
            }
            if(a.getFaceValue() == num){
                numCount++;
            }
            num++;
        }
        if(suitCount == 5){
            suit = true;
        }
        if(numCount == 5){
            consecutive = true;
        }
        //numSame is an array where the 3rd index is the number of four of a kind in an array of cards, the 2nd is the number of three of a kind etc.
        boolean first = true;
        int[] numSame = new int[6];
        for(int i = 3 ; i>=1 ; i--){
            for(int a = temp.length-1; a>=0; a--){
                if( a-i>=0 && temp[a] != null && temp[a-i] !=null && temp[a].getFaceValue() == temp[a-i].getFaceValue()){
                    numSame[i]++;
                    if(first){
                        first = false;
                        numSame[5] = temp[a].getFaceValue();
                    }
                    else{
                        score[2] = temp[a].getFaceValue();
                    }
                    for(int b = a-i ; b<=a; b++){
                        temp[b] = null;
                    }
                }
            }
        }

        int high = hand[hand.length-1].getFaceValue();

        //Once I have found all of the above characteristics I can check for the different hands
        if(suit){
            if(consecutive){
                if(high ==14){
                    score[0] = 10;
                    score[1] = high;
                    //System.out.println("Royal Flush");
                }
                else {
                    score[0] = 9;
                    score[1] = high;
                    //System.out.println("Straight Flush");
                }
            }
            else {
                score[0] = 6;
                score[1] = high;
                //System.out.println("Flush");
            }
        }else if(consecutive){
            score[0] = 5;
            score[1] = high;
            //System.out.println("Straight");
        }else if(numSame[3] == 1){
            score[0] = 8;
            score[1] = high;
            //System.out.println("Four of a Kind");
        }else if(numSame[2] == 1 && numSame[1] == 1){
            score[0] = 7;
            score[1] = numSame[5];
            //System.out.println("Full House");
        }else if(numSame[2] == 1){
            score[0] = 4;
            score[1] = numSame[5];
            //System.out.println("Three of a Kind");
        }else if(numSame[1] ==2){
            score[0] = 3;
            score[1] = numSame[5];
            //System.out.println("Two Pair");
        }else if(numSame[1] ==1){
            score[0] = 2;
            score[1] = numSame[5];
            //System.out.println("One Pair");
        }else {
            score[0] = 1;
            score[1] = hand[4].getFaceValue();
            temp[temp.length-1] = null;
            //System.out.println("High Card");
        }
        boolean f = true;
        int a  = temp.length-1;
        for(int i = 2; i<score.length ; i++){
            if(f && score[i] == 0){
                score[i] = -1;
                f = false;
            }
            else {
                for (; a >= 0; a--) {
                    if (temp[a] != null) {
                        score[i] = temp[a].getFaceValue();
                        break;
                    }
                }
                a--;
            }
        }
        return score;


    }
    public static void main(String args[]){
        Card[] hand = {new Card("Spades", "nine", 3), new Card("Spades", "jack", 5), new Card("Spades", "queen", 7), new Card("h", "king", 11), new Card("Spades", "ace", 12)};
        int[] temp = scoreCards(hand);
        for(int i = 0 ; i<temp.length ; i++){
            System.out.print(temp[i] + "\t");
        }
    }
}
