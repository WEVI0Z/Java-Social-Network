package wevioz.social_network.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wevioz.social_network.dto.UserPostDto;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.UniqueException;
import wevioz.social_network.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> get() {
        return userService.get();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id) throws NotFoundException {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public User deleteById(@PathVariable int id) throws NotFoundException {
        return userService.removeById(id);
    }

    @PostMapping
    public User create(@RequestBody @Valid UserPostDto userPostDto) {
        return userService.create(userPostDto);
    }
}