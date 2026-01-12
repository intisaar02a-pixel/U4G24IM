package model;

/**
 * Beskriver hur en cell på spelplanen ska visas i GUI.
 * Enligt krav U4IG6 får enums delas mellan model och view.
 *
 * @author Intisaar & Maya
 */

public enum CellState {
    EMPTY, //Tom ruta
    MYSTERY, //Oaktiverat mysterie
    P1, //Spelare 1
    P2 //Spelare 2
}
