package com.example.psttttuveuxuntwix;

import android.app.Activity;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private final static int CHOIX_NOMBRE = 0;

    private double prixCaisse = 51.0;
    private Button btnCaisse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCaisse = (Button)findViewById(R.id.circle_button_somme);
        btnCaisse.setText(String.format("%.2f", prixCaisse) + "€");
    }
    public void vendreProduit (View v) {
        Intent intentVendre = new Intent(MainActivity.this,VendreProduit.class);
        startActivityForResult(intentVendre,CHOIX_NOMBRE);
    }

    /**
     * Méthode invoquée automatiquement lors du retour d'une activité fille
     */
    @Override
    public void onActivityResult(int codeRequete, int codeResultat,
                                 Intent intention) {
        super.onActivityResult(codeRequete, codeResultat, intention);
        if (codeRequete == CHOIX_NOMBRE) {
            if (codeResultat == Activity.RESULT_OK) {
                int montantVente = intention.getIntExtra(VendreProduit.CLE_PRIX_TOTAL,0);
                prixCaisse += montantVente;
                btnCaisse.setText(String.format("%.2f", prixCaisse) + "€");
            }
        }
    }
}
