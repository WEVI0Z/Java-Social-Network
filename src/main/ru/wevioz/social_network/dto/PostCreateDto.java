package wevioz.social_network.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@AllArgsConstructor
public class PostCreateDto {
    @NotEmpty(message = "Field userId is empty")
    @NotBlank(message = "Field userId is empty")
    private int userId;

    @NotEmpty(message = "Field content is empty")
    @NotNull(message = "Field content is required")
    private String content;
}