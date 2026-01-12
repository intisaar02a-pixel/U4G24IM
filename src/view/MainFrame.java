package view;

import controller.GameController;
import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 * Huvudfönster för spelet Omvälvning
 * Uppfyller krav U4IG6 (MVC - View-lager);
 *
 * @authort Intisaar & Maya
 * @version 1.0
 */

public class MainFrame extends JFrame {

    private BoardPanel boardPanel;
    private InfoPanel infoPanel;

    /**
     * Skapar huvudfösntret
     *
     * @param controller spelcontrollern
     * @param rows antal rader
     * @param cols antal kolumner
     */

    public MainFrame(GameController controller, int rows, int cols){
        super("Omvälvning");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.boardPanel = new BoardPanel(controller, rows, cols);
        this.infoPanel = new InfoPanel();

        add(boardPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Hämtar spelplanspanelen.
     *
     * @return BoardPanel-objektet
     */

    public BoardPanel getBoardPanel(){
        return boardPanel;
    }

    /**
     * Hämtar informationspanelen.
     *
     * @return InfoPanel-objektet
     */

    public InfoPanel getInfoPanel(){
        return infoPanel;
    }

}
