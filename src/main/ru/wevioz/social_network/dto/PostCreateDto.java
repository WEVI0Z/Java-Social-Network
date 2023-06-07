package wevioz.social_network.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@AllArgsConstructor
public class PostCreateDto {
    @Min(0)
    private int userId;

    @NotEmpty
    @NotNull
    private String content;
}