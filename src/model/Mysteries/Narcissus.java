package model.Mysteries;

/**
 * Mysterium som gör att spelaren står över sin nästa tur.
 * Uppfyller krav U4FG9 och U4VG2 (minst 6 mysterier).
 *
 * @author Intisaar & Maya
 * @version 1.0
 */

public class Narcissus extends MysteryPiece{
    /**
     * Utför mysteriets effekt: spelaren står över sin nästa tur
     *
     * @param board spelplanen
     * @param r rad där mysteriet ligger
     * @param c kolumn där mysteriet ligger
     * @param activator spelaren som aktiverade mysteriet
     * @return en MysterEffect som gör att spelaren står över nästa tur
     */

    public MysteryEffect executeEffect(Boars board, int r, int c, PlayerType activator){
        return new MysterEffect(false, true, "Narcissus! " + activator + "står över sin nästa tur!");
    }
}
