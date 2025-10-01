package friendsofmine.m2.controllers;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.services.ActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.nio.charset.StandardCharsets;

@RestController
public class ActiviteController {

    private ActiviteService activiteService;

    public ActiviteController() {
    }

    @Autowired
    public ActiviteController(ActiviteService activiteService) {
        this.activiteService = activiteService;
    }

    public void setActiviteService(ActiviteService activiteService) {
        this.activiteService = activiteService;
    }

    @GetMapping(value = "/activitesWithResponsable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Activite>> findAllActivitesWithResponsable() {
        MediaType contentType = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        return ResponseEntity
                .ok()
                .contentType(contentType)
                .body(activiteService.findAllActivites());
    }
}
