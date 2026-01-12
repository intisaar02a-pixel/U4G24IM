package model.Mysteries;

/**
 * Mysterium som tömmer en 3x3-yta runt sig själv.
 * Uppfyller krav U4FG9 och U4VG2 (minst 6 mysterier)
 *
 * @author Intisaar & Maya
 * @version 1.0
 */

public class AvgrundsVral extends MysterPiece {
    /**
     * Utför mysteriets effekt: rensar en 3x3-yta runt mysteriet.
     *
     * @param board spelplanen
     * @param r rad där mysteriet ligger
     * @param c kolumn där mysteriet ligger
     * @param activator spelaren som aktiverade mysteriet
     * @return en MysteryEffect som beskriver effekten
     */

    public MysteryEffect executeEffect(Boars board, int r, int c, PlayerType activator){
        for (int dr = -1; dr <= 1; dr++){
            for (int dc = -1; dc <= 1; dc++){
                board.forceClear(r + dr, c + dc);
            }
        }
        return new MysteryEffect(false, false, "Avgrundsvårl! Rensade en 3x3-yta");
    }
}
