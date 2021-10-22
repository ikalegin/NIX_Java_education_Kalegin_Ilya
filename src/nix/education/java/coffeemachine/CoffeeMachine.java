package nix.education.java.coffeemachine;

import java.util.Scanner;

enum State {NONE, CHOOSING_ACTION, CHOOSING_COFFEE, FILLING, PRINTING}

final class Value {
    public static final int COFFEE_WATER = 200;
    public static final int COFFEE_MILK = 50;
    public static final int COFFEE_BEANS = 15;

    public static final int MIN_ESPRESSO_WATER = 250;
    public static final int MIN_ESPRESSO_COFFEE_BEANS = 16;
    public static final int ESPRESSO_COST = 4;

    public static final int MIN_LATTE_WATER = 350;
    public static final int MIN_LATTE_MILK = 75;
    public static final int MIN_LATTE_COFFEE_BEANS = 20;
    public static final int LATTE_COST = 7;

    public static final int MIN_CAPPUCCINO_WATER = 200;
    public static final int MIN_CAPPUCCINO_MILK = 100;
    public static final int MIN_CAPPUCCINO_COFFEE_BEANS = 12;
    public static final int CAPPUCCINO_COST = 6;
}

public class CoffeeMachine {
    public final int ESPRESSO = 1;
    public final int LATTE = 2;
    public final int CAPPUCCINO = 3;
    public final int EXIT = 4;


    private Scanner scanner = new Scanner(System.in);
    private State coffeeMachineState = State.NONE;
    private int machineWater = 400;
    private int machineMilk = 540;
    private int machineCoffeeBeans = 120;
    private int machineMoney = 550;
    private int machineCups = 9;

    public void printStatus() {
        System.out.println("Starting to make a coffee\n" +
                "Grinding coffee beans\n" +
                "Boiling water\n" +
                "Mixing boiled water with crushed coffee beans\n" +
                "Pouring coffee into the cup\n" +
                "Pouring some milk into the cup\n" +
                "Coffee is ready!");
    }

    public void printMachineStatus() {
        coffeeMachineState = State.PRINTING;
        System.out.println("The coffee machine has:\n" +
                machineWater + " of water\n" +
                machineMilk + " of milk\n" +
                machineCoffeeBeans + " of coffee beans\n" +
                machineCups + " of disposable cups\n" +
                machineMoney + " of money");
    }

    public void calculateIngredientsAmount(int coffeeCups) {
        System.out.println("For " + coffeeCups + " cups of coffee you will need: ");
        System.out.println((Value.COFFEE_WATER * coffeeCups) + " ml of water\n" +
                (Value.COFFEE_MILK * coffeeCups) + " ml of milk\n" +
                (Value.COFFEE_BEANS * coffeeCups) + " of coffee beans\n");
    }

    public void calculateIsIngredientsEnough() {
        System.out.println("How many ml of water the coffee machine has: ");
        machineWater = scanner.nextInt();
        System.out.println("How many ml of milk the coffee machine has: ");
        machineMilk = scanner.nextInt();
        System.out.println("How many grams of coffee beans the coffee machine has: ");
        machineCoffeeBeans = scanner.nextInt();
        System.out.println("How many cups of coffee you will need: ");
        int coffeeCups = scanner.nextInt();
        int waterAmount = Value.COFFEE_WATER * coffeeCups;
        int milkAmount = Value.COFFEE_MILK * coffeeCups;
        int coffeeBeansAmount = Value.COFFEE_BEANS * coffeeCups;
        if (machineWater == waterAmount && machineMilk == milkAmount &&
                machineCoffeeBeans == coffeeBeansAmount) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (machineWater > waterAmount && machineMilk > milkAmount &&
                machineCoffeeBeans > coffeeBeansAmount) {
            int waterForCup = (machineWater - waterAmount) / Value.COFFEE_WATER;
            int milkForCup = (machineMilk - milkAmount) / Value.COFFEE_MILK;
            int coffeeBeansForCup = (machineCoffeeBeans - coffeeBeansAmount) / Value.COFFEE_BEANS;
            System.out.println("Yes, I can make that amount of coffee (and even "
                    + Math.min(waterForCup, Math.min(milkForCup, coffeeBeansForCup)) + " more than that)");
        } else {
            System.out.println("No, I can make only " +
                    Math.min(machineWater / Value.COFFEE_WATER, Math.min(machineMilk / Value.COFFEE_MILK,
                            machineCoffeeBeans / Value.COFFEE_BEANS))
                    + " cups of coffee");
        }
    }

