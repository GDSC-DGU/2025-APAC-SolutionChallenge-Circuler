package circulo.server.global.apiPayload.code.exception.custom;

import circulo.server.global.apiPayload.code.exception.GeneralException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;

public class UserException extends GeneralException {
    public UserException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
