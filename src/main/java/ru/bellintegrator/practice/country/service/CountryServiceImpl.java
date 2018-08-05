package ru.bellintegrator.practice.country.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.country.dao.CountryDao;
import ru.bellintegrator.practice.country.model.Country;
import ru.bellintegrator.practice.country.view.CountryView;
import ru.bellintegrator.practice.docs.dao.DocDao;
import ru.bellintegrator.practice.docs.model.Doc;
import ru.bellintegrator.practice.docs.service.DocService;
import ru.bellintegrator.practice.docs.view.DocView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final CountryDao countryDao;

    @Autowired
    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }


    @Override
    @Transactional(readOnly = true)
    public List<CountryView> getAllCountry() {
        List<Country> all = countryDao.getAllCountry();
        return all.stream()
                .map(mapCountry())
                .collect(Collectors.toList());
    }
    private Function<Country, CountryView> mapCountry() {
        return p -> {

            CountryView view = new CountryView();
            view.id = Long.valueOf(p.getId());
            view.countryName = p.getCountryName();
            view.countryCode = p.getCountryCode();
            view.citizenshipCode = p.getCitizenshipCode();
            return view;
        };
    }
}

