package com.company.gameplay;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Représente le plateau de jeu, où chaque case est représenté par une coordonné commençant en haut a gauche.
 * Cette classe peut être itérée dans un foreach
 */
public class Plateau implements Iterable<Case[]> {

    /**
     * Taille horizontale du plateau
     */
    public final int tailleX;
    /**
     * Taille verticale du plateau
     */
    public final int tailleY;

    private final Case[][] plateau;

    /**
     * Créé un plateau avec un taille précise
     */
    public Plateau(int tailleX, int tailleY) {
        this.tailleX = tailleX;
        this.tailleY = tailleY;
        plateau = new Case[tailleY][tailleX];
        // On remplie le plateau de cases vides
        for (int i = 0; i < plateau.length; i++) {
            Case[] ligne = plateau[i];
            for (int j = 0; j < ligne.length; j++) {
                ligne[j] = new Case(j, i);
            }
        }
    }

    /**
     * Créé un plateau avec la taille par default: 11 x 10
     */
    public Plateau() {
        this(11, 10);
    }

    /**
     * Récupère une case adjacent au coordonnés dans la direction spécifié
     * @param direction La direction dans la quelle rechercher
     * @param x Coordonné x, en partant de la gauche
     * @param y Coordonné y, en partant du haut
     * @return La case trouvé, sinon null
     */
    public @Nullable Case getAdjacente(Direction direction, int x, int y){
        return switch (direction){
            case GAUCHE -> getCase(--x, y);
            case BAS -> getCase(x, ++y);
            case HAUT -> getCase(x, --y);
            case DROITE -> getCase(++x, y);
        };
    }

    /**
     * Récupère la case situé au coordonnés donnés
     * @param x Coordonné x, en partant de la gauche
     * @param y Coordonné y, en partant du haut
     * @return La case trouvé, sinon null
     */
    public Case getCase(int x, int y){
        // On vérifie que les coordonnées ne sont pas en dehors du plateau
        if (x < 0 || y < 0 || y >= plateau.length || x >= plateau[y].length) {
            return null;
        }
        return plateau[y][x];
    }


    /**
     * Permet a cette classe d'être utilisé dans un foreach
     */
    @Override
    public @NotNull Iterator<Case[]> iterator() {
        return Arrays.stream(plateau).iterator();
    }
}
