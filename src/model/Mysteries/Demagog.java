package model.Mysteries;

/**
 * Mysterium som konverterar orthogonala grannar till aktiverande spelarens färg
 *
 * Uppfyller krav U4FG9 och UFVG2 (minst 6 mysterier).
 *
 * @author Intisar & Maya
 * @version 1.0
 */

public class Demagog extends MysteryPiece {
    /**
     * Utför msyteriet effekt: konverterar orthogonala grannar till aktiverande spelaren.
     *
     * @param board spelplanen
     * @param r rad där mysteriet ligger
     * @param c kolumn där mysteriet ligger
     * @param activitator spelaren som aktiverade msyteriet
     * @return en MysteryEffect som beskriver effekten
     */

    public MysteryEffect executeEffect(Board board, int r, int c, PlayerType activator){
        board.forceSet(r - 1, c, activatator);
        board.forceSet(r + 1, c, activator);
        board.forceSet(r, c - 1, activator);
        board.forceSet(r, c + 1, activator);

        return new MysterEffect(false,false, "Demagog! Lockade närliggande rutor till " + activator + "!");

    }
}
