package com.example.futloo.cryptomobile;

/**
 * Created by Zanta on 08/04/2018.
 */

import android.util.Log;

import java.util.ArrayList;

public class PlayfairCipher {

    private static ArrayList<Integer> codePolybe;
    private static ArrayList<String> codeDepolybe;
    private static ArrayList<Integer> codePlayfair;
    private static ArrayList<String> codeDeplayfair;

    /*public static void main(String[] args){

        //Playfair
        String cle = "espoir";

        System.out.println("Chiffrement --------------------------");
        String chiffrement = playfair("bur'n ând ïgn<@ite", cle, true);
        System.out.println("Résultat: " + chiffrement);

        System.out.println("");System.out.println("");

        System.out.println("Déchiffrement --------------------------");
        String dechiffrement = playfair("qdmagwldnwuovp", cle, false);
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

                            if(lettre.equals("j")){
                                if (!codeDepolybe.isEmpty() && codeDepolybe.contains("j")){
                                    j--;

                                } else if(!codeDepolybe.isEmpty() && codeDepolybe.contains("i")){
                                    int posOfI = codePolybe.get(codeDepolybe.indexOf("i"));
                                    codePolybe.add(posOfI);
                                    codeDepolybe.add("j");

                                } else {
                                    codePolybe.add(i*10+j);
                                    codeDepolybe.add("i");
                                    codePolybe.add(i*10+j);
                                    codeDepolybe.add("j");
                                }
                            } else {
                                if (!codeDepolybe.isEmpty() && codeDepolybe.contains("i")){
                                    j--;

                                } else if(!codeDepolybe.isEmpty() && codeDepolybe.contains("j")){
                                    int posOfI = codePolybe.get(codeDepolybe.indexOf("j"));
                                    codePolybe.add(posOfI);
                                    codeDepolybe.add("j");

                                } else {
                                    codePolybe.add(i*10+j);
                                    codeDepolybe.add("i");
                                    codePolybe.add(i*10+j);
                                    codeDepolybe.add("j");
                                }
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

        Log.i("Playfair", "Construction over, probleme? " + codeDepolybe.isEmpty() + "/" + codePolybe.isEmpty());
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

                Log.i("Playfair", ligne);
            }
        }

        Log.i("Playfair", "Show over.");
    }

    private static void constructPlayfairSquare(String cle){

        constructPolybeSquare(cle);
        //showPolybeSquare();
        codePlayfair = new ArrayList<Integer>();
        codeDeplayfair = new ArrayList<String>();

        for(int i=1; i<6; i++){
            for(int j=1; j<6; j++){

                int index = codePolybe.indexOf(j*10+i);
                int index2 = codePolybe.lastIndexOf(j*10+i);

                if(index == index2){
                    String value = codeDepolybe.get(index);
                    codePlayfair.add(i*10+j);
                    codeDeplayfair.add(value);
                } else {
                    String value = codeDepolybe.get(index);
                    String value2 = codeDepolybe.get(index2);
                    codePlayfair.add(i*10+j);
                    codeDeplayfair.add(value);
                    codePlayfair.add(i*10+j);
                    codeDeplayfair.add(value2);

                }
            }
        }
    }

    private static void showPlayfairSquare(){
        if(!codeDeplayfair.isEmpty() && !codePlayfair.isEmpty()){
            for(int i=1; i<6; i++){
                String ligne = "";
                for(int j=1; j<6; j++){

                    int index = codePlayfair.indexOf(i*10+j);
                    int index2 = codePlayfair.lastIndexOf(i*10+j);

                    if(index == index2){
                        ligne = ligne + codeDeplayfair.get(index) + " ";
                    } else {
                        ligne = ligne + codeDeplayfair.get(index) + "," + codeDeplayfair.get(index2) + " ";
                    }

                }

                Log.i("Playfair", ligne);
            }
        }

        Log.i("Playfair", "Show over.");
    }

    public static String playfair(String msg, String cle, boolean code){

        cle = cle.toLowerCase();
        constructPlayfairSquare(cle);
        showPlayfairSquare();
        int index = 0;
        int ascii = 0;

        Log.i("Playfair", "original: " + msg);
        msg = msg.toLowerCase();
        msg = formatPlayfair(msg);
        Log.i("Playfair", "formattage: " + msg);

        String newMsg = "";

        if(code){
            for(int i = 0; i<msg.length(); i+=2){

                char firstChar = msg.charAt(i);
                char secondChar = msg.charAt(i+1);

                int posFirst = codePlayfair.get(codeDeplayfair.indexOf(""+firstChar));
                int posSecond = codePlayfair.get(codeDeplayfair.indexOf(""+secondChar));

                int colFirst = posFirst % 10;
                int lineFirst = posFirst - colFirst;
                int colSecond = posSecond % 10;
                int lineSecond = posSecond - colSecond;

                //Ni sur la même ligne, ni sur la même colonne
                if(colFirst != colSecond && lineFirst != lineSecond){
                    String first = codeDeplayfair.get(codePlayfair.indexOf(lineFirst + colSecond));
                    String second = codeDeplayfair.get(codePlayfair.indexOf(lineSecond + colFirst));
                    newMsg += first + second;

                } else if(colFirst == colSecond && lineFirst != lineSecond){
                    //Pas sur la même ligne, mais sur la même colonne
                    //On prend la lettre en dessous
                    lineFirst += 10;
                    if(lineFirst > 50)
                        lineFirst = (lineFirst % 60) + 10;

                    lineSecond += 10;
                    if(lineSecond > 50)
                        lineSecond = (lineSecond % 60) + 10;

                    String first = codeDeplayfair.get(codePlayfair.indexOf(lineFirst + colFirst));
                    String second = codeDeplayfair.get(codePlayfair.indexOf(lineSecond + colSecond));
                    newMsg += first + second;
                } else {
                    //Sur la même ligne, mais pas sur la même colonne
                    //On prend la lettre à droite
                    colFirst++;
                    if(colFirst > 5)
                        colFirst = (colFirst % 6) + 1;

                    colSecond++;
                    if(colSecond > 5)
                        colSecond = (colSecond % 6) + 1;

                    String first = codeDeplayfair.get(codePlayfair.indexOf(lineFirst + colFirst));
                    String second = codeDeplayfair.get(codePlayfair.indexOf(lineSecond + colSecond));
                    newMsg += first + second;
                }
            }

        } else {
            for(int i = 0; i<msg.length(); i+=2){

                char firstChar = msg.charAt(i);
                char secondChar = msg.charAt(i+1);

                int posFirst = codePlayfair.get(codeDeplayfair.indexOf(""+firstChar));
                int posSecond = codePlayfair.get(codeDeplayfair.indexOf(""+secondChar));

                int colFirst = posFirst % 10;
                int lineFirst = posFirst - colFirst;
                int colSecond = posSecond % 10;
                int lineSecond = posSecond - colSecond;

                //Ni sur la même ligne, ni sur la même colonne
                if(colFirst != colSecond && lineFirst != lineSecond){
                    String first = codeDeplayfair.get(codePlayfair.indexOf(lineFirst + colSecond));
                    String second = codeDeplayfair.get(codePlayfair.indexOf(lineSecond + colFirst));
                    newMsg += first + second;

                } else if(colFirst == colSecond && lineFirst != lineSecond){
                    //Pas sur la même ligne, mais sur la même colonne
                    //On prend la lettre en dessous
                    lineFirst -= 10;
                    if(lineFirst < 10)
                        lineFirst = lineFirst + 50;

                    lineSecond -= 10;
                    if(lineSecond < 10)
                        lineSecond = lineSecond + 50;

                    String first = codeDeplayfair.get(codePlayfair.indexOf(lineFirst + colFirst));
                    String second = codeDeplayfair.get(codePlayfair.indexOf(lineSecond + colSecond));
                    newMsg += first + second;
                } else {
                    //Sur la même ligne, mais pas sur la même colonne
                    //On prend la lettre à droite
                    colFirst--;
                    if(colFirst < 1)
                        colFirst = colFirst + 5;

                    colSecond--;
                    if(colSecond < 1)
                        colSecond = colSecond + 5;

                    String first = codeDeplayfair.get(codePlayfair.indexOf(lineFirst + colFirst));
                    String second = codeDeplayfair.get(codePlayfair.indexOf(lineSecond + colSecond));
                    newMsg += first + second;
                }
            }

            newMsg = disformatPlayfair(newMsg);
        }

        return newMsg;
    }

    //https://www.quennec.fr/trucs-astuces/langages/java/remplacer-les-caract%C3%A8res-accentu%C3%A9s-dune-chaine-par-des-caract%C3%A8res-simples
    private static String formatPlayfair(String msg){
        String postMsg = "";
        char splitChar = 'z';
        char oddChar = 'x';
        msg = msg.replace(" ", "");
        StringBuffer result = new StringBuffer();

        if(msg!=null && msg.length()!=0) {
            int index = -1;
            int ascii = 0;
            char c = (char)ascii;
            String chars= "àâäéèêëîïôöùûüç";
            String replace= "aaaeeeeiioouuuc";

            for(int i=0; i<msg.length(); i++) {
                c = msg.charAt(i);
                ascii = c;

                if( (index=chars.indexOf(c))!=-1 )
                    result.append(replace.charAt(index));
                else if(96 < ascii && ascii < 127)
                    result.append(c);
            }
        }

        msg = result.toString();
        int length = msg.length();

        for(int i=0; i<length; i+=2){
            char firstChar = msg.charAt(i);
            char secondChar = oddChar;

            if(i+1 < length){
                secondChar = msg.charAt(i+1);
            }

            if(firstChar != secondChar){
                postMsg = postMsg + firstChar + secondChar;
            } else {
                postMsg = postMsg + firstChar + splitChar;
                i--;
            }
        }

        return postMsg;
    }

    private static String disformatPlayfair(String msg){
        char splitChar = 'z';
        char oddChar = 'x';
        int length = msg.length();
        String postMsg = "";

        for(int i=0; i<length; i++){
            char firstChar = msg.charAt(i);

            if(i+1 != length || msg.charAt(i-1) != oddChar || firstChar != oddChar){

                if(i+1 == length && msg.charAt(i) != oddChar){
                    postMsg += firstChar;
                } else {
                    if(firstChar == splitChar && i>0 && i+1 < length){
                        char postChar = msg.charAt(i+1);
                        char preChar = msg.charAt(i-1);

                        if(postChar != preChar){
                            postMsg += firstChar;
                        }
                    } else if(firstChar != oddChar){
                        postMsg += firstChar;
                    }
                }
            }
        }

        return postMsg;
    }
}

