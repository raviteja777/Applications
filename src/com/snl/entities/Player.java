package com.snl.entities;

import java.util.Random;

/**
 * Class representing a single player
 * contains player id , current location
 * if player reaches last cell - then that player is declared winner
 */
public class Player {

    private int playerID;
    private String playerName;
    private int currentCell;
    Random rand = new Random();

    public Player(int playerID,String playerName){
        this.playerID = playerID;
        this.playerName = playerName;
        currentCell = 1;
    }

    //simulates random value between 1 and 6 indicating dice roll
    public int rollDice(){
        return 1+rand.nextInt(6);
    }

    //updates position as per value returned by gameManager
    public void updatePos(int pos) {
        currentCell = pos;
    }

    public String getPlayerName(){
        return playerName;
    }

    public int getPlayerID(){
        return playerID;
    }

    public int getCurrentCell(){
        return currentCell;
    }

}
