package com.company;

import com.company.gameplay.Joueur;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Sauvegarder ou charger le tableau des scores
 */
public class Sauvegarde {

    static String fichierSauvegarder = "sauvegarde";

    /**
     * Sauvegarder le tableau des scores
     * @param joueurs Le tableau à sauvegarder
     */
    public static void sauvegarder(List<Joueur> joueurs) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fichierSauvegarder));
        outputStream.writeObject(joueurs);
    }

    /**
     * Charger le tableau des scores
     * @return Le tableau présent dans le fichier de sauvegarde
     */
    public static List<Joueur> charger() throws IOException, ClassNotFoundException {
        if (!new File(fichierSauvegarder).exists())
            return new ArrayList<>();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fichierSauvegarder));
        return (List<Joueur>) inputStream.readObject();
    }

}
