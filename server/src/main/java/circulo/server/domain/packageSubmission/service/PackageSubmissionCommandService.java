package circulo.server.domain.packageSubmission.service;

import circulo.server.domain.packageSubmission.dto.response.PackageSubmissionResponse;

public interface PackageSubmissionCommandService {
    PackageSubmissionResponse.PackageSubmissionAcceptedResponse acceptPackageSubmission(Long userId, Long packageSubmissionId, Long packagingRequestId);
}
