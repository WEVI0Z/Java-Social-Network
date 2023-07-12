package wevioz.social_network.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import wevioz.social_network.dto.StatDto;
import wevioz.social_network.dto.TokenDto;
import wevioz.social_network.dto.UserDto;
import wevioz.social_network.dto.request.UserPostRequest;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.publisher.StatPublisher;
import wevioz.social_network.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

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

    @PostMapping(path = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public TokenDto getAuthUser(@RequestBody @Valid UserPostRequest userPostRequest) {
        return userService.authenticate(userPostRequest);
    }
}