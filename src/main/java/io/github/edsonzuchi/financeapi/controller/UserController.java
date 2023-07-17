package io.github.edsonzuchi.financeapi.controller;

import io.github.edsonzuchi.financeapi.orm.User;
import io.github.edsonzuchi.financeapi.response.ListUserResponse;
import io.github.edsonzuchi.financeapi.response.UserResponse;
import io.github.edsonzuchi.financeapi.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ListUserResponse findAll(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id){
        return userService.findByIdUser(id);
    }

    @PostMapping
    public UserResponse newUser(@RequestBody User user){
        return userService.saveNewUser(user);
    }

}
