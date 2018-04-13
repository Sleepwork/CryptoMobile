package com.example.futloo.cryptomobile;

/*
 * Projet Sécurité (cryptographie)
 * ZANTARI Laurent  36005825
 * FUTLOO Ayman     34001152
 */

import android.util.Log;

import java.util.ArrayList;

public class PlayfairCipher {

    /* Carré de polybe et carré de playfair chacun sous forme de 2 tableaux,
    *  un qui contiendra le caractère et l'autre sa position dans le rectangle
    *  La position sera écrite comme pour une matrice (ex: 1,2) en enlevant la virgule (ex: 12)
    *  Utilisation de l'index du tableau comme clé pour trouver l'un à partir de l'autre
    */
    private static ArrayList<Integer> positionPolybe;
    private static ArrayList<Character> caracPolybe;
    private static ArrayList<Integer> positionPlayfair;
    private static ArrayList<Character> caracPlayfair;

    // Fonction main pour test sous java
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

    // Fonction de chiffrement/déchiffrement avec playfair
    public static String playfair(String msg, String cle, boolean code){

        //Conversion de la clé en minuscules
        cle = cle.toLowerCase();

        //Construction et affichage du carré de playfair
        constructPlayfairSquare(cle);
        showPlayfairSquare();

        // Formattage du message
        Log.i("Playfair", "original: " + msg);
        msg = msg.toLowerCase();
        msg = formatPlayfair(msg);
        Log.i("Playfair", "formattage: " + msg);

        // Variable qui contiendra le msg chiffré/déchiffré
        String newMsg = "";

        // Chiffrement ou déchiffrement
        if(code){

            // Pour chaque couple de carac.
            for(int i = 0; i<msg.length(); i+=2){

                // Récupération du couple de carac.
                char firstChar = msg.charAt(i);
                char secondChar = msg.charAt(i+1);

                // Récupération de leur position dans le carré de playfair
                int posFirst = positionPlayfair.get(caracPlayfair.indexOf(firstChar));
                int posSecond = positionPlayfair.get(caracPlayfair.indexOf(secondChar));

                // Calcul de la position de la ligne et de la colonne de chacun
                int colFirst = posFirst % 10;
                int lineFirst = posFirst - colFirst;
                int colSecond = posSecond % 10;
                int lineSecond = posSecond - colSecond;

                // Ni sur la même ligne, ni sur la même colonne
                // On prend la lettre se trouvant sur la ligne de l'un et la colonne de l'autre
                if(colFirst != colSecond && lineFirst != lineSecond){
                    char first = caracPlayfair.get(positionPlayfair.indexOf(lineFirst + colSecond));
                    char second = caracPlayfair.get(positionPlayfair.indexOf(lineSecond + colFirst));
                    newMsg += "" + first + second;

                } else if(colFirst == colSecond && lineFirst != lineSecond){
                // Pas sur la même ligne, mais sur la même colonne
                // On prend la lettre en dessous
                    lineFirst += 10;
                    if(lineFirst > 50)
                        lineFirst = (lineFirst % 60) + 10;

                    lineSecond += 10;
                    if(lineSecond > 50)
                        lineSecond = (lineSecond % 60) + 10;

                    char first = caracPlayfair.get(positionPlayfair.indexOf(lineFirst + colFirst));
                    char second =  caracPlayfair.get(positionPlayfair.indexOf(lineSecond + colSecond));
                    newMsg += "" + first + second;
                } else {
                // Sur la même ligne, mais pas sur la même colonne
                // On prend la lettre à droite
                    colFirst++;
                    if(colFirst > 5)
                        colFirst = (colFirst % 6) + 1;

                    colSecond++;
                    if(colSecond > 5)
                        colSecond = (colSecond % 6) + 1;

                    char first = caracPlayfair.get(positionPlayfair.indexOf(lineFirst + colFirst));
                    char second = caracPlayfair.get(positionPlayfair.indexOf(lineSecond + colSecond));
                    newMsg += "" + first + second;
                }
            }

        } else {
            // Pour chaque couple de carac.
            for(int i = 0; i<msg.length(); i+=2){

                // Récupération du couple de carac.
                char firstChar = msg.charAt(i);
                char secondChar = msg.charAt(i+1);

                // Récupération de leur position dans le carré de playfair
                int posFirst = positionPlayfair.get(caracPlayfair.indexOf(firstChar));
                int posSecond = positionPlayfair.get(caracPlayfair.indexOf(secondChar));

                // Calcul de la position de la ligne et de la colonne de chacun
                int colFirst = posFirst % 10;
                int lineFirst = posFirst - colFirst;
                int colSecond = posSecond % 10;
                int lineSecond = posSecond - colSecond;

                // Ni sur la même ligne, ni sur la même colonne
                // On prend la lettre se trouvant sur la ligne de l'un et la colonne de l'autre
                if(colFirst != colSecond && lineFirst != lineSecond){
                    char first = caracPlayfair.get(positionPlayfair.indexOf(lineFirst + colSecond));
                    char second = caracPlayfair.get(positionPlayfair.indexOf(lineSecond + colFirst));
                    newMsg += "" + first + second;

                } else if(colFirst == colSecond && lineFirst != lineSecond){
                // Pas sur la même ligne, mais sur la même colonne
                // On prend la lettre en dessous
                    lineFirst -= 10;
                    if(lineFirst < 10)
                        lineFirst = lineFirst + 50;

                    lineSecond -= 10;
                    if(lineSecond < 10)
                        lineSecond = lineSecond + 50;

                    char first = caracPlayfair.get(positionPlayfair.indexOf(lineFirst + colFirst));
                    char second = caracPlayfair.get(positionPlayfair.indexOf(lineSecond + colSecond));
                    newMsg += "" + first + second;
                } else {
                // Sur la même ligne, mais pas sur la même colonne
                // On prend la lettre à droite
                    colFirst--;
                    if(colFirst < 1)
                        colFirst = colFirst + 5;

                    colSecond--;
                    if(colSecond < 1)
                        colSecond = colSecond + 5;

                    char first = caracPlayfair.get(positionPlayfair.indexOf(lineFirst + colFirst));
                    char second = caracPlayfair.get(positionPlayfair.indexOf(lineSecond + colSecond));
                    newMsg += "" + first + second;
                }
            }

            // On enlève le formattage réalisé lors du chiffrement du msg
            newMsg = disformatPlayfair(newMsg);
        }

        return newMsg;
    }

