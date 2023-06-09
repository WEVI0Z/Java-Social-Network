package wevioz.social_network.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wevioz.social_network.dto.UserGetDto;
import wevioz.social_network.dto.UserPostDto;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.service.UserService;

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
    public List<UserGetDto> get() {
        return userService.get();
    }

    @GetMapping("/{id}")
    public UserGetDto getById(@PathVariable int id) throws NotFoundException {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public UserGetDto deleteById(@PathVariable int id) throws NotFoundException {
        return userService.removeById(id);
    }

    @PostMapping
    public UserGetDto create(@RequestBody @Valid UserPostDto userPostDto) {
        return userService.create(userPostDto);
    }
}