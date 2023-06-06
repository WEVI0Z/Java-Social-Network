package wevioz.social_network.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.PostCreateDto;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.entity.Group;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.TextLimitException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@Getter
@Service
public class PostService implements EntityService<Post>{
    public final int TEXT_LIMIT = 200;
    private static AtomicInteger nextId = new AtomicInteger(0);
    private ArrayList<Post> posts = new ArrayList<>();

    private final UserService userService;

    private PostService(UserService userService) {
        this.userService = userService;

        create(new PostCreateDto(1, "Some Text 2"));
        create(new PostCreateDto(1, "Some Text 3"));
        create(new PostCreateDto(1, "Some Text 4"));
    }

    public Post createInstance(String content, User owner) throws TextLimitException {
        if (content.length() > TEXT_LIMIT) {
            throw new TextLimitException("content", TEXT_LIMIT);
        }

        return new Post(nextId.getAndIncrement(), content, owner);
    }

    public List<Comment> getPostCommentsById(int id) throws NotFoundException {
        return findById(id).getComments();
    }

    public Post create(PostCreateDto postCreateDto) {
        User user = userService.findById(postCreateDto.getUserId());

        Post post = createInstance(postCreateDto.getContent(), user);

        add(post);

        return post;
    }

    public Post delete(int id) {
        Post post = findById(id);

        remove(post);

        return post;
    }

    @Override
    public Post findById(int id) throws NotFoundException {
        Post post = posts.stream().filter(item -> item.getId() == id).findFirst().orElse(null);

        if(post != null) {
            return post;
        } else {
            throw new NotFoundException("post");
        }
    }

    @Override
    public void add(Post post) {
        posts.add(post);
    }

    @Override
    public void remove(Post post) {
        posts.remove(post);
    }

    public static void addComment(Comment comment, Post post) {
        post.getComments().add(comment);
    }
}