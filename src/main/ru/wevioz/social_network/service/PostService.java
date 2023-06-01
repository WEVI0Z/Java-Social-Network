package wevioz.social_network.service;

import lombok.Getter;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.TextLimitException;

import java.util.ArrayList;

@Getter
public class PostService implements EntityService<Post>{
    private final ArrayList<Post> posts = new ArrayList<>();

    public Post create(String content, User owner) throws TextLimitException {
        if (content.length() > Post.textLimit) {
            throw new TextLimitException("content", Post.textLimit);
        }

        Post post = new Post(content, owner);
        owner.addPost(post);

        return  post;
    }

    @Override
    public Post findById(int id) {
        return posts.stream().filter(post -> post.getId() == id).findFirst().get();
    }

    @Override
    public void add(Post post) {
        posts.add(post);
    }

    @Override
    public void remove(Post post) {
        posts.remove(post);
    }
}