package com.company;

import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.fusesource.jansi.Ansi.ansi;

public class Main {

    public static void main(String[] args) {
        Scores.chargerJoueur();
        //#region Code spécifique a windows
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            // Fait fonctionner jansi, pose apparent des problèmes sur mac et dans IntelliJ
            if (Main.class.getResource("Main.class").toString().startsWith("jar:")) // Détecte si le program est lancé depuis un jar
            {
                AnsiConsole.systemInstall();
            }
            // Force la console en UTF-8
            try {
                new ProcessBuilder("cmd.exe", "/c", "chcp", "65001>nul").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e) {
                System.out.println("Vérifier que votre terminale est bien en UTF-8");
            }
        }
        //#endregion

        //Affichage de l'en-tête du menu
        Menu.enteteMenu();

        boolean rester = true;

        while (rester) {
            rester = Menu.menu();
        }

        try {
            Sauvegarde.sauvegarder(Scores.getJoueurs());
        } catch (IOException e) {
            System.out.println(ansi().fgRed().a("Impossible de sauvegarder les joueurs"));
        }
    }

    /**
     * Converti une chaine pour que les accents s'affiche correctement sous windows
     * @param str Une string classique
     * @return Une string dont les accents l'afficherons correctement
     */
    public static String s(String str){
        return new String(str.getBytes(StandardCharsets.UTF_8));
    }

    //#region appelLimite

    /**
     * Appel la fonction passé, a partir d'une certaine profondeur de la pile d'appel, quitte le program
     * @param func Fonction à appeler
     * @param arg Argument à passer à la fonction
     * @param <T> Le type de paramètre à passer à la fonction
     * @param <R> Le type de retour de la fonction
     * @return La valeur retourné par la fonction
     */
    public static <T, R> R appelLimite(Function<T, R> func, T arg){
        // Vérification de la taille de la pile d'appel
        if (Thread.currentThread().getStackTrace().length > 1000) {
            // Si la pile est trop profonde, on quitte le programme
            System.out.println("X Trop de tentative, arrêt du programme");
            System.exit(-1);
        }

        // Appelle de la fonction passé
        return func.apply(arg);
    }

    /**
     * Appel la fonction passé, a partir d'une certaine profondeur de la pile d'appel, quitte le program
     * @param func Fonction à appeler
     * @param arg1 Argument à passer à la fonction
     * @param arg2 Argument à passer à la fonction
     * @param <T1> Le type de paramètre à passer à la fonction
     * @param <T2> Le type de paramètre à passer à la fonction
     * @param <R> Le type de retour de la fonction
     * @return La valeur retourné par la fonction
     */
    public static <T1, T2, R> R appelLimite(BiFunction<T1, T2, R> func, T1 arg1, T2 arg2){
        // Vérification de la taille de la pile d'appel
        if (Thread.currentThread().getStackTrace().length > 1000) {
            // Si la pile est trop profonde, on quitte le programme
            System.out.println("X Trop de tentative, arrêt du programme");
            System.exit(-1);
        }

        // Appelle de la fonction passé
        return func.apply(arg1, arg2);
    }

    /**
     * Appel la fonction passé, a partir d'une certaine profondeur de la pile d'appel, quitte le program
     * @param func Fonction à appeler
     * @param <R> Le type de retour de la fonction
     * @return La valeur retourné par la fonction
     */
    public static <R> R appelLimite(Supplier<R> func){
        // Vérification de la taille de la pile d'appel
        if (Thread.currentThread().getStackTrace().length > 1000) {
            // Si la pile est trop profonde, on quitte le programme
            System.out.println("X Trop de tentative, arrêt du programme");
            System.exit(-1);
        }

        // Appelle de la fonction passé
        return func.get();
    }

    /**
     * Appel la fonction passé, a partir d'une certaine profondeur de la pile d'appel, quitte le program
     * @param func Fonction à appeler
     */
    public static void appelLimite(Runnable func){
        // Vérification de la taille de la pile d'appel
        if (Thread.currentThread().getStackTrace().length > 1000) {
            // Si la pile est trop profonde, on quitte le programme
            System.out.println("X Trop de tentative, arrêt du programme");
            System.exit(-1);
        }

        // Appelle de la fonction passé
        func.run();
    }
    //#endregion
}
