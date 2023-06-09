package wevioz.social_network.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentCreateRequest {
    @NotEmpty
    @NotNull
    private String content;

    @Min(0)
    private int postId;

    @Min(0)
    private int userId;
}