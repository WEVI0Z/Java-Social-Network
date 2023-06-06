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

@Controller
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ArrayList<User> get() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public @ResponseBody User getById(
        @PathVariable int id
    ) throws NotFoundException {
        return userService.findById(id);
    }

    @GetMapping("/{id}/posts")
    public @ResponseBody List<Post> getUsersPostsById(
            @PathVariable int id
    ) throws NotFoundException {
        return userService.getUserPostsById(id);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody User deleteById(
            @PathVariable int id
    ) throws NotFoundException {
        return userService.removeById(id);
    }

    @PostMapping
    public @ResponseBody User create(
        @RequestBody @Valid UserPostDto userPostDto
    ) {
        return userService.create(userPostDto);
    }
}