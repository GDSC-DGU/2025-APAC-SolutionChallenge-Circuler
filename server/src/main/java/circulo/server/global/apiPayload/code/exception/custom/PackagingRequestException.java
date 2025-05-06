package circulo.server.global.apiPayload.code.exception.custom;

import circulo.server.global.apiPayload.code.exception.GeneralException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;

public class PackagingRequestException extends GeneralException {
    public PackagingRequestException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
