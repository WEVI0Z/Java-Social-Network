package wevioz.social_network.controller;

import org.springframework.web.bind.annotation.*;
import wevioz.social_network.dto.GroupDto;
import wevioz.social_network.dto.UserDto;
import wevioz.social_network.dto.request.GroupPostRequest;
import wevioz.social_network.dto.GroupUserDto;
import wevioz.social_network.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;

    private GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<GroupDto> get() {
        return groupService.getGroups();
    }

    @GetMapping("/{id}")
    public GroupDto findById(@PathVariable int id) {
        return groupService.findById(id);
    }

    @GetMapping("/{id}/participants")
    public List<UserDto> findGroupParticipantsById(@PathVariable int id) {
        return groupService.getGroupUsersById(id);
    }

    @DeleteMapping("/{id}")
    public GroupDto delete(@PathVariable int id) {
        return groupService.delete(id);
    }

    @PostMapping
    public GroupDto create(@RequestBody GroupPostRequest groupPostRequest) {
        return groupService.create(groupPostRequest);
    }

    @PutMapping("/add")
    public GroupDto addParticipant(@RequestBody GroupUserDto groupUserDto) {
        return groupService.addParticipantById(
                groupUserDto.getGroupId(),
                groupUserDto.getUserId()
        );
    }

    @PutMapping("/remove")
    public GroupDto removeParticipant(@RequestBody GroupUserDto groupUserDto) {
        return groupService.removeParticipantById(
                groupUserDto.getGroupId(),
                groupUserDto.getUserId()
        );
    }
}