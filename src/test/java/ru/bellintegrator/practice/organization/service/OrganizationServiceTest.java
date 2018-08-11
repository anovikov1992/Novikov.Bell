package ru.bellintegrator.practice.organization.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.organization.MyException.OrgOutException;
import ru.bellintegrator.practice.organization.MyException.OrganisationValidationException;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.OrganizationViewList;
import ru.bellintegrator.practice.organization.view.OrganizationViewSave;
import ru.bellintegrator.practice.organization.view.OrganizationViewUpdate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class OrganizationServiceTest {

    private Organization organization;


    @TestConfiguration
    static class OrganizationServiceImplTestContextConfiguration {

        @Bean
        public OrganizationService organizationService(){
            return new OrganizationServiceImpl();
        }

    }

    @Autowired
    @InjectMocks
    private OrganizationService organizationService;

    @Mock
    private OrganizationDao organizationDao;


    @Before
    public void setup() {

        organization = new Organization();
        organization.setId(1L);
        organization.setName("ООО Test");
        organization.setFullName("Общество с ограниченной ответственностью Test");
        organization.setInn(1234567899L);
        organization.setKpp(123456897L);
        organization.setPhone(3216589665L);
        organization.setActive(true);
    }

    @Test
    public void verifyLoadOrganizationByName() throws Exception {

        when(organizationDao.getOrganizationByName("ООО Test", null,null)).thenReturn(organization);
        OrganizationViewList view = organizationService.getOrganizationByName("ООО Test", null, null);
        assertEquals(organization.getName(), view.name);

    }


    @Test(expected = OrgOutException.class)
    public void verifyExceptionWhenOrganizationWithThisNameIsNotExists() throws Exception {
        when(organizationDao.getOrganizationByName("ООО Test123", null, null)).thenThrow(OrganisationValidationException.class);
        organizationService.getOrganizationByName("ООО Test123", null, null);
    }

    @Test
    public void verifyLoadById() throws Exception {

        when(organizationDao.loadById(1L)).thenReturn(organization);
        OrganizationView view = organizationService.loadById(1L);
        assertEquals(organization.getName(), view.name);

    }


    @Test(expected = OrgOutException.class)
    public void verifyExceptionWhenOrganizationIsNotExists() {
        when(organizationDao.loadById(100L)).thenThrow(OrganisationValidationException.class);
        organizationService.loadById(1L);
    }


    @Test
    public void verifySave() throws Exception {
        OrganizationViewSave view = new OrganizationViewSave();

        view.name = "ООО Test123";
        view.fullName = "Общество с ограниченной ответственностью Test123";
        view.inn = "1233213211";
        view.kpp = "123321321";
        view.urAddress = "Покровская";
        view.phone = "79202222222";
        view.isActive = true;

        doNothing().when(organizationDao).save(any(Organization.class));

        organizationService.add(view);

        verify(organizationDao).save(any(Organization.class));
    }

    @Test(expected = OrganisationValidationException.class)
    public void verifySaveExceptionInn() throws Exception {
        OrganizationViewSave view = new OrganizationViewSave();

        view.name = "ООО Test123";
        view.fullName = "Общество с ограниченной ответственностью Test123";
        view.inn = "12";
        view.kpp = "123321321";
        view.urAddress = "Покровская";
        view.phone = "79202222222";
        view.isActive = true;

        organizationService.add(view);
    }

    @Test
    public void verifyDelete(){
        Long id = 1L;

        doNothing().when(organizationDao).delete(id);

        organizationService.delete(id);

        verify(organizationDao).delete(id);
    }

    @Test
    public void verifyGetAllOrganization() {

        Organization organization1 = new Organization();

        organization1.setName("ООО Test");
        organization1.setFullName("Общество с ограниченной ответственностью Test");
        organization1.setInn(1233213211L);
        organization1.setKpp(123321321L);
        organization1.setUrAddress("Покровская");
        organization1.setPhone(79205555555L);
        organization1.setActive(true);

        Organization organization2 = new Organization();
        organization2.setName("ООО Test1");
        organization2.setFullName("Общество с ограниченной ответственностью Test1");
        organization2.setInn(1233213555L);
        organization1.setKpp(123852321L);
        organization1.setUrAddress("Покровская2");
        organization1.setPhone(79205777755L);
        organization2.setActive(true);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization1);
        organizations.add(organization2);
     //   System.out.println("Лист организаций - " + organizations);


        when(organizationDao.getAllOrganization()).thenReturn(organizations);
        List<OrganizationView> organizationViews = organizationService.getAllOrganization();


        assertEquals(organization1.getName(), organizationViews.get(0).name);

    }

}