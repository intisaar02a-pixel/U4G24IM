package controller;

import model.*;
import view.MainFrame;
import javax.swing.JOptionPane;
import java.util.List;

/**
 * Controller-klass som hanterar spellogik och kommunikation mellan Model och view
 * uppfyller krav U4IG6 MVC, U4FG14 TURBASERAT, U4FG16 FÖRSTA DRAGET FRITT.
 *
 * @author Intisaar & Maya
 * Version 1.0
 */

public class GameController {

    private Board board;
    private Mainframe frame;
    private PlayerType currentPlayer;

    private boolean p1FirstMove;
    private boolean p2FirstMove;

    private boolean p1SkipNext;
    private boolean p2SkipNext;

    /**
     * skapar en ny GameController och startar spelet.
     */

    public GameController(){

        this.board = new Board (8, 8, 6);
        this.currentPlayer = PlayerType.PLAYER_1;
        this.p1FirstMove = true;
        this.p2FirstMove = true;
        this.p1SkipNext = false;
        this.p2SkipNext = false;

        this.frame = new MainFrame(this, 8, 8);
        updateView("Välkommen till spelet Omvälvning! Spelare 1 börjar.");
        this.frame.setVisible(true);
    }
    /**
     * Hanterar klick på en cell i spelplanen
     *
     * @param r rad som klickades
     * @param c kolumn som klickades
     */

    public void onCellClicked(int r, int c) {
        boolean isFirstMove = false;
        if (currentPlayer == PlayerType.PLAYER_1) {
            isFirstMove = p1FirstMove;
        } else {
            isFirstMove = p2FirstMove;
        }

        MoveResult result = board.placePiece(r, c, currentPlayer, isFirstMove);

        if (result.isValid() == false) {
            updateView(result.getMessage());
            return;
        }

        if (currentPlayer == PlayerType.PLAYER_1) {
            p1FirstMove = false;
        } else {
            p2FirstMove = false;
        }

        boolean grantExtraTurn = false;
        boolean skipNextTurn = false;

        ArrayList<MysteryEffect> effects = result.getMysteryEffects();
        for (int i = 0; i < effects.size(); i++) {
            MysteryEffect e = effects.get(i);
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Mysterium!",
                    JOptionPane.INFORMATION_MESSAGE);

            if (e.isExtraTurn()) {
                grantExtraTurn = true;
            }
            if (e.isSkipTurn()) {
                skipNextTurn = true;
            }
        }

        if (skipNextTurn) {
            if (currentPlayer == PlayerType.PLAYER_1) {
                p1SkipNext = true;
            } else {
                p2SkipNext = true;
            }
        }

        if (board.isGameOver()) {
            updateView("Spelet är över!");
            handleGameOver();
            return;

        }

        if (grantExtraTurn == false) {
            switchPlayer();
        }

        updateView("Drag utfört!");
    }

        /**
         * Byter spelare och hanterar "stå över tur" - effekter
         */

        private void switchPlayer(){
            if (currentPlayer == PlayerType.PLAYER_1) {
                currentPlayer == PlayerType.PLAYER_2;
            } else {
                currentPlayer = PlayerType.PLAYER_1;
            }

            if (currentPlayer == PlayerType.PLAYER_1 && p1SkipNext) {
                p1SkipNext = false;
                JOptionPane.showMessageDialog(frame, "Spelare 1 står över sin" +
                        "tur", "Tur överhoppas", JOptionPane.INFORMATION_MESSAGE);
                currentPlayer = PlayerType.PLAYER_2;
            } else if (currentPlayer == PlayerType.PLAYER_2 && p2SkipNext){
                p2SkipNext = false;
                JOptionPane.showMessageDialog(frame, "Spelare 2 står över sin tur!",
                        "Tur överhoppad!", JOptionPane.INFORMATION_MESSAGE);
                currentPlayer = PlayerType.PLAYER_1;
            }
        }

        /**
         * Hanterar slutet av spelet och visar vinnare.
         */

        private void handleGameOver(){
            int p1Score = board.getScore(PlayerType.PLAYER_1);
            int p2Score = board.getScore(PlayerType.PLAYER_2);

            String winnerText = "";
            if (p1Score > p2Score) {
                winnerText = "Spelare 1 vinner (" + p1Score + " - " + p2Score + ")";
            } else if (p2Score > p1Score) {
                winnerText = "Spelare 2 vinner (" + p2Score + " - " + p1Score + ")";
            } else {
                winnerText = "Oavgjort! (" + p1Score + " - "+ p2Score + ")";

            }
            JOptionPane.showMessageDialog(frame, winnerText, "Game over",
                    JOptionPane.INFORMATION_MESSAGE);

            String name = JOptionPane.showInputDialog(frame, "Ange namn för highscore:");
            if (name != null && name.trim().isEmpty() == false) {
                int winnerScore = (p1Score > p2Score) ? p1Score : p2Score;
                board.getHighScore().addScore(name.trim(), winnerScore);

                String topScores = board.getHighScore().getTopScoresText(10);
                JOptionPane.showMessageDialog(frame, topScores, "Highscore (Top 10)",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            int choice = JOptionPane.showConfirmDialog(frame, "Vill du starta ett nytt spel?", "Nytt Spel", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                frame.dispose();
                new GameController();
            } else {
                System.exit(0);

            }
        }

        /**
         * Uppdaterar View med aktuell spelstaus.
         *
         * @param message meddelande att visa
         */

        private void updateView(String message){
            frame.getBoardPanel().refresh(board);
            frame.getInfoPanel().update(
                    currentPlayer,
                    board.getScore(PlayerType.PLAYER_1),
                    board.getScore(PlayerType.PLAYER_2),
                    message
            );
        }




}
