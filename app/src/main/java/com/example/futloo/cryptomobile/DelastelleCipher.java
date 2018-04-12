package com.example.futloo.cryptomobile;

/**
 * Created by Zanta on 08/04/2018.
 */

import android.util.Log;

import java.util.ArrayList;

public class DelastelleCipher {

    /* Carré de polybe sous forme de 2 tableaux,
    *  un qui contiendra le caractère et l'autre sa position dans le rectangle
    *  La position sera écrite comme pour une matrice (ex: 1,2) en enlevant la virgule (ex: 12)
    *  Utilisation de l'index du tableau comme clé pour trouver l'un à partir de l'autre
    */
    private static ArrayList<Integer> positionPolybe;
    private static ArrayList<Character> caracPolybe;

    // Fonction main pour test sous java
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

    // Fonction de chiffrement/déchiffrement avec delastelle
    public static String delastelle(String msg, String cle, int longueur, boolean code){

        // Initialisation du tableau qui contiendra le codage numérique
        // de chaque bloc (les uns à la suite des autres) après ou avant mélange
        ArrayList<Integer> codeDelastelle = new ArrayList<>();

        //Conversion de la clé et du msg en minuscules
        msg = msg.toLowerCase();
        cle = cle.toLowerCase();

        //Construction et affichage du carré de polybe
        constructPolybeSquare(cle);
        showPolybeSquare();

        // Formattage du msg
        Log.i("Delastelle", "original : " + msg);
        msg = formatDelastelle(msg, longueur);
        Log.i("Delastelle", "delastelle de: " + msg);

        // Variable qui contiendra le msg chiffré/déchiffré
        String newMsg = "";

        // Calcul du quotient de la division des longueurs du msg et des blocs
        // En cas de nombre à virgule, le cast (int) prendra la valeur arrondi supérieur
        int nbBloc = msg.length() / longueur;

        // Initialisation d'un tableau de String pour contenir le msg découpé en blocs
        String[] bloc = new String[nbBloc];

        // Remplissage du tableau avec les blocs du msg
        for(int i=0; i<nbBloc; i++)
            bloc[i] = msg.substring(i*longueur, (i+1)*longueur);

        // Pour chaque bloc
        for(int i=0; i<nbBloc; i++){

            //Récupération du bloc ième
            String blocMsg = bloc[i];

            // Chiffrement ou déchiffrement
            if(code){

                // On parcours le bloc en 2 fois
                for(int j=0; j+1<(longueur*2); j+=2){

                    // Récupération du couple de carac.
                    char firstChar = blocMsg.charAt(j%longueur);
                    char secondChar = blocMsg.charAt((j+1)%longueur);

                    // Récupération de leur position dans le carré de polybe
                    int posFirst = positionPolybe.get(caracPolybe.indexOf(firstChar));
                    int posSecond = positionPolybe.get(caracPolybe.indexOf(secondChar));

                    // Conversion de la position du 1er carac.
                    // Si l'on est au 1er passage, alors on récupère directement les dizaines
                    // Sinon, on est au 2eme passage, alors on récupère le produit des unités par 10 pour obtenir les dizaines
                    int dizaine;
                    if(j/longueur < 1){
                        dizaine = posFirst - (posFirst % 10);
                    } else {
                        dizaine = (posFirst % 10) * 10;
                    }

                    // Conversion de la position du 2eme carac.
                    // Si l'on est au 1er passage, alors on récupère la division des dizaines par 10 pour obtenir les unités
                    // Sinon, on est au 2eme passage, alors on récupère directement les unités
                    int unite;
                    if((j+1)/longueur < 1){
                        unite = posSecond - (posSecond % 10);
                        unite = unite / 10;
                    } else {
                        unite = (posSecond % 10);
                    }

                    // On ajoute la somme des 2 au tableau delastelle
                    codeDelastelle.add(dizaine + unite);
                }
            } else {

                // Tableaux pour récupérer le codage numérique sans mélange
                ArrayList<Integer> chaineDelastelle1 = new ArrayList<>();
                ArrayList<Integer> chaineDelastelle2 = new ArrayList<>();

                // Pour chaque carac.
                for(int j=0; j<longueur; j++){

                    // Récupération du ième carac.
                    char lettre = blocMsg.charAt(j);

                    // Récupération de sa position dans le carré de polybe
                    int pos = positionPolybe.get(caracPolybe.indexOf(lettre));

                    // Séparation des dizaines et des unités
                    int dizaine = pos - (pos % 10);
                    int unite = pos % 10;

                    /* 'pos' de chaque carac. contient les informations de chiffrage de 2 carac.
                     * Une moitié du msg chiffré concerne la 1ere ligne numérique du bloc
                     * et l'autre moitié la seconde
                     */

                    /* Si la position des dizaines dans le codage numérique mélangé est inférieur à la moitié de la longueur du bloc,
                     * alors on ajoute les dizaines directement dans la 1ère ligne
                     * Sinon on ajoute le quotient des dizaines par 10 dans la 2eme ligne
                     */
                    if(j*2<longueur)
                        chaineDelastelle1.add(dizaine);
                    else
                        chaineDelastelle2.add(dizaine/10);

                    /* Si la position des unités dans le codage numérique mélangé est inférieur à la moitié de la longueur du bloc,
                     * alors on ajoute le produit des unités par 10 dans la 1ère ligne
                     * Sinon on ajoute les unités directement dans la 2eme ligne
                     */
                    if((j*2)+1<longueur)
                        chaineDelastelle1.add(unite*10);
                    else
                        chaineDelastelle2.add(unite);
                }

                // Ajout de la somme de la position i de la 1ère ligne et de la 2eme dans le tableau de codage numérique
                for(int j=0; j<longueur; j++){
                    int pos = chaineDelastelle1.get(j) + chaineDelastelle2.get(j);
                    codeDelastelle.add(pos);
                }
            }
        }

        // Pour chaque position se trouvant dans le tableau de codage numérique
        for(int i=0; i<codeDelastelle.size(); i++){

            // Récupération de la ième position
            int integer = codeDelastelle.get(i);

            // Concaténation du carac. à la ième position dans le carré de polybe avec le msg chiffré/déchiffré
            newMsg += caracPolybe.get(positionPolybe.indexOf(integer));
        }

        return newMsg;
    }

    //https://www.quennec.fr/trucs-astuces/langages/java/remplacer-les-caract%C3%A8res-accentu%C3%A9s-dune-chaine-par-des-caract%C3%A8res-simples
    private static String formatDelastelle(String msg, int longueur){
        // Caractère à ajouter si la longueur du msg est impaire
        char oddChar = 'x';

        // Suppression des espaces
        msg = msg.replace(" ", "");

        // StringBuffer qui permettra de formatter le msg et de le recupérer
        StringBuffer result = new StringBuffer();

        // Si le message n'est ni vide ni nul
        if(msg != null && msg.length() != 0) {

            // Variable qui permettra de vérifier l'existence d'un carac. spécial
            int index;

            // Variable pour le carac. et sa valeur ascii
            int ascii;
            char c;

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
        String postMsg = result.toString();

        // Longueur du msg
        int length = postMsg.length();

        // Tant que la longueur du msg ne sera pas divisible par longueur du bloc
        // On concatène 'oddChar'
        while(length % longueur != 0){
            postMsg += ""+ oddChar;
            length = postMsg.length();
        }

        return postMsg;
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

}

