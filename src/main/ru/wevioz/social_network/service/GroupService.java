package wevioz.social_network.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.GroupPostDto;
import wevioz.social_network.entity.Group;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class GroupService implements EntityService<Group> {
    private final UserService userService;
    private final GroupRepository groupRepository;

    public List<Group> getGroups() {
        List<Group> groups = new ArrayList<>();

        groupRepository.findAll().forEach(groups::add);

        return groups;
    }

    public Group createInstance(String name) {
        return new Group(name);
    }

    @Override
    public Group findById(int id) throws NotFoundException {
        Optional<Group> group = groupRepository.findById((long) id);

        if(group.isEmpty()) {
            throw new NotFoundException("user");
        }

        return group.get();
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

        groupRepository.save(group);

        return group;
    }

    public Group removeParticipantById(int groupId, int participantId) {
        Group group = findById(groupId);
        User user = userService.findById(participantId);

        group.getParticipants().remove(user);

        groupRepository.save(group);

        return group;
    }

    public List<User> getGroupUsersById(int id) throws NotFoundException {
        return findById(id).getParticipants();
    }

    @Override
    public void add(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void remove(Group group) {
        groupRepository.delete(group);
    }

    public Group delete(int id) {
        Group group = findById(id);

        group.setParticipants(new ArrayList<>());

        groupRepository.delete(group);

        return group;
    }
}