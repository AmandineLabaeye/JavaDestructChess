package com.company;

import org.fusesource.jansi.Ansi;

import java.io.Serializable;

/**
 * Profil d'un joueur, représenté par un nom et une couleur immuable, contient son score.
 */
public class Profil implements Serializable {

    public final String nom;
    public final Ansi.Color couleur;

    public int score;

    public Profil(String nom, Ansi.Color couleur) {
        this.nom = nom;
        this.couleur = couleur;
    }
}
