package circulo.server.global.security.oauth;

import circulo.server.domain.user.entity.User;
import circulo.server.domain.user.repository.UserRepository;
import circulo.server.global.security.jwt.JwtTokenProvider;
import circulo.server.global.security.jwt.TokenInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        Object principal = authentication.getPrincipal();
        User user;

        if (principal instanceof CustomOAuth2User customOAuth2User) {
            user = customOAuth2User.getUser();
        } else {
            log.error("Unexpected principal type: {}", principal.getClass().getName());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Invalid authentication principal");
            return;
        }

        TokenInfo token = jwtTokenProvider.generateToken(user.getId());

        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Authorization", "Bearer " + token.getAccessToken());
        response.setContentType("application/json;charset=UTF-8");

        objectMapper.writeValue(response.getWriter(), token);
    }
}