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
        // Voir les règles
        System.out.println(ansi().reset().fg(Ansi.Color.MAGENTA).a("* - R : Voir les règles"));
        // Lancer une partie
        System.out.println(ansi().reset().fg(Ansi.Color.GREEN).a("* - L : Lancer une partie"));
        // Afficher le score
        System.out.println(ansi().reset().fg(Ansi.Color.YELLOW).a("* - S : Afficher le score"));
        // Sortir de l'application
        System.out.println(ansi().reset().fg(Ansi.Color.BLUE).a("* - E : Sortir de l'application").reset());

        Scanner scanner = new Scanner(System.in);
        String saisieUtilisateur = scanner.nextLine().toLowerCase();

        switch (saisieUtilisateur) {

            case "e": // Si l'utilisateur appuie sur e ou E, cela le fait sortir de l'application
                System.out.println("Vous sortez de l'application");
                break;

            case "r": // Sinon si il appuie sur r ou R, cela affiche les règles
                Regles();
                break;

            case "l": // Sinon si il appuie sur l ou L, cela lance la partie
                System.out.println("Début de la partie !");
                break;

            case "s": // Sinon si il appuie sur s ou S, cela affiche le tableau des scores
                Scores.AppelMenu(); // Rappel de la class pour afficher le menu des scores
                break;

            default: //Si l'utilisateur ne rentre pas d'option valide
                System.out.println(ansi().reset().fg(Ansi.Color.RED).a("Vous devez entrer une des options ci-dessus").reset());
                menu();

        }
    }

    public static void Regles() {

        System.out.println(ansi().fg(Ansi.Color.YELLOW).a("Bonjour et bienvenue dans [DestructChess] ! \n" +
                "Le but est simple ! Bloquez votre / vos adversaire(s) afin de décrocher la victoire !  \n" +
                "Vous débuterez la partie à côté de votre / vos ennemis, tour à tour vous devrez vous déplacer soit à la verticale, soit à l'horizontale, puis, après chaque déplacements, vous devrez détruire une case qui se trouve sur le plateau  \n" +
                "Dès qu'un joueur se retrouve bloqué par des cases détruites ou des joueurs en haut, en bas, à droite et à gauche, il perd la partie. \n" +
                "Vous êtes désormais prêts à jouer à [DestructChess] !").reset());
        menu();
    }
}


