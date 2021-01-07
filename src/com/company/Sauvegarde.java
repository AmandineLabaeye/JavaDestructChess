package com.company;

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
     * @param profils Le tableau à sauvegarder
     */
    public static void sauvegarder(List<Profil> profils) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fichierSauvegarder));
        outputStream.writeObject(profils);
    }

    /**
     * Charger le tableau des scores
     * @return Le tableau présent dans le fichier de sauvegarde
     */
    public static List<Profil> charger() throws IOException, ClassNotFoundException {
        if (!new File(fichierSauvegarder).exists())
            return new ArrayList<>();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fichierSauvegarder));
        return (List<Profil>) inputStream.readObject();
    }

}
