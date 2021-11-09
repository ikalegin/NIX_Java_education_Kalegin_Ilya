package nix.education.java.hangman;


public class Main {
    public static void main(String[] args) {
        Hangman hangman = new Hangman();
        String[] progLanguages = {"python", "java", "javascript", "kotlin"};
        hangman.showMenu(progLanguages);
    }
}