    // Fonction de création du carré de polybe à partir d'une clé
    private static void constructPolybeSquare(String cle){

        // Initialisation de la variable contenant la position ascii actuelle
        int ascii = 97;

        // Initialisation du carré
        positionPolybe = new ArrayList<>();
        caracPolybe = new ArrayList<>();

        // Si la clé est vide, alors on remplit avec les lettres de l'alphabet
        // Les lettres i et j sont confondues
        if(cle.equals("")){
            for(int i=1; i<6; i++){
                for(int j=1; j<6; j++){

                    // Ajout de la lettre dans le "carré"
                    positionPolybe.add(i*10+j);
                    caracPolybe.add((char) ascii);

                    // Si la lettre n'est pas i, alors on incrémente de 1
                    if(ascii != 105)
                        ascii++;
                    else{
                    // Sinon on ajoute la lettre j également avec la même position
                    // et on incrémente de 2 pour sauter la lettre j
                        positionPolybe.add(i*10+j);
                        caracPolybe.add('j');
                        ascii +=2;
                    }
                }
            }

        } else {
        // Sinon une clé a été fournie,
        // alors on remplit d'abord le carré avec la clé puis par ordre alphabétique

            // Variable contenant la position du caractère à récupérer dans la clé
            int l = 0;

            // Longueur de la clé
            int length = cle.length();

            // Pour chaque emplacement du carré de polybe
            for(int i=1; i<6; i++){
                for(int j=1; j<6; j++){

                    // Si l'on a pas encore parcouru tous les carac. de la clé,
                    // alors on continue en incrémentant l à chaque fois
                    if(l< length){

                        // Récupération de la lettre dans la clé
                        char lettre = cle.charAt(l);

                        // Si la lettre de la clé n'est ni i ou j (lette à confondre)
                        if(lettre != 'i' && lettre != 'j'){

                            // Si la lettre existe déjà dans le carré de polybe,
                            // alors on décrémente la position pour revenir au même emplacement avec une autre lettre
                            if(!caracPolybe.isEmpty() && caracPolybe.contains(lettre))
                                j--;
                            else{
                            // Sinon on ajoute la lettre de la clé et sa position dans le "carré"
                                positionPolybe.add(i*10+j);
                                caracPolybe.add(lettre);
                            }

                        } else {
                        // Sinon on prend la lettre est soit i, soit j

                            // Si la lettre est la lettre j,
                            // alors on doit vérifier son existence ou bien celle de i
                            // Dans le cas où aucune des 2 lettres n'existent, on ajoute les 2
                            if(lettre == 'j'){

                                // Vérification de l'existence de la lettre j dans le carré
                                if (!caracPolybe.isEmpty() && caracPolybe.contains('j')){
                                    j--;

                                } else if(!caracPolybe.isEmpty() && caracPolybe.contains('i')){
                                // Vérification de l'existence de sa lettre confondue i dans le carré
                                    int posOfI = positionPolybe.get(caracPolybe.indexOf('i'));
                                    positionPolybe.add(posOfI);
                                    caracPolybe.add('j');

                                } else {
                                // Vérification de l'existence de la lettre dans le carré
                                    positionPolybe.add(i*10+j);
                                    caracPolybe.add('i');
                                    positionPolybe.add(i*10+j);
                                    caracPolybe.add('j');
                                }
                            } else {
                            // Sinon la lettre est la lettre i,
                            // alors on doit vérifier son existence ou bien celle de j
                            // Dans le cas où aucune des 2 lettres n'existent, on ajoute les 2
                                if (!caracPolybe.isEmpty() && caracPolybe.contains('i')){
                                    j--;

                                } else if(!caracPolybe.isEmpty() && caracPolybe.contains('j')){
                                    int posOfJ = positionPolybe.get(caracPolybe.indexOf('j'));
                                    positionPolybe.add(posOfJ);
                                    caracPolybe.add('j');

                                } else {
                                    positionPolybe.add(i*10+j);
                                    caracPolybe.add('i');
                                    positionPolybe.add(i*10+j);
                                    caracPolybe.add('j');
                                }
                            }


                        }

                        // On incrémente l pour passer au carac. suivant de la clé
                        l++;

                    } else {
                    // Sinon tous les carac. de la clé ont été parcourus
                    // donc on complète le reste du carré avec les lettres de l'alphabet

                        /* Si la lettre n'est pas i,
                         *  alors on vérifie l'existence de la lettre dans le carré
                         *       si elle n'existe pas on l'ajoute
                         *       Sinon on décrémente la position pour revenir au même emplacement avec une autre lettre
                         */
                        if(ascii != 105){
                            // Conversion de la lettre (ascii) en String
                            char lettre = (char) ascii;

                            // Vérification de l'existence de la lettre dans le carré
                            if(caracPolybe.contains(lettre)){
                                j--;
                            } else {
                                positionPolybe.add(i*10+j);
                                caracPolybe.add(lettre);
                            }

                            // Incrémentation de ascii pour passer à la lettre de l'alphabet suivante
                            ascii++;

                        } else {
                        /* Sinon il s'agit de la lettre i
                         *      Si elle existe déjà dans le carré,
                         *      alors on décrémente la position pour revenir au même emplacement avec une autre lettre
                         *      Sinon on l'ajoute avec la lettre j et i et on incrémente de 2 pour sauter j
                         */
                            if(caracPolybe.contains('i')){
                                j--;
                            } else {
                                positionPolybe.add(i*10+j);
                                caracPolybe.add('i');
                                positionPolybe.add(i*10+j);
                                caracPolybe.add('j');
                            }
                            ascii +=2;
                        }
                    }
                }
            }
        }

        // Affichage d'un message dans la console
        Log.i("Playfair", "Construction over, probleme? " + caracPolybe.isEmpty() + "/" + positionPolybe.isEmpty());
    }

