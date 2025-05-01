package circulo.server.global.apiPayload.code.exception.custom;

import circulo.server.global.apiPayload.code.exception.GeneralException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;

public class BadRequestException extends GeneralException {
    public BadRequestException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
