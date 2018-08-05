package ru.bellintegrator.practice.organization.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.OrganizationViewList;
import ru.bellintegrator.practice.organization.view.OrganizationViewLoadById;
import ru.bellintegrator.practice.organization.view.OrganizationViewSave;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {


    private final OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
    }


  /*  @Override                                                                       //получить организацию по имени
    public Organization loadByName(String Name) {
        return dao.loadByName(Name);
    }*/


    @Override                                                                       //получить организацию по имени
    @Transactional
    public OrganizationViewList getOrganizationByName(String name, Long inn, boolean isActive) {
        OrganizationViewList view = new OrganizationViewList();
        Organization organizationByName = dao.getOrganizationByName(name, inn, isActive);
        view.id = organizationByName.getId();
        view.name = organizationByName.getName();
        view.isActive = organizationByName.getIsActive();
        return view;
    }


    @Override
    public OrganizationView loadById(Long id) {

        OrganizationView organizationView = new OrganizationView();
        Organization organization = dao.loadById(id);

        organizationView.id = organization.getId();
        organizationView.name = organization.getName();
        organizationView.fullName = organization.getFullName();
        organizationView.inn = organization.getInn();
        organizationView.kpp = organization.getKpp();
        organizationView.urAddress = organization.getUrAddress();
        organizationView.phone = organization.getPhone();
        organizationView.isActive = organization.getIsActive();

        return organizationView;
    }


    @Override                                                                       //добавить организацию
    public void add(String name, String fullName, Long inn, Long kpp, String urAddress, Long phone, boolean isActive) {
        Organization organization = null;
     //   if (phone == null) {
     //       organization = new Organization(name, fullName, inn, kpp, urAddress, isActive);
      //  }
      //  else {
            organization = new Organization(name, fullName, inn, kpp, urAddress, phone, isActive);
      //  }
        dao.save(organization);
    }



    /**
     * {@inheritDoc}
     */
    @Override                                                                       //получить весь список организаций
    @Transactional(readOnly = true)
    public List<OrganizationView> getAllOrganization() {
        List<Organization> all = dao.getAllOrganization();

        return all.stream()
                .map(mapOrganization())
                .collect(Collectors.toList());
    }
    private Function<Organization, OrganizationView> mapOrganization() {
        return p -> {
            OrganizationView view = new OrganizationView();
            view.id = Long.valueOf(p.getId());
            view.name = p.getName();
            view.fullName = p.getFullName();
            view.inn = p.getInn();
            view.kpp = p.getKpp();
            view.urAddress = p.getUrAddress();
            view.phone = p.getPhone();
            view.isActive = p.getIsActive();
            return view;
        };
    }




    @Override                                                                       //обновить данные организации
    public void update(OrganizationView organization) throws Exception {
       // validate(organization);
        Organization org = dao.loadById(organization.id);
        if (org == null) {
            throw new NotFoundException("organization not found");
        }
        org.setUrAddress(organization.urAddress);
        org.setInn(organization.inn);
        org.setName(organization.name);
        org.setPhone(organization.phone);
    }
  /*  private void validate(OrganizationView organization) throws Exception {
        if (organization.id ==  null || organization.name == null){
            throw new Exception("field is null");
        }*/
    }
