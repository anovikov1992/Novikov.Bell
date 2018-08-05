package ru.bellintegrator.practice.organization.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.model.Organization;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.OrganizationView;
import ru.bellintegrator.practice.organization.view.OrganizationViewList;
import ru.bellintegrator.practice.organization.view.OrganizationViewLoadById;
import ru.bellintegrator.practice.organization.view.OrganizationViewSave;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @ApiOperation(value = "/api/organization/list", nickname = "/api/organization/list", httpMethod = "GET")    //получить организацию по имени
    @GetMapping("/api/organization/list")
    public OrganizationViewList getOrganizationByName(@RequestParam String name,
                                                      @RequestParam (value = "inn", required=false) Long inn,
                                                      @RequestParam (value = "isActive", required=false) boolean isActive) {
        return organizationService.getOrganizationByName(name, inn, isActive); }


    @ApiOperation(value = "api/organization/{id}", nickname = "api/organization/{id}", httpMethod = "GET")      //получить организацию по ID
    @GetMapping("/api/organization/{id}")
    public OrganizationView loadById (@PathVariable Long id) {
        return organizationService.loadById(id);
    }


    @ApiOperation(value = "api/organization/save", nickname = "api/organization/save", httpMethod = "POST")     //добавить организацию
    @PostMapping("/api/organization/save")
    public void add( @RequestParam String name,
                     @RequestParam String fullName,
                     @RequestParam Long inn,
                     @RequestParam Long kpp,
                     @RequestParam String urAddress,
                     @RequestParam (value = "phone", required=false) Long phone,
                     @RequestParam (value = "isActive", required = false) boolean isActive) {
        organizationService.add(name, fullName, inn, kpp, urAddress, phone, isActive); }


    @ApiOperation(value = "update", nickname = "update", httpMethod = "POST")                                   //обновить данные организации
    @PostMapping("/api/organization/update")
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody OrganizationView organization) throws Exception {organizationService.update(organization); }




    @ApiOperation(value = "getAllOrganization", nickname = "getAllOrganization", httpMethod = "GET")                //получить весь список организаций
    @GetMapping("/api/organization/All")
    public List<OrganizationView> getAllOrganization() { return organizationService.getAllOrganization(); }







}
