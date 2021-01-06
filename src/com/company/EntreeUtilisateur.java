package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EntreeUtilisateur {

    public static int getInt(){
        Scanner scanner = new Scanner(System.in);

        Scanner scan=new Scanner(System.in);
        try {
            return scan.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Entrée incorrecte");
            return getInt();
        }

    }

}
