package ru.bellintegrator.practice.organization.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.organization.dao.OrganizationDao;
import ru.bellintegrator.practice.organization.model.Organization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration(value = "src/test/resources")
@Transactional
@DirtiesContext
@AutoConfigureTestDatabase

//@TestPropertySource("classpath:application-test.properties")
public class OrganizationDBTest {

    @Autowired
    private OrganizationDao organizationDao;


    private Organization organization;

    @Before
    public void setup() {
        organization = new Organization();
        organization.setId(100L);
        organization.setName("ООО Test");
        organization.setFullName("Общество с ограниченной ответственностью Test");
        organization.setInn(1236237899L);
        organization.setKpp(123852897L);
        organization.setPhone(32165L);
        organization.setActive(true);
        organization.setUrAddress("address");
        organizationDao.save(organization);
    }

    @Test
    public void testLoadById() {

        Organization organization = organizationDao.loadById(0L);
        assertNotNull(organization);
    }


    @Test
    public void testUpdate() {

        String newName = "ООО Test2";
        organization.setName(newName);
        String newFullName = "Общество с ограниченной ответственностью Test2";
        organization.setFullName(newFullName);

        organizationDao.save(organization);

        Organization updatedOrganization = organizationDao.loadById(organization.getId());

        assertEquals(newName, updatedOrganization.getName());
        assertEquals(newFullName, updatedOrganization.getFullName());
    }


    @Test(expected = Exception.class)
    public void testDelete() {

        Long id = organization.getId();

        organizationDao.delete(id);

        organizationDao.loadById(id);
    }


    @Test(expected = Exception.class)
    public void findNonExistingOrg(){
        Organization nonExistedOrg = new Organization();
        nonExistedOrg.setName("ООО Test1");
        nonExistedOrg.setInn(2224567888L);
        nonExistedOrg.setActive(true);

        organizationDao.getOrganizationByName(nonExistedOrg.getName(), null, null);
    }


    @Test
    public void testFindOrgByName() {

        Organization organizationByName = organizationDao.getOrganizationByName("ООО Test", null, null);

        assertEquals(organizationByName.getName(), organization.getName());
    }
}