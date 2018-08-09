package ru.bellintegrator.practice.organization.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.service.OrganizationService;
import ru.bellintegrator.practice.organization.view.*;

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

    /*
    получить организацию по имени
    */

    @ApiOperation(value = "/api/organization/list", nickname = "/api/organization/list", httpMethod = "GET")
    @GetMapping("/api/organization/list")
    public OrganizationViewList getOrganizationByName(@RequestParam String name,
                                                      @RequestParam (value = "inn", required=false) Long inn,
                                                      @RequestParam (value = "isActive", required=false) Boolean isActive) throws Exception {
        return organizationService.getOrganizationByName(name, inn, isActive); }

    /*
    получить организацию по ID
    */
    @ApiOperation(value = "api/organization/{id}", nickname = "api/organization/{id}", httpMethod = "GET")
    @GetMapping("/api/organization/{id}")
    public OrganizationView loadById (@PathVariable Long id) throws Exception {
        return organizationService.loadById(id);
    }

    /*
    обновить данные организации
    */
    @ApiOperation(value = "update", nickname = "update", httpMethod = "POST")
    @PostMapping("/api/organization/update")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> update(@RequestBody OrganizationViewUpdate organization) throws Exception {
            organizationService.update(organization);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("“result”:”success”");
    }

    /*
    добавить организацию
    */
    @ApiOperation(value = "api/organization/save", nickname = "api/organization/save",
            httpMethod = "POST")
    @PostMapping("/api/organization/save")
    @ResponseBody
    public ResponseEntity<String> add( @RequestParam String name,
                     @RequestParam String fullName,
                     @RequestParam Long inn,
                     @RequestParam Long kpp,
                     @RequestParam String urAddress,
                     @RequestParam (value = "phone", required=false) Long phone,
                     @RequestParam Boolean isActive) throws Exception {
        organizationService.add(name, fullName, inn, kpp, urAddress, phone, isActive);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("“result”:”success”");
    }


    /*
    получить весь список организаций
    */
    @ApiOperation(value = "getAllOrganization", nickname = "getAllOrganization", httpMethod = "GET")
    @GetMapping("/api/organization/All")
    public List<OrganizationView> getAllOrganization() { return organizationService.getAllOrganization(); }

    /*
    удалить организацию по ID
    */
    @ApiOperation(value = "deleteOrganization", nickname = "deleteOrganization", httpMethod = "POST")
    @PostMapping("/api/organization/DELETE/{id}")
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        organizationService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("“result”:”success”");
    }
}
