package ru.bellintegrator.practice.docs.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.docs.service.DocService;
import ru.bellintegrator.practice.docs.view.DocView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

    @RestController
    @RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    public class DocController {

        private final DocService docService;

        public DocController(DocService docService) {
            this.docService = docService;
        }

        @ApiOperation(value = "getAllDoc", nickname = "getAllUser", httpMethod = "GET")
        @GetMapping("/api/docs")
        public List<DocView> getAllDoc() {
        return docService.getAllDocs();
    }
}
