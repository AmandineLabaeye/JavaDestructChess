package com.company;

import org.fusesource.jansi.AnsiConsole;

import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        AnsiConsole.systemInstall();

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
