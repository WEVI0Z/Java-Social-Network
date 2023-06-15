package wevioz.social_network.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import wevioz.social_network.service.JwtService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtCsrfFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeder = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeder == null || !authHeder.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);

            return;
        }

        jwt = authHeder.substring(7);
        userEmail = jwtService.extractUsername(jwt);
    }
}