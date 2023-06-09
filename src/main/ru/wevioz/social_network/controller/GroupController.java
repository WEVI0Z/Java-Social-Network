package wevioz.social_network.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wevioz.social_network.dto.GroupGetDto;
import wevioz.social_network.dto.GroupPostDto;
import wevioz.social_network.dto.GroupUserDto;
import wevioz.social_network.dto.UserGetDto;
import wevioz.social_network.entity.Group;
import wevioz.social_network.entity.User;
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
    public List<GroupGetDto> get() {
        return groupService.getGroups();
    }

    @GetMapping("/{id}")
    public GroupGetDto findById(
            @PathVariable int id
    ) {
        return groupService.findById(id);
    }

    @GetMapping("/{id}/participants")
    public List<User> findGroupParticipantsById(
            @PathVariable int id
    ) {
        return groupService.getGroupUsersById(id);
    }

    @DeleteMapping("/{id}")
    public GroupGetDto delete(@PathVariable int id) {
        return groupService.delete(id);
    }

    @PostMapping
    public GroupGetDto create(@RequestBody GroupPostDto groupPostDto) {
        return groupService.create(groupPostDto);
    }

    @PutMapping("/add")
    public GroupGetDto addParticipant(
        @RequestBody GroupUserDto groupUserDto
    ) {
        return groupService.addParticipantById(
                groupUserDto.getGroupId(),
                groupUserDto.getUserId()
        );
    }

    @PutMapping("/remove")
    public GroupGetDto removeParticipant(
            @RequestBody GroupUserDto groupUserDto
    ) {
        return groupService.removeParticipantById(
                groupUserDto.getGroupId(),
                groupUserDto.getUserId()
        );
    }
}