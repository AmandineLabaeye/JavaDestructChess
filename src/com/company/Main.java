package com.company;

import com.company.gameplay.Jeu;
import com.company.gameplay.Joueur;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        new Jeu(new Joueur[] {new Joueur("Rkt", Ansi.Color.CYAN), new Joueur("Rkt2", Ansi.Color.YELLOW)}).jouer();
    }

    public static String s(String str){
        return new String(str.getBytes(StandardCharsets.UTF_8));
    }
}
