package circulo.server.global.apiPayload.code.exception.custom;

import circulo.server.global.apiPayload.code.exception.GeneralException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;

public class PackageSubmissionException extends GeneralException {
    public PackageSubmissionException(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
