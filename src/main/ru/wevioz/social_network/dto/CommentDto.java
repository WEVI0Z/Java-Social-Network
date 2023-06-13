package wevioz.social_network.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private int id;
    private String content;
    private UserDto owner;
    private PostDto post;
}