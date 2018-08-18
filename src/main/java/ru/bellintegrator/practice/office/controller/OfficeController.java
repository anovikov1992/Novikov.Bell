package ru.bellintegrator.practice.office.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeViewLoadById;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.OfficeViewRequest;
import ru.bellintegrator.practice.office.view.OfficeViewSave;
import ru.bellintegrator.practice.advice.ResponseView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /*
    получить офис по ID организации (с помощью @RequestBody)
    */
    @ApiOperation(value = "/api/office/list", nickname = "получить офис по ID организации", httpMethod = "POST")
    @PostMapping("/api/office/list/{orgId}")
    public List<OfficeView> getOfficeListByOrgId(@RequestBody OfficeViewRequest officeViewRequest ) {
        return officeService.getOfficeByOrgId(officeViewRequest); }

    /*
    получить офис по ID
    */
    @ApiOperation(value = "api/office/{id}", nickname = "получить офис по ID", httpMethod = "GET")
    @GetMapping("/api/office/{id}")
    public OfficeViewLoadById loadById (@PathVariable Long id) {
        return officeService.findById(id);
    }

    /*
    обновить данные офисa
    */
    @ApiOperation(value = "update", nickname = "update", httpMethod = "POST")
    @PostMapping("/api/office/update")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseView update(@RequestBody OfficeViewLoadById office) {
        officeService.update(office);
        return new ResponseView("advice");
    }

    /*
    добавить офис
    */
    @ApiOperation(value = "api/organization/save", nickname = "api/organization/save",
            httpMethod = "POST")
    @PostMapping("/api/office/save")
    public void add( @RequestBody OfficeViewSave officeViewSave) {
        officeService.add(officeViewSave);
    }

    /*
    получить весь список офисов
    */
    @ApiOperation(value = "getAllOffice", nickname = "getAllOffice", httpMethod = "GET")
    @GetMapping("/api/office/all")
    public List<OfficeViewLoadById> getAllOffice() {
        return officeService.getAllOffice();
    }

    /*
    удалить офис по ID
    */
    @ApiOperation(value = "deleteOffice", nickname = "deleteOffice", httpMethod = "POST")
    @PostMapping("/api/office/delete/{id}")
    public void delete(@PathVariable Long id) {
        officeService.delete(id);
    }
}