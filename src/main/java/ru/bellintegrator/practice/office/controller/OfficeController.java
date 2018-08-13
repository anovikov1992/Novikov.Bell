package ru.bellintegrator.practice.office.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.office.service.OfficeService;
import ru.bellintegrator.practice.office.view.OfficeViewLoadById;
import ru.bellintegrator.practice.office.view.OfficeView;
import ru.bellintegrator.practice.office.view.OfficeViewSave;
import ru.bellintegrator.practice.organization.ResponseSuccess.ResponseView;

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
    получить офис по ID организации
    */

    @ApiOperation(value = "/api/office/list", nickname = "получить офис по ID организации", httpMethod = "GET")
    @GetMapping("/api/office/list")
    public List<OfficeView> getOfficeByOrgId(@RequestParam Long orgId,
                                             @RequestParam (value = "name", required=false) String name,
                                             @RequestParam (value = "phone", required=false) String phone,
                                             @RequestParam (value = "isActive", required=false) Boolean isActive) throws Exception {
        return officeService.getOfficeByOrgId(orgId, name, phone, isActive); }

    /*
    получить офис по ID
    */
    @ApiOperation(value = "api/office/{id}", nickname = "получить офис по ID", httpMethod = "GET")
    @GetMapping("/api/office/{id}")
    public OfficeViewLoadById loadById (@PathVariable Long id) {
        return officeService.loadById(id);
    }

    /*
    обновить данные офисa
    */
    @ApiOperation(value = "update", nickname = "update", httpMethod = "POST")
    @PostMapping("/api/office/update")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseView update(@RequestBody OfficeViewLoadById office) throws Exception {
        officeService.update(office);
        return new ResponseView("success");
    }

    /*
    добавить офис
    */
    @ApiOperation(value = "api/organization/save", nickname = "api/organization/save",
            httpMethod = "POST")
    @PostMapping("/api/office/save")
    public ResponseView add( @RequestBody OfficeViewSave officeViewSave) throws Exception {
        officeService.add(officeViewSave);
        return new ResponseView("success");/*ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body("“result”:”success”");*/
    }

    /*
    получить весь список офисов
    */
    @ApiOperation(value = "getAllOffice", nickname = "getAllOffice", httpMethod = "GET")
    @GetMapping("/api/office")
    public List<OfficeViewLoadById> getAllOffice() {
        return officeService.getAllOffice();
    }
}