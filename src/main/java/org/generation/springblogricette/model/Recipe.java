package org.generation.springblogricette.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "recipes")

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Title must not be empty!")
    private String title;
    @NotEmpty(message = "Ingredient must not be empty!")
    private String ingredient;
    private String urlFoto;
    @NotEmpty(message = "time of preparation must not be empty!")
    private int timePrep;
    @NotEmpty(message = "portion must not be empty!")
    private int numberPortion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getTimePrep() {
        return timePrep;
    }

    public void setTimePrep(int timePrep) {
        this.timePrep = timePrep;
    }

    public int getNumberPortion() {
        return numberPortion;
    }

    public void setNumberPortion(int numberPortion) {
        this.numberPortion = numberPortion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Lob
    @NotEmpty(message = "description must not be empty!")
    private String description;
}
