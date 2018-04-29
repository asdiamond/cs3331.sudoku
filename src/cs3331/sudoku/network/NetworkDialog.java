package cs3331.sudoku.network;

import javax.swing.*;
import java.awt.*;

public class NetworkDialog extends JFrame {
    final static Dimension DEFAULT_SIZE = new Dimension(310, 430);

    NetworkDialog(){
        this(DEFAULT_SIZE, "Network Operations");
    }

    NetworkDialog(Dimension dim, String title){
        super(title);
        setSize(dim);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
