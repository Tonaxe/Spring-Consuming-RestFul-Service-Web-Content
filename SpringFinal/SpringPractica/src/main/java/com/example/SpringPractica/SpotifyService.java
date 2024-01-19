package com.example.SpringPractica;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders; // Importa desde el paquete correcto
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotifyService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SpotifyApiProperties spotifyApiProperties;

    public AlbumInfo getAlbumInfo(String albumId) {
        String url = spotifyApiProperties.getBaseUrl() + "/albums/?ids=" + albumId;
        System.out.println("URI construida: " + url);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", spotifyApiProperties.getApiKey());

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<SpotifyResponse> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    SpotifyResponse.class
            );

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                SpotifyResponse response = responseEntity.getBody();

                if (response != null && response.getAlbums() != null && !response.getAlbums().isEmpty()) {
                    return mapToAlbumInfo(response.getAlbums().get(0));
                }
            } else {
                // Manejar errores de la respuesta, por ejemplo, loguear el código de estado
                System.out.println("Error en la respuesta: " + responseEntity.getStatusCodeValue());
            }
        } catch (HttpClientErrorException.Unauthorized unauthorizedException) {
            // Manejar el error de autenticación
            System.out.println("Error de autenticación: " + unauthorizedException.getMessage());
        } catch (Exception e) {
            // Manejar otros errores
            e.printStackTrace();
        }

        return null;
    }


    private AlbumInfo mapToAlbumInfo(SpotifyAlbum album) {
        AlbumInfo albumInfo = new AlbumInfo();
        albumInfo.setAlbumName(album.getName());
        albumInfo.setSongNames(getSongNames(album.getTracks()));
        albumInfo.setAlbumImage(getAlbumImage(album.getImages()));
        return albumInfo;
    }

    private List<String> getSongNames(List<SpotifyTrack> tracks) {
        return tracks.stream()
                .map(SpotifyTrack::getName)
                .collect(Collectors.toList());
    }

    private String getAlbumImage(List<SpotifyImage> images) {
        if (images != null && !images.isEmpty()) {
            return images.get(0).getUrl();
        }
        return null;
    }
}
