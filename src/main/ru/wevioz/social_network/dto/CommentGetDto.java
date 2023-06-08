package wevioz.social_network.dto;

import lombok.Getter;
import wevioz.social_network.entity.Post;
import wevioz.social_network.entity.User;

@Getter
public class CommentGetDto {
    private String content;
    private User owner;
    private Post post;
}