package wevioz.social_network.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupDto {
    private int id;
    private String name;
    private List<UserDto> participants;
}