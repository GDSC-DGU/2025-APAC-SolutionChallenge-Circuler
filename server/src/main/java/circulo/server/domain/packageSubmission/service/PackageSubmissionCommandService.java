package circulo.server.domain.packageSubmission.service;

import circulo.server.domain.packageSubmission.dto.request.PackageSubmissionRequest;
import circulo.server.domain.packageSubmission.dto.response.PackageSubmissionResponse;

public interface PackageSubmissionCommandService {
    PackageSubmissionResponse.PackageSubmissionSuccessResponse createPackageSubmission(Long userId, Long packagingRequestId, PackageSubmissionRequest.CreatePackageSubmissionRequest request);
    PackageSubmissionResponse.PackageSubmissionAcceptedResponse acceptPackageSubmission(Long userId, Long packageSubmissionId, Long packagingRequestId);

    void markAsDelivered(Long packageSubmissionId);
}
