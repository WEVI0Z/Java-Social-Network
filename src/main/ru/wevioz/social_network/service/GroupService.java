package wevioz.social_network.service;

import lombok.Getter;
import wevioz.social_network.entity.Group;

import java.util.ArrayList;

@Getter
public class GroupService implements EntityService<Group> {
    private final ArrayList<Group> groups = new ArrayList<>();

    public Group createGroup() {
        return new Group();
    }

    @Override
    public Group findById(int id) {
        return this.groups.stream().
                filter(group -> group.getId() == id).
                findFirst().
                get();
    }

    @Override
    public void add(Group group) {
        this.groups.add(group);
    }

    @Override
    public void remove(Group group) {
        this.groups.remove(group);
    }
}