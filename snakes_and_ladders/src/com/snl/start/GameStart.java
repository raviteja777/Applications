package com.snl.start;

import com.snl.entities.Player;
import com.snl.manager.GameManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by BOI on 18-05-2019.
 */
public class GameStart {


    public static void main(String args[]){

        Scanner scan =  new Scanner(System.in);
        System.out.println("Enter no of human players---");
        int n = Integer.parseInt(scan.nextLine());
        if(n<=0){
            System.out.println("Num players cannot be zero or less");
            System.exit(0);
        }
        System.out.println("Enter player names for "+ n +" players :");
        List<String> playerNames = new ArrayList<>();
        for(int i=0;i<n;i++){
            playerNames.add(scan.nextLine());
        }
        boolean computerPlay = true;
        if(n>1){
            System.out.println("Do you want computer to play ? press Y for yes , any key for No");
            char resp = scan.nextLine().trim().charAt(0);
            if(resp!='Y' && resp!='y'){
                computerPlay = false;
            }
        }

        //start game manager
        GameManager manager = new GameManager(playerNames,computerPlay);
        manager.getPlayerList().forEach(p->System.out.println(p.getPlayerID()+"---"+p.getPlayerName()));
        Player winner = manager.play();
        System.out.println("Winner is "+winner.getPlayerName());
    }

}
