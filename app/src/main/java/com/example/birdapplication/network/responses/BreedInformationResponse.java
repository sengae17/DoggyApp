package com.example.birdapplication.network.responses;

import com.google.gson.annotations.SerializedName;

public class BreedInformationResponse {
    @SerializedName("bred_for")
    String bredFor;

    @SerializedName("breed_group")
    String breedGroup;

    int id;

    String imageUrl;

    @SerializedName("life_span")
    String lifeSpan;

    String name;

    @SerializedName("origin")
    String origin;

    String temperament;

    @SerializedName("weight")
    Weight weight;

    @SerializedName("height")
    Height height;

    public String getBredFor() {
        return bredFor;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getWeight() {
        return weight.metric;
    }

    public String getHeight() {
        return height.metric;
    }
}

class Height {
    String metric;
    String imperial;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getImperial() {
        return imperial;
    }

    public void setImperial(String imperial) {
        this.imperial = imperial;
    }
}

class Weight {
    String imperial;
    String metric;

    public String getImperial() {
        return imperial;
    }

    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}