package wevioz.social_network.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostDto {
    private int id;
    private String content;
    private UserDto owner;
    private LocalDate creationDate;
}