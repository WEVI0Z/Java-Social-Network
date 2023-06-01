package wevioz.social_network.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import wevioz.social_network.entity.User;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Setter(AccessLevel.PRIVATE)
@Getter
public class Group {
    private static AtomicInteger nextId = new AtomicInteger(0);
    private int id = nextId.getAndIncrement();
    private ArrayList<User> participants = new ArrayList<>();

    public void addUser(User user) {
        this.participants.add(user);
    }

    public void removeUser(User user) {
        this.participants.remove(user);
    }
}