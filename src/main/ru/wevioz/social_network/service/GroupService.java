package wevioz.social_network.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import wevioz.social_network.entity.Group;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Service
public class GroupService implements EntityService<Group> {
    private static AtomicInteger nextId = new AtomicInteger(0);
    private ArrayList<Group> groups = new ArrayList<>();

    private final UserService userService;

    private GroupService(UserService userService) {
        this.userService = userService;
    }

    public Group createInstance() {
        return new Group(nextId.getAndIncrement());
    }

    @Override
    public Group findById(int id) throws NotFoundException {
        Group group = groups.stream().filter(item -> item.getId() == id).findFirst().orElse(null);

        if(group != null) {
            return group;
        } else {
            throw new NotFoundException("user");
        }
    }

    public Group create() {
        Group group = createInstance();

        add(group);

        return group;
    }

    public Group addParticipantById(int groupId, int participantId) {
        Group group = findById(groupId);
        User user = userService.findById(participantId);

        group.getParticipants().add(user);

        return group;
    }

    public Group removeParticipantById(int groupId, int participantId) {
        Group group = findById(groupId);
        User user = userService.findById(participantId);

        group.getParticipants().remove(user);

        return group;
    }

    public List<User> getGroupUsersById(int id) throws NotFoundException {
        return findById(id).getParticipants();
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