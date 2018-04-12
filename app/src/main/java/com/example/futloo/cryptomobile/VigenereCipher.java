package com.example.futloo.cryptomobile;

/**
 * Created by Zanta on 08/04/2018.
 */

public class VigenereCipher {

    // Fonction main pour test sous java
    /*public static void main(String[] args){
        //Vigenere
        String cle = "eminem";

        System.out.println("Chiffrement --------------------------");
        String chiffrement = vigenere("They\" say that I rap like a robot, so call me rap-bot!", cle, true);
        System.out.println("Résultat: " + chiffrement);

        System.out.println("");System.out.println("");
        System.out.println("Déchiffrement --------------------------");
        String dechiffrement = vigenere("Zvo)(.yo$/zvg#*X&!g~*{oyk.k/x}h}~;&\"u.mprz&{o/xov;l~z/", cle, false);
        System.out.println("Résultat: " + dechiffrement);

    }*/

    // Fonction de chiffrement/déchiffrement avec vigenere
    public static String vigenere(String msg, String cle, boolean code){

        //Initialiation du msg à retourner
        String newMsg = "";

        // Pour chaque caractère
        for(int i = 0; i<msg.length(); i++){

            // Récupération du caractère et différence avec 32
            // (on ramène le 1er caractère de la table ascii à 0)
            char currentChar = msg.charAt(i);
            int ascii = (int) currentChar - 32;

            // Récupération de la valeur de l'incrémentation/décrémentation
            int cleAscii  = (int) cle.charAt(i%cle.length());

            // Chiffrement ou déchiffrement
            if(code){

                // Incrémentation du caractère par le décalage donné modulo 95
                ascii = ((ascii +  cleAscii) %95) + 32;
                currentChar = (char) ascii;
            } else {
                // Décrémentation du caractère par le décalage donné modulo 95
                ascii = ascii -  cleAscii % 95;

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

