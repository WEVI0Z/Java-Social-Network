package wevioz.social_network.controller;

import org.springframework.web.bind.annotation.*;
import wevioz.social_network.dto.CommentCreateDto;
import wevioz.social_network.dto.CommentGetDto;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    private CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentGetDto> get() {
        return commentService.getComments();
    }

    @GetMapping("/{id}")
    public CommentGetDto getById(@PathVariable int id) {
        return commentService.findById(id);
    }

    @PostMapping
    public CommentGetDto post(@RequestBody CommentCreateDto commentCreateDto) {
        return commentService.create(commentCreateDto);
    }

    @DeleteMapping("/{id}")
    public CommentGetDto delete(@PathVariable int id) {
        return commentService.delete(id);
    }
}