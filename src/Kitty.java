public class Kitty {
    private double total;
    public void update(double m){
        total+=m;
    }
    public double payout(){
        double temp = total;
        total = 0;
        return temp;
    }
}
