package com.onlyfy.recomenations.controller;

import com.onlyfy.recomenations.model.RecomendationModel;
import com.onlyfy.recomenations.service.RecomendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recomendations")
public class RecomendationController {

    @Autowired
    private RecomendationService recomendationService;

    @GetMapping
    public List<RecomendationModel> getAllRecomendations() {
        return recomendationService.getAllRecomendations();
    }

    @PostMapping
    public RecomendationModel createRecomendation(@RequestBody RecomendationModel recomendation) {
        return recomendationService.createRecomendation(recomendation);
    }
}
