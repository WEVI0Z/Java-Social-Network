package wevioz.social_network.service;

import lombok.Getter;
import wevioz.social_network.entity.Group;
import wevioz.social_network.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class GroupService implements EntityService<Group> {
    private static AtomicInteger nextId = new AtomicInteger(0);
    private ArrayList<Group> groups = new ArrayList<>();

    public Group createGroup() {
        return new Group(nextId.getAndIncrement());
    }

    @Override
    public Optional<Group> findById(int id) {
        return groups.stream().
                filter(group -> group.getId() == id).
                findFirst();
    }

    public List<User> getGroupUsersById(int id) {
        Group group = findById(id).get();

        return group.getParticipants();
    }

    @Override
    public void add(Group group) {
        groups.add(group);
    }

    @Override
    public void remove(Group group) {
        groups.remove(group);
    }

    public static void addUser(User user, Group group) {
        group.getParticipants().add(user);
    }

    public static void removeUser(User user, Group group) {
        group.getParticipants().remove(user);
    }
}