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

    Switch cesar,vigenere,playfair,hill,homophone,transposition,delastelle,des;//,methode;
    ToggleButton methode;
    EditText message,cle,resultat, cle2, int1, int2, int3, int4;
    int test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        message = (EditText) findViewById(R.id.et_message);
        cle = (EditText) findViewById(R.id.et_clé);
        cle.setVisibility(View.GONE);
        cle2 = (EditText) findViewById(R.id.et_clé2);
        cle2.setVisibility(View.VISIBLE);
        int1 = (EditText) findViewById(R.id.et_int1);
        int1.setVisibility(View.GONE);
        int2 = (EditText) findViewById(R.id.et_int2);
        int2.setVisibility(View.GONE);
        int3 = (EditText) findViewById(R.id.et_int3);
        int3.setVisibility(View.GONE);
        int4 = (EditText) findViewById(R.id.et_int4);
        int4.setVisibility(View.GONE);
        resultat = (EditText) findViewById(R.id.et_resultat);
        //methode = (Switch) findViewById(R.id.sMethode);
        //methode.setText("Crypter");
        methode = (ToggleButton) findViewById(R.id.tbtnMethode);
        methode.setTextOn("Chiffrer");
        methode.setTextOff("Déchiffrer");
        methode.setChecked(true);
        test = 1;


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

                    test++;
                } else {
                    test = 0;
                }
            }
        });

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

                    test++;
                } else {
                    test = 0;
                }
            }
        });

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

                    test++;
                } else {
                    test = 0;
                }
            }
        });

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

                    test++;
                } else {
                    test = 0;
                }
            }
        });

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

                    test++;
                } else {
                    test = 0;
                }
            }
        });

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

                    test++;
                } else {test = 0;}
            }
        });

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

                    test++;
                } else {
                    test = 0;
                }
            }
        });

        des.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                } else {
                    test = 0;
                }
            }
        });

        /*methode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    methode.setText("Décrypter");
                    String contenu = resultat.getText().toString();

                    if(!contenu.equals(""))
                        message.setText(contenu);
                } else {
                    methode.setText("Crypter");
                    String contenu = resultat.getText().toString();

                    if(!contenu.equals(""))
                        message.setText(contenu);
                }
            }
        });*/

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

    public void DoSomething(View view){
        resultat.setText("");
        if(test==1){
            if (message.length() == 0) {
                Toast.makeText(getApplicationContext(), "Veuillez entrer votre message!", Toast.LENGTH_LONG).show();
            } else {
                if (cle.length() == 0 && cle2.length() == 0 && (int1.length() == 0 || int2.length() == 0 || int3.length() == 0 ||int4.length() == 0)) {
                    Toast.makeText(getApplicationContext(), "Veuillez entrer votre clé!", Toast.LENGTH_LONG).show();
                } else {

                    //--CESAR--//
                    if(cesar.isChecked()){
                        if(methode.isChecked()){
                            try {
                                chiffrerCesar(message.getText().toString(),Integer.parseInt(cle2.getText().toString()));
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un entier dans le champ!", Toast.LENGTH_LONG).show();
                                Log.w("MainActivity Error", "cesar " + e.getMessage());
                            }
                        } else {
                            try {
                                dechiffrerCesar(message.getText().toString(),Integer.parseInt(cle2.getText().toString()));
                            } catch (NumberFormatException e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un entier dans le champ!", Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    //--VIGENERE--//
                    if(vigenere.isChecked()){
                        if(methode.isChecked()){
                            try {
                                chiffrerVigenere(message.getText().toString(), cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un mot dans le champ clé!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            try {
                                dechiffrerVigenere(message.getText().toString(), cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un mot dans le champ clé!", Toast.LENGTH_LONG).show();
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
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer des entiers dans les champs!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            try {
                                int one = Integer.parseInt(int1.getText().toString());
                                int two = Integer.parseInt(int2.getText().toString());
                                int three = Integer.parseInt(int3.getText().toString());
                                int four = Integer.parseInt(int4.getText().toString());
                                dechiffrerHill(message.getText().toString(), one, two, three, four);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer des entiers dans les champs!", Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    //--PLAYFAIR--//
                    if(playfair.isChecked()){
                        if(methode.isChecked()){
                            try {
                                chiffrerPlayfair(message.getText().toString(), cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un entier dans le champ!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            try {
                                dechiffrerPlayfair(message.getText().toString(), cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un entier dans le champ!", Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    //--HOMOPHONIQUE--//
                    if(homophone.isChecked()){
                        if(methode.isChecked()){
                            try {
                                chiffrerPolybe(message.getText().toString(),cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un mot dans le champ!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            try {
                                dechiffrerPolybe(message.getText().toString(),cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un mot dans le champ!", Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    //--TRANSPOSITION--//
                    if(transposition.isChecked()){
                        if(methode.isChecked()){
                            try {
                                chiffrerTranspoRect(message.getText().toString(),cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un mot dans le champ!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            try {
                                dechiffrerTranspoRect(message.getText().toString(),cle.getText().toString());
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un mot dans le champ!", Toast.LENGTH_LONG).show();
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
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un mot et des entiers dans les champs!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            try {
                                int longueur = Integer.parseInt(cle2.getText().toString());
                                dechiffrerDelastelle(message.getText().toString(), cle.getText().toString(), longueur);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Veuillez entrer un mot et des entier dans le champs!", Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                    //--DES--//
                }
            }
        } else {
            Toast.makeText(getApplicationContext(), "Veuillez choisir une méthode de cryptage!", Toast.LENGTH_LONG).show();
        }

    }

    public void chiffrerCesar(String message, int decalage) {
        String msgChiffrer = CesarCipher.cesar(message, decalage, true);
        Log.i("Cesar", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    public void dechiffrerCesar(String message, int decalage) {
        String msgDechiffrer = CesarCipher.cesar(message, decalage, false);
        Log.i("Cesar", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }

    public void chiffrerVigenere(String message, String cle) {
        String msgChiffrer = VigenereCipher.vigenere(message, cle, true);
        Log.i("Vigenere", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    public void dechiffrerVigenere(String message, String cle) {
        String msgDechiffrer = VigenereCipher.vigenere(message, cle, false);
        Log.i("Vigenere", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }

    public void chiffrerPolybe(String message, String cle){
        String msgChiffrer = PolybeCipher.polybe(message, cle, true);
        Log.i("Polybe", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    public void dechiffrerPolybe(String message, String cle){
        String msgDechiffrer = PolybeCipher.polybe(message, cle, false);
        Log.i("Polybe", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }

    public void chiffrerPlayfair(String message, String cle){
        String msgChiffrer = PlayfairCipher.playfair(message, cle, true);
        Log.i("Playfair", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    public void dechiffrerPlayfair(String message, String cle){
        String msgDechiffrer = PlayfairCipher.playfair(message, cle, false);
        Log.i("Playfair", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }

    public void chiffrerTranspoRect(String message, String cle){
        String msgChiffrer = RectangularTranspoCipher.transpoRect(message, cle, true);
        Log.i("RectangularTranspo", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    public void dechiffrerTranspoRect(String message, String cle){
        String msgDechiffrer = RectangularTranspoCipher.transpoRect(message, cle, false);
        Log.i("RectangularTranspo", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }

    public void chiffrerHill(String message, int one, int two, int three, int four){
        String msgChiffrer = HillCipher.hill(message, one,two, three, four, true);

        if(!msgChiffrer.isEmpty() && !msgChiffrer.equals("")){
            Log.i("Hill", msgChiffrer);
            resultat.setText(msgChiffrer);
        } else {
            Toast.makeText(getApplicationContext(), "La matrice de chiffrement créée à partir des clés n'est pas réversible", Toast.LENGTH_LONG).show();
        }
    }

    public void dechiffrerHill(String message, int one, int two, int three, int four){
        String msgDechiffrer = HillCipher.hill(message, one,two, three, four, false);

        if(!msgDechiffrer.isEmpty() && !msgDechiffrer.equals("")){
            Log.i("Hill", msgDechiffrer);
            resultat.setText(msgDechiffrer);
        } else {
            Toast.makeText(getApplicationContext(), "La matrice de chiffrement créée à partir des clés n'est pas réversible", Toast.LENGTH_LONG).show();
        }
    }

    public void chiffrerDelastelle(String message, String cle, int longueur){
        String msgChiffrer = DelastelleCipher.delastelle(message, cle, longueur, true);
        Log.i("Delastelle", msgChiffrer);
        resultat.setText(msgChiffrer);
    }

    public void dechiffrerDelastelle(String message, String cle, int longueur){
        String msgDechiffrer = DelastelleCipher.delastelle(message, cle, longueur, false);
        Log.i("Delastelle", msgDechiffrer);
        resultat.setText(msgDechiffrer);
    }
}
