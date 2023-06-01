package wevioz.social_network.service;

import lombok.Getter;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.UniqueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class UserService implements EntityService<User> {
    private static AtomicInteger nextId = new AtomicInteger(0);
    private ArrayList<User> users = new ArrayList<>();

    private User create(String email) throws UniqueException {
        if (users.stream().anyMatch(user -> user.getEmail().equals(email))) {
            throw new UniqueException("email");
        }
        return new User(nextId.getAndIncrement(), email);
    }

    public List<Post> getUserPostsById(int id) {
        User user = findById(id).get();

        return user.getPosts();
    }

    @Override
    public Optional<User> findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void remove(User user) {
        users.remove(user);
    }

    public static void addPost(Post post, User user) {
        user.getPosts().add(post);
    }
}