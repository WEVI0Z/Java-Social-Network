package wevioz.social_network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wevioz.social_network.dto.PostDto;
import wevioz.social_network.dto.request.PostCreateRequest;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.service.PostService;

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
    public List<PostDto> get() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public PostDto getById(@PathVariable int id) throws NotFoundException {
        return postService.findById(id);
    }

    @PostMapping
    public PostDto create(@RequestBody PostCreateRequest postCreateRequest) {
        return postService.create(postCreateRequest);
    }

    @DeleteMapping("/{id}")
    public PostDto delete(@PathVariable int id) {
        return postService.delete(id);
    }
}