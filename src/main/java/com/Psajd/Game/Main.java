package com.Psajd.Game;


public class Main {

    private static final String introduction = """
            
            ===============================================================
                    Hello, this is console "tic tac toe" game
                You might know rules of this simple game, so let's start
            ===============================================================
            """;



    public static void main(String[] args) {
        System.out.println(introduction);
        new Game().play();
    }
}
