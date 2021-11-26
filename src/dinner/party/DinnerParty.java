package dinner.party;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class DinnerParty {
    static String randomFriend;
    Scanner scanner = new Scanner(System.in);

    private void fillMap(int amountOfFriends, HashMap<String, Double> dinnerFriends) {
        for (int i = 0; i < amountOfFriends; i++) {
            String name = scanner.nextLine();
            dinnerFriends.put(name, 0.0);
        }
    }

    public void makeMainMovements(HashMap<String, Double> dinnerFriends) {
        System.out.println("Enter the number of friends joining (including you):");
        int amountOfFriends = scanner.nextInt();
        if (amountOfFriends <= 0) {
            System.out.println("No one is joining for the party");
        } else {
            System.out.println("Enter the name of every friend (including you), each on a new line");
            scanner.nextLine();
            fillMap(amountOfFriends, dinnerFriends);
            calculateMap(dinnerFriends, amountOfFriends);
        }
    }

    private void calculateMap(HashMap<String, Double> dinnerFriends, int amountOfFriends) {
        System.out.println("Enter the total amount: ");
        double totalAmount = scanner.nextDouble();
        String luckyFriend = useLuckyFeature(dinnerFriends);
        double mean;
        if (luckyFriend.equals(randomFriend + " is the lucky one!")) {
            mean = BigDecimal.valueOf(totalAmount / (amountOfFriends - 1)).
                    setScale(2, RoundingMode.HALF_DOWN).doubleValue();
            for (String k : dinnerFriends.keySet()) {
                if (k.equals(randomFriend)) {
                    dinnerFriends.put(k, 0.);
                } else {
                    dinnerFriends.put(k, mean);
                }
            }
            System.out.println(randomFriend + " is the lucky one" + "\n" + dinnerFriends);
        } else if (luckyFriend.equals("No one is going to be lucky")) {
            mean = BigDecimal.valueOf(totalAmount / amountOfFriends).
                    setScale(2, RoundingMode.HALF_DOWN).doubleValue();
            dinnerFriends.replaceAll((k, v) -> mean);
            System.out.println("No one is going to be lucky" + "\n" + dinnerFriends);
        }
    }

    private String useLuckyFeature(HashMap<String, Double> dinnerFriends) {
        scanner.nextLine();
        System.out.println("Do you want to use to use \"Who is lucky?\" feature? Write Yes/No: ");
        String choice = scanner.nextLine();
        if (choice.equals("Yes")) {
            Set<String> keySet = dinnerFriends.keySet();
            List<String> keyList = new ArrayList<>(keySet);

            int size = keyList.size();
            int randIdx = new Random().nextInt(size);

            randomFriend = keyList.get(randIdx);
            return randomFriend + " is the lucky one!";
        } else if (choice.equals("No"))
            return "No one is going to be lucky";
        return "none";
    }
}
