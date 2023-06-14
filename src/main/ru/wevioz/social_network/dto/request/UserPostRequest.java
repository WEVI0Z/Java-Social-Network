package wevioz.social_network.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostRequest {
    @NotNull(message = "Field email required")
    @NotEmpty(message = "Field email is empty")
    private String email;

    @NotNull(message = "Field email required")
    @NotEmpty(message = "Field email is empty")
    private String password;
}