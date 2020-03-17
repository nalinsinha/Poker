public class Card {
    private final String suit;
    private final String name;
    private final int faceValue;
    public Card(String s, String n, int f) {
        suit = s;
        name = n;
        faceValue = f;
    }

    public String getSuit() {
        return suit;
    }

    public String getName() {
        return name;
    }

    public int getFaceValue() {
        return faceValue;
    }
    public String toString(){
        return "Suit: " + suit + " Name: " + name + " Face Value: " + faceValue;
    }
}
