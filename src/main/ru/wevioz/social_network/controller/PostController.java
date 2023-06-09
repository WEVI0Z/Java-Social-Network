package wevioz.social_network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wevioz.social_network.dto.PostCreateDto;
import wevioz.social_network.dto.PostGetDto;
import wevioz.social_network.entity.Post;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.service.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostGetDto> get() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public PostGetDto getById(@PathVariable int id) throws NotFoundException {
        return postService.findById(id);
    }

    @PostMapping
    public PostGetDto create(@RequestBody PostCreateDto postCreateDto) {
        return postService.create(postCreateDto);
    }

    @DeleteMapping("/{id}")
    public PostGetDto delete(@PathVariable int id) {
        return postService.delete(id);
    }
}