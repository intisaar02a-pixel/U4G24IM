package model;

/**
 * Representerar en vanlig spelarpjäs på brädet.
 * Dessa pjäser räknas i poängen och kan "vändas" vid överraskning.
 *
 * @author Intisaar & Maya
 */
public class PlayerPiece implements Piece {


    /**
     * Spelaren som äger denna pjäs
     *
     */
    private PlayerType owner;


    /**
     * Skapar en ny spelpjäs.
     *
     * @param owner spelaren som äger pjäsen
     */
    public PlayerPiece(PlayerType owner){
        this.owner = owner;
    }


    /**
     * Hämtar ägaren av pjäsen.
     *
     * @return spelaren som äger pjäsen
     */
    @Override
    public PlayerType getOwner(){
        return owner;
    }


    /**
     * Sätter ägaren av pjäsen.
     *
     * @param owner den nya ägaren
     */
    public void setOwner(PlayerType owner){
        this.owner = owner;
    }


    /**
     * Kontrollerar om pjäsen är ett mysterium
     *
     * @return false eftersom detta är en vanlig spelpjäs.
     */
    public boolean isMystery(){
        return false;
    }
}
