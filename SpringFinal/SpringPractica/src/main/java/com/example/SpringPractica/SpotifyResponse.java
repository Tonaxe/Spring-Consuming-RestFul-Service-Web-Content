package com.example.SpringPractica;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyResponse {

    private List<SpotifyAlbum> albums;

    public List<SpotifyAlbum> getAlbums() {
        return albums;
    }

    public void setAlbums(List<SpotifyAlbum> albums) {
        this.albums = albums;
    }
}
