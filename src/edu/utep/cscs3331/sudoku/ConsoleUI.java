package edu.utep.cscs3331.sudoku;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsoleUI {
    private InputStream in;
    private PrintStream out;

    public ConsoleUI(){
        this(System.in, System.out);
    }

    public ConsoleUI(InputStream in, PrintStream out){
        this.in = in;
        this.out = out;
    }

    public void welcome(){
        out.println("Welcome to sudoku");
    }

    public int promptSize() {
        //TODO implement this method
        return 0;
    }

    public void showMessage(String message){
        out.println(message);
    }
}
