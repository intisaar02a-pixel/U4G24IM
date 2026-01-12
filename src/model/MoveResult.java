package model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Representerar resultatet av ett drag.
 * Används för kommunikation från Model till Controller.
 *
 * @author Intisaar & Maya
 */
public class MoveResult {

    private boolean valid;
    private String message;
    private ArrayList<MysteryEffect> effects;


    /**
     * Skapar ett nytt MoveResult.
     *
     * @param valid om draget var giltigt
     * @param message beskrivande meddelande
     * @param effects lista med mysterieeffekter
     */
    private MoveResult(boolean valid, String message, Array<MysteryEffect> effects){
        this.valid = valid;
        this.message = message;
        this.effects = effects;
    }


    /**
     * Skapar ett giltigt MoveResult.
     *
     * @param effects lista med mysterieeffekter
     * @return ett giltigt MoveResult
     */
    public static MoveResult ok(ArrayList<MysteryEffect> effects){
        return new MoveResult(true, "Drag utfört.", effects);
    }


    /**
     * Skapar ett ogiltigt MoveResult.
     *
     * @param message felmeddelande
     * @return ett ogiltigt MoveResult
     */
    public static MoveResult invalid(String message){
        return new MoveResult(false, message, new ArrayList<>());
    }


    /**
     * Kontrollerar om draet var giltigt.
     *
     * @return true om draget var giltigt
     */
    public boolean isValid(){
        return valid;
    }


    /**
     * Hämtar meddelandet om draget.
     *
     * @return beskrivande meddelande
     */
    public String getMessage(){
        return message;
    }


    /**
     * Hämtar listan med mysterieeffekter.
     *
     * @return lista med MysteryEffect-objekt
     */
    public ArrayList<MysteryEffect> getMysteryEffects(){
        return effects;
    }
}
