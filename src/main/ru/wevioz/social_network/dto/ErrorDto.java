package wevioz.social_network.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ErrorDto {
    private final List<String> messageKeys;
    private final Exception exception;
}