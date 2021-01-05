package com.company.gameplay;

import java.util.Arrays;
import java.util.Iterator;

public class Plateau implements Iterable<Case[]> {

    private final Case[][] plateau;

    public Plateau() {
        plateau = new Case[11][10];
        for (Case[] ligne : plateau) {
            for (int i = 0; i < ligne.length; i++) {
                ligne[i] = new Case();
            }
        }
    }

    public Case haut(int x, int y) {
        return get(x, --y);
    }

    public Case bas(int x, int y) {
        return get(x, ++y);
    }

    public Case gauche(int x, int y) {
        return get(--x, y);
    }

    public Case droite(int x, int y) {
        return get(++x, y);
    }

    private Case get(int x, int y){
        if (x < 0 || y < 0 || y >= plateau.length || x >= plateau[y].length)
            return null;
        return plateau[y][x];
    }


    @Override
    public Iterator<Case[]> iterator() {
        return Arrays.stream(plateau).iterator();
    }
}
