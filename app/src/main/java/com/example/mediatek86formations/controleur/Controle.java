package com.example.mediatek86formations.controleur;

import android.content.Context;
import android.util.Log;

import com.example.mediatek86formations.modele.AccesDistant;
import com.example.mediatek86formations.modele.AccesLocal;
import com.example.mediatek86formations.modele.Formation;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controle {

    private static Controle instance = null ;
    private ArrayList<Formation> lesFormations = new ArrayList<>();
    private Formation formation = null;
    private static AccesLocal _AccesLocal = null;
    private static Context context;
    /**
     * constructeur privé
     */
    private Controle(){
        super();
    }

    /**
     * récupération de l'instance unique de Controle
     * @return instance
     */
    public static final Controle getInstance(Context context){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            AccesDistant accesDistant = new AccesDistant();
            accesDistant.envoi("tous", null);
            if(context != null) {
                Controle.context = context;
            }
            _AccesLocal = new AccesLocal(Controle.context);
        }

        return Controle.instance;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public ArrayList<Formation> getLesFormations() {
        return setFavouris(lesFormations);
    }

    /**
     * retourne les formations dont le titre contient le filtre
     * @param filtre
     * @return
     */
    public ArrayList<Formation> getLesFormationFiltre(String filtre){
        Log.d("filter", "started");
        ArrayList<Formation> lesFormationsFiltre = new ArrayList<>();
        for(Formation uneFormation : lesFormations){
            if(uneFormation.getTitle().toUpperCase().contains(filtre.toUpperCase())){
                lesFormationsFiltre.add(uneFormation);
            }
        }
        setFavouris(lesFormationsFiltre);
        return setFavouris(lesFormationsFiltre);
    }

    /**
     *
     * @param lesFormations
     * @return
     */
    public ArrayList<Formation> setFavouris(ArrayList<Formation> lesFormations){
        ArrayList<Integer> Favouris = _AccesLocal.recupFavouris();
        for (Integer i : Favouris) {
            Log.d("1favID", String.valueOf(i));
        }
        for (Formation f : lesFormations) {

            if(Favouris.contains(f.getId()))
            {
                Log.d("1favID", String.valueOf(f.getId()) + " true");
                f.setFavouris(true);
            }
            else
            {

                f.setFavouris(false);
            }

        }
        return lesFormations;
    }
    public void setLesFormations(ArrayList<Formation> lesFormations) {
        this.lesFormations = lesFormations;
    }
    public void supprFavouris(int f) {
        _AccesLocal.suppr(f);
    }
    public void ajoutFavouris(int f) {
        _AccesLocal.ajout(f);
    }


}
