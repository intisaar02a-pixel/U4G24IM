package view;

import model.PlayerType;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

/**
 * Panel som visar spelinformation.
 * Upfyller krav U4FG15 (visa poäng).
 *
 * @author Intisaar & Maya
 * @version 1.0
 */

public class InfoPanel extends JPanel {

    private JLabel turnLabel;
    private JLabel p1Label;
    private JLabel p2Label;
    private JLabel msgLabel;

    /**
     * Skapar en ny InfoPanel
     */

    public InfoPanel(){
        setLayout(new FlowLayout(FlowLayout.LEFT, 16, 8));

        turnLabel = new JLabel("Tur :");
        p1Label = new JLabel("P1: 0");
        p2Label = new JLABEL ("P2: 0");
        msgLabel = new JLABEL("");

        add(turnLabel);
        add(p1Label);
        add(p2Label);
        add(msgLabel);
    }

    /**
     * Uppdaterar informationspanelen
     *
     * @param current nuvarande spelare
     * @param p1Score spelare 1:s poäng
     * @param p2Score spelare 2:s poäng
     * @param message statusmeddelande
     */

    public void update (PlayerType current, int p1Score, int p2Score, String message){
        turnLabel.setText("Tur: " + current);
        p1Label.setText("P1: " + p1Score);
        p2Label.setText("P2: " + p2Score);
        msgLabel.setText(message);
    }
}
