package ru.bellintegrator.practice.country.service;

import ru.bellintegrator.practice.country.view.CountryView;
import ru.bellintegrator.practice.docs.view.DocView;

import java.util.List;

public interface CountryService {

    List<CountryView> getAllCountry();
}
