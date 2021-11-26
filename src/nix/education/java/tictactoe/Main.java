package nix.education.java.tictactoe;

public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        String[][] ticTacToeField = new String[3][3];
        ticTacToe.makeAGame(ticTacToeField);
    }
}
