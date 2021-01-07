package com.company;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class TexteAlignement extends Format {

    private static final long serialVersionUID = 1L; // A traduire
    // Énumération
    public enum alignement {
        GAUCHE, CENTRE, DROITE,
    }

    // Justification actuelle du formatage
    private alignement alignementCourant;

    // Longueur maximale d'une ligne
    private int maxCaracteres;
    // Constructeur avec paramètres max caractères et alignement choisi
    public TexteAlignement(int maxCaracteres, alignement aligne) {
        //Switch sur la variable d'alignement choisi
        switch (aligne) {
            // Si gauche, droite, ou centre alors = alignement courant
            case GAUCHE, CENTRE, DROITE -> this.alignementCourant = aligne;
            // Erreur
            default -> throw new IllegalArgumentException("Argument de justification invalide");
        }
        // Si maxCaracteres < 0
        if (maxCaracteres < 0) {
            // Erreur
            throw new IllegalArgumentException("Le nombre de caractère doit être positif");
        }
        // Variable = maxCaracteres défini
        this.maxCaracteres = maxCaracteres;
    }

    public StringBuffer format(Object input, StringBuffer where, FieldPosition ignore)
    {
        String s = input.toString();
        List<String> strings = splitInputString(s);
        ListIterator<String> listItr = strings.listIterator();

        while (listItr.hasNext())
        {
            String wanted = listItr.next();

            //Get the spaces in the right place.
            switch (alignementCourant)
            {
                case DROITE:
                    pad(where, maxCaracteres - wanted.length());
                    where.append(wanted);
                    break;

                case CENTRE:
                    int toAdd = maxCaracteres - wanted.length();
                    pad(where, toAdd / 2);
                    where.append(wanted);
                    pad(where, toAdd - toAdd / 2);
                    break;

                case GAUCHE:
                    where.append(wanted);
                    pad(where, maxCaracteres - wanted.length());
                    break;
            }
            where.append("\n");
        }
        return where;
    }

    protected final void pad(StringBuffer to, int howMany) {
        for (int i = 0; i < howMany; i++)
            to.append(' ');
    }

    String format(String s) {
        return format(s, new StringBuffer(), null).toString();
    }

    /** ParseObject is required, but not useful here. */
    public Object parseObject(String source, ParsePosition pos) {
        return source;
    }

    private List<String> splitInputString(String str) {
        List<String> list = new ArrayList<String>();
        if (str == null)
            return list;
        for (int i = 0; i < str.length(); i = i + maxCaracteres)
        {
            int endindex = Math.min(i + maxCaracteres, str.length());
            list.add(str.substring(i, endindex));
        }
        return list;
    }
}



