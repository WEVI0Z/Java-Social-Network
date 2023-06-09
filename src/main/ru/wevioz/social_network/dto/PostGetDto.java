package wevioz.social_network.dto;

import lombok.Getter;
import lombok.Setter;
import wevioz.social_network.entity.Comment;
import wevioz.social_network.entity.User;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PostGetDto {
    private int id;
    private String content;
    private User owner;
    private LocalDate creationDate;
}