public class PokerPlayer extends Player{
    private int money;
    public PokerPlayer(String n, int s, int b) {
        super(n,s);
        money = b ;

    }
    public boolean canCoverBet(double amt){
        if(amt>money){
            return false;
        }
        else{
            return true;
        }
    }
    public double deduct(double amt){
        if(canCoverBet(amt)){
            money-= amt;
            return amt;
        }
        else{
            return -1;
        }
    }
    public void increase(double amt){
        money+= amt;
    }
    public int getMoney(){
        return money;
    }
}
