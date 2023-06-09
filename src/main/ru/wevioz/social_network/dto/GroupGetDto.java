package wevioz.social_network.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import wevioz.social_network.entity.User;

import java.util.List;

@Getter
@Setter
public class GroupGetDto {
    private int id;
    private String name;
    private List<User> participants;
}