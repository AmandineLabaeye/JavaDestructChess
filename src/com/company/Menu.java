package com.company;

import com.company.gameplay.Jeu;
import com.company.gameplay.Joueur;
import org.fusesource.jansi.Ansi;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.Scanner;

import static com.company.Main.appelLimite;
import static com.company.Main.s;
import static org.fusesource.jansi.Ansi.ansi;

public class Menu {

    public static void enteteMenu() {
        System.out.println(ansi().fgYellow().a(
                "********************************************************\n" +
                        "*                                                      *\n" +
                        "*            Bienvenue sur DestructChess  !            *\n" +
                        "*                                                      *\n" +
                        "********************************************************\n"
        ).reset());
    }

    // Création de la fonction " menu "
    public static boolean menu() {
        // Affichage des option
        System.out.println(ansi()
                .fgBlue().a(" =======================================================\n")
                .fgCyan().a("| Veuillez saisir une option ci-dessous                 |\n")
                .fgRed().a(s("| * - R : Voir les règles                               |\n"))
                .fgGreen().a("| * - L : Lancer une partie                             |\n")
                .fgYellow().a("| * - S : Afficher le score                             |\n")
                .fgBlue().a("| * - E : Sortir de l'application                       |\n")
                .fgBlue().a(" =======================================================")
                .reset());

        // Initialisation du scanner
        Scanner scanner = new Scanner(System.in);
        // Récupération de la saisie de l'utilisateur
        String saisieUtilisateur = scanner.nextLine().toLowerCase();
        // Création d'un switch pour la saisie de l'utilisateur
        switch (saisieUtilisateur) {

            case "e": // Si l'utilisateur appuie sur e ou E, cela le fait sortir de l'application
                System.out.println(ansi().fgBrightRed().a("Vous sortez de l'application").reset());
                return false;

            case "r": // Sinon si il appuie sur r ou R, cela affiche les règles
                regles();
                break;

            case "l": // Sinon si il appuie sur l ou L, cela lance la partie
                System.out.println(ansi().fgBrightMagenta().a(s("Début de la partie !")).reset());
                choixPseudoCouleurs();
                break;

            case "s": // Sinon si il appuie sur s ou S, cela affiche le tableau des scores
                Scores.appelMenu(); // Rappel de la class pour afficher le menu des scores
                break;

            default: //Si l'utilisateur ne rentre pas d'option valide
                System.out.println(ansi().fg(Ansi.Color.RED).a("Vous devez entrer une des options ci-dessus").reset());
                break;

        }
        return true;
    }

    public static void regles() {
        // Affichage des règles dans la console
        String enteteRegle = "==================================================";
        String texteRegle = s("| Bienvenue dans les règles de [DestructChess] ! |");

        TexteAlignement texteAlignement = new TexteAlignement(170, TexteAlignement.alignement.CENTRE);

        System.out.println(ansi().fg(Ansi.Color.BLUE).a(texteAlignement.format(enteteRegle)).reset());
        System.out.println(ansi().fg(Ansi.Color.GREEN).a(texteAlignement.format(texteRegle)).reset());
        System.out.println(ansi().fg(Ansi.Color.BLUE).a(texteAlignement.format(enteteRegle)).reset());

        System.out.println(ansi().fg(Ansi.Color.YELLOW).a("Le but est simple ! Bloquez votre / vos adversaire(s) afin de décrocher la victoire ! \n" +
                "Vous débuterez la partie à côté de votre / vos ennemis, tour à tour vous devrez vous déplacer soit à la verticale, soit à l'horizontale, puis,\n" +
                "après chaque déplacements, vous devrez détruire une case qui se trouve sur le plateau  \n" +
                "Dès qu'un joueur se retrouve bloqué par des cases détruites ou des joueurs en haut, en bas, à droite et à gauche, il perd la partie. \n" +
                "Vous êtes désormais prêts à jouer à [DestructChess] ! \n").reset());
        // Rappel du menu
        menu();


    }

