package nix.education.java.tictactoe;

import java.util.*;


public class TicTacToe {
    static List<List<Integer>> winningConditions;

    Scanner scanner = new Scanner(System.in);

    private ArrayList<Integer> xPositions = new ArrayList<>();
    private ArrayList<Integer> oPositions = new ArrayList<>();
    private boolean xWinner = false;
    private boolean oWinner = false;
    private String currentPlayer = "X";

    static {
        List<Integer> topRow = Arrays.asList(0, 1, 2);
        List<Integer> middleRow = Arrays.asList(3, 4, 5);
        List<Integer> bottomRow = Arrays.asList(6, 7, 8);
        List<Integer> leftCol = Arrays.asList(0, 3, 6);
        List<Integer> middleCol = Arrays.asList(1, 4, 7);
        List<Integer> rightCol = Arrays.asList(2, 5, 8);
        List<Integer> cross1 = Arrays.asList(0, 4, 8);
        List<Integer> cross2 = Arrays.asList(6, 4, 2);
        winningConditions = new ArrayList<>();
        winningConditions.add(topRow);
        winningConditions.add(middleRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftCol);
        winningConditions.add(rightCol);
        winningConditions.add(middleCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);
    }

    private boolean isImpossible() {
        return (oWinner && xWinner || xPositions.size() - oPositions.size() > 1 || oPositions.size() - xPositions.size()
                > 1);
    }

    private boolean isGameNotFinished() {
        return (!oWinner && !xWinner && xPositions.size() + oPositions.size() < 9);
    }

    private boolean isOWinner() {
        return oWinner && !xWinner;
    }

    private boolean isxWinner() {
        return xWinner && !oWinner;
    }

    private boolean isDraw() {
        return !xWinner && !oWinner;
    }


    public void switchPlayer() {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        } else if (currentPlayer.equals("O")) {
            currentPlayer = ("X");
        }
    }

    private void getWinnerConditions() {

        for (List<Integer> l : winningConditions) {
            if (xPositions.containsAll(l)) {
                xWinner = true;
            }
            if (oPositions.containsAll(l)) {
                oWinner = true;
            }
        }
    }

    public void readCells(String[][] ticTacToeField) {
        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++) {
                ticTacToeField[column][row] = " ";
            }
        }
    }

    public void makeAGame(String[][] ticTacToeField){
        readCells(ticTacToeField);
        showGameStatus(ticTacToeField);
        while (checkWinner().equals("Game not finished")) {
            makeUserMove(ticTacToeField);
            showGameStatus(ticTacToeField);
            checkWinner();
            switchPlayer();
        }
        System.out.println(checkWinner());
    }

    private void doUserMove(String[][] ticTacToeField, String player, int column, int row) {
        ticTacToeField[column][row] = player;
        if (player.equals("X")) {
            xPositions.add(3 * column + row);
        } else if (player.equals("O")) {
            oPositions.add(3 * column + row);
        }
    }

    private boolean checkUserMove(String[][] ticTacToeField, String[] coordinates) {
        if (coordinates.length != 2) {
            System.out.println("You should enter the right coordinates");
            return false;
        }
        String regex = "[^0-9]+";
        if (coordinates[0].matches(regex) || coordinates[1].matches(regex)) {
            System.out.println("You should use numbers!");
            return false;
        }
        int column = Integer.parseInt(coordinates[0]) - 1;
        int row = Integer.parseInt(coordinates[1]) - 1;
        if (column > 2 || column < 0 || row > 2 || row < 0) {
            System.out.println("Coordinates should be from 1 to 3");
            return false;
        }
        if (ticTacToeField[column][row].equals("X") || ticTacToeField[column][row].equals("O")) {
            System.out.println("This cell is occupied! Choose another one");
            return false;
        }

        return true;
    }

    public void makeUserMove(String[][] ticTacToeField) {
        System.out.print("Enter the coordinates: ");
        String[] coordinates = scanner.nextLine().split(" ");
        if (!checkUserMove(ticTacToeField, coordinates)) {
            makeUserMove(ticTacToeField);
        } else {
            int column = Integer.parseInt(coordinates[0]) - 1;
            int row = Integer.parseInt(coordinates[1]) - 1;
            doUserMove(ticTacToeField, currentPlayer, column, row);
        }
    }

    public void showGameStatus(String[][] ticTacToeField) {
        System.out.println("---------");
        for (int column = 0; column < 3; column++) {
            System.out.print("| ");
            for (int row = 0; row < 3; row++) {
                System.out.printf("%s ", ticTacToeField[column][row]);
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public String checkWinner() {
        getWinnerConditions();
        String winnerStatus = "NULL";
        if (isImpossible()) {
            winnerStatus = "Impossible";
        } else if (isGameNotFinished()) {
            winnerStatus = "Game not finished";
        } else if (isOWinner()) {
            winnerStatus = "O won";
        } else if (isxWinner()) {
            winnerStatus = "X won";
        } else if (isDraw()) {
            winnerStatus = "Draw";
        }
        return winnerStatus;
    }
}