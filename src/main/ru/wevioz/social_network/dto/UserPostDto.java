package wevioz.social_network.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserPostDto {
    @NotNull(message = "Field email required")
    @NotEmpty(message = "Field email is empty")
    private String email;
}