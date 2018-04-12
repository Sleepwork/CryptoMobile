package com.example.futloo.cryptomobile;

/**
 * Created by Zanta on 08/04/2018.
 */

import android.util.Log;

import java.util.ArrayList;

public class PolybeCipher {

    private static ArrayList<Integer> codePolybe;
    private static ArrayList<String> codeDepolybe;

    /*public static void main(String[] args){

        //Polybe
        String cle = "ignite";

        System.out.println("Chiffrement --------------------------");
        String chiffrement = polybe("Oh baby it's a triple!", cle, true);
        System.out.println("Résultat: " + chiffrement);

        System.out.println("");System.out.println("");

        System.out.println("Déchiffrement --------------------------");
        String dechiffrement = polybe("hzov akfhcqzm tved'ry fv emuxtvshoriz!", cle, false);
        System.out.println("Résultat: " + dechiffrement);

    }*/

    private static void constructPolybeSquare(String cle){
        int ascii = 97;
        codePolybe = new ArrayList<Integer>();
        codeDepolybe = new ArrayList<String>();

        if(cle == ""){
            for(int i=1; i<6; i++){
                for(int j=1; j<6; j++){
                    codePolybe.add(i*10+j);
                    String lettre = "" + (char) ascii;
                    codeDepolybe.add(lettre);

                    if(ascii != 105)
                        ascii++;
                    else{
                        codePolybe.add(i*10+j);
                        codeDepolybe.add("j");
                        ascii +=2;
                    }
                }
            }

        } else {
            int l = 0;
            int length = cle.length();

            for(int i=1; i<6; i++){
                for(int j=1; j<6; j++){
                    if(l< length){

                        String lettre = "" + cle.charAt(l);

                        if(!lettre.equals("i") && !lettre.equals("j")){

                            if(!codeDepolybe.isEmpty() && codeDepolybe.contains(lettre))
                                j--;
                            else{
                                codePolybe.add(i*10+j);
                                codeDepolybe.add(lettre);
                            }

                        } else {

                            if(!codeDepolybe.isEmpty() && codeDepolybe.contains("i") && !codeDepolybe.contains("j")){
                                codePolybe.add(codePolybe.get(i));
                                codeDepolybe.add("j");
                            } else if (!codeDepolybe.isEmpty() && codeDepolybe.contains("i") && !codeDepolybe.contains("j")){
                                codePolybe.add(codePolybe.get(i));
                                codeDepolybe.add("i");
                            } else if (codeDepolybe.isEmpty()){
                                codePolybe.add(i*10+j);
                                codeDepolybe.add("i");
                                codePolybe.add(i*10+j);
                                codeDepolybe.add("j");
                            } else {
                                j--;
                            }

                        }

                        l++;

                    } else {


                        if(ascii != 105){
                            String lettre = "" + (char) ascii;

                            if(codeDepolybe.contains(lettre)){
                                j--;
                            } else {
                                codePolybe.add(i*10+j);
                                codeDepolybe.add(lettre);
                            }

                            ascii++;

                        } else {
                            if(codeDepolybe.contains("i")){
                                j--;
                            } else {
                                codePolybe.add(i*10+j);
                                codeDepolybe.add("i");
                                codePolybe.add(i*10+j);
                                codeDepolybe.add("j");
                            }
                            ascii +=2;
                        }
                    }
                }
            }
        }

        Log.i("Polybe Cipher", "Construction over: " + codeDepolybe.isEmpty() + "/" + codePolybe.isEmpty());
    }

    private static void showPolybeSquare(){
        if(!codeDepolybe.isEmpty() && !codePolybe.isEmpty()){
            for(int i=1; i<6; i++){
                String ligne = "";
                for(int j=1; j<6; j++){

                    int index = codePolybe.indexOf(i*10+j);
                    int index2 = codePolybe.lastIndexOf(i*10+j);

                    if(index == index2){
                        ligne = ligne + codeDepolybe.get(index) + " ";
                    } else {
                        ligne = ligne + codeDepolybe.get(index) + "," + codeDepolybe.get(index2) + " ";
                    }

                }

                Log.i("Polybe Cipher", ligne);
            }
        }

        Log.i("Polybe Cipher", "Show over.");
    }


    public static String polybe(String msg, String cle, boolean code){

        msg = msg.toLowerCase();
        cle = cle.toLowerCase();
        constructPolybeSquare(cle);
        showPolybeSquare();
        Log.i("Polybe Cipher", "Polybe de: " +msg);

        int pos = 0;
        int ascii = 0;
        String newMsg = "";

        if(code){
            for(int i = 0; i<msg.length(); i++){
                ascii = msg.charAt(i);
                if(96 < ascii && ascii < 123){
                    String lettre = "" + msg.charAt(i);
                    String lettreL = "";
                    String lettreC = "";
                    pos = codePolybe.get(codeDepolybe.indexOf(lettre));
                    int unite = (pos % 10);
                    int dizaine = (pos - unite)/10;
                    int random = 0;

                    do{
                        random = (int) Math.ceil(Math.random()*5);
                    }while(random == unite);

                    lettreL = codeDepolybe.get(codePolybe.indexOf(dizaine*10+random));

                    do{
                        random = (int) Math.ceil(Math.random()*5);
                    }while(random == dizaine);

                    lettreC = codeDepolybe.get(codePolybe.indexOf(random*10+unite));
                    newMsg += lettreL + lettreC;

                } else {
                    newMsg = newMsg + msg.charAt(i);
                }
            }

        } else {
            for(int i = 0; i<msg.length(); i+=2){
                ascii = (int) msg.charAt(i);

                if(96 < ascii && ascii < 123){
                    char firstChar = msg.charAt(i);
                    char secondChar = msg.charAt(i+1);

                    int posFirst = codePolybe.get(codeDepolybe.indexOf(""+firstChar));
                    int posSecond = codePolybe.get(codeDepolybe.indexOf(""+secondChar));

                    int lineFirst = posFirst - (posFirst % 10);
                    int colSecond = posSecond % 10;

                    String lettre = codeDepolybe.get(codePolybe.indexOf(lineFirst + colSecond));
                    newMsg += lettre;
                } else {
                    newMsg = newMsg + msg.charAt(i);
                    i--;
                }
            }
        }

        return newMsg;
    }

}
