package circulo.server.global.security.oauth;

import circulo.server.global.apiPayload.code.exception.custom.AuthException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Builder
@Slf4j
public class OAuth2UserInfo {

    private final String id;
    private final String email;
    private final String name;

    public static OAuth2UserInfo of(Map<String, Object> attributes) {
        Object idObj = attributes.get("sub");
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        if (idObj == null || email == null) {
            throw new AuthException(ErrorStatus.UNSUPPORTED_SOCIAL_TYPE);
        }

        return OAuth2UserInfo.builder()
                .id(String.valueOf(idObj))
                .email(email)
                .name(name)
                .build();
    }
}