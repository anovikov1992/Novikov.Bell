package ru.bellintegrator.practice.country.dao;

import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.docs.model.Doc;

import java.util.List;

public interface CountryDao {

    List<Country> getAllCountry();

    Country getByCitizenshipCode(String citizenshipCode);
}
