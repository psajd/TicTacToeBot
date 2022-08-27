package com.Psajd.Game;

public class Player {

    private int wins;
    private int loses;
    private int draws;
    private String shape;

    private String name;

    public void printStats() {
        System.out.println("===================\n" +
                "Player name: " + name +
                "\n     Wins: " + wins +
                "\n     Loses: " + loses +
                "\n     Draws: " + draws);
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(String name) {
        this.wins = 0;
        this.loses = 0;
        this.draws = 0;
        this.name = name;
    }

    public Player(String name, String shape) {
        this.wins = 0;
        this.loses = 0;
        this.draws = 0;
        this.name = name;
        this.shape = shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public String getShape() {
        return shape;
    }

    public String getName() {
        return name;
    }
}