    // Fonction pour afficher dans la console le carré de polybe
    private static void showPolybeSquare(){

        // Vérification que le carré a bien été créé au préalable
        if(!caracPolybe.isEmpty() && !positionPolybe.isEmpty()){

            // Pour chaque ligne
            for(int i=1; i<6; i++){
                // Variable qui contiendra le contenu d'une ligne
                String ligne = "";

                // On parcourt chaque colonne
                for(int j=1; j<6; j++){

                    // Récupération de l'index pour la position actuelle
                    int index = positionPolybe.indexOf(i*10+j);
                    int index2 = positionPolybe.lastIndexOf(i*10+j);

                    // Si la 1ère et dernière index sont les mêmes,
                    // alors on récupère le carac. correspondant et le concatène à la ligne
                    if(index == index2){
                        ligne = ligne + caracPolybe.get(index) + " ";
                    } else {
                    // Sinon il s'agit de 2 lettres confondues,
                    // on les affiche toutes les 2 séparées par une virgule
                        ligne = ligne + caracPolybe.get(index) + "," + caracPolybe.get(index2) + " ";
                    }

                }

                // Affichage de la ligne
                Log.i("Playfair", ligne);
            }
        }

        Log.i("Playfair", "Show over.");
    }

    // Fonction de création du carré de playfair
    private static void constructPlayfairSquare(String cle){

        // Création du carré de polybe
        constructPolybeSquare(cle);

        // Initialisation du carré de playfair
        positionPlayfair = new ArrayList<>();
        caracPlayfair = new ArrayList<>();

        // On prend la transposée du carré de polybe pour faire le carré de playfair
        for(int i=1; i<6; i++){
            for(int j=1; j<6; j++){

                // Récupération de l'index pour la position transposée actuelle
                int index = positionPolybe.indexOf(j*10+i);
                int index2 = positionPolybe.lastIndexOf(j*10+i);

                // Si la 1ère et dernière index sont les mêmes,
                // alors on récupère le carac. correspondant et on l'ajoute avec sa position
                if(index == index2){
                    char value = caracPolybe.get(index);
                    positionPlayfair.add(i*10+j);
                    caracPlayfair.add(value);
                } else {
                // Sinon il s'agit de 2 lettres confondues,
                // on les recupère toutes les 2 et on les ajoute à la même position
                    char value = caracPolybe.get(index);
                    char value2 = caracPolybe.get(index2);
                    positionPlayfair.add(i*10+j);
                    caracPlayfair.add(value);
                    positionPlayfair.add(i*10+j);
                    caracPlayfair.add(value2);

                }
            }
        }
    }

