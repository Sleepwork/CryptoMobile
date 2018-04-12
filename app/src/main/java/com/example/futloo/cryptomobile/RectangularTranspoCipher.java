package com.example.futloo.cryptomobile;

/**
 * Created by Zanta on 08/04/2018.
 */

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RectangularTranspoCipher {

    private static ArrayList<Integer> codeRect;
    private static ArrayList<Character> codeDerect;

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

    public static String transpoRect(String msg, String cle, boolean code){

        String newMsg = "";
        HashMap<Character, Integer> hashmap = cleHashmap(cle);
        int nbCol = cle.length();

        if(code){
            createRect(msg, nbCol);
            showRect(cle, hashmap);
            Log.i("RectangularTranspo", msg);

            for(int i = 1; i <= nbCol; i++){
                ArrayList<Integer> colPos = new ArrayList<Integer>();

                for(int j = 0; j < nbCol ; j++){
                    if(hashmap.get(cle.charAt(j)) == i)
                        colPos.add(j + 1);
                }

                int nbLignes = msg.length() / nbCol;

                if(msg.length() % nbCol != 0)
                    nbLignes++;

                for(int doublon = 0; doublon < colPos.size(); doublon++){
                    for(int j = 1; j <= nbLignes; j++){
                        int pos = j * 10 + colPos.get(doublon);
                        if(codeRect.contains(pos)){
                            int index = codeRect.indexOf(pos);
                            newMsg += codeDerect.get(index);
                        }

                    }
                }
            }

        } else {
            createRect("burn and ignite", nbCol);
            showRect(cle, hashmap);
            int msgL = msg.length();
            int quotient = msgL / nbCol;
            int reste = msgL % nbCol;
            int newMsgL = 0;
            String[][] newMsgTab = new String[quotient+1][nbCol];

            for(int i = 0; i < quotient + 1; i++){
                for(int j = 0; j < nbCol; j++){
                    newMsgTab[i][j] = "";
                }
            }

            Log.i("RectangularTranspo", msg);

            for(int i = 1; i <= nbCol; i++){
                ArrayList<Integer> colPos = new ArrayList<Integer>();

                for(int j = 0; j < nbCol ; j++){
                    if(hashmap.get(cle.charAt(j)) == i)
                        colPos.add(j);
                }

                for(int doublon = 0; doublon < colPos.size(); doublon++){
                    int nbLignes = quotient;
                    int pos = colPos.get(doublon);

                    if(pos < reste)
                        nbLignes++;

                    for(int ligne = 0; ligne < nbLignes; ligne++){
                        newMsgTab[ligne][pos] =  "" + msg.charAt(ligne + newMsgL);
                    }

                    newMsgL += nbLignes;
                }
            }

            for(int i = 0; i < quotient + 1; i++){
                for(int j = 0; j < nbCol; j++){
                    newMsg += newMsgTab[i][j];
                }
            }
        }

        return newMsg;
    }

    private static HashMap<Character, Integer> cleHashmap(String cle){

        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();
        int[] tab = new int[cle.length()];

        for(int i = 0; i < cle.length(); i++){
            char carac = cle.charAt(i);
            tab[i] = (int) carac;
        }

        Arrays.sort(tab);

        for(int i = 0; i < cle.length(); i++){
            hashmap.put((char) tab[i], i+1);
        }

        return hashmap;
    }

    private static void createRect(String msg, int nbCol){
        codeRect = new ArrayList<Integer>();
        codeDerect = new ArrayList<Character>();
        char ascii = 'r';

        for(int i = 0; i < msg.length(); i++){
            int pos = 0;
            ascii = msg.charAt(i);

            if(i>=nbCol)
                pos = (i / nbCol) * 10 + (i % nbCol) + 11;
            else
                pos = 11 + i;

            codeRect.add(pos);
            codeDerect.add(ascii);
        }

    }

    private static void showRect(String cle, HashMap<Character, Integer> hashmap){
        int nbCol = cle.length();
        int msgLength = codeRect.size();
        char ascii = 'r';
        String print = "";

        for(int i = 0; i < nbCol; i++)
            print += Character.toString(cle.charAt(i));

        Log.i("RectangularTranspo", print);
        print = "";

        for(int i = 0; i < nbCol; i++)
            print += Integer.toString(hashmap.get(cle.charAt(i)));

        Log.i("RectangularTranspo", print);
        print = "";

        for(int i = 0; i < msgLength; i++){
            if(i % nbCol == 0){
                Log.i("RectangularTranspo", print);
                print = "";
            }

            print += Character.toString(codeDerect.get(i)) + " ";
        }

        Log.i("RectangularTranspo", "Show over.");
    }
}