    public void buy() {
        System.out.println("What do you wanna buy: 1 - espresso, 2 - latte, 3 - cappuccino, 4 - back to main menu");
        coffeeMachineState = State.CHOOSING_COFFEE;
        int coffeeChoice = scanner.nextInt();
        if (coffeeChoice == ESPRESSO) {
            if (machineWater >= Value.MIN_ESPRESSO_WATER && machineCoffeeBeans >= Value.MIN_ESPRESSO_COFFEE_BEANS &&
                    machineCups >= 1) {
                machineWater -= Value.MIN_ESPRESSO_WATER;
                machineCoffeeBeans -= Value.MIN_ESPRESSO_COFFEE_BEANS;
                machineCups--;
                machineMoney += Value.ESPRESSO_COST;
                System.out.println("Here's your Espresso");
            } else {
                if (machineWater < Value.MIN_ESPRESSO_WATER)
                    System.out.println("Sorry, not enough water");
                else if (machineCoffeeBeans < Value.MIN_ESPRESSO_COFFEE_BEANS)
                    System.out.println("Sorry, not enough coffee beans");
                else if (machineCups < 1)
                    System.out.println("Sorry, not enough cups");
            }
        } else if (coffeeChoice == LATTE) { // latte
            if (machineWater >= Value.MIN_LATTE_WATER && machineMilk >= Value.MIN_LATTE_MILK &&
                    machineCoffeeBeans >= Value.MIN_LATTE_COFFEE_BEANS) {
                machineWater -= Value.MIN_LATTE_WATER;
                machineMilk -= Value.MIN_LATTE_MILK;
                machineCoffeeBeans -= Value.MIN_LATTE_COFFEE_BEANS;
                machineCups--;
                machineMoney += Value.LATTE_COST;
                System.out.println("Here's your Latte");
            } else {
                if (machineWater < Value.MIN_LATTE_WATER)
                    System.out.println("Sorry, not enough water");
                else if (machineMilk < Value.MIN_LATTE_MILK)
                    System.out.println("Sorry, not enough milk");
                else if (machineCoffeeBeans < Value.MIN_LATTE_COFFEE_BEANS)
                    System.out.println("Sorry, not enough coffee beans");
                else if (machineCups < 1)
                    System.out.println("Sorry, not enough cups");
            }
        } else if (coffeeChoice == CAPPUCCINO) { // cappuccino
            if (machineWater >= Value.MIN_CAPPUCCINO_WATER && machineMilk >= Value.MIN_CAPPUCCINO_MILK &&
                    machineCoffeeBeans >= Value.MIN_CAPPUCCINO_COFFEE_BEANS) {
                machineWater -= Value.MIN_CAPPUCCINO_WATER;
                machineMilk -= Value.MIN_CAPPUCCINO_MILK;
                machineCoffeeBeans -= Value.MIN_CAPPUCCINO_COFFEE_BEANS;
                machineCups--;
                machineMoney += Value.CAPPUCCINO_COST;
                System.out.println("Here's your Cappuccino");
            } else {
                if (machineWater < Value.MIN_CAPPUCCINO_WATER)
                    System.out.println("Sorry, not enough water");
                else if (machineMilk < Value.MIN_CAPPUCCINO_MILK)
                    System.out.println("Sorry, not enough milk");
                else if (machineCoffeeBeans < Value.MIN_CAPPUCCINO_COFFEE_BEANS)
                    System.out.println("Sorry, not enough coffee beans");
                else if (machineCups < 1)
                    System.out.println("Sorry, not enough cups");
            }
        } else if (coffeeChoice == EXIT) {
            System.out.println("");
        }
    }

    public void fill() {
        coffeeMachineState = State.FILLING;
        System.out.println("How many ml of water you want to add: ");
        int fill = scanner.nextInt();
        machineWater += fill;
        System.out.println("How many ml of milk you want to add: ");
        fill = scanner.nextInt();
        machineMilk += fill;
        System.out.println("How many grams of coffee beans you want to add: ");
        fill = scanner.nextInt();
        machineCoffeeBeans += fill;
        System.out.println("How many disposable coffee cups you want to add: ");
        fill = scanner.nextInt();
        machineCups += fill;
    }

    public void take() {
        System.out.println("I gave you " + machineMoney);
        machineMoney -= machineMoney;
    }

    public void remaining() {
        printMachineStatus();
    }


    public void choosingAction() {
        while (true) {
            coffeeMachineState = State.CHOOSING_ACTION;
            System.out.println("Write action: buy, fill, take, remaining, exit");
            String choice = scanner.nextLine();
            if (choice.equals("buy")) {
                buy();
            } else if (choice.equals("fill")) {
                fill();
            } else if (choice.equals("take")) {
                take();
            } else if (choice.equals("remaining")) {
                remaining();
            } else if (choice.equals("exit")) {
                return;
            }
        }
    }
}
