package org.generation.springblogricette.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "recipes")

public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Title must not be empty!")
    private String title;
    @NotBlank(message = "Ingredient must not be empty!")
    private String ingredient;
    @Lob
    @Column(length = 500)
    private String urlFoto;
    @NotNull(message = "time of preparation must not be empty!")
    private int timePrep;
    @NotNull(message = "portion must not be empty!")
    private int numberPortion;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

}
