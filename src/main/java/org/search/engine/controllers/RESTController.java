package org.search.engine.controllers;

import org.search.engine.model.Document;
import org.search.engine.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Set;

@RestController
public class RESTController {

    @Autowired
    DocumentService documentService;

    @RequestMapping(value = "/documents/", method = RequestMethod.GET)
    public ResponseEntity<Map<Long, String>> listAllDocuments() {
        Map<Long, String> documents = documentService.findAllDocuments();
        if (documents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @RequestMapping(value = "/documents/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUser(@PathVariable("id") long id) {
        String document = documentService.findById(id);
        if (document == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @RequestMapping(value = "/documents/tokens/{tokens}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Long>> getUser(@PathVariable("tokens") String tokens) {
        Set<Long> keys = documentService.findByTokens(tokens);
        if (keys.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(keys, HttpStatus.OK);
    }

    @RequestMapping(value = "/document/", method = RequestMethod.POST)
    public ResponseEntity<Void> createDocument(@RequestBody Document document, UriComponentsBuilder ucBuilder) {
        Long id = document.getId();

        if (documentService.isDocumentExist(document.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        documentService.saveDocument(id, document.getText());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/document/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}


