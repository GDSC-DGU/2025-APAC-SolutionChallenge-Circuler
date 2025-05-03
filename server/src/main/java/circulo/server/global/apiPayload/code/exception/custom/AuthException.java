package circulo.server.global.apiPayload.code.exception.custom;

import circulo.server.global.apiPayload.code.exception.GeneralException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;

public class AuthException extends GeneralException {

    public AuthException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
