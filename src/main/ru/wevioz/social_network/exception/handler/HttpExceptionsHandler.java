package wevioz.social_network.exception.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import wevioz.social_network.dto.ErrorDto;
import wevioz.social_network.exception.NotFoundException;
import wevioz.social_network.exception.TextLimitException;
import wevioz.social_network.exception.UniqueException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class HttpExceptionsHandler extends ResponseEntityExceptionHandler {
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> messageKeys = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    return Arrays.stream(Objects.requireNonNull(fieldError.getCodes())).findFirst().orElse(null);
                }).toList();
        ErrorDto errorDto = new ErrorDto(messageKeys, exception);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleItemNotFoundException(
            NotFoundException notFoundException,
            WebRequest webRequest
    ) {
        List<String> messageKeys = new ArrayList<>();
        messageKeys.add(notFoundException.getMessage());
        ErrorDto errorDto = new ErrorDto(messageKeys, notFoundException);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TextLimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleItemTextLimitException(
            TextLimitException textLimitException,
            WebRequest webRequest
    ) {
        List<String> messageKeys = new ArrayList<>();
        messageKeys.add(textLimitException.getMessage());
        ErrorDto errorDto = new ErrorDto(messageKeys, textLimitException);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UniqueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleItemUniqueException(
            UniqueException uniqueException,
            WebRequest webRequest
    ) {
        List<String> messageKeys = new ArrayList<>();
        messageKeys.add(uniqueException.getMessage());
        ErrorDto errorDto = new ErrorDto(messageKeys, uniqueException);
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}