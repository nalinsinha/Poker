public class ComputerPokerPlayer extends PokerPlayer {
    private Strategy strat;
    public ComputerPokerPlayer(String n, int s, int b, int a) {
        super(n,s,b);
        strat = new Strategy(a);
    }
    public Strategy getStrat(){
        return strat;
    }

}
