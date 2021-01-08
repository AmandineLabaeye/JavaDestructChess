package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Gères le tableau des meilleurs scores
 */
public class Scores {

    // On défini la liste des profils
    private static List<Profil> profils;

    /**
     * Fonction qui ajoute un profil à la liste des scores
     * @param profil Liste des joueurs pour les scores
     */
    public static void ajouterJoueur(Profil profil) {
        // ajoute un profil au début de la liste pour qu'elle reste dans l'ordre
        profils.add(0, profil);
    }

    /**
     * Fonction qui recherche dans la liste des joueurs si le jour existe déjà ou non, pour actualiser ou modifier le score
     * @param pseudoUtilisateur pseudo choisi par l'utilisateur
     * @return la condition qui vérifie si il existe déjà ou non (Null ou le pseudo du joueur)
     */
    public static Profil chercheJoueur(String pseudoUtilisateur) {
        // Il récupère tout les profils dont le pseudo est égal au pseudo passé en paramètre (Normalement il y en a qu'un),
        // récupère le premier et retourne null si il y en a pas.
        return profils.stream().filter(joueur -> joueur.nom.equals(pseudoUtilisateur)).findFirst().orElse(null);
    }

    /**
     * Fonction qui actualise les scores et les trient
     */
    public static void actualiserScores() {

        // Je récupère la taille du tableau
        int taille = profils.size();

        // J'appelle la fonction et je passe en paramètre la liste, l'index de début à 0, et la taille à -1
        tri(profils, 0, taille - 1);

    }

    /**
     * Fonction qui renvoie la liste des joueurs
     * @return la liste pour la réafficher
     */
    public static List<Profil> getJoueurs() {
        return profils;
    }

    /**
     * Fonction qui charge le fichier de sauvegarde, pour toujours accéder au scores au redémarrage de l'application
     */
    public static void chargerJoueur() {
        try {
            profils = Sauvegarde.charger();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(ansi().fgRed().a("Impossible de lire le fichier de sauvegarde"));
            profils = new ArrayList<>();
        }
    }

    /**
     * Fonction de partitionnement pour le tri
     * @param profils la liste des joueurs
     * @param indexDebut index du début de la liste
     * @param indexFin index de fin de la liste
     * @return retourne l'index ou doit être placé la carte
     */
    private static int partition(List<Profil> profils, int indexDebut, int indexFin) {

        // Le pivot est initialisé au dernier index de la liste
        Profil pivot = profils.get(indexFin);
        // J est initialisé au premier index de la liste
        int j = indexDebut;
        // Boucle sur i qui commence à l'index du début, tant que i est inférieur à indexFix alors i s'incrémente
        for (int i = indexDebut; i < indexFin; i++) {
            // Si l'index de la liste i est inférieur au pivot alors
            if (profils.get(i).score < pivot.score) {
                // On stock l'index j dans une variable temp
                Profil temp = profils.get(j);

                // On remplace l'index j par la valeur de l'index i
                profils.set(j, profils.get(i));
                // On remplace l'index i par la valeur stock dans temp soit l'ancienne valeur de j
                profils.set(i, temp);
                // On incrémente j de 1 à chaque passage ici
                j++;

            }
        }

        // On stock la valeur du pivot dans une variable temp
        Profil tempTab = profils.get(indexFin);
        // On remplace l'index de fin par la valeur de l'index j
        profils.set(indexFin, profils.get(j));
        // On remplace l'index de j, par la valeur de temp, soit l'ancienne valeur de l'index de fin du tableau
        profils.set(j, tempTab);

        // On renvoie j
        return j;

    }

    /**
     * Fonction qui tri la liste en récursif
     * @param profils liste des joueurs
     * @param indexDebut index de début de la liste
     * @param indexFin index de fin de la liste
     */
    private static void tri(List<Profil> profils, int indexDebut, int indexFin) {
        // Si l'index du début est inférieur à l'index de fin alors
        if (indexDebut < indexFin) {
            // On défini le pivot en rappelant la fonction partition en mettant les paramètres
            int pivot = partition(profils, indexDebut, indexFin);
            tri(profils, indexDebut, pivot - 1);
            tri(profils, pivot + 1, indexFin);
        }
    }

}
