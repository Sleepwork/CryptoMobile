package com.example.futloo.cryptomobile;

/**
 * Created by Zanta on 08/04/2018.
 */

public class VigenereCipher {

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

    public static String vigenere(String msg, String cle, boolean code){
        String newMsg = "";

        for(int i = 0; i<msg.length(); i++){

            char currentChar = msg.charAt(i);
            int ascii = (int) currentChar - 32;
            int cleAscii  = (int) cle.charAt(i%cle.length());

            if(code){
                ascii = ((ascii +  cleAscii) %95) + 32;
                currentChar = (char) ascii;
            } else {
                ascii = ascii -  cleAscii % 95;

                while(ascii<0)
                    ascii += 95;

                ascii += 32;
                currentChar = (char) ascii;
            }

            if((int) currentChar != 34 && (int) currentChar != 92)
                newMsg = newMsg + currentChar;
            else
                newMsg = newMsg + "\\" + currentChar;
        }

        return newMsg;
    }
}