    // Création d'une nouvelle fonction qui permet de choisir le pseudo et la couleur des joueurs
    public static void choixPseudoCouleurs() {
        System.out.println("Combien de joueurs? (entre 2 et 4)");

        // Demande du nombre de joueurs
        int nbJoueurs = EntreeUtilisateur.getInt();
        // Vérification qu'il y en a le bon nombre
        if (nbJoueurs < 2 || nbJoueurs > 4) {
            System.out.println(ansi().fgRed().a("Il peut y avoir entre y et 4 joueurs"));
            appelLimite(Menu::choixPseudoCouleurs);
        }

        // Initialisation du tableau des joueurs
        Joueur[] joueurs = new Joueur[nbJoueurs];
        // Boucle qui permet de créer deux utilisateurs ( pseudo + couleur )
        for (int i = 0; i < nbJoueurs; i++) {
            // Création d'une variable pour vérifier la taille du pseudo de l'utilisateur
            String pseudoUtilisateur = pseudoVerif(i + 1, joueurs);
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
        System.out.println(ansi()
                .a("Merci " + pseudoUtilisateur + ", choisissez la couleur de votre pion\n")
                .fgBlue().a("* - B : Bleu\n")
                .fgGreen().a("* - V : Vert\n")
                .fgYellow().a("* - J : Jaune\n")
                .fgRed().a("* - R : Rouge\n")
                .fgCyan().a("* - C : Cyan\n")
                .fgBlack().a("* - N : Noir\n")
                .fg(Ansi.Color.WHITE).a("* - W : Blanc\n")
                .fgMagenta().a("* - M : Magenta")
                .reset());
        // Récupération des saisies de l'utilisateur dans le scanner et transformation en majuscule
        String couleurUtilisateur = scanner.nextLine().toUpperCase();
        // Initialisation de la variable " couleur "
        Ansi.Color couleur;
        // Switch qui permet de vérifier la saisie de l'utilisateur
        switch (couleurUtilisateur) {
            case "B", "BLEU" -> couleur = Ansi.Color.BLUE; // Si l'utilisateur tape B ou BLEU alors on définie la couleur Bleu
            case "V", "VERT" -> couleur = Ansi.Color.GREEN; // // alors on définie la couleur Verte
            case "J", "JAUNE" -> couleur = Ansi.Color.YELLOW; // // alors on définie la couleur Jaune
            case "R", "ROUGE" -> couleur = Ansi.Color.RED; // // alors on définie la couleur Rouge
            case "C", "CYAN" -> couleur = Ansi.Color.CYAN; // // alors on définie la couleur Cyan
            case "N", "NOIR" -> couleur = Ansi.Color.BLACK; // // alors on définie la couleur Noire
            case "W", "BLANC" -> couleur = Ansi.Color.WHITE; // // alors on définie la couleur Blanche
            case "M", "MAGENTA" -> couleur = Ansi.Color.MAGENTA; // // alors on définie la couleur Magenta
            default -> {
                // Message d'erreur qui permet de prévenir l'utilisateur en cas de mauvaise saisie
                System.out.println(ansi().fg(Ansi.Color.RED).a("Vous devez entrer une des options ci-dessus").reset());
                return appelLimite(Menu::couleurVerif, pseudoUtilisateur);
            }
        }

        // Retourner la variable couleur
        return couleur;

    }

    // Création d'une fonction pour vérifier la taille du pseudo
    public static String pseudoVerif(int i, Joueur[] joueurs) {
        // Initialisation du scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer le pseudo du joueur " + i);
        // Stockage de la valeur saisie par l'utilisateur
        String pseudoUtilisateur = scanner.nextLine();
        // Condition : Si le pseudo < 2 caractères ou que le pseudo > 10 alors message d'erreur
        if (pseudoUtilisateur.length() < 2 || pseudoUtilisateur.length() > 10) {

            System.out.println(ansi().fg(Ansi.Color.RED).a("Vous devez saisir un pseudo entre 2 caractères minimum et 10 caractères maximum").reset());
            // Rappel de la fonction ( en cas d'erreur )
            return appelLimite(Menu::pseudoVerif, i, joueurs);

            // Vérification que les pseudos sont différents et que pour le premier joueur il peut choisir ce qu'il veut par rapport au tableau joeurs
        } else if (Arrays.stream(joueurs).anyMatch(joueur -> joueur != null && joueur.nom.equals(pseudoUtilisateur))) {

            System.out.println(ansi().fg(Ansi.Color.RED).a("Ce joueur existe déjà, veuillez modifier votre pseudo").reset());
            // Rappel de la fonction ( en cas d'erreur )
            return appelLimite(Menu::pseudoVerif, i, joueurs);

        }

        // Cas d'arrêt de la récursivité
        // Retourne la variable pseudo
        return pseudoUtilisateur;
    }
}

