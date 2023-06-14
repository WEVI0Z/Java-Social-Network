package wevioz.social_network.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.atn.ErrorInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.util.UrlUtils;
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
import wevioz.social_network.repository.JwtTokenRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class HttpExceptionsHandler extends ResponseEntityExceptionHandler {
    private JwtTokenRepository tokenRepository;

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> messageKeys = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    return Arrays.stream(Objects.requireNonNull(fieldError.getCodes()))
                            .findFirst()
                            .orElse(null);
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

    @ExceptionHandler({
            AuthenticationException.class,
            MissingCsrfTokenException.class,
            InvalidCsrfTokenException.class,
            SessionAuthenticationException.class
    })
    public ResponseEntity<Object> handleAuthenticationException(RuntimeException ex, HttpServletRequest request, HttpServletResponse response){
        this.tokenRepository.clearToken(response);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        List<String> messageKeys = new ArrayList<>();
        messageKeys.add(ex.getMessage());

        ErrorDto errorDto = new ErrorDto(messageKeys, ex);

        return new ResponseEntity<>(errorDto, HttpStatus.UNAUTHORIZED);
    }
}