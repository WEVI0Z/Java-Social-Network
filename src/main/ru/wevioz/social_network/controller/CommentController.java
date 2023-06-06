package wevioz.social_network.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wevioz.social_network.dto.CommentCreateDto;
import wevioz.social_network.dto.PostCreateDto;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    public final CommentService commentService;

    private CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> get() {
        return commentService.getComments();
    }

    @GetMapping("/{id}")
    public Comment getByid(
            @PathVariable int id
    ) {
        return commentService.findById(id);
    }

    @PostMapping
    public Comment post(
            @RequestBody CommentCreateDto commentCreateDto
    ) {
        return commentService.create(commentCreateDto);
    }

    @DeleteMapping("/{id}")
    public Comment delete(
            @PathVariable int id
    ) {
        return commentService.delete(id);
    }
}