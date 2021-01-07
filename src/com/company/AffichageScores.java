package com.company;

import com.company.gameplay.Joueur;
import org.fusesource.jansi.Ansi;

import java.util.List;
import java.util.Scanner;

import static com.company.Main.appelLimite;
import static com.company.Main.s;
import static org.fusesource.jansi.Ansi.ansi;

@SuppressWarnings("RedundantLabeledSwitchRuleCodeBlock")
public class AffichageScores {

    // Affiche l'entête du menu et le menu des scores
    public static void appelMenu() {
        menuEntete();
        menuScore();
    }

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

    // Création de la fonction " menuScore "
    public static void menuScore() {

        // Vérifie la taille de la liste Joueur si elle est égal à 0 alors on le renvoie au menu principal, car la liste est vide
        if (Scores.getJoueurs().size() == 0) {
            System.out.println(ansi().fgRed().a(s(
                    "Aucun joueur n'a été enregistré\n" +
                    "Appuyé sur ENTRER pour retourner au menu principale"
            )));
            new Scanner(System.in).nextLine();
            return;
        }

        // Sous menu du score
        System.out.println(ansi()

                .fgBlue().a(" ==============================================================\n")
                .fgBlue().a("| ").fgCyan().a("Veuillez saisir une option ci-dessous ").fgBlue().a("                       |\n")
                .fgBlue().a("| ").fgMagenta().a("* - C : Voir le score croissant (Du meilleur au moins fort)").fgBlue().a("  |\n")
                .fgBlue().a("| ").fgGreen().a("* - D : Voir le score (Du moins fort au meilleur)").fgBlue().a("            |\n")
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
                return;
            }

            default -> {
                // On lui indique qu'il n'a pas entrée l'une des propositions affichée
                System.out.println(ansi().fg(Ansi.Color.RED).a("Vous devez entrer une des propositions demandées").reset());
            }
        }

        appelLimite(AffichageScores::menuScore);
    }

    public static void affichageScoreDecroissant() {

        List<Joueur> joueurs = Scores.getJoueurs();

        // Affichage des scores dans l'odre décroissant (meilleur au moins fort)

        System.out.println(ansi().fgCyan().a("Tableau de scores : (Ordre Décroissant)").reset());

        // Boucle qui affiche la liste à l'envers
        for (int i = joueurs.size() - 1; i >= Math.max(0, joueurs.size() - 10); i--) {
            System.out.println(ansi().fg(joueurs.get(i).couleur).a(joueurs.get(i).nom + ", " + joueurs.get(i).score).reset());
        }

    }

    public static void affichageScoreCroissant() {

        List<Joueur> joueurs = Scores.getJoueurs();

        // Affichage des scores dans l'ordre croissant (moins fort au meilleur)

        System.out.println(ansi().fgCyan().a("Tableau de scores : (Ordre Croissant)").reset());

        // Boucle qui affiche la liste dans l'ordre
        for (int i = 0; i < Math.min(joueurs.size(), 10); i++) {
            System.out.println(ansi().fg(joueurs.get(i).couleur).a(joueurs.get(i).nom + ", " + joueurs.get(i).score).reset());
        }

    }


}
