package com.onlyfy.music_category_service.dto;

public class MusicCategoryResponseDTO {

    private int id;
    private String name;
    private String description;
    private String genre;
    private String imageUrl;
    private boolean active;

    public MusicCategoryResponseDTO() {}

    public MusicCategoryResponseDTO(int id, String name, String description,
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