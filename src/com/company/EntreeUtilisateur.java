package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.company.Main.s;
import static com.company.Main.appelLimite;

/**
 * Récupère une entré dans la console dans un format donné
 */
public class EntreeUtilisateur {

    /**
     * Attend un nombre dans la console, recommence tant que le joueur entre autre chose qu'un nombre
     * @return Le nombre entré par le joueur
     */
    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println(s("Entrée incorrecte"));
            Sons.ERREUR.jouer();
            return appelLimite(EntreeUtilisateur::getInt);
        }
    }

    /**
     * Attend un caractère dans la console, recommence tant que le joueur entre plus ou moins qu'un caractère
     * @return Le caractère entrée dans la console
     */
    public static char getChar() {
        Scanner scanner = new Scanner(System.in);
        String entree = scanner.nextLine();
        if (entree.length() != 1) {
            System.out.println(s("Entrée incorrecte"));
            Sons.ERREUR.jouer();
            return appelLimite(EntreeUtilisateur::getChar);
        }
        return entree.toLowerCase().charAt(0);
    }

}
