package com.example.mediatek86formations.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mediatek86formations.outils.MesOutils;
import com.example.mediatek86formations.outils.MySQLiteOpenHelper;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class AccesLocal {

    private String nomBase = "bdTekformations.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    /**
     * constructeur : valorise l'accès à la BDD
     * @param context
     */
    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);
    }

    /**
     * ajout d'une cle formation dans la BDD
     * @param IDFormation
     */
    public void ajout(Integer IDFormation){
        Log.d("idformation", IDFormation.toString());
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idFormation", IDFormation);
        bd.insert("favouris", null, values);
        bd.close(); 
    }
    /**
     * ajout d'une cle formation dans la BDD
     * @param IDFormation
     */
    public void suppr(Integer IDFormation){
        Log.d("suppr idformation", IDFormation.toString());
        bd = accesBD.getWritableDatabase();
        String[] whereArgs = new String[] { String.valueOf(IDFormation) };
        bd.delete("favouris", "idFormation = ?", whereArgs);
        bd.close();
    }

    /**
     * retourne les favouris enregistrés dans la BDD
     * @return dernier profil
     */
    public ArrayList<Integer> recupFavouris(){
        Log.d("recup", "started");
        ArrayList<Integer> favouris = new ArrayList<Integer>();
        bd = accesBD.getReadableDatabase();
        String req = "select idFormation from favouris";
        Cursor curseur = bd.rawQuery(req, null);
        if (curseur.moveToFirst()) {
            do {
                Integer FavID = curseur.getInt(0);

                favouris.add(FavID);

            } while (curseur.moveToNext());
        }
        curseur.close();
        Log.d("recup done",  String.valueOf(favouris.size()));
        return favouris;
    }

}
