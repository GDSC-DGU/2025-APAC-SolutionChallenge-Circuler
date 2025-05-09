package circulo.server.global.apiPayload.code.exception.custom;

import circulo.server.global.apiPayload.code.exception.GeneralException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;

public class DeliveryException extends GeneralException {
    public DeliveryException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}

