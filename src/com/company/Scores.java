package com.company;

import com.company.gameplay.Joueur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class Scores {

    // On défini la liste des joueurs
    private static List<Joueur> joueurs;

    // Fonction pour ajouter un joueur à la liste
    public static void ajouterJoueur(Joueur joueur) {
        // ajoute un joueur au début de la liste pour qu'elle reste dans l'ordre
        joueurs.add(0, joueur);
    }

    public static Joueur chercheJoueur(String pseudoUtilisateur) {
        // Il récupère tout les joueurs dont le pseudo est égal au pseudo passé en paramètre (Normalement il y en a qu'un),
        // récupère le premier et retourne null si il y en a pas.
        return joueurs.stream().filter(joueur -> joueur.nom.equals(pseudoUtilisateur)).findFirst().orElse(null);
    }

    public static void actualiserScores() {

        // Je récupère la taille du tableau
        int taille = joueurs.size();

        // J'appelle la fonction et je passe en paramètre la liste, l'index de début à 0, et la taille à -1
        tri(joueurs, 0, taille - 1);

    }

    // Récupérer la liste des joueurs
    public static List<Joueur> getJoueurs() {
        return joueurs;
    }

    public static void chargerJoueur() {
        try {
            joueurs = Sauvegarde.charger();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(ansi().fgRed().a("Impossible de lire le fichier de sauvegarde"));
            joueurs = new ArrayList<>();
        }
    }

    // Fonction de partionnement
    private static int partition(List<Joueur> joueurs, int indexDebut, int indexFin) {

        // Le pivot est initialisé au dernier index de la liste
        Joueur pivot = joueurs.get(indexFin);
        // J est initialisé au premier index de la liste
        int j = indexDebut;
        // Boucle sur i qui commence à l'index du début, tant que i est inférieur à indexFix alors i s'incrémente
        for (int i = indexDebut; i < indexFin; i++) {
            // Si l'index de la liste i est inférieur au pivot alors
            if (joueurs.get(i).score < pivot.score) {
                // On stock l'index j dans une variable temp
                Joueur temp = joueurs.get(j);

                // On remplace l'index j par la valeur de l'index i
                joueurs.set(j, joueurs.get(i));
                // On remplace l'index i par la valeur stock dans temp soit l'ancienne valeur de j
                joueurs.set(i, temp);
                // On incrémente j de 1 à chaque passage ici
                j++;

            }
        }

        // On stock la valeur du pivot dans une variable temp
        Joueur tempTab = joueurs.get(indexFin);
        // On remplace l'index de fin par la valeur de l'index j
        joueurs.set(indexFin, joueurs.get(j));
        // On remplace l'index de j, par la valeur de temp, soit l'ancienne valeur de l'index de fin du tableau
        joueurs.set(j, tempTab);

        // On renvoie j
        return j;

    }

    // Fonction de tri
    private static void tri(List<Joueur> joueurs, int indexDebut, int indexFin) {
        // Si l'index du début est inférieur à l'index de fin alors
        if (indexDebut < indexFin) {
            // On défini le pivot en rappelant la fonction partition en mettant les paramètres
            int pivot = partition(joueurs, indexDebut, indexFin);
            tri(joueurs, indexDebut, pivot - 1);
            tri(joueurs, pivot + 1, indexFin);
        }
    }

}
