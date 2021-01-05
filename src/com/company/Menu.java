package com.company;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Menu {
    public static void enteteMenu() {
        // Message de Bienvenue
        System.out.println(ansi().reset().fg(Ansi.Color.YELLOW).a("********************************************************"));
        System.out.println("*                                                      *");
        System.out.println("*            Bienvenue sur DestructChess  !            *");
        System.out.println("*                                                      *");
        System.out.println("********************************************************");
    }

    public static void menu() {

        System.out.println();
        System.out.println(ansi().reset().fg(Ansi.Color.CYAN).a("Veuillez saisir une option ci-dessous"));
        System.out.println();
        // Menu principal
        System.out.println(ansi().reset().fg(Ansi.Color.RED).a("- M : Menu Principal"));
        // Voir les règles
        System.out.println(ansi().reset().fg(Ansi.Color.MAGENTA).a("- R : Voir les règles"));
        // Lancer une partie
        System.out.println(ansi().reset().fg(Ansi.Color.GREEN).a("- L : Lancer une partie"));
        // Afficher le score
        System.out.println(ansi().reset().fg(Ansi.Color.YELLOW).a("- S : Afficher le score"));
        // Sortir de l'application
        System.out.println(ansi().reset().fg(Ansi.Color.BLUE).a("- E : Sortir de l'application"));

        Scanner scanner = new Scanner(System.in);
        String saisieUtilisateur = scanner.nextLine();
        // Si l'utilisateur entre une des options ci dessous :
        if (saisieUtilisateur.equals("m") || saisieUtilisateur.equals("M") || saisieUtilisateur.equals("r") || saisieUtilisateur.equals("e") || saisieUtilisateur.equals("E") || saisieUtilisateur.equals("s") || saisieUtilisateur.equals("S") || saisieUtilisateur.equals("R") || saisieUtilisateur.equals("l") || saisieUtilisateur.equals("L")) {

            if (saisieUtilisateur.equals("e") || saisieUtilisateur.equals("E")) { // Si l'utilisateur appuie sur e ou E, cela le fait sortir de l'application
                System.out.println("Vous sortez de l'application");
            } else if (saisieUtilisateur.equals("m") || saisieUtilisateur.equals("M")) { // Sinon si il appuie sur m ou M, cela le fait revenir au menu principal
                System.out.println("Vous revenez au menu principal");
            } else if (saisieUtilisateur.equals("r") || saisieUtilisateur.equals("R")) { // Sinon si il appuie sur r ou R, cela affiche les règles
                affichageRegles();
            } else if (saisieUtilisateur.equals("L") || saisieUtilisateur.equals("l")) { // Sinon si il appuie sur l ou L, cela lance la partie
                System.out.println("Début de la partie !");
            } else if (saisieUtilisateur.equals("S") || saisieUtilisateur.equals("s")) { // Sinon si il appuie sur s ou S, cela affiche le tableau des scores
                System.out.println("Affichage du score...");
            }

        } else {
            //Si l'utilisateur ne rentre pas d'option valide
            System.out.println(ansi().reset().fg(Ansi.Color.RED).a("Vous devez entrer une des options ci-dessus"));
            menu();
        }

    }

    public static void affichageRegles() { // Affichage dans la console des règles

        System.out.println(ansi().reset().fg(Ansi.Color.CYAN).a("Bonjour et Bienvenue dans DestructChess ! Voici les règles du jeu : "));
        System.out.println();
        System.out.println(ansi().reset().fg(Ansi.Color.RED).a("Au début de la partie, votre pion sera placé à côté de celui de votre adversaire, le but est simple, gagner la partie en bloquant au maximum votre ennemi."));
        System.out.println("Vous jouerez tour à tour et vous disposerez de 15 secondes pour jouer le vôtre.");
        System.out.println("A chaque tours vous devrez déplacer soit horizontalement soit verticalement votre pion, puis, vous devrez détruire une case sur le plateau");
        System.out.println("Le premier pion étant bloqué par une case au dessus, une case en dessous, une case à droite et une case à gauche aura perdu !");
        System.out.println("Choisissez bien vos déplacements ! Vous êtes prêt ? A vos marques, Prêt ? Jouez !");
        menu();

    }
}


