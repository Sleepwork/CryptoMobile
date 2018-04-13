package com.example.futloo.cryptomobile;

/**
 * Created by Zanta on 08/04/2018.
 */

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RectangularTranspoCipher {

    /* Rectangle pour la transposition sous forme de 2 tableaux,
    *  un qui contiendra le caractère et l'autre sa position dans le rectangle
    *  La position sera écrite comme pour une matrice (ex: 1,2) en enlevant la virgule (ex: 12)
    *  Utilisation de l'index du tableau comme clé pour trouver l'un à partir de l'autre
    */
    private static ArrayList<Integer> positionRect;
    private static ArrayList<Character> caracRect;

    // Fonction main pour test sous java
    /*public static void main(String[] args){

        //Transposition rectangulaire
        String cle = "boltor";

        System.out.println("Chiffrement --------------------------");
        String chiffrement = transpoRect("burn and ignite", cle, true);
        System.out.println("Résultat: " + chiffrement);

        System.out.println("");System.out.println("");

        System.out.println("Déchiffrement --------------------------");
        String dechiffrement = transpoRect("bnir eudt ganni", cle, false);
        System.out.println("Résultat: " + dechiffrement);

    }*/

    // Fonction de chiffrement/déchiffrement avec la transposition rectangulaire
    public static String transpoRect(String msg, String cle, boolean code){

        // Variable qui contiendra le msg chiffré/déchiffré
        String newMsg = "";

        // Tableau indiquant la position des caractères de la clé par ordre alphabétique
        HashMap<Character, Integer> hashmap = cleHashmap(cle);

        // Longueur de la clé -> nb de colonne du rectangle
        int nbCol = cle.length();

        // Chiffrement ou déchiffrement
        if(code){

            // Création du rectangle pour la transposition
            createRect(msg, nbCol);

            // Affiche une représentation du rectangle dans la console
            showRect(cle, hashmap);
            Log.i("RectangularTranspo", msg);

            // Pour la colonne à prendre en i-ème
            for(int i = 1; i <= nbCol; i++){
                ArrayList<Integer> colPos = new ArrayList<>();

                /* Recherche de l'identifiant de la colonne à prendre en i-ème
                *  Si la clé possède plusieurs instances de la même lettre,
                *  alors elles auront toutes la même position
                *  Stockage dans un arraylist pour toutes les prendre en compte
                */
                for(int j = 0; j < nbCol ; j++){
                    if(hashmap.get(cle.charAt(j)) == i)
                        colPos.add(j + 1);
                }

                // Recherche du nombre de lignes associé à la colonne
                int nbLignes = msg.length() / nbCol;

                // Si la longueur du msg n'est pas divisible par la longueur de la clé,
                // alors il existe une ligne supplémentaire
                if(msg.length() % nbCol != 0)
                    nbLignes++;

                // Pour chaque identifiant de colonne récupéré
                for(int doublon = 0; doublon < colPos.size(); doublon++){

                    // Récupération du caractère ligne par ligne
                    for(int j = 1; j <= nbLignes; j++){
                        int pos = j * 10 + colPos.get(doublon);

                        // Vérification nécessaire en cas de ligne supplémentaire
                        if(positionRect.contains(pos)){
                            int index = positionRect.indexOf(pos);
                            newMsg += "" + caracRect.get(index);
                        }

                    }
                }
            }

        } else {

            // Création du rectangle pour la transposition
            createRect(msg, nbCol);

            // Affiche une représentation du rectangle dans la console
            showRect(cle, hashmap);

            // Calcul de valeurs nécesaires dans la suite
            // Longueur du msg
            int msgL = msg.length();

            // Quotient de la division des longueurs du msg et de la clé
            int quotient = msgL / nbCol;

            // Reste de la division des longueurs du msg et de la clé
            int reste = msgL % nbCol;

            // Longueur actuelle du msg dechiffré
            int newMsgL = 0;

            // Initialisation d'un tableau String qui contiendra le rectangle du msg déchiffré
            String[][] newMsgTab = new String[quotient+1][nbCol];

            for(int i = 0; i < quotient + 1; i++){
                for(int j = 0; j < nbCol; j++){
                    newMsgTab[i][j] = "";
                }
            }

            Log.i("RectangularTranspo", msg);

            // Pour la colonne à prendre en i-ème
            for(int i = 1; i <= nbCol; i++){
                ArrayList<Integer> colPos = new ArrayList<>();

                /* Recherche de l'identifiant de la colonne à prendre en i-ème
                *  Si la clé possède plusieurs instances de la même lettre,
                *  alors elles auront toutes la même position
                *  Stockage dans un arraylist pour toutes les prendre en compte
                */
                for(int j = 0; j < nbCol ; j++){
                    if(hashmap.get(cle.charAt(j)) == i)
                        colPos.add(j);
                }

                // Pour chaque identifiant de colonne récupéré
                for(int doublon = 0; doublon < colPos.size(); doublon++){
                    int nbLignes = quotient;
                    int pos = colPos.get(doublon);

                    // Si la position est inférieur au reste, il y a une ligne supplémentaire
                    if(pos < reste)
                        nbLignes++;

                    /* Reconstitution de la colonne du rectangle:
                     * Le contenu des colonnes a été écrit à la suite dans le msg chiffré
                     * Il faut donc prendre les suites de caractères du msg chiffré
                     * en considérant l'existence d'une ligne supplémentaire ou non
                     */
                    for(int ligne = 0; ligne < nbLignes; ligne++){
                        newMsgTab[ligne][pos] =  "" + msg.charAt(ligne + newMsgL);
                    }

                    // Incrémentation par le nombre de caractères ajoutés, sert de marqueur pour les carac. suivants
                    newMsgL += nbLignes;
                }
            }

            // Reconstitution du msg à partir du rectangle
            for(int i = 0; i < quotient + 1; i++){
                for(int j = 0; j < nbCol; j++){
                    newMsg += "" + newMsgTab[i][j];
                }
            }
        }

        return newMsg;
    }


    // Fonction de création de l'ordre de sélection des colonnes à partir d'une clé
    private static HashMap<Character, Integer> cleHashmap(String cle){

        // Tableau qui contiendra le résultat
        HashMap<Character, Integer> hashmap = new HashMap<>();

        // Tableau intermédiaire pour opération  de tri
        int[] tab = new int[cle.length()];

        // Chaque caractère de la clé est stockée dans le tableau intermédiaire
        for(int i = 0; i < cle.length(); i++){
            char carac = cle.charAt(i);
            tab[i] = (int) carac;
        }

        // Tri des caractères du tableau ASC, les mêmes carac. seront mergés en un seul carac.
        Arrays.sort(tab);

        // Récupération des carac. triés dans le tableau final, ajout de la position par carac.
        for(int i = 0; i < cle.length(); i++){
            hashmap.put((char) tab[i], i+1);
        }

        return hashmap;
    }

    // Fonction de création du rectangle de transposition à partir d'un message et de la longueur de la clé
    private static void createRect(String msg, int nbCol){

        // Initialisation du rectangle de transpo.
        positionRect = new ArrayList<>();
        caracRect = new ArrayList<>();

        // Initialisation de la variable qui contiendra chaque carac. du msg
        char ascii;

        /* Pour chaque carac. du msg, calcul de sa position fictive dans le rectangle
         * i représente sa position dans le message
         * La ligne correspond au quotient de la division de i par le nb de colonne
         * La colonne correspond au reste de la division de i par le nb de colonne
         * On ajoute 11 car les lignes et colonnes commencent à 1,1
         */
        for(int i = 0; i < msg.length(); i++){
            int pos;
            ascii = msg.charAt(i);

            if(i>=nbCol)
                pos = (i / nbCol) * 10 + (i % nbCol) + 11;
            else
                pos = 11 + i;

            // Ajout de la position et du caractère dans le "rectangle"
            positionRect.add(pos);
            caracRect.add(ascii);
        }

    }

    // Fonction pour afficher le contenu du rectangle dans la console
    private static void showRect(String cle, HashMap<Character, Integer> hashmap){

        // Récupération de la longueur du msg et de la clé
        int nbCol = cle.length();
        int msgLength = positionRect.size();

        // Variable qui contiendra le contenu d'une ligne
        String print = "";

        // Affichage de la clé
        for(int i = 0; i < nbCol; i++)
            print += Character.toString(cle.charAt(i));

        Log.i("RectangularTranspo", print);
        print = "";

        // Affichage des positions de sélection des colonnes en fonction de la clé
        for(int i = 0; i < nbCol; i++)
            print += Integer.toString(hashmap.get(cle.charAt(i)));

        Log.i("RectangularTranspo", print);
        print = "";

        /* Pour chaque caractère du msg,
         * Si sa position est divisible par le nb de colonnes,
         * càd qu'il s'agit d'une fin de ligne,
         * donc on affiche et on réinitialise la variable
         *
         * Sinon on concatène le carac. à la variable
         */
        for(int i = 0; i < msgLength; i++){
            if(i % nbCol == 0){
                Log.i("RectangularTranspo", print);
                print = "";
            }

            print += Character.toString(caracRect.get(i)) + " ";
        }

        Log.i("RectangularTranspo", "Show over.");
    }
}

