package model;

/**
 * Abstrakt basklass för alla mysterier i spelet
 * Uppfyller krav U4IG7 (abstrakt klass eller interface)
 *
 * @author Intisaar & Maya
 * @version 1.0
 */


public abstract class MysteryPiece implements Piece {

    /**
     * Hämtar ägaren av mysteriet (alltid NONE)
     *
     * @return Player.Type.NONE
     */

    public PlayerType getOwner(){
        return PlayerType.NONE;
    }
    /**
     * Sätter ägaren av mysteriet (ignoreras för mysterier).
     *
     * @param owner ägaren (ignoreras)
     */

    public void setOwner(PlayerType owner){

    }
    /**
     * Kontrollerar om pjäsen är ett mysterium.
     *
     * @return true eftersom detta är ett mysterium
     */
    public boolean isMystery(){
        return true;
    }

    /**
     * Utför mysteriets effekt när det aktiveras
     * Uppfyller krav U4FG9 (mysterier påverkar spelet)
     *
     * @param board spelplanen
     * @param r rad där mysteriet ligger
     * @param c kolumn där mysteriet ligger
     * @param activator spelaren som aktiverade mysteriet
     * @return en MysteryEffect som beskriver vad som hände
     */

    public abstract MysteryEffect executeEffect(Board board, int r, int c, PlayerType activator);
}
