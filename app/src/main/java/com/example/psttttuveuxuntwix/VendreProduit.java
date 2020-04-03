package com.example.psttttuveuxuntwix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class VendreProduit extends AppCompatActivity {

    public final static String CLE_PRIX_TOTAL = "android.com.example.jeumemorisation.CLE_PRIX_TOTAL";

    /**
     * Type de friandise
     */
    private Spinner spinnerFriandise;
    private ArrayAdapter<String> adapdateurFriandise;

    /**
     * Client
     */
    private Spinner spinnerClient;
    private ArrayAdapter<String> adapdateurClient;

    /**
     * Quantite
     */
    private Spinner spinnerQuantite;
    private ArrayAdapter<String> adapdateurQuantite;

    /**
     * Total
     */
    private EditText editTextTotal;

    /**
     * Checkbox crédit
     */
    private CheckBox cbCredit;

    /**
     * Constante contenant le prix des friandises
     */
    public final int PRIX_TWIX = 1;
    public final int PRIX_KITKAT = 2;
    public final int PRIX_BUENO = 3;
    public final int PRIX_MARS = 1;
    public final int PRIX_LION = 2;

    /*
    * On initialise la variable qui contiendra le prix total
     */
    public int prixTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendre_produit);

        /* On initialise les attributs */
        spinnerFriandise = (Spinner) findViewById(R.id.spinner_friandise);
        spinnerClient = (Spinner) findViewById(R.id.spinner_client);
        spinnerQuantite = (Spinner) findViewById(R.id.spinner_quantite);
        editTextTotal = (EditText) findViewById(R.id.edit_total);
        cbCredit = (CheckBox) findViewById(R.id.checkbox_choix);

        /* On initialise le spinner des friandises */
        adapdateurFriandise = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Friandises));
        adapdateurFriandise.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFriandise.setAdapter(adapdateurFriandise);

        /* On initialise le spinner des clients */
        adapdateurClient = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Clients));
        adapdateurClient.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClient.setAdapter(adapdateurClient);

        /* On initialise le spinner da la quantité */
        adapdateurQuantite = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Quantite));
        adapdateurQuantite.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQuantite.setAdapter(adapdateurQuantite);

        spinnerFriandise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                calculerPrix();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        spinnerQuantite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                calculerPrix();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        spinnerClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                calculerPrix();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }

    public void clickAnnuler(View v) {

        // on envoie une intention de retour à l'activité principale
        Intent intentionRetour = new Intent();
        setResult(Activity.RESULT_OK,intentionRetour);
        finish();
    }

    public void clickVendre(View v) {
        if (cbCredit.isChecked()){
            // On ne fait rien car c'est une maquette. Sinon on doit ajouté le crédit au client
            Toast.makeText(VendreProduit.this, R.string.messageToastVenteSucces, Toast.LENGTH_LONG).show();
            clickAnnuler(v);
        } else {
            Intent intentionRetour = new Intent();
            intentionRetour.putExtra(CLE_PRIX_TOTAL,prixTotal);
            setResult(Activity.RESULT_OK,intentionRetour);
            Toast.makeText(VendreProduit.this, R.string.messageToastVenteSucces, Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private int calculerPrix () {
        String ProduitSelectionne;
        String ClientSelectionne;
        int quantiteSelectionne;


        // On récupère les selections des différents spinner
        ProduitSelectionne = spinnerFriandise.getSelectedItem().toString();
        ClientSelectionne = spinnerClient.getSelectedItem().toString();
        quantiteSelectionne = Integer.parseInt(spinnerQuantite.getSelectedItem().toString());

        // Switch en fonction de ce qui à été sélectionné par l'utilisateur
        if (ProduitSelectionne != "" && ClientSelectionne != "" &&  spinnerQuantite.getSelectedItem().toString() != "") {

            switch(ProduitSelectionne) {
                case "Twix":
                    prixTotal = PRIX_TWIX*quantiteSelectionne;
                    break;
                case "Kit Kat":
                    prixTotal = PRIX_KITKAT*quantiteSelectionne;
                    break;
                case "Kinder Bueno":
                    prixTotal = PRIX_BUENO*quantiteSelectionne;
                    break;
                case "Mars":
                    prixTotal = PRIX_MARS*quantiteSelectionne;
                    break;
                case "Lion":
                    prixTotal = PRIX_LION*quantiteSelectionne;
                    break;
            }
        }else{
            // On affiche un toast
            Toast.makeText(VendreProduit.this, R.string.messageToastErreurSaisieVendreProduit, Toast.LENGTH_SHORT).show();
        }
        // On affecte le prix total à l'edit text
        editTextTotal.setText(Integer.toString(prixTotal));
        return prixTotal;
    }
}
