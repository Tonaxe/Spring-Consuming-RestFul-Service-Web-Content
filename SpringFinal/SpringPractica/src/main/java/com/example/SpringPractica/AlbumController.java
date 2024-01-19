package com.example.SpringPractica;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/album/{id}")
    public String showAlbumInfo(@PathVariable String id, Model model) {
        AlbumInfo albumInfo = spotifyService.getAlbumInfo(id);

        model.addAttribute("albumInfo", albumInfo);

        return "albumInfo";
    }
}
