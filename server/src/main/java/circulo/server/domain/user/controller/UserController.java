package circulo.server.domain.user.controller;


import circulo.server.domain.user.entity.User;
import circulo.server.domain.user.repository.UserRepository;
import circulo.server.global.apiPayload.ApiResponse;
import circulo.server.global.apiPayload.code.exception.custom.AuthException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;
import circulo.server.global.handler.annotation.Auth;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @Operation(summary = "내 프로필 확인 API | by ChatGPT", description = "Access Token 기반으로 로그인한 유저 본인의 정보를 조회합니다.")
    @GetMapping("/me")
    public ApiResponse<User> getMyProfile(@Auth Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AuthException(ErrorStatus._UNAUTHORIZED));
        return ApiResponse.onSuccess(user);
    }
}
