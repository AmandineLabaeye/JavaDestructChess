package com.company.gameplay;

import org.fusesource.jansi.Ansi;

public class Joueur {

    public final String nom;
    public final Ansi.Color couleur;

    public int posX;
    public int posY;

    public Joueur(String nom, Ansi.Color couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }
}
