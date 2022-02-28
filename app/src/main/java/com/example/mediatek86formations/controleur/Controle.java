package com.example.mediatek86formations.controleur;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.mediatek86formations.modele.AccesDistant;
import com.example.mediatek86formations.modele.AccesLocal;
import com.example.mediatek86formations.modele.Formation;

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

    /**
     * Retourner une formation
     * @return
     */
    public Formation getFormation() {
        return formation;
    }

    /**
     * set la formation en vue
     * @param formation
     */
    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    /**
     *  Retourne une liste de formations avec boolean isFavoris ou pas por chaque
     * @return  Liste de formations
     */
    public ArrayList<Formation> getLesFormations() {
        return setFavoris(lesFormations);
    }

    /**
     * retourne les formations dont le titre contient le filtre
     * @param filtre
     * @return
     */
    public ArrayList<Formation> getLesFormationFiltre(String filtre){
        Log.d("filter", "started");
        // réinitialiser liste
        ArrayList<Formation> lesFormationsFiltre = new ArrayList<>();
        AccesDistant accesDistant = new AccesDistant();
        accesDistant.envoi("tous", null);
        for(Formation uneFormation : lesFormations){
            if(uneFormation.getTitle().toUpperCase().contains(filtre.toUpperCase())){
                lesFormationsFiltre.add(uneFormation);
            }
        }
        setFavoris(lesFormationsFiltre);
        return setFavoris(lesFormationsFiltre);
    }

    /**
     * filtre et retourne les formations favouris
     * @return
     */
    public ArrayList<Formation> getLesFormationFavouris(String filterText){
        Log.d("filter", "started");
        ArrayList<Formation> lesFormationsFiltre = new ArrayList<>();
        if(!TextUtils.isEmpty(filterText)) {
            lesFormations = getLesFormationFiltre(filterText);
        }

            ArrayList<Integer> Favouris = _AccesLocal.recupFavouris();
            for (Formation uneFormation : lesFormations) {
                if (Favouris.contains(uneFormation.getId())) {
                    lesFormationsFiltre.add(uneFormation);
                }
            }
        setFavoris(lesFormationsFiltre);
        return setFavoris(lesFormationsFiltre);
    }
    /**
     * Enregistrer les formations favoris à partir de la base de données locale
     * @param lesFormations
     * @return
     */
    public ArrayList<Formation> setFavoris(ArrayList<Formation> lesFormations){
        ArrayList<Integer> Favoris = _AccesLocal.recupFavouris();
        SupprimerFavorisOrphelin(lesFormations, Favoris);
        SetFormationsQuiSontFavoris(lesFormations, Favoris);
        return lesFormations;
    }

    /***
     * Set les formations isfavoris = true, lorsqu'elle existent en favoris
     * @param lesFormations
     * @param Favoris
     */
    public void SetFormationsQuiSontFavoris(ArrayList<Formation> lesFormations, ArrayList<Integer> Favoris)
    {
        // verifier si chaque formation est un favoris ou non
        for (Formation f : lesFormations) {
            if(Favoris.contains(f.getId()))
            {
                f.setFavouris(true);
            }
            else
            {
                f.setFavouris(false);
            }
        }
    }
    /**
     * Supprimer les formations favoris qui n'existent plus dans la BDD distante
     * @param lesFormations
     * @param Favouris
     */
     public void SupprimerFavorisOrphelin(ArrayList<Formation> lesFormations,  ArrayList<Integer> Favouris)
     {
         // supprimer les favoris qui ont était supprimé dans la BDD distante
         for(Integer i : Favouris)
         {
             Boolean existes = false;
             for(Formation formation : lesFormations)
             {
                 if(formation.getId() == i)
                 {
                     existes = true;
                     break;
                 }
             }
             if(existes == false)
             {
                 supprFavouris(i);
             }
         }
     }
    /**
     * Set les formations à controle
     * @param lesFormations
     */
    public void setLesFormations(ArrayList<Formation> lesFormations) {
        this.lesFormations = lesFormations;
    }

    /**
     * Supprimer une favoris
     * @param f
     */
    public void supprFavouris(int f) {
        _AccesLocal.suppr(f);
    }

    /**
     * Ajouter une favoris
     * @param f
     */
    public void ajoutFavouris(int f) {
        _AccesLocal.ajout(f);
    }


}
