package com.example.mediatek86formations.modele;

import com.example.mediatek86formations.outils.MesOutils;

import java.util.ArrayList;
import java.util.Date;

public class Formation extends ArrayList<Formation> implements Comparable{

    private int id;
    private Date publishedAt;
    private String title;
    private String description;
    private String miniature;
    private String picture;
    private String videoId;
    private boolean isFavouris;

    public Formation(int id, Date publishedAt, String title, String description, String miniature, String picture, String videoId, boolean isFavouris) {
        this.id = id;
        this.publishedAt = publishedAt;
        this.title = title;
        this.description = description;
        this.miniature = miniature;
        this.picture = picture;
        this.videoId = videoId;
        this.isFavouris = isFavouris;
    }

    public int getId() {
        return id;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    /**
     * retourne la date en String au format jj/MM/yyyy
     * @return La Date converti en chaine
     */
    public String getPublishedAtToString() {
        return MesOutils.convertDateToString(this.publishedAt);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMiniature() {
        return miniature;
    }

    public String getPicture() {
        return picture;
    }

    public String getVideoId() {
        return videoId;
    }

    public boolean getIsFavouris() {
        return isFavouris;
    }

    public void setFavouris(boolean favouris) {
        isFavouris = favouris;
    }

    @Override
    public int compareTo(Object o) {
        return publishedAt.compareTo(((Formation)o).getPublishedAt());
    }

}
