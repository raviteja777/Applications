package com.snl.entities;

import java.util.*;

/**
 * Snakes and ladders Board
 * generates board with all snakes and ladders
 * contains common dictionary - key is origin cell , value is destination cell
 * if key<value - then entry is snake , else entry is ladder
 */
public class Board{

    final int START = 1;
    final int END = 100;
    Random rand = new Random();

    //dict contains <start,dest> - represents all cells with snake or ladder presence
    private TreeMap<Integer,Integer>  dict;
    private List<Integer> lookup;
    private int numSnakes=5;
    private int numLadders = 5;

    private int diffCell = 10;

    long ts = System.currentTimeMillis();

    public Board(){
        dict = new TreeMap<>();
        lookup = new ArrayList<>();
        lookup.add(START);
        lookup.add(END);
        lookup.add(END-1);
    }

    //user sends custom number of snakes then this will get called
    public Board(int numSnakes,int numLadders){
        this();
        this.numSnakes = numSnakes;
        this.numLadders = numLadders;
    }

    //generates either snakes or ladder
    private void genEntity(Boolean isSnake){

        int orig=0 ;
        int dest=0 ;

        //generate origin cell
        boolean again ;
        do{
            again = false;
            orig = 1+rand.nextInt(END-2);
            //System.out.println("orig check "+orig);
            if(lookup.contains(orig)){
                again = true;
            }
            else{
                if(isSnake && orig<START+9){
                    again = true;
                }
                else if(!isSnake && orig>END-15){
                    again = true;
                }
            }
        }while(again);

        //generate destination cell
        //dest cell < orig cell for snake , dest cell > orig cell for ladder
        //atleast 10 diff between orig and dest cell
        do{
            int counter = 0;
            again = false;

            if(isSnake){
                dest = 1+rand.nextInt(orig-diffCell);
            }
            else{
                dest = diffCell+orig+rand.nextInt(END-2-orig-diffCell);
            }

            //System.out.println("dest check "+dest);

            if(lookup.contains(dest)){
                again = true;
            }
            else{
                if(isSnake){
                    if(orig-dest<=diffCell || dest>END-9){
                        again = true;
                    }
                }
                else{
                    if(dest-orig<=diffCell || dest<START+10){
                        again = true;
                    }
                }
            }
            if(counter>10){
                System.out.println(orig+" --"+dest);
            }

        }while(again);


        updateBoard(orig,dest);
        System.out.println((isSnake?"snake":"ladder")+"-->"+orig+" ,"+dest);

    }


    private void updateBoard(int orig,int dest){
        dict.put(orig,dest);
        lookup.add(orig);
        lookup.add(dest);
    }

    public void genSnakesLadders(){
        System.out.println("--generating snakes and ladders --");

        for(int i=0;i<numSnakes;i++){
            genEntity(true);
        }
        for(int i=0;i<numLadders;i++){
            genEntity(false);
        }
    }

    public Map<Integer,Integer> getBoard(){
        return Collections.unmodifiableMap(dict);
    }

    public String toString(){
        return dict.toString();
    }


}
