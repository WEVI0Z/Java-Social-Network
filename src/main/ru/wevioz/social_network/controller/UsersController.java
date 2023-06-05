package wevioz.social_network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
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
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public @ResponseBody ArrayList<User> get() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public @ResponseBody User getById(
        @PathVariable final int id
    ) throws NotFoundException {
        return userService.findById(id);
    }

    @GetMapping("/{id}/posts")
    public @ResponseBody List<Post> getUsersPostsById(
            @PathVariable final int id
    ) throws NotFoundException {
        User user = userService.findById(id);

        return user.getPosts();
    }

    @DeleteMapping("/{id}")
    public @ResponseBody User deleteById(
            @PathVariable final int id
    ) throws NotFoundException {
        User user = userService.findById(id);

        userService.remove(user);

        return user;
    }

    @PostMapping
    public @ResponseBody User create(
        @RequestBody UserPostDto userPostDto
    ) throws UniqueException {
        User user = userService.create(userPostDto.email);

        userService.add(user);

        return user;
    }
}