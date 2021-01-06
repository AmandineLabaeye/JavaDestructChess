package com.company;

import org.fusesource.jansi.Ansi;

import java.util.Arrays;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Scores {

    public static void AppelMenu() { // Constructeur qui rappel la fonction menu et Entete afin de les afficher au rappel de la class
        menuEntete();
        menuScore();
    }

    public static void menuEntete() {

        // Message de bienvenue
        System.out.println(ansi().fg(Ansi.Color.YELLOW).a("********************************************************"));
        System.out.println("*                                                      *");
        System.out.println("*            Bienvenue sur le menu des scores          *");
        System.out.println("*                                                      *");
        System.out.println("********************************************************\n");

    }
    // Création de la fonction " menuScore "
    public static void menuScore() {

        // Tableau temporaire de la liste des joueurs et des scores correspondant au nom
        String[][] monTableau = {
                {"Thiphaine", "50"},
                {"Isabelle", "46"},
                {"Amandine", "45"},
                {"Anthony", "38"},
                {"Alan", "34"},
                {"Sandrine", "32"},
                {"Toinon", "23"},
                {"Christophe", "21"},
                {"Killian", "20"},
                {"Patrick", "12"},
        };

        // Sous menu du score

        System.out.println(ansi().fg(Ansi.Color.WHITE).a("Veuillez saisir la lettre de l'option voulu \n"));
        System.out.println(ansi().fg(Ansi.Color.BLUE).a("* - D : Voir le score (Du meilleur au moins fort)"));

        System.out.println(ansi().fg(Ansi.Color.MAGENTA).a("* - C : Voir le score croissant (Du moins fort au meilleur)"));
        System.out.println(ansi().fg(Ansi.Color.GREEN).a("* - M : Retour au menu principal"));
        System.out.println(ansi().fg(Ansi.Color.RED).a("* - E : Pour quitter l'application").reset());


        //On récupérer et demande à l'utilisateur d'entrée son choix
        Scanner scanner = new Scanner(System.in);
        String saisieUtilisateur = scanner.nextLine().toLowerCase();

        // On réalise un switch par rapport à la variable qui récupère la saisie de l'utilisateur
        switch (saisieUtilisateur) {
            // Si l'option entrée est C alors on lui affiche la liste des scores par ordre croissant
            case "c" -> {
                affichageScoreDecroissant(monTableau);
            }
            // Si l'option entrée est D alors on lui affiche la liste des scores par ordre décroissant
            case "d" -> {
                affichageScoreCroissant(monTableau);
            }
            // Si l'option entrée est M alors on lui affiche le menu principal du jeu
            case "m" -> {
                Menu.menu();
            }
            // Si l'option entrée est E alors il quitte l'application
            case "e" -> System.out.println(ansi().fg(Ansi.Color.RED).a("Vous avez quitté l'application.").reset());

            default -> {
                // On lui indique qu'il n'a pas entrée l'une des propositions affichée
                System.out.println(ansi().fg(Ansi.Color.RED).a("Vous devez entrer une des propositions demandées").reset());
                menuScore();
            }
        }

    }
    // Création de la fonction " affichageScoreDecroissant "
    public static void affichageScoreDecroissant(String[][] monTableau) {

        // Affichage des scores dans l'odre décroissant (meilleur au moins fort)

        System.out.println("Tableau de scores : (Ordre Décroissant)");

        // Boucle qui affiche le tableau multidimensionnel dans l'ordre
        for (int i = 0; i < 10; i++) {
            System.out.println(ansi().fg(Ansi.Color.BLUE).a(monTableau[i][0] + ", " + monTableau[i][1]));
        }

        // Affichage du menu score
        menuScore();

    }

    public static void affichageScoreCroissant(String[][] monTableau) {

        // Affichage des scores dans l'ordre croissant (moins fort au meilleur)

        System.out.println("Tableau de scores : (Ordre Croissant)");

        // Boucle qui affiche le tableau multidimensionnel à l'envers
        for (int i = monTableau.length - 1; i >= 0; i--) {
            System.out.println(ansi().fg(Ansi.Color.BLUE).a(monTableau[i][0] + ", " + monTableau[i][1]));
        }

        // Affichage du menu score
        menuScore();

    }

    public static void modifierLesScores() {

        // Faire l'ajout ou la modif dans le tableau et renvoyer le tableau pour retrier à chaque fois dans les fonctions d'affichages
        // Ajout et Modif (Commencement de partie et de choix du pseudo si déjà existant on actualise le score correspondant donc retrie derrière)

    }


}
