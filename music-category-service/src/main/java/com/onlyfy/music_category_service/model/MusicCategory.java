package com.onlyfy.music_category_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "music_categories")
public class MusicCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String genre; // POP, ROCK, JAZZ, ELECTRONIC, HIP_HOP, CLASSICAL

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private boolean active;

    public MusicCategory() {}

    public MusicCategory(int id, String name, String description,
                         String genre, String imageUrl, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.imageUrl = imageUrl;
        this.active = active;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}