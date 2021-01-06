package com.company;

import com.company.gameplay.Jeu;
import com.company.gameplay.Joueur;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        //AnsiConsole.systemInstall();



        new Jeu(new Joueur[]{new Joueur("J1", Ansi.Color.BLUE), new Joueur("J2", Ansi.Color.YELLOW)}).jouer();

        Menu menu = new Menu();
        //Affichage de l'en-tête du menu
        menu.enteteMenu();
        //Affichage du Menu
        menu.menu();


    }

    /**
     * Converti une chaine pour que les accents s'affiche correctement sous windows
     * @param str Une string classique
     * @return Une string dont les accents l'afficherons correctement
     */
    public static String s(String str){
        return new String(str.getBytes(StandardCharsets.UTF_8));
    }
}
