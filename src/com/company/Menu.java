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
        if (saisieUtilisateur.equals("r") || saisieUtilisateur.equals("e") || saisieUtilisateur.equals("E") || saisieUtilisateur.equals("s") || saisieUtilisateur.equals("S") || saisieUtilisateur.equals("R") || saisieUtilisateur.equals("l") || saisieUtilisateur.equals("L")) {

            switch (saisieUtilisateur) {
                
                case "e":
                case "E":  // Si l'utilisateur appuie sur e ou E, cela le fait sortir de l'application
                    System.out.println("Vous sortez de l'application");
                    break;
                case "r":
                case "R":  // Sinon si il appuie sur r ou R, cela affiche les règles
                    Regles menuRegles = new Regles();
                    break;
                case "L":
                case "l":  // Sinon si il appuie sur l ou L, cela lance la partie
                    System.out.println("Début de la partie !");
                    break;
                case "S":
                case "s":  // Sinon si il appuie sur s ou S, cela affiche le tableau des scores
                    Scores scores = new Scores(); // Rappel de la class pour afficher le menu des scores

                    break;

            }

        } else {
            //Si l'utilisateur ne rentre pas d'option valide
            System.out.println(ansi().reset().fg(Ansi.Color.RED).a("Vous devez entrer une des options ci-dessus"));
            menu();
        }

    }

}


