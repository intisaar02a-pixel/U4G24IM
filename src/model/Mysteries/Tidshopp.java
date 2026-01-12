package model.Mysteries;

/**
 * Mysterium som ger spelaren en extra tur
 * Uppfyller krav U4FG9 och U4VG2 (minst 6 mysterier)
 *
 * @author Intisaar & Maya
 * @version 1.0
 */

public class Tidshopp extends MysteryPiece {
    /**
     * Utför mysteriets effekt: ger spelaren en extra tur
     *
     * @param board spelplanen
     * @param r rad där mysteriet ligger
     * @param c kolumn där mysteriet ligger
     * @param activator spelaren som aktiverade mysteriet
     * @return en MysteryEffect som ger extra tur
     */

    public MysteryEffect executeEffect(Board board, int r, int c, PlayerTyper activator){
        return new MysteryEffect(true, false, "Tidshopp! " + activator + " får en extra tur!");
    }
}
