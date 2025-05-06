package circulo.server.global.apiPayload.code.exception;

import circulo.server.global.apiPayload.code.BaseErrorCode;
import circulo.server.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;
    private final String customMessage;

    public GeneralException(BaseErrorCode code) {
        super(code.getReason().getMessage());
        this.code = code;
        this.customMessage = code.getReason().getMessage();
    }

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
