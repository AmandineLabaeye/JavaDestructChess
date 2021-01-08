package com.company;

import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.Scanner;

import static com.company.Main.appelLimite;
import static com.company.Main.s;
import static org.fusesource.jansi.Ansi.ansi;

@SuppressWarnings("RedundantLabeledSwitchRuleCodeBlock")
public class AffichageScores {

    /**
     * Fonction qui affiche l'entête du menu et le menu des scores
     */
    public static void appelMenu() {
        menuEntete();
        menuScore();
    }

    /**
     * Fonction qui affiche l'entête du menu
     */
    public static void menuEntete() {

        // Message de bienvenue
        System.out.println(ansi().fg(Ansi.Color.YELLOW).a(
                        "********************************************************\n" +
                        "*                                                      *\n" +
                        "*            Bienvenue sur le menu des scores          *\n" +
                        "*                                                      *\n" +
                        "********************************************************\n"
        ).reset());

    }

    /**
     * Fonction qui affiche le menu des scores et qui récupère les choix de l'utilisateur pour le rediriger vers le bon onglet
     */
    public static void menuScore() {
        Sons.CLICK.jouer();
        // Vérifie la taille de la liste Profil si elle est égal à 0 alors on le renvoie au menu principal, car la liste est vide
        if (Scores.getJoueurs().size() == 0) {
            System.out.println(ansi().fgRed().a(s(
                    "Aucun joueur n'a été enregistré\n" +
                    "Appuyé sur ENTRER pour retourner au menu principal"
            )));
            new Scanner(System.in).nextLine();
            return;
        }

        // Sous menu du score
        System.out.println(ansi()

                .fgBlue().a(" ==============================================================\n")
                .fgBlue().a("| ").fgCyan().a("Veuillez saisir une option ci-dessous ").fgBlue().a("                       |\n")
                .fgBlue().a("| ").fgMagenta().a("* - C : Voir le score croissant (Du moins fort au plus fort)").fgBlue().a(" |\n")
                .fgBlue().a("| ").fgGreen().a("* - D : Voir le score (Du plus fort au moins fort)").fgBlue().a("           |\n")
                .fgBlue().a("| ").fgBlue().a("* - M : Retour au menu principal").fgBlue().a("                             |\n")
                .fgBlue().a(" ==============================================================\n")

        .reset());


        //On récupérer et demande à l'utilisateur d'entrée son choix
        Scanner scanner = new Scanner(System.in);
        String saisieUtilisateur = scanner.nextLine().toLowerCase();

        // On réalise un switch par rapport à la variable qui récupère la saisie de l'utilisateur
        switch (saisieUtilisateur) {
            // Si l'option entrée est C alors on lui affiche la liste des scores par ordre croissant
            case "c" -> {
                affichageScoreCroissant();
            }
            // Si l'option entrée est D alors on lui affiche la liste des scores par ordre décroissant
            case "d" -> {
                affichageScoreDecroissant();
            }
            // Si l'option entrée est M alors on lui affiche le menu principal du jeu
            case "m" -> {
                Sons.CLICK.jouer();
                return;
            }

            default -> {
                // On lui indique qu'il n'a pas entrée l'une des propositions affichée
                System.out.println(ansi().fg(Ansi.Color.RED).a("Vous devez entrer une des propositions demandées").reset());
                Sons.ERREUR.jouer();
            }
        }

        // Fonction récursive limiter pour éviter que l'utilisateur face planter le jeu, si il rentre trop de tentatives
        appelLimite(AffichageScores::menuScore);
    }

    /**
     * Fonction qui affichage les scores dans l'odre décroissant (meilleur au moins fort)
     */
    public static void affichageScoreDecroissant() {

        // On défini la liste des profils (Joueurs)
        List<Profil> profils = Scores.getJoueurs();


        System.out.println(ansi().fgCyan().a("Tableau de scores : (Ordre Décroissant)").reset());

        // Boucle qui affiche la liste à l'envers (Pour qu'elle soit dans le bon sens)
        for (int i = profils.size() - 1; i >= Math.max(0, profils.size() - 10); i--) {
            System.out.println(ansi().fg(profils.get(i).couleur).a(profils.get(i).nom + ", " + profils.get(i).score).reset());
        }

    }

    /**
     * Fonction qui affiche les scores dans l'ordre croissant (moins fort au meilleur)
     */
    public static void affichageScoreCroissant() {

        // On défini la liste des profils (Joueurs)
        List<Profil> profils = Scores.getJoueurs();

        System.out.println(ansi().fgCyan().a("Tableau de scores : (Ordre Croissant)").reset());

        // Boucle qui affiche la liste dans l'ordre
        for (int i = 0; i < Math.min(profils.size(), 10); i++) {
            System.out.println(ansi().fg(profils.get(i).couleur).a(profils.get(i).nom + ", " + profils.get(i).score).reset());
        }

    }


}
