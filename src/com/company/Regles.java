package com.company;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;


public class Regles {

    public Regles() {
        menuRegles();
    }


    public static void menuRegles() {
        System.out.println("Veuillez choisir le nombre de joueurs");
        System.out.println(ansi().reset().fg(Ansi.Color.YELLOW).a("- 2 : Deux joueurs"));
        System.out.println(ansi().reset().fg(Ansi.Color.YELLOW).a("- 3 : Trois joueurs"));
        System.out.println(ansi().reset().fg(Ansi.Color.YELLOW).a("- 4 : Quatre joueurs"));
        System.out.println(ansi().reset().fg(Ansi.Color.RED).a("- M : Menu Principal"));
        System.out.println(ansi().reset().fg(Ansi.Color.CYAN).a("- E : Sortir de l'application"));
        Scanner scanner = new Scanner(System.in);
        String saisieUtilisateur = scanner.nextLine();
        if (saisieUtilisateur.equals("2") || saisieUtilisateur.equals("3") || saisieUtilisateur.equals("4") || saisieUtilisateur.equals("m") || saisieUtilisateur.equals("M") || saisieUtilisateur.equals("e") || saisieUtilisateur.equals("E")) {

            switch (saisieUtilisateur) {
                // Si l'utilisateur appuie sur 2, les règles pour 2 joueurs s'afficheront
                case "2" -> {
                    System.out.println("Vous savez choisis de voir les règles pour 2 joueurs");
                    affichageReglesDeuxJoueurs();

                }
                // Si l'utilisateur appuie sur 3, les règles pour 3 joueurs s'afficheront
                case "3" -> {
                    System.out.println("Vous avez choisis de voir les règles pour 3 joueurs");
                    affichageReglesTroisJoueurs();
                }

                // Si l'utilisateur appuie sur 4, les règles pour 4 joueurs s'afficheront
                case "4" -> {
                    System.out.println("Vous savez choisis de voir les règles pour 4 joueurs");
                    affichageReglesQuatreJoueurs();
                }
                case "m","M"->  {
                    System.out.println("Retour au Menu Principal");
                    Menu.menu();
                }
                case "e","E" -> {
                    System.out.println("Vous sortez de l'application");
                }
            }
        } else {
            //Si l'utilisateur ne rentre pas d'option valide
            System.out.println(ansi().reset().fg(Ansi.Color.RED).a("Vous devez entrer une des options ci-dessus"));
            menuRegles();
        }
    }
    public static void affichageReglesDeuxJoueurs(){
        System.out.println("Au début de la partie, votre pion sera placé à côté de celui de votre adversaire, le but est simple, gagner la partie en bloquant au maximum votre ennemi." + "\n" +
                "Vous jouerez tour à tour et vous disposerez de 15 secondes pour jouer le vôtre." + "\n" +
                "A chaque tours vous devrez déplacer soit horizontalement soit verticalement votre pion, puis, vous devrez détruire une case sur le plateau" + "\n" +
                "Le premier pion étant bloqué par une case au dessus, une case en dessous, une case à droite et une case à gauche aura perdu !" + "\n" +
                "Choisissez bien vos déplacements ! Vous êtes prêt ? A vos marques, Prêt ? Jouez !");
        Menu.menu();

    }
    public static void affichageReglesTroisJoueurs(){
        System.out.println("Au début de la partie, votre pion sera placé à côté de celui de vos adversaires, le but est simple, gagner la partie en bloquant au maximum vos ennemis.\\n\" +\n" +
                "\"Vous jouerez tour à tour et vous disposerez de 15 secondes pour jouer le vôtre.\\n\" +\n" +
                "\"A chaque tours vous devrez déplacer soit horizontalement soit verticalement votre pion, puis, vous devrez détruire une case sur le plateau\\n\" +\n" +
                "\"Le premier pion étant bloqué par une case au dessus, une case en dessous, une case à droite et une case à gauche aura perdu la partie. En revanche, elle continuera pour les deux pions restants. !\\n\" +\n" +
                "\"Le dernier pion pouvant encoure bouger aura gagné la partie\\n\" +\n" +
                "\"Choisissez bien vos déplacements ! Vous êtes prêt ? A vos marques, Prêt ? Jouez !");
        Menu.menu();
    }
    public static void affichageReglesQuatreJoueurs(){
        System.out.println("Au début de la partie, votre pion sera placé à côté de celui de vos adversaires, le but est simple, gagner la partie en bloquant au maximum vos ennemis.\\n\" +\n" +
                "\"Vous jouerez tour à tour et vous disposerez de 15 secondes pour jouer le vôtre.\\n\" +\n" +
                "\"A chaque tours vous devrez déplacer soit horizontalement soit verticalement votre pion, puis, vous devrez détruire une case sur le plateau\\n\" +\n" +
                "\"Le premier pion étant bloqué par une case au dessus, une case en dessous, une case à droite et une case à gauche aura perdu la partie. En revanche, elle continuera pour les trois pions restants. !\\n\" +\n" +
                "\"Le dernier pion pouvant encoure bouger aura gagné la partie\\n\" +\n" +
                "\"Choisissez bien vos déplacements ! Vous êtes prêt ? A vos marques, Prêt ? Jouez !");
        Menu.menu();
    }



}
