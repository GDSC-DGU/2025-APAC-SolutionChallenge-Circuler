package circulo.server.global.security.oauth;

import circulo.server.domain.user.entity.User;
import circulo.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        OAuth2UserInfo userInfo = OAuth2UserInfo.of(oAuth2User.getAttributes());

        User user = userRepository.findByEmail(userInfo.getEmail())
                .orElseGet(() -> userRepository.save(User.builder()
                        .email(userInfo.getEmail())
                        .name(userInfo.getName() != null ? userInfo.getName() : "")
                        .build()));

        return new CustomOAuth2User(user, oAuth2User.getAttributes());
    }
}