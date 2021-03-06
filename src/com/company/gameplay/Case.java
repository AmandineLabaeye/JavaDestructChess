package com.company.gameplay;

/**
 * Une case du plateau, qui peut être libre, détruite ou occupée.
 */
public class Case {

    private Joueur occupant;
    private boolean detruite;

    public final int x;
    public final int y;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return true si la case n'est ni occupé ni détruite
     */
    public boolean estLibre() {
        return !detruite && occupant == null;
    }

    public boolean estDetruite() {
        return detruite;
    }

    public void detruire() {
        detruite = true;
    }

    public void liberer() {
        occupant = null;
    }

    public void setOccupant(Joueur joueur) {
        occupant = joueur;
    }

    public Joueur getOccupant() {
        return occupant;
    }
}
