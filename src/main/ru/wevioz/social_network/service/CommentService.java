package wevioz.social_network.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.CommentCreateDto;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.TextLimitException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Service
public class CommentService implements EntityService<Comment> {
    public final static int TEXT_LIMIT = 100;
    private static AtomicInteger nextId = new AtomicInteger(0);
    private ArrayList<Comment> comments = new ArrayList<>();

    private final PostService postService;
    private final UserService userService;

    private CommentService(
            PostService postService,
            UserService userService
    ) {
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    public void add(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void remove(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public Comment findById(int id) throws NotFoundException {
        Comment comment = comments.stream().filter(item -> item.getId() == id).findFirst().orElse(null);

        if(comment != null) {
            return comment;
        } else {
            throw new NotFoundException("comment");
        }
    }

    public Comment createInstance(String content, Post post, User owner) throws TextLimitException {
        if (content.length() > TEXT_LIMIT) {
            throw new TextLimitException("content", TEXT_LIMIT);
        }

        return new Comment(nextId.getAndIncrement(), post, owner, content);
    }

    public Comment create(CommentCreateDto commentCreateDto) {
        Post post = postService.findById(commentCreateDto.getPostId());
        User user = userService.findById(commentCreateDto.getUserId());

        Comment comment = createInstance(commentCreateDto.getContent(), post, user);

        post.getComments().add(comment);

        add(comment);

        return comment;
    }

    public Comment delete(int id) {
        Comment comment = findById(id);
        Post post = comment.getPost();

        post.getComments().remove(comment);
        remove(comment);

        return comment;
    }
}