package com.Psajd.Game;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Game {

    private Player firstPlayer, secondPlayer;
    private Field field;

    private Scanner gameScanner = new Scanner(System.in);

    private void clearConsole() {
        IntStream.range(0, 15).forEach(x -> System.out.println("\n"));
    }

    private void playWithBot() {
        System.out.println("Input player name");
        firstPlayer = new Player(nameChoice(), shapeChoice());
        secondPlayer = new Player("Bot", (firstPlayer.getShape().equals("X")) ? "O" : "X");
    }

    private int twoPlayersGameProcess() {
        field = new Field();
        int winnerNumber = (firstPlayer.getShape().equals("X")) ? 1 : 0;
        while (!field.winnerCheck().equals("X") && !field.winnerCheck().equals("O") && !field.isDraw()) {

            clearConsole();
            field.printField();
            winnerNumber++;
            int r, c;
            if (winnerNumber % 2 + 1 == 1) {
                System.out.println("First player's move\n\n\n");
                r = rowColChoice();
                c = rowColChoice();
                while (field.isCeilBusy(r, c)) {
                    System.out.println("Ceil is busy, try another");
                    r = rowColChoice();
                    c = rowColChoice();
                }
                field.setFigure(r, c, firstPlayer.getShape());

            } else {
                System.out.println("Second player's move\n\n\n");
                r = rowColChoice();
                c = rowColChoice();
                while (field.isCeilBusy(r, c)) {
                    System.out.println("Ceil is busy, try another");
                    r = rowColChoice();
                    c = rowColChoice();
                }
                field.setFigure(r, c, secondPlayer.getShape());
            }
        }
        clearConsole();
        field.printField();
        System.out.println();
        return winnerNumber;
    }

    private void gameResults(int winnerNumber) {
        if (field.isDraw()) {
            System.out.println("Draw!");
            firstPlayer.setDraws(firstPlayer.getDraws() + 1);
            secondPlayer.setDraws(secondPlayer.getDraws() + 1);
        } else if (winnerNumber % 2 == 1) {
            System.out.println(secondPlayer.getName() + " player wins!");
            secondPlayer.setWins(secondPlayer.getWins() + 1);
            firstPlayer.setLoses(firstPlayer.getLoses() + 1);
        } else if (winnerNumber % 2 == 0) {
            System.out.println(secondPlayer.getName() + " player wins!");
            firstPlayer.setWins(firstPlayer.getWins() + 1);
            secondPlayer.setLoses(secondPlayer.getLoses() + 1);
        }

    }

    private void playWithAnotherPlayer() {
        System.out.println("Input first player's name");
        firstPlayer = new Player(nameChoice());
        System.out.println("Input second player's name");
        secondPlayer = new Player(nameChoice());

        while (true) {

            firstPlayer.setShape(shapeChoice());
            secondPlayer.setShape((firstPlayer.getShape().equals("X")) ? "O" : "X");
            int winnerNumber = twoPlayersGameProcess();
            gameResults(winnerNumber);

            firstPlayer.printStats();
            secondPlayer.printStats();

            System.out.println("Next game(1) or exit(0)?");
            while (true) {
                while (!gameScanner.hasNextInt()) {
                    gameScanner.next();
                    System.out.println("err: input a number");
                }
                int result = gameScanner.nextInt();
                if (result == 0) {
                    return;
                } else if (result == 1) {
                    break;
                }
            }

        }
    }

    private int rowColChoice() {
        int rowOrCol;
        while (true) {
            while (!gameScanner.hasNextInt()) {
                gameScanner.next();
                System.out.println("err: input a number");
            }
            rowOrCol = gameScanner.nextInt();
            if (rowOrCol == 1 || rowOrCol == 2 || rowOrCol == 3) {
                break;
            }
            System.out.println("Error: input 1 or 2 or 3");
        }
        return rowOrCol;
    }


    private String nameChoice() {
        return gameScanner.next();
    }

    private String shapeChoice() {
        System.out.println("X or O (input first player's character)");
        String shape;
        while (true) {
            shape = gameScanner.next();
            if (shape.equals("O") || shape.equals("X")) {
                break;
            }
            System.out.println("Error: input X or O");
        }
        return shape;
    }

    private int playerValueChoice() {
        System.out.println("1 player / 2 player (input number)");
        int playerValue;
        while (true) {
            while (!gameScanner.hasNextInt()) {
                gameScanner.next();
                System.out.println("err: input a number");
            }
            playerValue = gameScanner.nextInt();
            if (playerValue == 1 || playerValue == 2) {
                break;
            }
            System.out.println("Error: input 1 or 2");
        }
        return playerValue;
    }

    public void play() {
        int playerValue = playerValueChoice();
        if (playerValue == 1) {
            playWithBot();
        } else {
            playWithAnotherPlayer();
        }
    }
}
