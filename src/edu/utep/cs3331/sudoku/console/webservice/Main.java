package edu.utep.cs3331.sudoku.console.webservice;

import edu.utep.cs3331.sudoku.console.ConsoleUI;
import edu.utep.cs3331.sudoku.model.Board;

/**
 * Author: Aleksandr Diamond on 1/29/2018
 * Assignment: sudoku
 * Purpose:
 */
public class Main {
    private ConsoleUI ui = new ConsoleUI();
    private Board board;

    public static void main(String[] args) {
        new Main().play();
    }

    public void play(){
        ui.welcome();
        Info info = Info.query();
        ui.showMessage("Info default level is: " + info.defaultLevel);
        ui.showMessage("Info default size is : " + info.defaultSize);

        ui.showMessage("levels: ");
        for (int level : info.levels)
            ui.showMessage(level + "");

        ui.showMessage("Sizes: ");
        for (int size : info.sizes)
            ui.showMessage(size + "");

        //its default by default
        int size = info.defaultSize, level = info.defaultLevel;
        while (true) {
            try {
                size = ui.promptSize();
                board = new Board(size);
                level = ui.promptLevel(info.levels);
                break;
            } catch (IllegalArgumentException e) {
                ui.promptQuit();
                ui.promptIllegalArgument();
            }
        }
        //if here we have valid size and level
        //so build board
        PuzzleRequest.Puzzle puzzle = new PuzzleRequest(size, level).query();
        if(puzzle.getResponse()){
            for (Square square : puzzle.getSquares()) {
//                try {
                    System.out.println("square.x = " + square.x);
                    System.out.println("square.y = " + square.y);
                    System.out.println("square.value = " + square.value);
                board.updateBoard(new edu.utep.cs3331.sudoku.model.Square(square.x, square.y, square.value));
                    ui.displayBoard(board);
                    System.out.println();

                    //returns 0 based indexes..
//                } catch (SudokuInvalidPositionException e) {
                    //definitely should NOT happen...
                    //means an invalid position was given by the web service
                    //nonetheless exception must be handled
//                    e.printStackTrace();
//                    continue;
//                }
            }
        }
        //board is now update from web service
        boolean quiting = false;
        while(!board.isSolved() && !quiting){
            ui.displayBoard(board);
            edu.utep.cs3331.sudoku.model.Square position = ui.promptMove();
//            try{
                board.updateBoard(position);
//            }  catch (SudokuInvalidPositionException e) {
            ui.promptIllegalArgument();
//            }
            ui.displayBoard(board);
            quiting = ui.promptQuit();
        }
        if(board.isSolved()) ui.showMessage("Solved!");
        ui.showMessage("Goodbye!");
        ui.exit();
    }
}
