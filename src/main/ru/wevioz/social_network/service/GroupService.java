package wevioz.social_network.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.GroupPostDto;
import wevioz.social_network.entity.Group;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class GroupService implements EntityService<Group> {
    private static AtomicInteger nextId = new AtomicInteger(0);
    private ArrayList<Group> groups = new ArrayList<>();

    private final UserService userService;

    public List<Group> getGroups() {
        return groups;
    }

    public Group createInstance(String name) {
        return new Group(nextId.getAndIncrement(), name);
    }

    @Override
    public Group findById(int id) throws NotFoundException {
        Optional<Group> group = groups.stream().filter(item -> item.getId() == id).findFirst();

        if(group.isPresent()) {
            return group.get();
        } else {
            throw new NotFoundException("user");
        }
    }

    public Group create(GroupPostDto groupPostDto) {
        Group group = createInstance(groupPostDto.getName());

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