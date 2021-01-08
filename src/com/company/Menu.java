package com.company;

import com.company.gameplay.Jeu;
import org.fusesource.jansi.Ansi;

import java.util.Arrays;
import java.util.Scanner;

import static com.company.Main.appelLimite;
import static com.company.Main.s;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Affiche les différents menus
 */
@SuppressWarnings("RedundantLabeledSwitchRuleCodeBlock")
public class Menu {

    /**
     * Fonction qui affiche l'entête du Menu Principale
     */
    public static void enteteMenu() {
        System.out.println(ansi().fgYellow().a(
                "********************************************************\n" +
                        "*                                                      *\n" +
                        "*            Bienvenue sur DestructChess  !            *\n" +
                        "*                                                      *\n" +
                        "********************************************************\n"
        ).reset());
    }

    /**
     * Fonction qui affiche le menu, qui gère les choix de l'utilisateur et le redirige correctement
     * @return vrai ou faux si il faut sortir de l'application ou non
     */
    public static boolean menu() {
        // Affichage des option
        System.out.println(ansi()
                .fgBlue().a(" =======================================================\n")
                .fgBlue().a("|") .fgCyan().a(" Veuillez saisir une option ci-dessous                 ").fgBlue().a("|\n")
                .fgBlue().a("|").fgRed().a(s(" * - R : Voir les règles                               ")).fgBlue().a("|\n")
                .fgBlue().a("|").fgGreen().a(" * - L : Lancer une partie                             ").fgBlue().a("|\n")
                .fgBlue().a("|").fgYellow().a(" * - S : Afficher les score                            ").fgBlue().a("|\n")
                .fgBlue().a("|").fgBlue().a(" * - E : Sortir de l'application                       ").fgBlue().a("|\n")
                .fgBlue().a(" =======================================================")
                .reset());

        // Initialisation du scanner
        Scanner scanner = new Scanner(System.in);
        // Récupération de la saisie de l'utilisateur
        String saisieUtilisateur = scanner.nextLine().toLowerCase();

        // Création d'un switch pour la saisie de l'utilisateur
        switch (saisieUtilisateur) {
            // Si l'utilisateur appuie sur e ou E, cela le fait sortir de l'application
            case "e" -> {
                System.out.println(ansi().fgBrightRed().a("Vous sortez de l'application").reset());
                return false;
            }
            // Sinon si il appuie sur r ou R, cela affiche les règles
            case "r" -> {
                regles();
            }
            // Sinon si il appuie sur l ou L, cela lance la partie
            case "l" -> {
                System.out.println(ansi().fgBrightMagenta().a(s("Début de la partie !")).reset());
                choixPseudoCouleurs();
            }
            // Sinon si il appuie sur s ou S, cela affiche le tableau des scores
            case "s" -> { // Rappel de la class pour afficher le menu des scores
                AffichageScores.appelMenu();
            }
            //Si l'utilisateur ne rentre pas d'option valide
            default -> {
                System.out.println(ansi().fg(Ansi.Color.RED).a("Vous devez entrer une des options ci-dessus").reset());
                Sons.ERREUR.jouer();
            }
        }
        return true;
    }

    /**
     * Affichage des règles dans la console
     *
     */
    public static void regles() {
        // Jouer le son de click
        Sons.CLICK.jouer();
        System.out.println(ansi()
                .fgBlue().a("==================================================")
                .fgGreen().a(s("| Bienvenue dans les règles de [DestructChess] ! |"))
                .fgBlue().a("==================================================\n")
        .reset());

        System.out.println(ansi().fg(Ansi.Color.YELLOW).a(s(
                "Le but est simple ! Bloquez votre / vos adversaire(s) afin de décrocher la victoire ! \n" +
                "Vous débuterez la partie à côté de votre / vos ennemis, tour à tour vous devrez vous déplacer soit à la verticale, soit à l'horizontale, puis,\n" +
                "après chaque déplacements, vous devrez détruire une case qui se trouve sur le plateau  \n" +
                "Dès qu'un joueur se retrouve bloqué par des cases détruites ou des joueurs en haut, en bas, à droite et à gauche, il perd la partie. \n" +
                "Vous êtes désormais prêts à jouer à [DestructChess] ! \n"
        )).reset());

    }

