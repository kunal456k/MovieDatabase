package com.kunal456k.moviedatabase.db.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Language {
    @SerializedName("iso_639_1")
    private String isoName;
    @PrimaryKey
    @SerializedName("name")
    @NonNull
    private String languageName;

    public Language(@NonNull String languageName) {
        this.languageName = languageName;
    }

    public String getIsoName() {
        return isoName;
    }

    @NonNull
    public String getLanguageName() {
        return languageName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
}
