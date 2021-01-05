package com.company.gameplay;

public class Case {

    private Joueur occupant;
    private boolean detruite;

    public boolean estLibre(){
        return !detruite && occupant == null;
    }

    public void detruire(){
        detruite = true;
    }

    public void liberer(){
        occupant = null;
    }

    public void setOccupant(Joueur joueur){
        occupant = joueur;
    }

    public Joueur getOccupant() {
        return occupant;
    }
}
