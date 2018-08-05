package ru.bellintegrator.practice.user.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserView;

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


    @ApiOperation(value = "loadByName", nickname = "loadByName", httpMethod = "GET")                                //получить организацию по имени
    @GetMapping("/api/user/loadByName")
    public User loadByName(@RequestParam String name) {
        return userService.loadByName(name);
    }


    @ApiOperation(value = "getAllUser", nickname = "getAllUser", httpMethod = "GET")                //получить весь список организаций
    @GetMapping("/api/user")
    public List<UserView> getAllUser() { return userService.getAllUser(); }


    @ApiOperation(value = "update", nickname = "update", httpMethod = "POST")                                       //обновить данные организации
    @PostMapping("/api/user/update")
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody UserView organization) throws Exception {
        userService.update(organization); }


    @ApiOperation(value = "addOrg", nickname = "addOrg", httpMethod = "POST")                                       //добавить организацию
    @PostMapping("/api/user")
    public void add(@RequestBody UserView organization) { userService.add(organization); }







}
