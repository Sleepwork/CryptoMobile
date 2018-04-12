package com.example.futloo.cryptomobile;

import android.util.Log;

/**
 * Created by Zanta on 08/04/2018.
 */


public class HillCipher {

    // Fonction main pour test sous java
    /*public static void main(String[] args){

        //Hill
        System.out.println("Chiffrement --------------------------");
        String chiffrement = hill("election", 3, 3, 1, 2, true);
        System.out.println("Résultat: " + chiffrement);

        System.out.println("");System.out.println("");

        System.out.println("Déchiffrement --------------------------");
        String dechiffrement = hill("W?<-{H{M", 3, 3, 1, 2, false);
        System.out.println("Résultat: " + dechiffrement);

    }*/

    // Fonction de chiffrement/déchiffrement avec hill
    public static String hill(String msg, int a, int b, int c, int d, boolean code){

        //Calcul du determinant
        int determinant = a * d - b * c;

        // Initialisation du booléen indiquant si la matrice de chiffrement est réversible
        boolean reversibility = true;

        // Si le chiffrement n'est pas réversible,
        // alors on passe 'reversibility' à false
        if(determinant%2==0 || determinant%47==0){
            Log.i("Hill", "Chiffrement non réversible");
            reversibility = false;
        } else {
            Log.i("Hill", "Determinant: " + determinant);
        }

        // Valeur du modulo
        int mod = 95;

        // Valeur à décrémenter aux valeurs ascii des carac. pour travailler sur 0-94
        int decalage = 32;

        // Formattage du msg et affichage
        msg = formatHill(msg);
        System.out.println(msg);

        // Variable qui contiendra le msg chiffré/déchiffré
        String newMsg = "";

        // Chiffrement ou déchiffrement
        if(code && reversibility){
            // Affichage de la matrice de chiffrement en console
            Log.i("Hill", "Matrice de chiffrement: " + a + " " + b + " " + c + " " + d);

            // Pour chaque couple de carac.
            for(int i = 0; i<msg.length(); i+=2){

                // Récupération du couple
                char firstChar = msg.charAt(i);
                char secondChar = msg.charAt(i+1);

                // Récupération de leur ascii
                int firstCharPos = firstChar;
                int secondCharPos = secondChar;
                firstCharPos -= decalage;
                secondCharPos -= decalage;

                // Calcul de y1 et y2
                int yFirst = firstCharPos * a + secondCharPos * b;
                int ySecond = firstCharPos * c + secondCharPos * d;

                // Modulo 'mod' et incrémentation par décalage pour obtenir l'ascii correct
                yFirst = (yFirst % mod) + decalage;
                ySecond = (ySecond % mod) + decalage;

                // Récupération des caractères chiffrés
                char yFirstChar = (char) yFirst;
                char ySecondChar = (char) ySecond;

                // Concaténation des caractères chiffrés
                newMsg += "" + yFirstChar + ySecondChar;
            }

        } else if(reversibility){

            // Calcul de l'inverse du déterminant modulo 'mod'
            int invDet = invDet(determinant, mod);
            Log.i("Hill", "Inverse multiplicative modulaire: " + invDet);

            // Calcul de la matrice inverse pour déchiffrer
            int invA = modulo(invDet*d, mod);
            int invB = modulo(invDet*-b, mod);
            int invC = modulo(invDet*-c, mod);
            int invD = modulo(invDet*a, mod);
            Log.i("Hill", "Matrice de déchiffrement: " + invA + " " + invB + " " + invC + " " + invD);

            // Pour chaque couple de carac.
            for(int i = 0; i<msg.length(); i+=2){
                char firstChar = msg.charAt(i);
                char secondChar = msg.charAt(i+1);
                int firstCharPos = firstChar;
                int secondCharPos = secondChar;

                firstCharPos -= decalage;
                secondCharPos -= decalage;


                int xFirst = firstCharPos * invA + secondCharPos * invB;
                int xSecond = firstCharPos * invC + secondCharPos * invD;

                xFirst = (xFirst % mod) + decalage;
                xSecond = (xSecond % mod) + decalage;

                char xFirstChar = (char) xFirst;
                char xSecondChar = (char) xSecond;

                newMsg += "" + xFirstChar + xSecondChar;
            }
        }

        return newMsg;
    }

    //https://www.quennec.fr/trucs-astuces/langages/java/remplacer-les-caract%C3%A8res-accentu%C3%A9s-dune-chaine-par-des-caract%C3%A8res-simples
    private static String formatHill(String msg){
        char oddChar = 'x';
        String formatMsg = msg;

        if(formatMsg.length()%2==1)
            formatMsg += oddChar;

        StringBuffer result = new StringBuffer();

        if(!formatMsg.isEmpty() && formatMsg.length()!=0) {
            int index;
            char c;
            String chars= "àâäéèêëîïôöùûüç";
            String replace= "aaaeeeeiioouuuc";

            for(int i=0; i<formatMsg.length(); i++) {
                c = formatMsg.charAt(i);

                if( (index=chars.indexOf(c))!=-1 )
                    result.append(replace.charAt(index));
                else
                    result.append(c);
            }
        }

        formatMsg = result.toString();
        return formatMsg;
    }

    //Fonction pour exécuter le modulo (normal + sur nombre négatif)
    private static int modulo(int x, int mod){
        x = x%mod;

        while(x<0)
            x += mod;

        return x;
    }

    //Fonction pour calculer l'inverse modulo 'mod' du déterminant
    private static int invDet(int determinant, int mod){
        int invDeterminant = 1;
        int cong = modulo(determinant, mod);

        while(cong != 1 && invDeterminant < 100){
            invDeterminant++;
            cong = modulo(determinant*invDeterminant, mod);
        }

        return invDeterminant;
    }
}

