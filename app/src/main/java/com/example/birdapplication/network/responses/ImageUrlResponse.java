package com.example.birdapplication.network.responses;

import com.google.gson.annotations.SerializedName;

//displaying how the response looks like
public class ImageUrlResponse {
    @SerializedName("url")
    String imageUrl;

    String width;

    String height;

    public ImageUrlResponse(String url) {
        this.imageUrl = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}