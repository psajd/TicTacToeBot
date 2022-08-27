package com.Psajd.Game;

import java.util.ArrayList;
import java.util.List;
// 5 13 21
// 3 7 11

public class Field {

    List<StringBuilder> asciiField;
    String[][] gameMap;
    private static final int mapSize = 24;

    private static final String monoLine = "     # # # # # # # # # # # # #\n";
    private static final String separatedLine = "     #       #       #       #\n";

    private static List<StringBuilder> getNewAsciiField() {
        List<StringBuilder> result = new ArrayList<>(mapSize + 2);
        result.add(new StringBuilder("         1       2       3    \n"));
        result.add(new StringBuilder("                              \n"));
        for (int i = 0; i < mapSize / 2 + 1; i++) {
            StringBuilder temp = new StringBuilder().append(((i + 2) % 4 == 0) ? (i + 2) / 4 : "");
            result.add(temp.append((i % 4 == 0) ? monoLine : (((i + 2) % 4 == 0) ? separatedLine.substring(1) : separatedLine)));
        }
        return result;
    }

    boolean isCeilBusy(int x, int y) {
        return !gameMap[y - 1][x - 1].equals("");
    }

    void setFigure(int x, int y, String figure) {
        gameMap[y - 1][x - 1] = figure;
        y = y * 4;
        x = x * 8 + 1;
        StringBuilder currentLine = asciiField.get(y);
        currentLine.replace(x, x + 1, figure);

    }

    private String getFieldAsString() {
        StringBuilder finalString = new StringBuilder();
        asciiField.forEach(finalString::append);
        return finalString.toString();
    }

    void printField() {
        System.out.println(getFieldAsString());
    }

    Field() {
        gameMap = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameMap[i][j] = "";
            }
        }
        asciiField = getNewAsciiField();
    }

    String winnerCheck() {
        for (int i = 0; i < 3; i++) {
            if (gameMap[i][0].equals(gameMap[i][1]) && gameMap[i][1].equals(gameMap[i][2]))
                return gameMap[i][0];
        }
        for (int i = 0; i < 3; i++) {
            if (gameMap[0][i].equals(gameMap[1][i]) && gameMap[1][i].equals(gameMap[2][i]))
                return gameMap[0][i];
        }
        if (gameMap[0][0].equals(gameMap[1][1]) && gameMap[1][1].equals(gameMap[2][2]))
            return gameMap[0][0];
        if (gameMap[2][0].equals(gameMap[1][1]) && gameMap[1][1].equals(gameMap[0][2]))
            return gameMap[2][0];

        return "";
    }

    boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameMap[i][j].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }


}
