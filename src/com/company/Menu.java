package com.company;

import com.company.gameplay.Jeu;
import com.company.gameplay.Joueur;
import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Menu {

    public static void enteteMenu() {
        // Message de Bienvenue
        System.out.println(ansi().fg(Ansi.Color.YELLOW).a("********************************************************"));
        System.out.println("*                                                      *");
        System.out.println("*            Bienvenue sur DestructChess  !            *");
        System.out.println("*                                                      *");
        System.out.println("********************************************************\n");
    }
    // Création de la fonction " menu "
    public static void menu() {

        System.out.println(ansi().fg(Ansi.Color.CYAN).a("Veuillez saisir une option ci-dessous\n"));
        // Voir les règles
        System.out.println(ansi().fg(Ansi.Color.MAGENTA).a("* - R : Voir les règles"));
        // Lancer une partie
        System.out.println(ansi().fg(Ansi.Color.GREEN).a("* - L : Lancer une partie"));
        // Afficher le score
        System.out.println(ansi().fg(Ansi.Color.YELLOW).a("* - S : Afficher le score"));
        // Sortir de l'application
        System.out.println(ansi().fg(Ansi.Color.BLUE).a("* - E : Sortir de l'application").reset());
        // Initialisation du scanner
        Scanner scanner = new Scanner(System.in);
        // Récupération de la saisie de l'utilisateur
        String saisieUtilisateur = scanner.nextLine().toLowerCase();
        // Création d'un switch pour la saisie de l'utilisateur
        switch (saisieUtilisateur) {

            case "e": // Si l'utilisateur appuie sur e ou E, cela le fait sortir de l'application
                System.out.println("Vous sortez de l'application");
                break;

            case "r": // Sinon si il appuie sur r ou R, cela affiche les règles
                Regles();
                break;

            case "l": // Sinon si il appuie sur l ou L, cela lance la partie
                System.out.println("Début de la partie !");
                nomUtilisateur();
                break;

            case "s": // Sinon si il appuie sur s ou S, cela affiche le tableau des scores
                Scores scores = new Scores(); // Rappel de la class pour afficher le menu des scores
                break;

            default: //Si l'utilisateur ne rentre pas d'option valide
                System.out.println(ansi().fg(Ansi.Color.RED).a("Vous devez entrer une des options ci-dessus").reset());
                menu();

        }
    }

    public static void Regles() {
        // Affichage des règles dans la console
        System.out.println(ansi().fg(Ansi.Color.YELLOW).a("Bonjour et bienvenue dans [DestructChess] ! \n" +
                "Le but est simple ! Bloquez votre / vos adversaire(s) afin de décrocher la victoire !  \n" +
                "Vous débuterez la partie à côté de votre / vos ennemis, tour à tour vous devrez vous déplacer soit à la verticale, soit à l'horizontale, puis, après chaque déplacements, vous devrez détruire une case qui se trouve sur le plateau  \n" +
                "Dès qu'un joueur se retrouve bloqué par des cases détruites ou des joueurs en haut, en bas, à droite et à gauche, il perd la partie. \n" +
                "Vous êtes désormais prêts à jouer à [DestructChess] !").reset());
        // Rappel du menu
        menu();


    }
    // Création d'une nouvelle fonction qui permet de choisir le pseudo et la couleur des joueurs
    public static void nomUtilisateur() {
        // Initialisation du tableau des joueurs
        Joueur[] joueurs = new Joueur[2];
        // Boucle qui permet de créer deux utilisateurs ( pseudo + couleur )
        for (int i = 0; i < 2; i++) {
            // Création d'une variable pour vérifier la taille du pseudo de l'utilisateur
            String pseudoUtilisateur = pseudoVerif(i + 1);
            // Création d'une variable pour vérifier la couleur de l'utilisateur
            Ansi.Color couleur = couleurVerif(pseudoUtilisateur);
            // Ajout du nom et de la couleur dans le tableau
            joueurs[i] = new Joueur(pseudoUtilisateur, couleur);
        }
        // Affichage du plateau
        new Jeu(joueurs).jouer();

    }
    // Création d'une fonction de vérification pour la couleur
    public static Ansi.Color couleurVerif(String pseudoUtilisateur) {
        // Initialisation du scanner
        Scanner scanner = new Scanner(System.in);
        // Affichage du menu de couleur
        System.out.println("Merci " + pseudoUtilisateur + ", choisissez la couleur de votre pion");
        System.out.println(ansi().fg(Ansi.Color.BLUE).a("* - B : Bleu"));
        System.out.println(ansi().fg(Ansi.Color.GREEN).a("* - V : Vert"));
        System.out.println(ansi().fg(Ansi.Color.YELLOW).a("* - J : Jaune"));
        System.out.println(ansi().fg(Ansi.Color.RED).a("* - R : Rouge"));
        System.out.println(ansi().fg(Ansi.Color.CYAN).a("* - C : Cyan"));
        System.out.println(ansi().fg(Ansi.Color.BLACK).a("* - N : Noir"));
        System.out.println(ansi().fg(Ansi.Color.WHITE).a("* - W : Blanc"));
        System.out.println(ansi().fg(Ansi.Color.MAGENTA).a("* - M : Magenta").reset());
        // Récupération des saisies de l'utilisateur dans le scanner et transformation en majuscule
        String couleurUtilisateur = scanner.nextLine().toUpperCase();
        // Initialisation de la variable " couleur "
        Ansi.Color couleur = Ansi.Color.WHITE;
        // Switch qui permet de vérifier la saisie de l'utilisateur
        switch (couleurUtilisateur) {
            case "B", "BLEU" -> couleur = Ansi.Color.BLUE; // Si l'utilisateur tape B ou BLEU alors on définie la couleur Bleu
            case "V", "VERT" -> couleur = Ansi.Color.GREEN; // // alors on définie la couleur Verte
            case "J", "JAUNE" -> couleur = Ansi.Color.YELLOW; // // alors on définie la couleur Jaune
            case "R", "ROUGE" -> couleur = Ansi.Color.RED; // // alors on définie la couleur Rouge
            case "C", "CYAN" -> couleur = Ansi.Color.CYAN; // // alors on définie la couleur Cyan
            case "N", "NOIR" -> couleur = Ansi.Color.BLACK; // // alors on définie la couleur noire
            case "W", "BLANC" -> couleur = Ansi.Color.WHITE; // // alors on définie la couleur blanche
            case "M", "MAGENTA" -> couleur = Ansi.Color.MAGENTA; // // alors on définie la couleur magenta
            default -> {
                // Message d'erreur qui permet de prévenir l'utilisateur en cas de mauvaise saisie
                System.out.println(ansi().fg(Ansi.Color.RED).a("Vous devez entrer une des options ci-dessus").reset());
                return couleurVerif(pseudoUtilisateur);
            }
        }

        // Retourner la variable couleur
        return couleur;

    }
    // Création d'une fonction pour vérifier la taille du pseudo
    public static String pseudoVerif(int i) {
        // Initialisation du scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer le pseudo du joueur " + i + 1);
        // Stockage de la valeur saisie par l'utilisateur
        String pseudoUtilisateur = scanner.nextLine();
        // Condition : Si le pseudo < 2 caractères ou que le pseudo > 10 alors message d'erreur
        if (pseudoUtilisateur.length() < 2 || pseudoUtilisateur.length() > 10) {
            // Cas d'arrêt de la récursivité
            System.out.println(ansi().fg(Ansi.Color.RED).a("Vous devez saisir un pseudo entre 2 caractères minimum et 10 caractères maximum").reset());
            // Rappel de la fonction ( en cas d'erreur )
            return pseudoVerif(i);
        }
        // Retourne la variable pseudo
        return pseudoUtilisateur;
    }
}


