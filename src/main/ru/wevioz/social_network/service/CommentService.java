package wevioz.social_network.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.CommentCreateDto;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.TextLimitException;
import wevioz.social_network.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CommentService implements EntityService<Comment> {
    public final static int TEXT_LIMIT = 100;
    private static AtomicInteger nextId = new AtomicInteger(0);
    private ArrayList<Comment> comments = new ArrayList<>();

    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;

    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();

        commentRepository.findAll().forEach(comments::add);

        return comments;
    }

    @Override
    public void add(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void remove(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment findById(int id) throws NotFoundException {
        Optional<Comment> comment = commentRepository.findById((long) id);

        if(comment.isEmpty()) {
            throw new NotFoundException("comment");
        }

        return comment.get();
    }

    public Comment createInstance(String content, Post post, User owner) {
        if (content.length() > TEXT_LIMIT) {
            throw new TextLimitException("content", TEXT_LIMIT);
        }

        return new Comment(content, owner, post);
    }

    public Comment create(CommentCreateDto commentCreateDto) {
        Post post = postService.findById(commentCreateDto.getPostId());
        User user = userService.findById(commentCreateDto.getUserId());

        Comment comment = createInstance(commentCreateDto.getContent(), post, user);

        add(comment);

        return comment;
    }

    public Comment delete(int id) {
        Comment comment = findById(id);

        remove(comment);

        return comment;
    }
}