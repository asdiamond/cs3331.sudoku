package edu.utep.cscs3331.sudoku;

public class Main {
    private ConsoleUI ui = new ConsoleUI();
    private Board board;

    public static void main(String[] args) {
        new Main().play();
    }

    private void play() {
        ui.welcome();

        //keep asking until you get a valid input size
        boolean validsize = false;
        do {
            int size;
            try {
                size = ui.promptSize();
                board = new Board(size);
                validsize = true;
                //if there are no problems start game
            } catch (SudokuSizeInputException e) {
                ui.promptQuit();
                ui.promptInvalidInput();
            }
        } while (!validsize);

        while(!board.isSolved()){
            ui.displayBoard(board);
            UserInputPosition userPosition = ui.promptMove();
            try {
                board.updateBoard(userPosition);
            } catch (SudokuInvalidPositionException e) {
                e.printStackTrace();
            }
            ui.displayBoard(board);
            ui.promptQuit();
        }
        ui.showMessage("Solved!");
    }
}