    // Fonction pour afficher dans la console le carré de playfair
    private static void showPlayfairSquare(){

        // Vérification que le carré a bien été créé au préalable
        if(!caracPlayfair.isEmpty() && !positionPlayfair.isEmpty()){

            // Pour chaque ligne
            for(int i=1; i<6; i++){
                // Variable qui contiendra le contenu d'une ligne
                String ligne = "";

                // On parcourt chaque colonne
                for(int j=1; j<6; j++){

                    // Récupération de l'index pour la position actuelle
                    int index = positionPlayfair.indexOf(i*10+j);
                    int index2 = positionPlayfair.lastIndexOf(i*10+j);

                    // Si la 1ère et dernière index sont les mêmes,
                    // alors on récupère le carac. correspondant et le concatène à la ligne
                    if(index == index2){
                        ligne = ligne + caracPlayfair.get(index) + " ";
                    } else {
                    // Sinon il s'agit de 2 lettres confondues,
                    // on les affiche toutes les 2 séparées par une virgule
                        ligne = ligne + caracPlayfair.get(index) + "," + caracPlayfair.get(index2) + " ";
                    }

                }

                // Affichage de la ligne
                Log.i("Playfair", ligne);
            }
        }

        Log.i("Playfair", "Show over.");
    }

    // Fonction de formattage d'un msg pour utiliser playfair
    private static String formatPlayfair(String msg){

        // Variable qui contiendra le msg formatté
        String postMsg = "";

        // Caractère à ajouter si la même lettre est répété à la suite
        char splitChar = 'z';

        // Caractère à ajouter si la longueur du msg est impaire
        char oddChar = 'x';

        // Suppression des espaces
        msg = msg.replace(" ", "");

        // StringBuffer qui permettra de formatter le msg et de le recupérer
        StringBuffer result = new StringBuffer();

        // Si le message n'est ni vide ni nul
        if(!msg.isEmpty() && msg.length() != 0) {
            // Variable qui permettra de vérifier l'existence d'un carac. spécial
            int index;

            // Variable pour le carac. et sa valeur ascii
            char c;
            int ascii;

            // Carac. spéciaux et leur remplacement
            String chars= "àâäéèêëîïôöùûüç";
            String replace= "aaaeeeeiioouuuc";

            /* Pour chaque carac.,
             * Si c'est un carac. spécial on prend son remplacement
             * Sinon on ajoute le carac. s'il s'agit d'une lettre
             */
            for(int i=0; i<msg.length(); i++) {
                c = msg.charAt(i);
                ascii = c;

                if( (index=chars.indexOf(c))!=-1 )
                    result.append(replace.charAt(index));
                else if(96 < ascii && ascii < 127)
                    result.append(c);
            }
        }

        // Récupération du msg formatté
        msg = result.toString();

        // Longueur du msg
        int length = msg.length();

        // Pour chaque couple de carac.
        for(int i=0; i<msg.length(); i+=2){

            // Récupération du carac i
            char firstChar = msg.charAt(i);

            // Initialisation de l'autre carac. à la valeur de oddChar
            char secondChar = oddChar;

            // Si le carac. i+1 ne dépasse pas la taille du msg
            // alors on le récupère
            if(i+1 < length){
                secondChar = msg.charAt(i+1);
            }

            // Si le couple comporte 2 carac. différents,
            // alors on les concatène au msg formatté
            if(firstChar != secondChar){
                postMsg = postMsg + firstChar + secondChar;
            } else {
            /* Sinon on concatène le 1er carac. du couple suivi du splitChar,
             * on décrémente i de 1 pour que le 1er carac. du prochain souple soit 2eme carac. du couple qui a été séparé
             * et on décrémente length de 1 pour simuler l'ajout d'un nouveau carac. dans le msg à formater
             */
                postMsg = postMsg + firstChar + splitChar;
                length--;
                i--;
            }
        }

        return postMsg;
    }

