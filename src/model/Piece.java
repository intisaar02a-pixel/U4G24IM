package model;

/**
 * Interface för alla typer av pjäser på brädet.
 *
 * @author Intisaar & Maya
 */
public interface Piece {


    /**
     * Returnerar vilken spelar som äger pjäsen.
     *
     * @return ägaren av pjäsen, eller null om det är ett oaktiverat mysterium.
     */
    PlayerType getOwner();


    /**
     * Ändrar ägare på pjäsen (används vid överraskning).
     *
     * @param owner den nya ägaren
     */
    void setOwner(PlayerType owner);


    /**
     * Kontrollerar om pjäsen är ett mysterium.
     *
     * @return true om pjäsen är ett mysterium, annars false.
     */
    boolean isMystery();
}
