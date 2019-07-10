package com.snl.manager;

import com.snl.entities.Board;
import com.snl.entities.Player;

import java.util.*;

/**
 * Manager class which runs the game
 * Core game logic , init board and players and track player positions and dice roll
 */
public class GameManager{

    public static final int END = 100;

    private int numPlayers;
    private List<Player> playerList;
    private Board board ;
    boolean computerPlay;
    private String computer = "COMPUTER";

    Scanner scan = new Scanner(System.in);

    public GameManager(List<String> playerNames,boolean computerPlay){
        board = new Board();
        board.genSnakesLadders();
        this.numPlayers = playerNames.size();
        playerList = new ArrayList<>();
        this.computerPlay = computerPlay ;

        for(int i=0;i<numPlayers;i++){
            playerList.add(new Player(i+1,playerNames.get(i)));
        }
        if(computerPlay){
            playerList.add(new Player(numPlayers+1,computer));
        }

    }

    public Map<Integer,Integer> getBoard(){
        return  board.getBoard();
    }

    public List<Player> getPlayerList(){
        return Collections.unmodifiableList(playerList);
    }

    public Player play(){

        System.out.println("----Begin Play---");

        int currPos = 0;

        while(currPos<END){
            for(Player p : playerList){
                System.out.println("Current player "+p.getPlayerName() + " current position :"+p.getCurrentCell());

                if(!computerPlay || (computerPlay && p.getPlayerID()<=numPlayers)){
                    System.out.println("Roll - dice --press ENTER to roll ");
                    scan.nextLine();
                }
                else if((computerPlay && p.getPlayerID()>numPlayers)){
                    System.out.println("Computer rolling dice");
                }
                //get position after turn
                currPos = turn(p);
                if(currPos==END){
                    return p;
                }
            }
        }
        return null;
    }

    public int turn(Player player){

        int diceResult = player.rollDice();
        System.out.println("Dice rolled to "+diceResult);
        int curr = player.getCurrentCell();
        int temp = curr+diceResult;
        if(temp<=END){
            //in case of snake or ladder encountered
            Integer jump = getBoard().get(temp);
            if(jump!=null){
                System.out.println(player.getPlayerName()+" encountered snake/ladder at "+temp);
                temp = jump;
            }
            player.updatePos(temp);
            System.out.println(player.getPlayerName()+" moves to "+temp);
            return temp;
        }
        System.out.println("Position exceeds END position ,chance lost!");
        return curr;

    }



}
