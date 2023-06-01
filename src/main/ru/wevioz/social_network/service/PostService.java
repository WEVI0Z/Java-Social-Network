package wevioz.social_network.service;

import lombok.Getter;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.entity.Group;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.TextLimitException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@Getter
public class PostService implements EntityService<Post>{
    public final int TEXT_LIMIT = 200;
    private static AtomicInteger nextId = new AtomicInteger(0);
    private ArrayList<Post> posts = new ArrayList<>();

    public Post create(String content, User owner) throws TextLimitException {
        if (content.length() > TEXT_LIMIT) {
            throw new TextLimitException("content", TEXT_LIMIT);
        }

        Post post = new Post(nextId.getAndIncrement(), content, owner);
        UserService.addPost(post, owner);

        return  post;
    }

    public List<Comment> getPostCommentsById(int id) {
        Post post = findById(id).get();

        return post.getComments();
    }

    @Override
    public Optional<Post> findById(int id) {
        return posts.stream().filter(post -> post.getId() == id).findFirst();
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