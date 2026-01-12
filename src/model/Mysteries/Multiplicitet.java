package model.Mysteries;

/**
 * Mysterium som placerar pjäser i en X-form (diagnoler).
 * Uppfyller krav U4FG9 och U4VG2 (minst 6 mysterier)
 *
 * @author Intisaar & Maya
 * @version 1.0
 */

public class Multiplicitet extends MysteryPiece {
    /**
     * Utför mysteriets effekt: placerar pjäser i en X-form runt mysteriet
     *
     * @param board spelplanen
     * @param r rad där mysteriet ligger
     * @param c kolumn där mysteriet
     * @param activator spelaren som aktiverade mysteriet
     * @return en MysteryEffect som beskriver effekten
     */

    public MysteryEffect executeEffect(Board board, int r, int c, PlayerType activator){
        board.forceSet(r - 1, c - 1, activator);
        board.forceSet(r - 1, c +1, activator);
        board.forceSet(r + 1, c - 1, activator);
        board.forceSet(r + 1, c + 1, activator);

        return new MysterEffect(false, false,"Multiplicitet! X-form placerad för " + ".");
    }
}
