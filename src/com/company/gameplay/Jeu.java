package com.company.gameplay;

import com.company.EntreeUtilisateur;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;
import static com.company.Main.s;

/**
 * Class de base du gameplay
 */
public class Jeu {

    private final Plateau plateau = new Plateau();
    private final List<Joueur> joueurVivants;

    public Jeu(Collection<Joueur> joueurs) {
        /* Initialisation de la liste de joueurs.
         */
        joueurVivants = new LinkedList<>(joueurs);
    }

    public Jeu(Joueur[] joueurs) {
        // Si un array est passé au constructeur, on le converti en liste
        this(Arrays.asList(joueurs));
    }

    /**
     * Lancer la partie
     */
    public void jouer() {
        // On commence par demander à chaque joueur ou il veut se placer
        for (Joueur joueur : joueurVivants) {
            dessiner(true);
            System.out.println(ansi().bg(joueur.couleur).a(joueur.nom).a(s(" choisissez votre case de départ")).reset());
            Case depart = demanderCase();
            placerJoueur(joueur, depart.x, depart.y);
        }

        dessiner(false);
    }

    /**
     * Dessiner le plateau dans la console
     * @param afficherCaseIndex Faut il afficher l'index des cases vides?
     */
    public void dessiner(boolean afficherCaseIndex){
        StringBuilder sb = new StringBuilder();

        int i = 1; // Index de la case

        // Pour chaque ligne du plateau
        for (Case[] ligne : plateau) {

            // Dessin des séparations entre les lignes
            sb.append("-".repeat(ligne.length * 5 + 1)).append('\n');

            // Pour chaque case de la ligne
            for (Case c : ligne) {
                // Séparation entre les cases
                sb.append("| ");

                // Dessin si la case est libre
                if (c.estLibre()) {
                    if (afficherCaseIndex){
                        // On affiche l'index de la case si besoin
                        sb.append(String.format("%02d", i));
                    }
                    else {
                        // Sinon on affiche des espaces
                        sb.append("  ");
                    }
                }
                else if(c.estDetruite()) {
                    // Dessin si la case est détruite
                    sb.append(new String("░░".getBytes(StandardCharsets.UTF_8)));
                }
                else {
                    // Dessin si la case est occupé par un joueur
                    Joueur joueur = c.getOccupant();
                    sb.append(ansi().bg(joueur.couleur).a(joueur.nom.substring(0, 2)).reset());
                }

                // Ajout d'un espace après la case, sauf si on a affiché un index à 3 chiffres
                if (!afficherCaseIndex || i < 100 || !c.estLibre())
                    sb.append(' ');

                i++;
            }
            sb.append("|\n");

        }
        // Dessin de la dernière ligne
        sb.append("-".repeat(Plateau.TAILLE_X * 5 + 1));

        // Envoie du texte dans la console
        System.out.println(sb.toString());
    }

    /**
     * Place un joueur à une cordonné et marque la case en question comme occupé par le joueur
     * @param joueur Le joueur a placer
     * @param x La coordonné x où placer le joueur
     * @param y La coordonné y où placer le joueur
     */
    private void placerJoueur(Joueur joueur, int x, int y) {
        plateau.getCase(x, y).setOccupant(joueur);
        joueur.posX = x;
        joueur.posY = y;
    }

    /**
     * Demander une case au joueur via la console
     * Réessaie tant que le joueur ne donne pas une case valide
     * @return La case correspondant à l'index donné par le joueur
     */
    private Case demanderCase() {
        // Demande l'index de la case au joueur
        int index = EntreeUtilisateur.getInt() - 1;

        // Calcul les cordonné de la case
        int x = index % Plateau.TAILLE_X;
        int y = index / Plateau.TAILLE_X;

        Case c = plateau.getCase(x, y);

        if (c == null) {
            // Si l'index est en dehors du plateau, redemander
            System.out.println(ansi().fgBrightRed().a("Cette case n'existe pas").reset());
            return demanderCase();
        }

        if (c.estLibre()) {
            // Si la case est libre, on la renvoie
            return c;
        }

        // Sinon on redemande
        if (c.estDetruite()) {
            // Texte si la case est détruite
            System.out.println(ansi().fgBrightRed().a(s("Cette case est détruite")).reset());
        }
        else {
            // Texte si la case est occupé
            Joueur joueur = c.getOccupant();
            System.out.println(ansi().fgBrightRed().a(s("Cette case est occupé par ")).fg(joueur.couleur).a(c.getOccupant().nom).reset());
        }
        return demanderCase();
    }

}
