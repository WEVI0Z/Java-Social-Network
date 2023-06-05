package wevioz.social_network.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.UniqueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Service
public class UserService implements EntityService<User> {
    private static AtomicInteger nextId = new AtomicInteger(0);
    private ArrayList<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(nextId.getAndIncrement(), "Wevioz"));
        users.add(new User(nextId.getAndIncrement(), "Alex"));
        users.add(new User(nextId.getAndIncrement(), "Pioter"));
        users.add(new User(nextId.getAndIncrement(), "Folk"));
    }

    public User create(String email) throws UniqueException {
        if (users.stream().anyMatch(user -> user.getEmail().equals(email))) {
            throw new UniqueException("email");
        }
        return new User(nextId.getAndIncrement(), email);
    }

    public List<Post> getUserPostsById(int id) throws NotFoundException {
        return findById(id).getPosts();
    }

    @Override
    public User findById(int id) throws NotFoundException {
        User user = users.stream().filter(item -> item.getId() == id).findFirst().orElse(null);

        if(user != null) {
            return user;
        } else {
            throw new NotFoundException("user");
        }
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