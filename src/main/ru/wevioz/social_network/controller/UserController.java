package wevioz.social_network.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wevioz.social_network.dto.UserDto;
import wevioz.social_network.dto.request.UserPostRequest;
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
    public List<UserDto> get() {
        return userService.get();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable int id) throws NotFoundException {
        return userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public UserDto deleteById(@PathVariable int id) throws NotFoundException {
        return userService.removeById(id);
    }

    @PostMapping
    public UserDto create(@RequestBody @Valid UserPostRequest userPostRequest) {
        return userService.create(userPostRequest);
    }
}