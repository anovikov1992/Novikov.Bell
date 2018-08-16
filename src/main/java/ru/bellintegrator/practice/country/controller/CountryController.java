package ru.bellintegrator.practice.country.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.country.service.CountryService;
import ru.bellintegrator.practice.country.view.CountryView;
import ru.bellintegrator.practice.docs.service.DocService;
import ru.bellintegrator.practice.docs.view.DocView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "getAllCountry", nickname = "getAllCountry", httpMethod = "GET")
    @GetMapping("/api/countries")
    public List<CountryView> getAllDoc() {
    return countryService.getAllCountry();
}
}
