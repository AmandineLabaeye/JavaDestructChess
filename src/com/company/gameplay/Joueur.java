package com.company.gameplay;

import org.fusesource.jansi.Ansi;

import java.io.Serializable;

/**
 * Joueur représenté par un nom et une couleur immuable, contient son score.
 * Contient également des cordonnées.
 */
public class Joueur implements Serializable {

    public final String nom;
    public final Ansi.Color couleur;

    public int score;

    //transient: ignoré lors de la sérialisation
    public transient int posX;
    public transient int posY;

    public Joueur(String nom, Ansi.Color couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }
}
