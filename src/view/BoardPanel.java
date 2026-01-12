package view;

import  controller.GameController;
import model.Board;
import model.CellState;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

/**
 * Panel som visar spelpnanen som ett rutnät av kanppar.
 * Uppfyller krav U4FG3 (tydlig visning av tillstånd).
 *
 * @author Intisaar & Maya
 * @version 1.0
 */

public class BoardPanel extends JPanel{

    private JButton[][] buttons;
    private int rows;
    private int cols;

    /**
     * Spar en ny BoardPanel
     *
     * @param controller speelcontrollern
     * @param rows antal rader
     * @param cols antal kolumner
     */

    public BoardPanel (GameController controller, int rows, int cols){

        this.rows = rows;
        this.cols = cols;
        this.buttons = new JButton[rows][cols];

        setLayout(new GridLayout(rows, cols, 2, 2));

        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                JButton b = new JButton();
                b.setPreferredSize(new Dimension(60, 60));

                int finalR = r;
                int finalC = c;
                b.addActionListener (e -> controller.onCellClicked(finalR, finalC));

                buttons[r][c] = b;
                add(b);
            }
        }
    }

    /**
     * Uppdaterar visningen av spelplanen
     *
     * @param board spelplanen att visa
     */

    public void refresh (Board board){
    for (int r = 0; r < rows; r++){
        for (int c = 0; c < cols; c++){
            CellState state = board.getCellState(r, c);
            JButton b = buttons [r][c];

            if (state == CellState.EMPTY) {
                b.setText("");
                b.setBackground(Color.LIGHT_GRAY);
            } else if (state == CellState.MYSTERY) {
                b.setText("?");
                b.setBackground(Color.YELLOW);
            } else if (state== CellState.P1){
                b.setText("2");
                b.setBackground(Color.PINK);
            }
        }
    }
    }
}
