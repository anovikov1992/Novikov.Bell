package ru.bellintegrator.practice.user.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.organization.ResponseSuccess.ResponseView;
import ru.bellintegrator.practice.user.model.User;
import ru.bellintegrator.practice.user.service.UserService;
import ru.bellintegrator.practice.user.view.UserView;
import ru.bellintegrator.practice.user.view.UserViewLoadById;
import ru.bellintegrator.practice.user.view.UserViewSave;
import ru.bellintegrator.practice.user.view.UserViewUpdate;

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
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseView update(@RequestBody UserViewUpdate userViewUpdate) throws Exception {
        userService.update(userViewUpdate);
        return new ResponseView("success");
    }

    /*
    добавить нового пользователя
    */
    @ApiOperation(value = "api/user/save", nickname = "api/user/save",
            httpMethod = "POST")
    @PostMapping("/api/user/save")
    public ResponseView add( @RequestBody UserViewSave userViewSave) throws Exception {
        userService.add(userViewSave);
        return new ResponseView("success");
    }

    /*
    получить весь список пользователей
    */
    @ApiOperation(value = "getAllUser", nickname = "getAllUser", httpMethod = "GET")
    @GetMapping("/api/user")
    public List<UserView> getAllUser() { return userService.getAllUser(); }

}
