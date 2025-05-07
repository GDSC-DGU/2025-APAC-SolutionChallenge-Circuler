package circulo.server.domain.packageSubmission.dto.response;

import circulo.server.domain.packageSubmission.entity.enums.DeliveryMethod;
import circulo.server.domain.packageSubmission.entity.enums.PackageSubmissionStatus;
import circulo.server.domain.packagingRequest.entity.enums.PackagingRequestStatus;
import lombok.*;

public class PackageSubmissionResponse {

    @Getter
    @AllArgsConstructor
    public static class PackageSubmissionSuccessResponse {
        private Long id;
    }

    @Getter
    @AllArgsConstructor
    public static class PackageSubmissionListItem {
        private Long id;
        private Integer quantity;
        private String location;
        private DeliveryMethod method;
    }

    @Getter
    @AllArgsConstructor
    public static class PackageSubmissionAcceptedResponse {
        private Long packageSubmissionId;
        private Long packagingRequestId;
        private PackagingRequestStatus totalStatus;
        private PackageSubmissionStatus detailStatus;
    }

}
