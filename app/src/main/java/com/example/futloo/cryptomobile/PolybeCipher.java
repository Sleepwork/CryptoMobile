package com.example.futloo.cryptomobile;

/*
 * Projet Sécurité (cryptographie)
 * ZANTARI Laurent  36005825
 * FUTLOO Ayman     34001152
 */

import android.util.Log;

import java.util.ArrayList;

public class PolybeCipher {

    /* Carré de polybe sous forme de 2 tableaux,
    *  un qui contiendra le caractère et l'autre sa position dans le rectangle
    *  La position sera écrite comme pour une matrice (ex: 1,2) en enlevant la virgule (ex: 12)
    *  Utilisation de l'index du tableau comme clé pour trouver l'un à partir de l'autre
    */
    private static ArrayList<Integer> positionPolybe;
    private static ArrayList<Character> caracPolybe;

    // Fonction main pour test sous java
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

    // Fonction de chiffrement/déchiffrement homophonique avec polybe
    public static String polybe(String msg, String cle, boolean code){

        //Conversion de la clé et du msg en minuscules
        msg = msg.toLowerCase();
        cle = cle.toLowerCase();

        //Construction et affichage du carré de polybe
        constructPolybeSquare(cle);
        showPolybeSquare();
        Log.i("Polybe Cipher", "Polybe de: " +msg);

        // Variable qui contiendra le msg chiffré/déchiffré
        String newMsg = "";

        // Chiffrement ou déchiffrement
        if(code){

            // Pour chaque carac.
            for(int i = 0; i<msg.length(); i++){

                // Récupération du carac
                int ascii = msg.charAt(i);

                // Si c'est une lettre alors on la chiffre
                // Sinon on concatène au msg chiffré
                if(96 < ascii && ascii < 123){

                    // Récupération de la position du carac. dans le carré de polybe
                    int pos = positionPolybe.get(caracPolybe.indexOf(msg.charAt(i)));

                    // Récupération du numéro de ligne (dizaine) et colonne (unite)
                    int unite = (pos % 10);
                    int dizaine = (pos - unite)/10;
                    int random;

                    // Choix aléatoire d'une autre colonne sur la même ligne
                    do{
                        random = (int) Math.ceil(Math.random()*5);
                    }while(random == unite);

                    // Récupération d'une lettre aléatoire se trouvant sur la même ligne
                    char lettreL = caracPolybe.get(positionPolybe.indexOf(dizaine*10+random));

                    // Choix aléatoire d'une autre ligne sur la même colonne
                    do{
                        random = (int) Math.ceil(Math.random()*5);
                    }while(random == dizaine);

                    // Récupération d'une lettre aléatoire se trouvant sur la même colonne
                    char lettreC = caracPolybe.get(positionPolybe.indexOf(random*10+unite));

                    // Concaténation des 2 lettres aléatoires au msg chiffré
                    newMsg += "" + lettreL + lettreC;

                } else {
                    newMsg = "" + newMsg + msg.charAt(i);
                }
            }

        } else {

            // Pour chaque couple de carac.
            for(int i = 0; i<msg.length(); i+=2){

                // Récupération du carac
                int ascii = msg.charAt(i);

                // Si c'est une lettre, alors le carac. suivant est aussi une lettre
                // Donc, on peut déchiffrer
                if(96 < ascii && ascii < 123){

                    // Récupération du couple de carac.
                    char firstChar = msg.charAt(i);
                    char secondChar = msg.charAt(i+1);

                    // Récupération de leur position dans le carré de polybe
                    int posFirst = positionPolybe.get(caracPolybe.indexOf(firstChar));
                    int posSecond = positionPolybe.get(caracPolybe.indexOf(secondChar));

                    // Calcul du n° de ligne  du 1er et du n° de colonne du 2eme
                    int lineFirst = posFirst - (posFirst % 10);
                    int colSecond = posSecond % 10;

                    // Récupération du carac. déchiffré
                    char lettre = caracPolybe.get(positionPolybe.indexOf(lineFirst + colSecond));

                    // Concaténation au msg dechiffré
                    newMsg += "" + lettre;
                } else {
                // Sinon on concatène et on analyse le prochain carac.
                    newMsg = "" + newMsg + msg.charAt(i);
                    i--;
                }
            }
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
}
