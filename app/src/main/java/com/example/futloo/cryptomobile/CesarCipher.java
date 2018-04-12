package com.example.futloo.cryptomobile;

/**
 * Created by Zanta on 08/04/2018.
 */

public class CesarCipher {

    // Fonction main pour test sous java
    /*public static void main(String[] args){

        //César
        int decalage = 18;

        System.out.println("Chiffrement --------------------------");
        String chiffrement = cesar("Oh baby it's a triple!", decalage, true);
        System.out.println("Résultat: " + chiffrement);

        System.out.println("");System.out.println("");

        System.out.println("Déchiffrement --------------------------");
        String dechiffrement = cesar("az2tst,2{'9&2s2'%{#~w3", decalage, false);
        System.out.println("Résultat: " + dechiffrement);

    }*/

    // Fonction de chiffrement/déchiffrement avec césar
    public static String cesar(String msg, int decalage, boolean code){

        //Initialiation du msg à retourner
        String newMsg = "";

        //Pour chaque caractère
        for(int i = 0; i<msg.length(); i++){

            // Récupération du caractère et différence avec 32
            // (on ramène le 1er caractère de la table ascii à 0)
            char currentChar = msg.charAt(i);
            int ascii = (int) currentChar - 32;

            // Chiffrement ou déchiffrement
            if(code){

                // Incrémentation du caractère par le décalage donné modulo 95
                ascii = ((ascii +  decalage) %95) + 32;
                currentChar = (char) ascii;

            } else {
                // Décrémentation du caractère par le décalage donné modulo 95
                ascii = ascii -  decalage % 95;

                while(ascii<0)
                    ascii += 95;

                ascii += 32;
                currentChar = (char) ascii;
            }

            // Ajout du caractère dans le msg à retourner
            if((int) currentChar != 34 && (int) currentChar != 92)
                newMsg = newMsg + currentChar;
            else
                newMsg = newMsg + "\\" + currentChar;
        }

        return newMsg;
    }
}