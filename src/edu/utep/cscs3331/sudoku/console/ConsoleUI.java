package edu.utep.cscs3331.sudoku.console;

import edu.utep.cscs3331.sudoku.graphics2d.model.Square;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

public class ConsoleUI {
    private InputStream in;
    private PrintStream out;
    private Scanner input;

    public ConsoleUI(){
        this(System.in, System.out);
    }

    public ConsoleUI(InputStream in, PrintStream out){
        this.in = in;
        this.out = out;
        this.input = new Scanner(this.in);
    }

    public void welcome(){
        out.println("Welcome to sudoku");
    }

    public int promptSize() throws SudokuSizeInputException {
        out.println("Please enter a valid size (4 or 9)");
        try{
            return Integer.parseInt(input.nextLine());
        } catch(NumberFormatException e){//will work for strings, or empty space
            throw new SudokuSizeInputException("Input not a number");
        }
    }

    public int promptLevel(int[] levels) throws NumberFormatException{
        out.println("Please enter a valid level");
        try{
            int level = Integer.parseInt(input.nextLine());
            for (int validLevel : levels) {
                if (validLevel == level) return level;
            }
            //if youve gotten here its an invalid level
            throw new NumberFormatException();
        } catch(NumberFormatException e){
            throw new NumberFormatException("Invalid level");
        }
    }

    public void showMessage(String message){
        out.println(message);
    }

    //different board packages
    public void displayBoard(Board board){
        //TODO implement this properly
        //and make it pretty.
        for(int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j++) {
                out.print(board.getIndex(i, j) + "  ");
            }
            out.println();
        }
        out.println();
    }

    public void displayBoard(edu.utep.cscs3331.sudoku.graphics2d.model.Board board){
        for(int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j++) {
                out.print(board.getIndex(i, j) + "  ");
            }
            out.println();
        }
        out.println();
    }

    public Square promptMove(){
        //FIXME needs to be more safe...
        out.println("Enter the row and column separated by a space example: row col val");
        String[] rawInput = input.nextLine().trim().split("\\s+");
        return new Square(Integer.parseInt(rawInput[0]) - 1, Integer.parseInt(rawInput[1]) - 1, Integer.parseInt(rawInput[2]));
    }

    public boolean promptQuit(){
        out.println("enter quit to quit. Or anything else to continue");
        return input.nextLine().trim().equalsIgnoreCase("quit");
    }

    public void promptInvalidInput() {
        out.println("invalid input, try again");
    }

    //IMPORTANT, call this before leaving please?
    public void exit(){
        input.close();
    }
}
