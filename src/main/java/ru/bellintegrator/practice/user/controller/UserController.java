package ru.bellintegrator.practice.user.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    получить пользователя по ID офиса
    */
    @ApiOperation(value = "/api/office/list", nickname = "/api/office/list", httpMethod = "POST")
    @PostMapping("/api/user/list")
    public List<UserViewByOfficeIdResponse> getUserByOfficeId(@RequestBody UserViewByOfficeIdRequest userByOfficeId) {
        return userService.getUserByOfficeId(userByOfficeId.officeId, userByOfficeId.firstName, userByOfficeId.middleName,
                                            userByOfficeId.secondName, userByOfficeId.position, userByOfficeId.docCode,
                                            userByOfficeId.citizenshipCode); }

    /*
    получить пользователя по ID
    */
    @ApiOperation(value = "api/user/{id}", nickname = "получить офис по ID", httpMethod = "GET")
    @GetMapping("/api/user/{id}")
    public UserViewLoadById loadById (@PathVariable Long id) {
        return userService.loadById(id);
    }

    /*
    обновить данные пользователя
    */
    @ApiOperation(value = "update", nickname = "update", httpMethod = "POST")
    @PostMapping("/api/user/update")
    public void update(@RequestBody UserViewUpdate userViewUpdate) {
        userService.update(userViewUpdate);
    }

    /*
    добавить нового пользователя
    */
    @ApiOperation(value = "api/user/save", nickname = "api/user/save",
            httpMethod = "POST")
    @PostMapping("/api/user/save")
    public void add( @RequestBody UserViewSave userViewSave) {
        userService.add(userViewSave);
    }

    /*
    получить весь список пользователей
    */
    @ApiOperation(value = "getAllUser", nickname = "getAllUser", httpMethod = "GET")
    @GetMapping("/api/user/all")
    public List<UserView> getAllUser() {
        return userService.getAllUser(); }

    /*
    удалить пользователя по ID
    */
    @ApiOperation(value = "deleteOffice", nickname = "deleteOffice", httpMethod = "POST")
    @PostMapping("/api/user/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}
