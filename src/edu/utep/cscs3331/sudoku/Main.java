package edu.utep.cscs3331.sudoku;

public class Main {
    private ConsoleUI ui = new ConsoleUI();
    private Board board;



    public static void main(String[] args) {
        new Main().play();
    }

    private void play() {
        ui.welcome();
        int size = ui.promptSize();
        board = new Board(size);

        while(!board.isSolved()){

        }

        ui.showMessage("Solved!");
    }
}
