package wevioz.social_network.dto;

import lombok.Getter;
import lombok.Setter;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;

@Getter
@Setter
public class CommentGetDto {
    private int id;
    private String content;
    private User owner;
    private Post post;
}