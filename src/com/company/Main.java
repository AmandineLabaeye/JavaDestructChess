package com.company;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        //Affichage de l'en-tête du menu
        Menu.enteteMenu();
        //Affichage du Menu
        Menu.menu();

    }

    /**
     * Converti une chaine pour que les accents s'affiche correctement sous windows
     * @param str Une string classique
     * @return Une string dont les accents l'afficherons correctement
     */
    public static String s(String str){
        return new String(str.getBytes(StandardCharsets.UTF_8));
    }

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
}
