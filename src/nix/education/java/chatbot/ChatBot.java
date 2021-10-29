package nix.education.java.chatbot;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        showBotInfo();

        System.out.println("Please, remind me your name.");
        String name = scanner.nextLine();
        System.out.println("What a great name you have, " + name);

        System.out.println("Let me guess your age. \n" +
                "Enter remainders of dividing your age by 3,5 and 7.");
        int remainder3 = scanner.nextInt();
        int remainder5 = scanner.nextInt();
        int remainder7 = scanner.nextInt();
        System.out.println("Your age is " + guessAge(remainder3, remainder5, remainder7) + "; " +
                "that's a good time to start programming");

        System.out.println("Now I will prove to you that I can count to any number you want");
        int number = scanner.nextInt();
        countNumbers(number);

        testKnowledge();
        System.out.println("Goodbye, have a nice day!");
    }

    static void showBotInfo() {
        System.out.println("Hello! My name is AlohaDanceTV \n" +
                "I was created in 2021");
    }

    static int guessAge(int remainder3, int remainder5, int remainder7) {
        return (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
    }

    static void countNumbers(int numberLimit) {
        for (int i = 0; i <= numberLimit; i++) {
            System.out.println(i + " !");
        }
    }

    static void testKnowledge() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Let's test your programming knowledge. \n" +
                "Why do we use methods?");
        System.out.println("1. To repeat a statement multiple times \n" +
                "2. To decompose a program into several small subroutines \n" +
                "3. To determine the execution time of a program \n" +
                "4. To interrupt the execution of a program.");
        int choice = scanner.nextInt();
        while (choice != 2) {
            System.out.println("Please, try again.");
            choice = scanner.nextInt();
        }
        System.out.println("Great, you right!");
    }
}
