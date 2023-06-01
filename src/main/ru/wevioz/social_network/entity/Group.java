package wevioz.social_network.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Setter(AccessLevel.PRIVATE)
@Getter
public class Group {
    private static AtomicInteger nextId = new AtomicInteger(0);
    private int id = nextId.getAndIncrement();
    private ArrayList<User> participants = new ArrayList<>();

    public void addUser(User user) {
        participants.add(user);
    }

    public void removeUser(User user) {
        participants.remove(user);
    }
}