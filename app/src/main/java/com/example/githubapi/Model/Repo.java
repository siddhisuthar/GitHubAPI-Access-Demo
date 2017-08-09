package com.example.githubapi.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by siddhi on 8/8/17.
 */

public class Repo {
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("language")
    private String language;

    public String getName() {
        return name;
    }

    public Repo(String name, String description, String language) {
        this.setName(name);
        this.setDescription(description);
        this.setLanguage(language);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
