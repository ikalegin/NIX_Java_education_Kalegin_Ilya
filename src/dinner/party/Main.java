package dinner.party;


import java.util.HashMap;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Double> dinnerFriends = new HashMap<>();
        DinnerParty dinnerParty = new DinnerParty();
        dinnerParty.makeMainMovements(dinnerFriends);
    }
}
