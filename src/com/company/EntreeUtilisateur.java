package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.company.Main.s;

public class EntreeUtilisateur {

    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println(s("Entrée incorrecte"));
            return getInt();
        }
    }

    public static char getChar() {
        Scanner scanner = new Scanner(System.in);
        String entree = scanner.nextLine();
        if (entree.length() != 1) {
            System.out.println(s("Entrée incorrecte"));
            return getChar();
        }
        return entree.toLowerCase().charAt(0);
    }

}
