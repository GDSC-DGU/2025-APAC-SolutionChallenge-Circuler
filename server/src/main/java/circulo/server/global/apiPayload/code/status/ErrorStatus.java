package circulo.server.global.apiPayload.code.status;

import circulo.server.global.apiPayload.code.BaseErrorCode;
import circulo.server.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "Server error, please contact the administrator."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","Invalid request."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","Authentication required."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "Forbidden request."),

    // Auth 관련 에러
    UNSUPPORTED_SOCIAL_TYPE(HttpStatus.BAD_REQUEST, "AUTH4001", "지원하지 않는 소셜 로그인 타입입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
