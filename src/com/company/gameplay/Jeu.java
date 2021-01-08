package com.company.gameplay;

import com.company.EntreeUtilisateur;
import com.company.Profil;
import com.company.Scores;
import com.company.Son;

import java.util.*;
import java.util.stream.Collectors;

import static com.company.Main.appelLimite;
import static com.company.Main.s;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Class de base du gameplay
 */
public class Jeu {
    private final Son MUSIC = new Son("8-Bit-Puzzler");
    private final Son SON_PLACER = new Son("chiou");
    private final Son SON_DEBUT = new Son("game-start");

    private final Plateau plateau = new Plateau();
    private final List<Joueur> joueurs; // Tous les joueurs de la partie
    private final List<Joueur> joueurVivants; // Les joueurs encore vivants

    public Jeu(Profil[] profils) {
        // Initialisation de la liste de joueurs.
        this.joueurs = Arrays.stream(profils).map(Joueur::new).collect(Collectors.toList());
        joueurVivants = new LinkedList<>(joueurs);
    }

    /**
     * Lancer la partie
     */
    public void jouer() {
        SON_DEBUT.jouer();
        // On commence par demander à chaque joueur ou il veut se placer
        for (Joueur joueur : joueurVivants) {
            // On dessine le plateau et affiche un message
            dessiner(true);
            System.out.println(ansi().fg(joueur.couleur).a(joueur.nom).a(s(" choisissez votre case de départ")).reset());
            // Puis on attend la réponse de joueur
            Case depart = demanderCase();
            // Et on le place à la case en question
            placerJoueur(joueur, depart);
            SON_PLACER.jouer();
        }

        // Lancement de la musique
        MUSIC.jouerEnBoucle();

        // Initialise l'itérateur en commençant par un index aléatoire
        Iterator<Joueur> iterator = joueurVivants.listIterator(new Random().nextInt(joueurVivants.size()));
        boolean premierTour = true;

        // Boucle sur tous les joueurs tant qu'il en rete plus d'un en vie
        while (joueurVivants.size() > 1) {
            Joueur joueur = iterator.next();

            // Si le joueur est éliminé, il ne peux pas joué
            if (joueur.estElimine()) {
                continue;
            }

            // Début du tour, on dessine le plateau
            dessiner(false);

            if (premierTour) {
                System.out.println(ansi().fg(joueur.couleur).a(joueur.nom).a(", c'est a vous de commencer").reset());
                premierTour = false;
            }
            else {
                System.out.println(ansi().fg(joueur.couleur).a("C'est au tour de " + joueur.nom).reset());
            }

            /* ** déplacement ** */

            System.out.println(
                    s("Choisissez un direction dans laquelle vous déplacer\n") +
                    ansi().fgBrightBlack().a("<: Q\t^: Z\tv: S\t>: D").reset()
            );

            // On stoke la case actuelle et récupère la nouvelle en fonction de ce que le joueur entre
            Case ancienneCase = plateau.getCase(joueur.posX, joueur.posY);
            Case nouvelleCase = demanderDeplacement(joueur);

            // On marque l'ancienne case comme libre et place le joueur sur la nouvelle case
            ancienneCase.liberer();
            placerJoueur(joueur, nouvelleCase);

            /* ** destruction d'une case ** */

            // On redessine le plateau avec les index de cases
            dessiner(true);
            System.out.println(s("Choisissez une case a détruire"));

            // On détruit la case entré par le joueur
            demanderCase().detruire();

            // On élimine les joueurs qui doive l'être
            verifierEliminations();

            // Si on est arrivé au bout de la liste
            if (!iterator.hasNext()) {
                // Supprime tous les joueurs éliminés
                joueurVivants.removeAll(joueurVivants.stream().filter(Joueur::estElimine).collect(Collectors.toList()));
                // Retourne au début de la liste
                iterator = joueurVivants.listIterator();
            }
        }

        /* ** fin de la partie ** */

        partieTerminee(joueurVivants.get(0));
    }

