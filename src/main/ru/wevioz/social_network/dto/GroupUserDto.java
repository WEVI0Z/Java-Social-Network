package wevioz.social_network.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class GroupUserDto {
    @NotEmpty(message = "Field groupId is empty")
    @NotBlank(message = "Field groupId is empty")
    private int groupId;

    @NotEmpty(message = "Field userId is empty")
    @NotBlank(message = "Field userId is empty")
    private int userId;
}