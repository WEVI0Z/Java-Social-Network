package wevioz.social_network.repository;

import com.sun.net.httpserver.HttpServer;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS;

@Repository
@Getter
public class JwtTokenRepository implements CsrfTokenRepository {
    private String secret;

    public JwtTokenRepository() {
        this.secret = "Security secret";
    }

    @Override
    public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
        String id = UUID.randomUUID().toString().replace("-", "");

        Date now = new Date();
        Date expiration = Date.from(
                LocalDateTime.now()
                .plusMinutes(30)
                .atZone(ZoneId.systemDefault()).toInstant()
        );

        String token = "";

        try {
            token = Jwts.builder()
                    .setId(id)
                    .setIssuedAt(now)
                    .setNotBefore(now)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS256, secret)
                    .compact();
        } catch (JwtException e) {
            e.printStackTrace();
        }

        return new DefaultCsrfToken("x-csrf-token", "_csrf", token);
    }

    @Override
    public void saveToken(
            CsrfToken csrfToken,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        if (Objects.nonNull(csrfToken)) {
            if (!httpServletResponse.getHeaderNames().contains(ACCESS_CONTROL_EXPOSE_HEADERS)) {
                httpServletResponse.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS, csrfToken.getHeaderName());
            }

            if (httpServletResponse.getHeaderNames().contains(csrfToken.getHeaderName())) {
                httpServletResponse.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());
            }
            else {
                httpServletResponse.addHeader(csrfToken.getHeaderName(), csrfToken.getToken());
            }
        }
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest httpServletRequest) {
        return (CsrfToken) httpServletRequest.getAttribute(CsrfToken.class.getName());
    }

    public void clearToken(HttpServletResponse httpServletResponse) {
        if (httpServletResponse.getHeaderNames().contains("x-csrf-token")) {
            httpServletResponse.setHeader("x-csrf-token", "");
        }
    }
}