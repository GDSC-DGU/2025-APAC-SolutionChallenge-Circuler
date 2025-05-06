package circulo.server.domain.packageSubmission.dto.response;

import circulo.server.domain.packageSubmission.entity.enums.DeliveryMethod;
import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;
import lombok.*;

import java.util.List;

public class PackageSubmissionResponse {

    @Getter
    @AllArgsConstructor
    public static class PackageSubmissionListItem {
        private Long id;
        private Integer quantity;
        private String location;
        private DeliveryMethod method;
    }

}
