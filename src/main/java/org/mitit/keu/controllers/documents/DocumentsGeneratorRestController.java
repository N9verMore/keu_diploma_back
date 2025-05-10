package org.mitit.keu.controllers.documents;

import org.mitit.keu.core.service.DocumentGeneratorService;
import org.mitit.keu.core.service.MilitaryManService;
import org.mitit.keu.core.entities.MilitaryMan;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class DocumentsGeneratorRestController {

    public final MilitaryManService militaryManService;

    public final DocumentGeneratorService generatorService;

    @GetMapping("/downloadExhaustFromKEU/{id}")
    public ResponseEntity<InputStreamResource> download(@PathVariable("id") Long milManId){
        MilitaryMan militaryMan;
        if (militaryManService.findById(milManId).isPresent()) {
            militaryMan = militaryManService.findById(milManId).get();

            String pathToGeneratedFile = generatorService.generateExhaustFromKEU(militaryManService.getExhaustInfo(militaryMan), militaryMan.getSurname());
            File file = new File(pathToGeneratedFile);

            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

            InputStreamResource resource = null;
            try {
                resource = new InputStreamResource(Files.newInputStream(file.toPath()));

            } catch (IOException e) {
                return ResponseEntity.badRequest().body(null);
            }

            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        }
        return ResponseEntity.badRequest().body(null);
    }
}
