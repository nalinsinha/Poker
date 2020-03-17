public class Strategy {
    //boldness is on a scale of 1 to 3 todetermine how often the player bets a lot
    private int boldness;
    private Rules rule;
    public Strategy(int b) {
        boldness = b;
    }
    public int chooseBet(int balance, Card[] hand, int bet){
        int[] score = rule.scoreCards(hand);
        int retval = 0;
        switch (boldness){
            case 1:
                if(score[0] ==1){
                    retval = -1;
                }else if(score[0] <=3 && bet<balance/2){
                    retval = 1;
                }else if(score[0] == 3){
                    retval = 0;
                }else if(score[0]<=6){
                    retval =2;
                }else if(score[0]<=9){
                    retval =3;
                }
                else if(score[0]<=10){
                    retval =4;
                }
                if(retval>balance && score[0] >7){
                    retval = balance;
                }
                break;
            case 2:
                if(score[0] ==1){
                    retval = -1;
                }else if(score[0] <=3 && bet<balance/2){
                    retval = 2;
                }else if(score[0] == 3){
                    retval = 1;
                }else if(score[0]<=7) {
                    retval = 3;
                }else if(score[0]<=10){
                    retval =5;
                }
                if(retval>balance && score[0] >5){
                    retval = balance;
                }
                break;
            case 3:
                if(score[0] ==1){
                    retval = 0;
                }else if(score[0] <=3 && bet<balance/2){
                    retval = 3;
                }else if(score[0] == 3){
                    retval = 1;
                }else if(score[0]<=7) {
                    retval = 4;
                }else if(score[0]<=10){
                    retval =6;
                }
                if(retval>balance && score[0] >3){
                    retval = balance;
                }
                break;

        }
        return retval;
    }
    public int match(int balance, Card[] hand, int bet){
        int[] score = rule.scoreCards(hand);
        int retval = 0;
        switch (boldness){
            case 1:
                if(score[0] <=2 && bet>1){
                    retval = -1;
                }
                else {
                    retval = 0;
                }
                if(bet >balance){
                    retval = -1;
                }
                break;
            case 2:
                if(score[0] <2 && bet>1){
                    retval = -1;
                }
                else {
                    retval = 0;
                }
                if(bet >balance){
                    retval = 0;
                }
                break;
            case 3:
                retval = 0;
                if(bet >balance){
                    retval = 0;
                }
                break;

        }

        return retval;
    }
    public int[] chooseTrade(Card[] hand){
        int[] score = rule.scoreCards(hand);
        int[] tradeIndexes = new int[5];
        int start;
        for(start = 1; start<score.length ; start++){
            if(score[start] == -1){
                break;
            }
        }
        int index = 0;
        for(int i = start ; i<score.length ; i++){
            for(int a = hand.length-1 ; a>=0; a--){
                if(hand[a].getFaceValue() == score[i]){
                    tradeIndexes[index] = a+1;
                    index++;
                }
            }
        }
        return tradeIndexes;
    }
    public static void main(String args[]){
        Strategy start = new Strategy(3);
        Card[] hand = {new Card("Spades", "nine", 2), new Card("Spades", "jack", 4), new Card("Spades", "queen", 5), new Card("h", "king", 8), new Card("Spades", "ace", 10)};
        int[] temp = start.chooseTrade(hand);
        for(int i = 0 ; i<temp.length ; i++){
            System.out.print(temp[i] + "\t");
        }
    }
}
