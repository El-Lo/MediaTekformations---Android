package com.example.mediatek86formations.vue;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mediatek86formations.*;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;

import java.util.Collections;
import java.util.List;

public class FavorisActivity extends AppCompatActivity {

    private Controle controle;

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
     */
    public void filtrerGo(View v)
    {
        creerListe(((EditText)findViewById(R.id.txtFiltreFavs)).getText().toString());
    }
    /**
     * création de la liste adapter
     */
    private void creerListe(String filterText){
        List<Formation> lesFormations;

        lesFormations = controle.getLesFormationFavouris(filterText);
        if(lesFormations != null){
            Collections.sort(lesFormations, Collections.<Formation>reverseOrder());
            ListView listView = (ListView)findViewById(R.id.lstFormations);
            FavorisListAdapter adapter = new FavorisListAdapter(lesFormations, FavorisActivity.this);
            listView.setAdapter(adapter);
        }
    }



}