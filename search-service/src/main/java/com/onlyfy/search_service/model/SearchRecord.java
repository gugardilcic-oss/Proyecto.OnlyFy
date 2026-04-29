package com.onlyfy.search_service.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "search_records")
public class SearchRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String query;

    @Column(nullable = false)
    private String category; // SONG, ARTIST, PLAYLIST, USER

    @Column(nullable = false)
    private int resultsCount;

    @Column(nullable = false)
    private LocalDateTime searchDate;

    public SearchRecord() {}

    public SearchRecord(int id, String userId, String query, String category,
                        int resultsCount, LocalDateTime searchDate) {
        this.id = id;
        this.userId = userId;
        this.query = query;
        this.category = category;
        this.resultsCount = resultsCount;
        this.searchDate = searchDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getResultsCount() { return resultsCount; }
    public void setResultsCount(int resultsCount) { this.resultsCount = resultsCount; }

    public LocalDateTime getSearchDate() { return searchDate; }
    public void setSearchDate(LocalDateTime searchDate) { this.searchDate = searchDate; }
}