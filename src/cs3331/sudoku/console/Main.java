package cs3331.sudoku.console;

import cs3331.sudoku.model.Board;
import cs3331.sudoku.model.Square;

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
            } catch (IllegalArgumentException e) {
                ui.promptIllegalArgument();
            }
        } while (!validsize);

        while (!board.isSolved()) {
            ui.displayBoard(board);
            Square userPosition = ui.promptMove();
            if (userPosition == null) {
                //user wants to quit
                if (ui.promptQuit()) break;
            }
            board.updateBoard(userPosition);
        }
        //couldve just quit and not solved
        if(board.isSolved()) ui.showMessage("Solved!");
        ui.showMessage("Goodbye!");
        ui.exit();
    }
}
