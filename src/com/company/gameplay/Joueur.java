package com.company.gameplay;

import org.fusesource.jansi.Ansi;

/**
 * Joueur représenté par un nom et une couleur immuable.
 * Contient également des cordonnées.
 */
public class Joueur {

    public final String nom;
    public final Ansi.Color couleur;

    public int score;

    public int posX;
    public int posY;

    public Joueur(String nom, Ansi.Color couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }
}
