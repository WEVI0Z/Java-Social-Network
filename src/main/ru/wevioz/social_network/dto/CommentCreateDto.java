package wevioz.social_network.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentCreateDto {
    @NotEmpty
    @NotBlank
    private String content;

    @NotEmpty
    @NotBlank
    private int postId;

    @NotEmpty
    @NotBlank
    private int userId;
}