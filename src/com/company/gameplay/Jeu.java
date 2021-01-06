package com.company.gameplay;

import com.company.EntreeUtilisateur;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.company.Main.s;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Class de base du gameplay
 */
public class Jeu {

    private final Plateau plateau = new Plateau();
    private final List<Joueur> joueurVivants;

    private final int nbJoueurs;

    public Jeu(Collection<Joueur> joueurs) {
        // Initialisation de la liste de joueurs.
        joueurVivants = new LinkedList<>(joueurs);
        nbJoueurs = joueurs.size();
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
            System.out.println(ansi().fg(joueur.couleur).a(joueur.nom).a(s(" choisissez votre case de départ")).reset());
            Case depart = demanderCase();
            placerJoueur(joueur, depart);
        }

        Iterator<Joueur> iterator = joueurVivants.listIterator();

        while (joueurVivants.size() > 1) {
            Joueur joueur = iterator.next();

            dessiner(false);

            System.out.println(ansi().fg(joueur.couleur).a("C'est au tour de " + joueur.nom).reset());

            if (!peuxJoue(joueur)) {
                iterator.remove();
                if (joueurVivants.size() == 1) {
                    continue;
                }

                System.out.println(ansi().bgBrightRed().fgBlack().a(s("Vous êtes encerclé! Vous ne pouvez plus vous déplacer jusqu'a la fin de la partie.")).reset());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) { }

                continue;
            }

            System.out.println(
                    s("Choisissez un direction dans laquelle vous déplacer\n") +
                    ansi().fgBrightBlack().a("<: Q\t^: Z\tv: S\t>: D")
            );

            Case ancienneCase = plateau.getCase(joueur.posX, joueur.posY);
            Case nouvelleCase = demanderDeplacement(joueur);

            ancienneCase.liberer();
            placerJoueur(joueur, nouvelleCase);

            dessiner(true);
            System.out.println(s("Choisissez une case a détruire"));

            demanderCase().detruire();

            if (!iterator.hasNext()) {
                iterator = joueurVivants.listIterator();
            }
        }

        dessiner(false);

        Joueur gagnant = joueurVivants.get(0);

        System.out.println(s(
                "Tous les joueurs sont encerclés!\n" +
                ansi().fg(gagnant.couleur).a(gagnant.nom).reset() + " gagne la partie."
        ));
    }

    /**
     * Test si le joueur peux effectué un déplacement
     */
    private boolean peuxJoue(Joueur joueur) {
        for (Direction direction : Direction.values()) {
            Case c = plateau.getAdjacente(direction, joueur.posX, joueur.posY);
            if (c != null && c.estLibre()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Dessiner le plateau dans la console
     * @param afficherCaseIndex Faut il afficher l'index des cases vides?
     */
    public void dessiner(boolean afficherCaseIndex) {
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
                        sb.append(ansi().fgBrightBlack().a(String.format("%02d", i)).reset());
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
     * @param c La case sur laquelle déplacer le joueur
     */
    private void placerJoueur(Joueur joueur, Case c) {
        c.setOccupant(joueur);
        joueur.posX = c.x;
        joueur.posY = c.y;
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

        if (!verifierCase(c)) {
            return demanderCase();
        }
        return c;
    }

    private Case demanderDeplacement(Joueur joueur) {
        char choix = EntreeUtilisateur.getChar();
        Direction direction = switch (choix) {
            case 'z' -> Direction.HAUT;
            case 'q' -> Direction.GAUCHE;
            case 's' -> Direction.BAS;
            case 'd' -> Direction.DROITE;
            default -> null;
        };

        if (direction == null) {
            System.out.println(s("Entrée incorrecte"));
            return demanderDeplacement(joueur);
        }

        Case c = plateau.getAdjacente(direction, joueur.posX, joueur.posY);

        if (!verifierCase(c)) {
            return demanderDeplacement(joueur);
        }

        return c;

    }

    private boolean verifierCase(Case c) {
        if (c == null) {
            // Si la case est en null, ça veux dire que le joueur a choisi une case en dehors du tableau
            System.out.println(ansi().fgBrightRed().a("Il n'y a pas de case ici").reset());
            return false;
        }

        if (c.estLibre()) {
            // La case est libre
            return true;
        }

        // La case n'est pa libre
        if (c.estDetruite()) {
            // Texte si la case est détruite
            System.out.println(ansi().fgBrightRed().a(s("Cette case est détruite")).reset());
        }
        else {
            // Texte si la case est occupé
            Joueur joueur = c.getOccupant();
            System.out.println(ansi().fgBrightRed().a(s("Cette case est occupé par ")).fg(joueur.couleur).a(c.getOccupant().nom).reset());
        }
        return false;
    }

}
