package com.example.futloo.cryptomobile;

/**
 * Created by Zanta on 08/04/2018.
 */

import android.util.Log;

import java.util.ArrayList;

public class DelastelleCipher {
    private static ArrayList<Integer> codePolybe;
    private static ArrayList<String> codeDepolybe;
    private static ArrayList<Integer> codeDelastelle;


    /*public static void main(String[] args){

        //Delastelle
        String cle = "espoir";

        System.out.println("Chiffrement --------------------------");
        String chiffrement = delastelle("LE LOUP EST ENTRE DANS LA BERGERIE", cle, 11, true);
        System.out.println("Résultat: " + chiffrement);

        System.out.println("");System.out.println("");

        System.out.println("Déchiffrement --------------------------");
        String dechiffrement = delastelle("ffmemuiufcsnscpaoewawfbsezzvriphh", cle, 11, false);
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

        Log.i("Delastelle", "Construction over, probleme? " + codeDepolybe.isEmpty() + "/" + codePolybe.isEmpty());
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

                System.out.println(ligne);
            }
        }

        Log.i("Delastelle", "Show over.");
    }


    public static String delastelle(String msg, String cle, int longueur, boolean code){
        codeDelastelle = new ArrayList<Integer>();
        cle = cle.toLowerCase();
        constructPolybeSquare(cle);
        showPolybeSquare();

        Log.i("Delastelle", "original : " + msg);
        msg = msg.toLowerCase();
        msg = formatDelastelle(msg, longueur);
        Log.i("Delastelle", "delastelle de: " + msg);

        String newMsg = "";
        int nbBloc = msg.length() / longueur;
        String[] bloc = new String[nbBloc];

        for(int i=0; i<nbBloc; i++)
            bloc[i] = msg.substring(i*longueur, (i+1)*longueur);

        for(int i=0; i<nbBloc; i++){
            String blocMsg = bloc[i];

            if(code){
                for(int j=0; j+1<(longueur*2); j+=2){
                    char firstChar = blocMsg.charAt(j%longueur);
                    char secondChar = blocMsg.charAt((j+1)%longueur);
                    int posFirst = codePolybe.get(codeDepolybe.indexOf(""+firstChar));
                    int posSecond = codePolybe.get(codeDepolybe.indexOf(""+secondChar));

                    int dizaine = 0;
                    if(j/longueur < 1){
                        dizaine = posFirst - (posFirst % 10);
                    } else {
                        dizaine = (posFirst % 10) * 10;
                    }

                    int unite = 0;
                    if((j+1)/longueur < 1){
                        unite = posSecond - (posSecond % 10);
                        unite = unite / 10;
                    } else {
                        unite = (posSecond % 10);
                    }

                    codeDelastelle.add(dizaine + unite);
                }
            } else {
                ArrayList<Integer> chaineDelastelle1 = new ArrayList<Integer>();
                ArrayList<Integer> chaineDelastelle2 = new ArrayList<Integer>();
                String intMsg = "";

                for(int j=0; j<longueur; j++){
                    char lettre = blocMsg.charAt(j);
                    int pos = codePolybe.get(codeDepolybe.indexOf(""+lettre));
                    int dizaine = pos - (pos % 10);
                    int unite = pos % 10;

                    if(j*2<longueur)
                        chaineDelastelle1.add(dizaine);
                    else
                        chaineDelastelle2.add(dizaine/10);

                    if((j*2)+1<longueur)
                        chaineDelastelle1.add(unite*10);
                    else
                        chaineDelastelle2.add(unite);
                }

                for(int j=0; j<longueur; j++){
                    int pos = chaineDelastelle1.get(j) + chaineDelastelle2.get(j);
                    codeDelastelle.add(pos);
                }
            }
        }

        for(int i=0; i<codeDelastelle.size(); i++){
            int integer = codeDelastelle.get(i);
            newMsg += codeDepolybe.get(codePolybe.indexOf(integer));
        }

        return newMsg;
    }

    //https://www.quennec.fr/trucs-astuces/langages/java/remplacer-les-caract%C3%A8res-accentu%C3%A9s-dune-chaine-par-des-caract%C3%A8res-simples
    private static String formatDelastelle(String msg, int longueur){
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

        String postMsg = result.toString();
        int length = postMsg.length();

        while(length % longueur != 0){
            postMsg += ""+ oddChar;
            length = postMsg.length();
        }

        return postMsg;
    }

}

