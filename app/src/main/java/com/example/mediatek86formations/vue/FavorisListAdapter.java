package com.example.mediatek86formations.vue;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mediatek86formations.R;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;
import com.google.android.gms.common.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class FavorisListAdapter extends BaseAdapter {

    private List<Formation> lesFormations;
    private LayoutInflater inflater;
    private Controle controle;
    private Context context;

    /**
     *
     * @param lesFormations
     * @param context
     */
    public FavorisListAdapter(List<Formation> lesFormations, Context context) {
        this.lesFormations = lesFormations;
        this.context = context;
        this.controle = Controle.getInstance(context);
        this.inflater = LayoutInflater.from(context);
    }

    /**
     *
     * @return nombre de formations
     */
    @Override
    public int getCount() {
        return lesFormations.size();
    }

    /**
     *
     * @param i position de l'item
     * @return valeur à cette position
     */
    @Override
    public Object getItem(int i) {
        return lesFormations.get(i);
    }

    /**
     *
     * @param i position de l'item
     * @return id à cette position
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Construction de la ligne
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewProperties viewProperties;
        if(view == null){
            viewProperties = new ViewProperties();
            view = inflater.inflate(R.layout.layout_liste_formations, null);
            viewProperties.txtListeTitle = (TextView)view.findViewById(R.id.txtListTitle);
            viewProperties.txtListPublishedAt = (TextView)view.findViewById(R.id.txtListPublishedAt);
            viewProperties.btnListFavori = (ImageButton)view.findViewById(R.id.btnListFavori);

            view.setTag(viewProperties) ;
        }else{
            viewProperties = (ViewProperties)view.getTag();
        }
        viewProperties.txtListeTitle.setText(lesFormations.get(i).getTitle());
        viewProperties.txtListPublishedAt.setText(lesFormations.get(i).getPublishedAtToString());
        viewProperties.txtListeTitle.setTag(i);
        viewProperties.txtListeTitle.setOnClickListener(v -> ouvrirUneFormationActivity(v));
        viewProperties.txtListPublishedAt.setTag(i);
        viewProperties.txtListPublishedAt.setOnClickListener(v -> ouvrirUneFormationActivity(v));
        viewProperties.btnListFavori.setTag(i);

            if (lesFormations.get(i).getIsFavouris()) {
                viewProperties.btnListFavori.setImageResource(R.drawable.coeur_rouge);
            } else {
                viewProperties.btnListFavori.setImageResource(R.drawable.coeur_gris);
            }
        //app:srcCompat="@drawable/coeur_gris"
        viewProperties.btnListFavori.setOnClickListener(v -> {

            int indice = (int)v.getTag();
            Controle controle = Controle.getInstance(context);
                viewProperties.btnListFavori = (ImageButton)v.findViewById(R.id.btnListFavori);
                if (lesFormations.get(indice).getIsFavouris()) {
                    controle.supprFavouris((lesFormations.get(indice)).getId());
                     lesFormations.remove(indice);
                    notifyDataSetChanged();

                } else {
                    controle.ajoutFavouris((lesFormations.get(indice)).getId());
                    lesFormations.get(indice).setFavouris(true);
                    viewProperties.btnListFavori.setImageResource(R.drawable.coeur_rouge);
                }
            v = inflater.inflate(R.layout.layout_liste_formations, null);


        });
        return view;
    }

    /**
     * Ouvre la page du détail de la formation
     * @param v
     */
    private void ouvrirUneFormationActivity(View v){
        int indice = (int)v.getTag();
        controle.setFormation(lesFormations.get(indice));
        Intent intent = new Intent(context, UneFormationActivity.class);
        context.startActivity(intent);
    }

    /**
     * Propriétés de la ligne
     */
    private class ViewProperties{
        ImageButton btnListFavori;
        TextView txtListPublishedAt;
        TextView txtListeTitle;
    }

}
