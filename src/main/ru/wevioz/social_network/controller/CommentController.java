package wevioz.social_network.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wevioz.social_network.dto.CommentDto;
import wevioz.social_network.dto.request.CommentCreateRequest;
import wevioz.social_network.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public List<CommentDto> get() {
        return commentService.getComments();
    }

    @GetMapping("/{id}")
    public CommentDto getById(@PathVariable int id) {
        return commentService.findById(id);
    }

    @PostMapping
    public CommentDto post(@RequestBody CommentCreateRequest commentCreateRequest) {
        return commentService.create(commentCreateRequest);
    }

    @DeleteMapping("/{id}")
    public CommentDto delete(@PathVariable int id) {
        return commentService.delete(id);
    }
}