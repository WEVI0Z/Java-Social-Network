package wevioz.social_network.service;

import lombok.Getter;
import wevioz.social_network.entity.Group;
import wevioz.social_network.entity.User;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GroupService implements EntityService<Group> {
    private final ArrayList<Group> groups = new ArrayList<>();

    public Group createGroup() {
        return new Group();
    }

    @Override
    public Group findById(int id) {
        return groups.stream().
                filter(group -> group.getId() == id).
                findFirst().
                get();
    }

    public List<User> getGroupUsersById(int id) {
        Group group = findById(id);

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
}