package edu.utep.cscs3331.sudoku;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

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

    public void showMessage(String message){
        out.println(message);
    }

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

    public UserInputPosition promptMove(){
        //FIXME needs to be more safe...
        out.println("Enter the row and column separated by a space example: row col val");
        String[] rawInput = input.nextLine().trim().split("\\s+");
        return new UserInputPosition(Integer.parseInt(rawInput[0]) - 1, Integer.parseInt(rawInput[1]) - 1, Integer.parseInt(rawInput[2]));
    }

    public boolean promptQuit(){
        out.println("enter quit to quit.");
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
