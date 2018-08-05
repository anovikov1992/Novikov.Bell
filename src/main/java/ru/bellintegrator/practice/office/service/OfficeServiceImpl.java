package ru.bellintegrator.practice.office.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.office.view.OfficeView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;

    public OfficeServiceImpl(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    @Override
    public List<OfficeView> getAllOffice() {
        List<Office> all = officeDao.getAllOffice();
        return all.stream()
                .map(mapOffice())
                .collect(Collectors.toList());
    }
    private Function<Office, OfficeView> mapOffice() {
        return p -> {

            OfficeView view = new OfficeView();
            view.id = Long.valueOf(p.getId());
            view.name = p.getName();
            view.address = p.getAddress();
            view.isActive = p.getIsActive();
            view.organization = p.getOrganization();
            return view;
        };
    }
}

