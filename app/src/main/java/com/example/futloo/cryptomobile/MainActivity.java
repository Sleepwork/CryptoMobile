// 34 00 11 52
package com.example.futloo.cryptomobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    // Eléments de l'interface graphique
    Switch cesar,vigenere,playfair,hill,homophone,transposition,delastelle,des;
    ToggleButton methode;
    EditText message,cle,resultat, cle2, int1, int2, int3, int4;
    boolean anySwitchChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Boutons Switch pour chacun des algos implémentés
        cesar = (Switch) findViewById(R.id.sCesar);
        cesar.setChecked(true);
        vigenere = (Switch) findViewById(R.id.sVigenere);
        playfair = (Switch) findViewById(R.id.sPlayfair);
        hill = (Switch) findViewById(R.id.sHill);
        homophone = (Switch) findViewById(R.id.sHomophone);
        transposition = (Switch) findViewById(R.id.sTransposition);
        delastelle = (Switch) findViewById(R.id.sDelastelle);
        des = (Switch) findViewById(R.id.sDes);
        des.setEnabled(false);

        // Zone de texte pour le message à chiffrer/déchiffrer
        message = (EditText) findViewById(R.id.et_message);

        // Zone de texte pour une clé alphanumérique
        cle = (EditText) findViewById(R.id.et_clé);
        cle.setVisibility(View.GONE);

        // Zone de texte pour une clé numérique
        cle2 = (EditText) findViewById(R.id.et_clé2);
        cle2.setVisibility(View.VISIBLE);

        //  4 zones de textes pour une clé numérique composé de 4 nombres (-> pour Hill)
        int1 = (EditText) findViewById(R.id.et_int1);
        int1.setVisibility(View.GONE);
        int2 = (EditText) findViewById(R.id.et_int2);
        int2.setVisibility(View.GONE);
        int3 = (EditText) findViewById(R.id.et_int3);
        int3.setVisibility(View.GONE);
        int4 = (EditText) findViewById(R.id.et_int4);
        int4.setVisibility(View.GONE);

        // Zone de texte pour le message chiffré/déchiffré
        resultat = (EditText) findViewById(R.id.et_resultat);

        // Toggle Bouton pour choisir entre chiffrer (par défaut) ou déchiffrer
        methode = (ToggleButton) findViewById(R.id.tbtnMethode);
        methode.setTextOn("Chiffrer");
        methode.setTextOff("Déchiffrer");
        methode.setChecked(true);

        // Indication de l'alphabet utilisé par l'algo (césar -> Alaphabet 1)
        message.setHint("Message (Alphabet 1)");

        // Booléen indiquant si un algo de chiffrement a été sélectionné
        anySwitchChecked = true;


        /* Pour chaque bouton switch d'algo :
        *  cas activation:
        *    - Changement de l'indication de l'alphabet utilisé
        *    - Réinitialisation du message chiffré/déchiffré
        *    - Désactivation des autres boutons switchs
        *    - Apparition des zones de texte nécessaires pour la(les) clé(s)
        *    - Disparition des zones de texte inutiles pour la(les) clé(s)
        */
        cesar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message.setHint("Message (Alphabet 1)");
                    resultat.setText("");

                    vigenere.setChecked(false);
                    playfair.setChecked(false);
                    homophone.setChecked(false);
                    hill.setChecked(false);
                    transposition.setChecked(false);
                    delastelle.setChecked(false);
                    des.setChecked(false);

                    cle.setVisibility(View.GONE);
                    cle2.setVisibility(View.VISIBLE);
                    int1.setVisibility(View.GONE);
                    int2.setVisibility(View.GONE);
                    int3.setVisibility(View.GONE);
                    int4.setVisibility(View.GONE);

                    anySwitchChecked = true;
                } else {
                    anySwitchChecked = false;
                }
            }
        });

        // Voir lignes 75-82 pour explications
        vigenere.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message.setHint("Message (Alphabet 1)");
                    resultat.setText("");

                    cesar.setChecked(false);
                    playfair.setChecked(false);
                    homophone.setChecked(false);
                    hill.setChecked(false);
                    transposition.setChecked(false);
                    delastelle.setChecked(false);
                    des.setChecked(false);

                    cle.setVisibility(View.VISIBLE);
                    cle2.setVisibility(View.GONE);
                    int1.setVisibility(View.GONE);
                    int2.setVisibility(View.GONE);
                    int3.setVisibility(View.GONE);
                    int4.setVisibility(View.GONE);

                    anySwitchChecked = true;
                } else {
                    anySwitchChecked = false;
                }
            }
        });

        // Voir lignes 75-82 pour explications
        playfair.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message.setHint("Message (Alphabet 2)");
                    resultat.setText("");

                    cesar.setChecked(false);
                    vigenere.setChecked(false);
                    homophone.setChecked(false);
                    hill.setChecked(false);
                    transposition.setChecked(false);
                    delastelle.setChecked(false);
                    des.setChecked(false);

                    cle.setVisibility(View.VISIBLE);
                    cle2.setVisibility(View.GONE);
                    int1.setVisibility(View.GONE);
                    int2.setVisibility(View.GONE);
                    int3.setVisibility(View.GONE);
                    int4.setVisibility(View.GONE);

                    anySwitchChecked = true;
                } else {
                    anySwitchChecked = false;
                }
            }
        });

        // Voir lignes 75-82 pour explications
        hill.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message.setHint("Message (Alphabet 2)");
                    resultat.setText("");

                    cesar.setChecked(false);
                    vigenere.setChecked(false);
                    homophone.setChecked(false);
                    transposition.setChecked(false);
                    playfair.setChecked(false);
                    delastelle.setChecked(false);
                    des.setChecked(false);

                    cle.setVisibility(View.GONE);
                    cle2.setVisibility(View.GONE);
                    int1.setVisibility(View.VISIBLE);
                    int2.setVisibility(View.VISIBLE);
                    int3.setVisibility(View.VISIBLE);
                    int4.setVisibility(View.VISIBLE);

                    anySwitchChecked = true;
                } else {
                    anySwitchChecked = false;
                }
            }
        });

        // Voir lignes 75-82 pour explications
        homophone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message.setHint("Message (Alphabet 2)");
                    resultat.setText("");

                    cesar.setChecked(false);
                    vigenere.setChecked(false);
                    playfair.setChecked(false);
                    hill.setChecked(false);
                    transposition.setChecked(false);
                    delastelle.setChecked(false);
                    des.setChecked(false);

                    cle.setVisibility(View.VISIBLE);
                    cle2.setVisibility(View.GONE);
                    int1.setVisibility(View.GONE);
                    int2.setVisibility(View.GONE);
                    int3.setVisibility(View.GONE);
                    int4.setVisibility(View.GONE);

                    anySwitchChecked = true;
                } else {
                    anySwitchChecked = false;
                }
            }
        });

        // Voir lignes 75-82 pour explications
       transposition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message.setHint("Message (Alphabet 1)");
                    resultat.setText("");

                    cesar.setChecked(false);
                    vigenere.setChecked(false);
                    playfair.setChecked(false);
                    homophone.setChecked(false);
                    hill.setChecked(false);
                    delastelle.setChecked(false);
                    des.setChecked(false);

                    cle.setVisibility(View.VISIBLE);
                    cle2.setVisibility(View.GONE);
                    int1.setVisibility(View.GONE);
                    int2.setVisibility(View.GONE);
                    int3.setVisibility(View.GONE);
                    int4.setVisibility(View.GONE);

                    anySwitchChecked = true;
                } else {
                    anySwitchChecked = false;
                }
            }
        });

        // Voir lignes 75-82 pour explications
        delastelle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message.setHint("Message (Alphabet 2)");
                    resultat.setText("");

                    cesar.setChecked(false);
                    vigenere.setChecked(false);
                    playfair.setChecked(false);
                    hill.setChecked(false);
                    homophone.setChecked(false);
                    transposition.setChecked(false);
                    des.setChecked(false);

                    cle.setVisibility(View.VISIBLE);
                    cle2.setVisibility(View.VISIBLE);
                    int1.setVisibility(View.GONE);
                    int2.setVisibility(View.GONE);
                    int3.setVisibility(View.GONE);
                    int4.setVisibility(View.GONE);

                    anySwitchChecked = true;
                } else {
                    anySwitchChecked = false;
                }
            }
        });

        // Voir lignes 75-82 pour explications
        // Pour future implémentation du des
        /*des.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    message.setHint("Message (Alphabet 1)");
                    resultat.setText("");

                    cesar.setChecked(false);
                    vigenere.setChecked(false);
                    playfair.setChecked(false);
                    hill.setChecked(false);
                    homophone.setChecked(false);
                    transposition.setChecked(false);
                    delastelle.setChecked(false);

                    anySwitchChecked = true;
                } else {
                    anySwitchChecked = false;
                }
            }
        });*/

        /* En cas de changement de méthode
         *      - Si il existe un message chiffré/déchiffré
         *          alors on le déplace dans la zone de texte message
         *      - Réinitialisation du message chiffré/déchiffré
         */
        methode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    String contenu = resultat.getText().toString();
                    resultat.setText("");

                    if(!contenu.equals(""))
                        message.setText(contenu);
                } else {
                    String contenu = resultat.getText().toString();
                    resultat.setText("");

                    if(!contenu.equals(""))
                        message.setText(contenu);
                }
            }
        });
    }

    // Méthode appelée lors du clic sur le bouton OK
    public void DoSomething(View view){

        // Réinitialisation du message chiffré/déchiffré
        resultat.setText("");

        // Vérification qu'un algo a été sélectionné
        if(anySwitchChecked){

            // Vérification de la présence d'un message à chiffrer/déchiffrer
            if (message.length() == 0) {
                Toast.makeText(getApplicationContext(), "Veuillez entrer votre message!", Toast.LENGTH_LONG).show();
            } else {

                // Vériffication de la présence d'une clé complète -> pour hill 4 zones doivent être remplies
                if (cle.length() == 0 && cle2.length() == 0 && (int1.length() == 0 || int2.length() == 0 || int3.length() == 0 ||int4.length() == 0)) {
                    Toast.makeText(getApplicationContext(), "Veuillez entrer/compléter votre clé!", Toast.LENGTH_LONG).show();
                } else {

                    //--CESAR--//
                    if(cesar.isChecked()){
                        if(methode.isChecked()){
                            try {
                                chiffrerCesar(message.getText().toString(),Integer.parseInt(cle2.getText().toString()));
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un entier dans le champ!", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "cesar " + e.getMessage());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "cesar " + e.getMessage());
                            }
                        } else {
                            try {
                                dechiffrerCesar(message.getText().toString(),Integer.parseInt(cle2.getText().toString()));
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un entier dans le champ!", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "cesar " + e.getMessage());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "cesar " + e.getMessage());
                            }
                        }
                    }

                    //--VIGENERE--//
                    if(vigenere.isChecked()){
                        if(methode.isChecked()){
                            try {
                                chiffrerVigenere(message.getText().toString(), cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "vigenere " + e.getMessage());
                            }
                        } else {
                            try {
                                dechiffrerVigenere(message.getText().toString(), cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "vigenere " + e.getMessage());
                            }

                        }
                    }

                    //--HILL--//
                    if(hill.isChecked()){
                        if(methode.isChecked()){
                            try {
                                int one = Integer.parseInt(int1.getText().toString());
                                int two = Integer.parseInt(int2.getText().toString());
                                int three = Integer.parseInt(int3.getText().toString());
                                int four = Integer.parseInt(int4.getText().toString());
                                chiffrerHill(message.getText().toString(), one, two, three, four);
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer des entiers dans les champs!", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "hill " + e.getMessage());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "hill " + e.getMessage());
                            }
                        } else {
                            try {
                                int one = Integer.parseInt(int1.getText().toString());
                                int two = Integer.parseInt(int2.getText().toString());
                                int three = Integer.parseInt(int3.getText().toString());
                                int four = Integer.parseInt(int4.getText().toString());
                                dechiffrerHill(message.getText().toString(), one, two, three, four);
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer des entiers dans les champs!", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "hill " + e.getMessage());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "hill " + e.getMessage());
                            }

                        }
                    }

                    //--PLAYFAIR--//
                    if(playfair.isChecked()){
                        if(methode.isChecked()){
                            try {
                                chiffrerPlayfair(message.getText().toString(), cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "playfair " + e.getMessage());
                            }
                        } else {
                            try {
                                dechiffrerPlayfair(message.getText().toString(), cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "playfair " + e.getMessage());
                            }

                        }
                    }

                    //--HOMOPHONIQUE--//
                    if(homophone.isChecked()){
                        if(methode.isChecked()){
                            try {
                                chiffrerPolybe(message.getText().toString(),cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "homophonique " + e.getMessage());
                            }
                        } else {
                            try {
                                dechiffrerPolybe(message.getText().toString(),cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "homophonique " + e.getMessage());
                            }

                        }
                    }

                    //--TRANSPOSITION--//
                    if(transposition.isChecked()){
                        if(methode.isChecked()){
                            try {
                                chiffrerTranspoRect(message.getText().toString(),cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "transposition " + e.getMessage());
                            }
                        } else {
                            try {
                                dechiffrerTranspoRect(message.getText().toString(),cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "transposition " + e.getMessage());
                            }

                        }
                    }

                    //--DELASTELLE--//
                    if(delastelle.isChecked()){
                        if(methode.isChecked()){
                            try {
                                int longueur = Integer.parseInt(cle2.getText().toString());
                                chiffrerDelastelle(message.getText().toString(),cle.getText().toString(), longueur);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "delastelle " + e.getMessage());
                            }
                        } else {
                            try {
                                int longueur = Integer.parseInt(cle2.getText().toString());
                                dechiffrerDelastelle(message.getText().toString(), cle.getText().toString(), longueur);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Une erreur est survenue.", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "delastelle " + e.getMessage());
                            }

                        }
                    }

                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Veuillez choisir un algorithme de cryptage!", Toast.LENGTH_LONG).show();
        }

    }

    /* Pour chaque chiffrement/déchiffrement:
     *      - Appel à la fonction  se trouvant dans la classe java correspondante (true pour chiffrer et false pour déchiffrer)
     *      - Affichage du résultat dans la console
     *      - Affichage du résultat dans la zone texte resultat
     */
    public void chiffrerCesar(String message, int decalage) {
        String msgChiffrer = CesarCipher.cesar(message, decalage, true);
        Log.i("Cesar", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    // Voir lignes 530-534 pour explications
    public void dechiffrerCesar(String message, int decalage) {
        String msgDechiffrer = CesarCipher.cesar(message, decalage, false);
        Log.i("Cesar", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }

    // Voir lignes 530-534 pour explications
    public void chiffrerVigenere(String message, String cle) {
        String msgChiffrer = VigenereCipher.vigenere(message, cle, true);
        Log.i("Vigenere", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    // Voir lignes 530-534 pour explications
    public void dechiffrerVigenere(String message, String cle) {
        String msgDechiffrer = VigenereCipher.vigenere(message, cle, false);
        Log.i("Vigenere", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }

    // Voir lignes 530-534 pour explications
    public void chiffrerPolybe(String message, String cle){
        String msgChiffrer = PolybeCipher.polybe(message, cle, true);
        Log.i("Polybe", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    // Voir lignes 530-534 pour explications
    public void dechiffrerPolybe(String message, String cle){
        String msgDechiffrer = PolybeCipher.polybe(message, cle, false);
        Log.i("Polybe", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }

    // Voir lignes 530-534 pour explications
    public void chiffrerPlayfair(String message, String cle){
        String msgChiffrer = PlayfairCipher.playfair(message, cle, true);
        Log.i("Playfair", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    // Voir lignes 530-534 pour explications
    public void dechiffrerPlayfair(String message, String cle){
        String msgDechiffrer = PlayfairCipher.playfair(message, cle, false);
        Log.i("Playfair", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }

    // Voir lignes 530-534 pour explications
    public void chiffrerTranspoRect(String message, String cle){
        String msgChiffrer = RectangularTranspoCipher.transpoRect(message, cle, true);
        Log.i("RectangularTranspo", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    // Voir lignes 530-534 pour explications
    public void dechiffrerTranspoRect(String message, String cle){
        String msgDechiffrer = RectangularTranspoCipher.transpoRect(message, cle, false);
        Log.i("RectangularTranspo", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }

    // Voir lignes 530-534 pour explications
    public void chiffrerHill(String message, int one, int two, int three, int four){
        String msgChiffrer = HillCipher.hill(message, one,two, three, four, true);

        if(!msgChiffrer.isEmpty() && !msgChiffrer.equals("")){
            Log.i("Hill", msgChiffrer);
            resultat.setText(msgChiffrer);
        } else {
            Toast.makeText(getApplicationContext(), "La matrice de chiffrement créée à partir des clés n'est pas réversible", Toast.LENGTH_LONG).show();
        }
    }

    // Voir lignes 530-534 pour explications
    public void dechiffrerHill(String message, int one, int two, int three, int four){
        String msgDechiffrer = HillCipher.hill(message, one,two, three, four, false);

        if(!msgDechiffrer.isEmpty() && !msgDechiffrer.equals("")){
            Log.i("Hill", msgDechiffrer);
            resultat.setText(msgDechiffrer);
        } else {
            Toast.makeText(getApplicationContext(), "La matrice de chiffrement créée à partir des clés n'est pas réversible", Toast.LENGTH_LONG).show();
        }
    }

    // Voir lignes 530-534 pour explications
    public void chiffrerDelastelle(String message, String cle, int longueur){
        String msgChiffrer = DelastelleCipher.delastelle(message, cle, longueur, true);
        Log.i("Delastelle", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    // Voir lignes 530-534 pour explications
    public void dechiffrerDelastelle(String message, String cle, int longueur){
        String msgDechiffrer = DelastelleCipher.delastelle(message, cle, longueur, false);
        Log.i("Delastelle", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }
}
