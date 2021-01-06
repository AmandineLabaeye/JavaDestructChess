package com.company.gameplay;

import com.company.EntreeUtilisateur;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;
import static com.company.Main.s;

public class Jeu {

    private final Plateau plateau = new Plateau();
    private final List<Joueur> joueurVivants;

    public Jeu(Collection<Joueur> joueurs) {
        joueurVivants = new LinkedList<>(joueurs);

    }

    public Jeu(Joueur[] joueurs) {
        this(Arrays.asList(joueurs));
    }

    /**
     * Lancer la partie
     */
    public void jouer() {
        for (Joueur joueur : joueurVivants) {
            dessiner(true);
            System.out.println(ansi().bg(joueur.couleur).a(joueur.nom).a(" choisissez votre case de départ").reset());
            Case depart = demanderCase();
            placerJoueur(joueur, depart.x, depart.y);
        }

        dessiner(false);
    }

    /**
     * Dessiner le plateau dans la console
     * @param afficherCaseIndex faut il afficher l'index des cases vides?
     */
    public void dessiner(boolean afficherCaseIndex){
        StringBuilder sb = new StringBuilder();

        int i = 1;

        for (Case[] ligne : plateau) {
            sb.append("-".repeat(ligne.length * 5 + 1)).append('\n');
            for (Case c : ligne) {

                sb.append("| ");
                if (c.estLibre()) {
                    if (afficherCaseIndex){
                        sb.append(String.format("%02d", i));
                    }
                    else {
                        sb.append("  ");
                    }
                }
                else if(c.estDetruite()) {
                    sb.append(new String("░░".getBytes(StandardCharsets.UTF_8)));
                }
                else {
                    Joueur joueur = c.getOccupant();
                    //sb.append(ansi().fg(c.getOccupant().couleur).a(new String("██".getBytes(StandardCharsets.UTF_8))).reset());
                    sb.append(ansi().bg(joueur.couleur).a(joueur.nom.substring(0, 2)).reset());
                }

                if (!afficherCaseIndex || i < 100 || !c.estLibre())
                    sb.append(' ');

                i++;
            }
            sb.append("|\n");

        }
        sb.append("-".repeat(Plateau.TAILLE_X * 5 + 1));

        System.out.println(sb.toString());
    }

    private void placerJoueur(Joueur joueur, int x, int y) {
        plateau.getCase(x, y).setOccupant(joueur);
        joueur.posX = x;
        joueur.posY = y;
    }

    private Case demanderCase() {
        int index = EntreeUtilisateur.getInt() - 1;

        int x = index % Plateau.TAILLE_X;
        int y = index / Plateau.TAILLE_X;

        Case c = plateau.getCase(x, y);

        if (c == null) {
            System.out.println(ansi().fgBrightRed().a("Cette case n'existe pas").reset());
            return demanderCase();
        }

        if (c.estLibre()) {
            return c;
        }

        if (c.estDetruite()) {
            System.out.println(ansi().fgBrightRed().a(s("Cette case est détruite")).reset());
        }
        else {
            Joueur joueur = c.getOccupant();
            System.out.println(ansi().fgBrightRed().a(s("Cette case est occupé par ")).fg(joueur.couleur).a(c.getOccupant().nom).reset());
        }
        return demanderCase();
    }

}
