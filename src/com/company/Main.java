package com.company;

import org.fusesource.jansi.AnsiConsole;

public class Main {

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        Menu menu = new Menu();
        //Affichage de l'en-tête du menu
        menu.enteteMenu();
        //Affichage du Menu
        menu.menu();

    }
}
