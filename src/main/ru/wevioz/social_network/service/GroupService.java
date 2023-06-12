package wevioz.social_network.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wevioz.social_network.dto.GroupDto;
import wevioz.social_network.dto.UserDto;
import wevioz.social_network.dto.request.GroupPostRequest;
import wevioz.social_network.entity.Group;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.mapper.GroupMapper;
import wevioz.social_network.mapper.UserMapper;
import wevioz.social_network.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService implements EntityService<Group> {
    private final UserService userService;
    private final GroupRepository groupRepository;
    private final UserMapper userMapper;
    private final GroupMapper groupMapper;

    public List<GroupDto> getGroups() {
        return groupMapper.toGetDtoList(groupRepository.findAll());
    }

    public Group createInstance(String name) {
        return new Group(name);
    }

    public GroupDto findById(int id) throws NotFoundException {
        Optional<Group> group = groupRepository.findById((long) id);

        if(group.isEmpty()) {
            throw new NotFoundException("user");
        }

        return groupMapper.toGetDto(group.get());
    }

    public GroupDto create(GroupPostRequest groupPostRequest) {
        Group group = createInstance(groupPostRequest.getName());

        add(group);

        return groupMapper.toGetDto(group);
    }

    public GroupDto addParticipantById(int groupId, int participantId) {
        GroupDto groupDto = findById(groupId);
        Group group = groupMapper.toEntity(groupDto);
        UserDto userDto = userService.findById(participantId);

        group.getParticipants().add(userMapper.toEntity(userDto));

        groupRepository.save(group);

        return groupDto;
    }

    public GroupDto removeParticipantById(int groupId, int participantId) {
        GroupDto groupDto = findById(groupId);
        Group group = groupMapper.toEntity(groupDto);
        UserDto userDto = userService.findById(participantId);

        group.getParticipants().remove(userMapper.toEntity(userDto));

        groupRepository.save(group);

        return groupDto;
    }

    public List<UserDto> getGroupUsersById(int id) throws NotFoundException {
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

    public GroupDto delete(int id) {
        GroupDto groupDto = findById(id);
        Group group = groupMapper.toEntity(groupDto);

        group.setParticipants(new ArrayList<>());

        groupRepository.delete(group);

        return groupDto;
    }
}