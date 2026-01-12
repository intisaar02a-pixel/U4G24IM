package model.Mysteries;

/**
 * Mysterium som placerar pjäser i en plus-form(upp, ner, vänster, höger)
 *
 * @author Intisaar & Maya
 * @version 1.0
 */

public class AdditivaMetoder extends MysteryPiece{

    /**
     * Utför mysteriets effekt: placerar pjäser i en plus-form runt mysteriet
     *
     * @param board spelplanen
     * @param r rad där mysteriet ligger
     * @param c kolumn där mysteriet ligger
     * @param activator spelaren som aktiverade mysteriet
     * @return en MysterEffect som beskriver effekten
     */
    public MysteryEffect executeEffect(Board board, int r, int c, PlayerType activator){
        board.forceSet(r -1, c, activator);
        board.forceSet(r + 1, c, activator);
        board.forceSet(r, c - 1, activator);
        board.forceSet(r, c + 1, activator);

        return new MysteryEffect(false, false, "Additiva Metoder! Plus-form placerad för " + activator + ".");
    }
}
