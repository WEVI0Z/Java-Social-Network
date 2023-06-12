package wevioz.social_network.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.GroupGetDto;
import wevioz.social_network.dto.GroupPostDto;
import wevioz.social_network.dto.UserGetDto;
import wevioz.social_network.entity.Group;
import wevioz.social_network.entity.User;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.mapper.GroupMapper;
import wevioz.social_network.mapper.UserMapper;
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
    private final UserMapper userMapper;
    private final GroupMapper groupMapper;

    public List<GroupGetDto> getGroups() {
        return groupMapper.toGetDtoList(groupRepository.findAll());
    }

    public Group createInstance(String name) {
        return new Group(name);
    }

    public GroupGetDto findById(int id) throws NotFoundException {
        Optional<Group> group = groupRepository.findById((long) id);

        if(group.isEmpty()) {
            throw new NotFoundException("user");
        }

        return groupMapper.toGetDto(group.get());
    }

    public GroupGetDto create(GroupPostDto groupPostDto) {
        Group group = createInstance(groupPostDto.getName());

        add(group);

        return groupMapper.toGetDto(group);
    }

    public GroupGetDto addParticipantById(int groupId, int participantId) {
        GroupGetDto groupGetDto = findById(groupId);
        Group group = groupMapper.toEntity(groupGetDto);
        UserGetDto userGetDto = userService.findById(participantId);

        group.getParticipants().add(userMapper.toEntity(userGetDto));

        groupRepository.save(group);

        return groupGetDto;
    }

    public GroupGetDto removeParticipantById(int groupId, int participantId) {
        GroupGetDto groupGetDto = findById(groupId);
        Group group = groupMapper.toEntity(groupGetDto);
        UserGetDto userGetDto = userService.findById(participantId);

        group.getParticipants().remove(userMapper.toEntity(userGetDto));

        groupRepository.save(group);

        return groupGetDto;
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

    public GroupGetDto delete(int id) {
        GroupGetDto groupGetDto = findById(id);
        Group group = groupMapper.toEntity(groupGetDto);

        group.setParticipants(new ArrayList<>());

        groupRepository.delete(group);

        return groupGetDto;
    }
}