package circulo.server.domain.packageSubmission.service;

import circulo.server.domain.packageSubmission.dto.request.PackageSubmissionRequest;
import circulo.server.domain.packageSubmission.dto.response.PackageSubmissionResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PackageSubmissionCommandService {
    PackageSubmissionResponse.PackageSubmissionSuccessResponse createPackageSubmission(Long userId, Long packagingRequestId, PackageSubmissionRequest.CreatePackageSubmissionRequest request);
    PackageSubmissionResponse.PackageSubmissionAcceptedResponse acceptPackageSubmission(Long userId, Long packageSubmissionId, Long packagingRequestId);
    PackageSubmissionResponse.VerifyResponse verifyPackageType(Long userId, Long packageSubmissionId, MultipartFile file);
    void markAsDelivered(Long packageSubmissionId);
}
