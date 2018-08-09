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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.office.dao.OfficeDao;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import java.util.List;

import static org.junit.Assert.assertEquals;
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

    @Mock
    private OfficeDao officeDao;

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

        when(organizationDao.loadById(1L)).thenReturn(organization);

//        when(organizationDao.getAllOrganization()).thenReturn();
    }

    @Test
    public void verifyLoadById() throws Exception {
//        List<OrganizationView> allOrganization = organizationService.getAllOrganization();
//        allOrganization.get(0);
        OrganizationView view = organizationService.loadById(1L);
        System.out.println(view.id);
        assertEquals(organization.getName(), view.name);

    }
}