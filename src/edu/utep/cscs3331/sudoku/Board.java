package edu.utep.cscs3331.sudoku;

import java.util.Random;

public class Board {
    int size;
    boolean solved;

    public Board(){
        this(4);
    }

    public Board(int size){
        this.size = size;
    }

    private static Random rand = new Random();
    public boolean isSolved(){
        return rand.nextBoolean();
    }
}