    /**
     * Fonction qui permet de demander aux joueurs de choisir leur pseudo et la couleur de leur pion
     */
    public static void choixPseudoCouleurs() {
        // Jouer le son de click
        Sons.CLICK.jouer();
        System.out.println("Combien de joueurs? (entre 2 et 4)");

        // Demande du nombre de profils
        int nbJoueurs = EntreeUtilisateur.getInt();
        // Vérification qu'il y en a le bon nombre
        if (nbJoueurs < 2 || nbJoueurs > 4) {
            System.out.println(ansi().fgRed().a("Il peut y avoir entre 2 et 4 joueurs").reset());
            Sons.ERREUR.jouer();
            appelLimite(Menu::choixPseudoCouleurs);
        }
        Sons.CLICK.jouer();

        // Initialisation du tableau des profils
        Profil[] profils = new Profil[nbJoueurs];
        // Boucle qui permet de créer deux utilisateurs ( pseudo + couleur )
        for (int i = 0; i < nbJoueurs; i++) {
            // Création d'une variable pour vérifier la taille du pseudo de l'utilisateur
            String pseudoUtilisateur = pseudoVerif(i + 1, profils);

            // On stock le résultat de la fonction, on regarde si le profil existe déjà dans le tableau des scores
            Profil profil = Scores.chercheJoueur(pseudoUtilisateur);

            // Si le profil n'existe pas donc est null
            if (profil == null) {
                // Création d'une variable pour vérifier la couleur de l'utilisateur
                Ansi.Color couleur = couleurVerif(pseudoUtilisateur);
                // Ajout du nom et de la couleur dans le tableau (Crée un nouveau profil)
                Profil nouveau = new Profil(pseudoUtilisateur, couleur);
                profils[i] = nouveau;
                Scores.ajouterJoueur(nouveau);
            } else { // Si il existe
                // On stock juste le profil dans le tableau
                profils[i] = profil;
            }
        }
        // Affichage du plateau
        new Jeu(profils).jouer();

    }

    /**
     * Fonction qui permet de vérifier si la couleur entrée existe et qu'elle est correcte
     * @param pseudoUtilisateur récupère le nom d'utilisateur choisi par le joueur pour le réafficher dans la question
     * @return couleur, on retourne la couleur pour l'ajouter au tableau des scores
     */
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
                Sons.ERREUR.jouer();
                return appelLimite(Menu::couleurVerif, pseudoUtilisateur);
            }
        }
        Sons.CLICK.jouer();
        // Retourner la variable couleur
        return couleur;

    }

    /**
     * Fonction qui vérifie que le pseudo saisie est conforme au règle demander par le jeu et que les pseudo des joueurs sont tous différents
     * @param i itération de la boucle pour savoir à quel joueur on demande un pseudo
     * @param profils Liste des joueurs
     * @return pseudo choisi par l'utilisateur
     */
    public static String pseudoVerif(int i, Profil[] profils) {
        // Initialisation du scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer le pseudo du joueur " + i);
        // Stockage de la valeur saisie par l'utilisateur
        String pseudoUtilisateur = s(scanner.nextLine());
        // Condition : Si le pseudo < 2 caractères ou que le pseudo > 10 alors message d'erreur
        if (pseudoUtilisateur.length() < 2 || pseudoUtilisateur.length() > 10) {
            Sons.ERREUR.jouer();
            System.out.println(ansi().fg(Ansi.Color.RED).a(s("Vous devez saisir un pseudo entre 2 caractères minimum et 10 caractères maximum")).reset());
            // Rappel de la fonction ( en cas d'erreur )
            return appelLimite(Menu::pseudoVerif, i, profils);

            // Vérification que les pseudos sont différents et que pour le premier joueur il peut choisir ce qu'il veut par rapport au tableau profils
        } else if (Arrays.stream(profils).anyMatch(joueur -> joueur != null && joueur.nom.equals(pseudoUtilisateur))) {
            Sons.ERREUR.jouer();
            System.out.println(ansi().fg(Ansi.Color.RED).a(s("Ce joueur existe déjà, veuillez modifier votre pseudo")).reset());
            // Rappel de la fonction ( en cas d'erreur )
            return appelLimite(Menu::pseudoVerif, i, profils);

        }

        Sons.CLICK.jouer();

        // Cas d'arrêt de la récursivité
        // Retourne la variable pseudo
        return pseudoUtilisateur;
    }
}

