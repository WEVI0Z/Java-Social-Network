package wevioz.social_network.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {
    @Min(0)
    private int userId;

    @NotEmpty
    @NotNull
    private String content;
}