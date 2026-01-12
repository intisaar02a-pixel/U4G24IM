package model;

/**
 * Representerar effekten av ett aktiverat mysterium.
 *
 * @author Intisaar & Maya
 */
public class MysteryEffect {

    private boolean extraTurn;
    private boolean skripTurn;
    private String message;


    /**
     * Skapar en ny mysterieeffekt.
     *
     * @param extraTurn om spelaren får en extra tur
     * @param skipTurn om spelaren ska stå över sin nästa
     * @param message beskrivning av effekten
     */
    public MysteryEffect(boolean extraTurn, boolean skipTurn, String message){
        this.extraTurn = extraTurn;
        this.skripTurn = skipTurn;
        this.message = message;
    }


    /**
     * Kontrollerar om effekten ger en extra tur.
     *
     * @return true om spelaren får spela igen
     */
    public boolean isExtraTurn(){
        return extraTurn;
    }


    /**
     * Kontrollerar om effekten gör att spelaren står över sin nästa tur.
     *
     * @return true om spelaren ska skippa nästa tur
     */
    public boolean isSkripTurn(){
        return skipTurn;
    }


    /**
     * Hämtar beskrivningen av effekten.
     *
     * @return meddelande som förklarar vad som hände
     */
    public String getMessage(){
        return message;
    }
}
