package com.company;

import org.fusesource.jansi.Ansi;

import java.util.Arrays;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Scores {

    public Scores() { // Constructeur qui rappel la fonction menu et Entete afin de les afficher au rappel de la class
        menuEntete();
        menuScore();
    }

    public static void menuEntete() {

        // Message de bienvenue
        System.out.println(ansi().reset().fg(Ansi.Color.YELLOW).a("********************************************************"));
        System.out.println("*                                                      *");
        System.out.println("*            Bienvenue sur le menu des scores          *");
        System.out.println("*                                                      *");
        System.out.println("********************************************************");

    }

    public static void menuScore() {

        // Sous menu du score
        System.out.println(ansi().reset().fg(Ansi.Color.WHITE).a("* Veuillez saisir la lettre de l'option voulu *"));
        System.out.println(ansi().reset().fg(Ansi.Color.MAGENTA).a("* - C : Voir le score croissant               *"));
        System.out.println(ansi().reset().fg(Ansi.Color.BLUE).a("* - D : Voir le score décroissant             *"));
        System.out.println(ansi().reset().fg(Ansi.Color.GREEN).a("* - M : Retour au menu principal              *"));
        System.out.println(ansi().reset().fg(Ansi.Color.RED).a("* - E : Pour quitter l'application            *"));


        //On récupérer et demande à l'utilisateur d'entrée son choix
        Scanner scanner = new Scanner(System.in);
        String saisieUtilisateur = scanner.nextLine();

        // On vérifie l'entrée de l'utilisateur, on vérifie qu'il entre au moins une option parmis toutes celle proposé
        if (saisieUtilisateur.equals("c") || saisieUtilisateur.equals("d") || saisieUtilisateur.equals("e") || saisieUtilisateur.equals("m") || saisieUtilisateur.equals("M") || saisieUtilisateur.equals("C") || saisieUtilisateur.equals("D") || saisieUtilisateur.equals("E")) {

            // On réalise un switch par rapport à la variable qui récupère la saisie de l'utilisateur
            switch (saisieUtilisateur) {
                // Si l'option entrée est C alors on lui affiche la liste des scores par ordre croissant
                case "c", "C" -> {
                    System.out.println("Affichage du score par ordre croissant");
                }
                // Si l'option entrée est D alors on lui affiche la liste des scores par ordre décroissant
                case "d", "D" -> {
                    System.out.println("Affichage du score par ordre décroissant");
                }
                // Si l'option entrée est M alors on lui affiche le menu principal du jeu
                case "m", "M" -> {
                    Menu menu = new Menu();
                    menu.menu();
                }
                // Si l'option entrée est E alors il quitte l'application
                case "e", "E" -> System.out.println(ansi().reset().fg(Ansi.Color.RED).a("Vous avez quitté l'application."));
            }
        } else {
            // On lui indique qu'il n'a pas entrée l'une des propositions affichée
            System.out.println(ansi().reset().fg(Ansi.Color.RED).a("Vous devez entrer une des propositions demandées"));
            menuScore();
        }
    }


}