    // Fonction pour retirer le formattage
    private static String disformatPlayfair(String msg){
        // Variable qui contiendra le msg original (sans les espaces)
        String postMsg = "";

        // Caractère à ajouter si la même lettre est répété à la suite
        char splitChar = 'z';

        // Caractère à ajouter si la longueur du msg est impaire
        char oddChar = 'x';

        // Longueur du msg formatté
        int length = msg.length();

        // Pour chaque carac.
        for(int i=0; i<length; i++){
            // Récupération du carac. à la position i
            char firstChar = msg.charAt(i);

            /* Si le firstChar n'est pas le dernier carac.
             * ou que firstChar n'est pas oddChar
             * (négation de firstChar est le dernier carac. et est égal à oddChar)
             */
            if(i+1 != length || firstChar != oddChar){

                //Si le carac. actuel est le dernier et est différent de oddChar
                // alors on le concatène
                if(i+1 == length && firstChar != oddChar){
                    postMsg += firstChar;

                } else if(firstChar == splitChar && i>0 && i+1 < length){
                /* Sinon si le carac. actuel est égal au splitChar
                 * alors on vérifie si les carac. avant et après sont les mêmes
                 *      Si oui, alors on ignore le carac. actuel
                 *      Sinon on concatène le carac. actuel
                 */
                    char postChar = msg.charAt(i+1);
                    char preChar = msg.charAt(i-1);

                    if(postChar != preChar){
                        postMsg += firstChar;
                    }
                } else{
                // Sinon on concatène le carac. actuel
                    postMsg += firstChar;
                }
            }
        }

        return postMsg;
    }
}

