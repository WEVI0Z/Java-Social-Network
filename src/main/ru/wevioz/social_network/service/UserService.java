package wevioz.social_network.service;

import lombok.Getter;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.UniqueException;

import java.util.ArrayList;

@Getter
public class UserService implements EntityService<User> {
    private final ArrayList<User> users = new ArrayList<>();

    private User create(String email) throws UniqueException {
        if (users.stream().anyMatch(user -> user.getEmail().equals(email))) {
            throw new UniqueException("email");
        }
        return new User(email);
    }

    @Override
    public User findById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void remove(User user) {
        users.remove(user);
    }
}