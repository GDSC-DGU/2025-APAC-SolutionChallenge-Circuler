package circulo.server.global.handler.resolver;

import circulo.server.global.apiPayload.code.exception.custom.AuthException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;
import circulo.server.global.handler.annotation.Auth;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Auth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String userIdString = authentication.getName();
            try {
                return Long.parseLong(userIdString);
            } catch (NumberFormatException e) {
                throw new AuthException(ErrorStatus.INVALID_USER_ID_FORMAT);
            }
        }
        throw new AuthException(ErrorStatus.TOKEN_ERROR);
    }
}
