package com.example.mediatek86formations.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mediatek86formations.R;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;

import java.util.ArrayList;
import java.util.Collections;

public class FormationsActivity extends AppCompatActivity {

    private Controle controle;
    private Button btnFiltrer;
    private EditText txtFiltre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations);
        init();
    }

    /**
     * initialisations
     */
    private void init(){
        controle = Controle.getInstance(this);
        creerListe("");

    }
    /**
     * OnClick Event Handler pour filtrer
     * @param v
     */
    public void filtrerGo(View v)
    {
        creerListe(((EditText)findViewById(R.id.txtFiltreFavs)).getText().toString());
    }
    /**
     * création de la liste adapter
     */
    private void creerListe(String filterText){
        ArrayList<Formation> lesFormations = null;
        if(TextUtils.isEmpty(filterText)) {
           lesFormations = controle.getLesFormations();
        }
        else
        {
        Log.d("goToFormationsFilter","going");
            lesFormations = controle.getLesFormationFiltre(filterText);
        }
        if(lesFormations != null){
            Collections.sort(lesFormations, Collections.<Formation>reverseOrder());
            ListView listView = (ListView)findViewById(R.id.lstFormations);
            FormationListAdapter adapter = new FormationListAdapter(lesFormations,FormationsActivity.this);
            listView.setAdapter(adapter);
        }
    }



}