    private void partieTerminee(Joueur gagnant){
        List<Joueur> perdants = joueurs.stream().filter(joueur -> joueur != gagnant).collect(Collectors.toList());
        System.out.println(
                s(ansi().fg(gagnant.couleur).a(gagnant.nom).fgBrightYellow().a(" gagne la partie!\n").reset() +
                "Il remporte " + ansi().fgBrightBlue().a(5).reset().a(" points")
        ));

        if ((perdants.size() > 1)) {
            System.out.println(("Les autres joueurs perdent " + ansi().fgBrightBlue().a(3).reset().a(" points")));
        }
        else {
            Joueur perdant = perdants.get(0);
            System.out.println(ansi().fg(perdant.couleur).a(perdant.nom).reset().a(" perd ").fgBrightBlue().a(3).reset().a(" points"));
        }

        // Ajout et retraits des points
        for (Joueur joueur : joueurs) {
            if (joueur == gagnant) {
                joueur.profil.score += 5;
            }
            else {
                joueur.profil.score -= 3;
            }
        }

        Scores.actualiserScores();

        MUSIC.stop();
    }


    /**
     * Test si le est encerclé
     * Retournera false si le joueur est éliminé
     */
    private boolean encercle(Joueur joueur) {
        if (joueur.estElimine()) {
            return false;
        }
        for (Direction direction : Direction.values()) {
            Case c = plateau.getAdjacente(direction, joueur.posX, joueur.posY);
            if (c != null && c.estLibre()) {
                return false;
            }
        }
        return true;
    }

    private void verifierEliminations() {
        // Récupération des joueurs qui ne peuvent plus se déplacer
        List<Joueur> elimines = joueurVivants.stream().filter(this::encercle).collect(Collectors.toList());

        // Si aucun joueur n'est éliminé
        if (elimines.size() == 0)
            return;

        dessiner(false);

        // Retire tous les joueurs élimés de la liste
        for (Joueur elimine : elimines) {
            elimine.eliminer();
        }

        // Affichage du message de défaite
        if (elimines.size() > 1) {
            System.out.println(
                    ansi().bgBrightYellow().fgBlack().a("COMBO!\n").bgDefault()
                    .bgBrightYellow().a(elimines.size() + s( " joueurs sont encerclés! Ils sont éliminés.")).reset()
            );
        } else {
            Joueur elimine = elimines.get(0);
            System.out.println(ansi().fg(elimine.couleur).a(elimine.nom).fgBrightRed().a(s( " est encerclé! Il est éliminé.")).reset());
        }

        // On attend une 2.5s que le joueur puisse lire le message
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ignored) { }
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
                    sb.append(s("░░"));
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

        if (caseOccupee(c)) {
            return appelLimite(this::demanderCase);
        }
        return c;
    }

    /**
     * Demande une direction au joueur via la console et récupère la case correspondante
     * Réessaie tant que le joueur ne donne pas une direction correspondante à une case valide
     * @param joueur Le joueur atour du quel cherché la case
     * @return La case se adjacente au joueurs dans la direction donnée
     */
    private Case demanderDeplacement(Joueur joueur) {
        // Demande la lettre correspondant
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
            return appelLimite(this::demanderDeplacement, joueur);
        }

        Case c = plateau.getAdjacente(direction, joueur.posX, joueur.posY);

        if (caseOccupee(c)) {
            return appelLimite(this::demanderDeplacement, joueur);
        }

        return c;
    }

    /**
     * Vérifie si une case est occupée et affiche un message adéquat si c'est le cas
     * @param c La case à tester
     */
    private boolean caseOccupee(Case c) {
        if (c == null) {
            // Si la case est en null, ça veux dire que le joueur a choisi une case en dehors du tableau
            System.out.println(ansi().fgBrightRed().a("Il n'y a pas de case ici").reset());
            return true;
        }

        if (c.estLibre()) {
            // La case est libre
            return false;
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
        return true;
    }

}
