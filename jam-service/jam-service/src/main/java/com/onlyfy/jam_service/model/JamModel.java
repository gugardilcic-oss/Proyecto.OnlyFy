package com.onlyfy.jam_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;  
import jakarta.persistence.Table;

@Entity
@Table(name = "jams")
public class JamModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String flavor;

    @Column(unique = true, nullable = false)
    private String inviteCode;

    @Column(nullable = false)
    private Long hostUserId;

    @Column(nullable = false)
    private String currentSong;

    public JamModel() {
    }

    public JamModel(String name, String flavor) {
        this.name = name;
        this.flavor = flavor;
    }

    public JamModel(String name, String inviteCode, Long hostUserId, String currentSong) {
        this.name = name;
        this.inviteCode = inviteCode;
        this.hostUserId = hostUserId;
        this.currentSong = currentSong;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Long getHostUserId() {
        return hostUserId;
    }

    public void setHostUserId(Long hostUserId) {
        this.hostUserId = hostUserId;
    }

    public String getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(String currentSong) {
        this.currentSong = currentSong;
    }
}