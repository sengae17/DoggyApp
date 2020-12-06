package com.example.birdapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//specyfing dog object
@Entity(tableName = "dog_database")
public class Dog implements Parcelable {
    @PrimaryKey(autoGenerate = false)
    private int id;
    private String name;
    private String bredFor;
    private String pictureUrl;
    private String origin;
    private String weight;
    private String height;
    private String temperament;

    public Dog(int id, String name, String bredFor, String pictureUrl, String origin, String weight, String height, String temperament) {
        this.id = id;
        this.name = name;
        this.bredFor = bredFor;
        this.pictureUrl = pictureUrl;
        this.origin = origin;
        this.weight = weight;
        this.height = height;
        this.temperament = temperament;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(bredFor);
        dest.writeString(pictureUrl);
        dest.writeString(origin);
        dest.writeString(weight);
        dest.writeString(height);
        dest.writeString(temperament);
    }

    private Dog(Parcel in) {
        id = in.readInt();
        name = in.readString();
        bredFor = in.readString();
        pictureUrl = in.readString();
        origin = in.readString();
        weight = in.readString();
        height = in.readString();
        temperament = in.readString();
    }

    public static final Parcelable.Creator<Dog> CREATOR = new Parcelable.Creator<Dog>() {

        @Override
        public Dog createFromParcel(Parcel source) {
            return new Dog(source);
        }

        @Override
        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBredFor() {
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public static Creator<Dog> getCREATOR() {
        return CREATOR;
    }
}
