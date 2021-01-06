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
    public static final int TAILLE_X = 11;
    /**
     * Taille verticale du plateau
     */
    public static final int TAILLE_Y = 10;

    private final Case[][] plateau;

    public Plateau() {
        plateau = new Case[TAILLE_Y][TAILLE_X];
        // On remplie le plateau de cases vides
        for (int i = 0; i < plateau.length; i++) {
            Case[] ligne = plateau[i];
            for (int j = 0; j < ligne.length; j++) {
                ligne[j] = new Case(j, i);
            }
        }
    }

    /**
     * Récupère une case adjacent au coordonnés dans la direction spécifié
     * @param direction La direction dans la quel recherché
     * @param x Coordonné x, en partant de la gauche
     * @param y Coordonné y, en partant du haut
     * @return La case trouvé, sinon null
     */
    public @Nullable Case getAdjacente(Direction direction, int x, int y){
        return switch (direction){
            case GAUCHE -> gauche(x, y);
            case BAS -> bas(x, y);
            case HAUT -> haut(x, y);
            case DROITE -> droite(x, y);
        };
    }

    /**
     * Récupère la case situé en haut des coordonnés donnés
     * @param x Coordonné x, en partant de la gauche
     * @param y Coordonné y, en partant du haut
     * @return La case trouvé, sinon null
     */
    public @Nullable Case haut(int x, int y) {
        return getCase(x, --y);
    }

    /**
     * Récupère la case situé en bas des coordonnés donnés
     * @param x Coordonné x, en partant de la gauche
     * @param y Coordonné y, en partant du haut
     * @return La case trouvé, sinon null
     */
    public @Nullable Case bas(int x, int y) {
        return getCase(x, ++y);
    }

    /**
     * Récupère la case situé a gauche des coordonnés donnés
     * @param x Coordonné x, en partant de la gauche
     * @param y Coordonné y, en partant du haut
     * @return La case trouvé, sinon null
     */
    public @Nullable Case gauche(int x, int y) {
        return getCase(--x, y);
    }

    /**
     * Récupère la case situé a droite des coordonnés donnés
     * @param x Coordonné x, en partant de la gauche
     * @param y Coordonné y, en partant du haut
     * @return La case trouvé, sinon null
     */
    public @Nullable Case droite(int x, int y) {
        return getCase(++x, y);
    }

    /**
     * Récupère la case situé au coordonnés donnés
     * @param x Coordonné x, en partant de la gauche
     * @param y Coordonné y, en partant du haut
     * @return La case trouvé, sinon null
     */
    public Case getCase(int x, int y){
        // On vérifie que les coordonnés ne sont pas en dehors du plateau
        if (x < 0 || y < 0 || y >= plateau.length || x >= plateau[y].length)
            return null;
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
