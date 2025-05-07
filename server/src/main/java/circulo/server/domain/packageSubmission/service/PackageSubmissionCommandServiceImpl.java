package circulo.server.domain.packageSubmission.service;

import circulo.server.domain.packageSubmission.converter.PackageSubmissionConverter;
import circulo.server.domain.packageSubmission.dto.response.PackageSubmissionResponse;
import circulo.server.domain.packageSubmission.entity.PackageSubmission;
import circulo.server.domain.packageSubmission.entity.enums.DeliveryMethod;
import circulo.server.domain.packageSubmission.entity.enums.PackageSubmissionStatus;
import circulo.server.domain.packageSubmission.repository.PackageSubmissionRepository;
import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import circulo.server.domain.packagingRequest.entity.enums.PackagingRequestStatus;
import circulo.server.domain.packagingRequest.repository.PackagingRequestRepository;
import circulo.server.global.apiPayload.code.exception.custom.PackageSubmissionException;
import circulo.server.global.apiPayload.code.exception.custom.PackagingRequestException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PackageSubmissionCommandServiceImpl implements PackageSubmissionCommandService {

    private final PackageSubmissionRepository packageSubmissionRepository;
    private final PackagingRequestRepository packagingRequestRepository;
    private final PackageSubmissionConverter packageSubmissionConverter;

    @Override
    public PackageSubmissionResponse.PackageSubmissionAcceptedResponse acceptPackageSubmission(Long userId, Long packageSubmissionId, Long packagingRequestId) {
        PackagingRequest packagingRequest = packagingRequestRepository.findById(packagingRequestId)
                .orElseThrow(() -> new PackagingRequestException(ErrorStatus.PACKAGING_REQUEST_NOT_FOUND));

        PackageSubmission packageSubmission = packageSubmissionRepository.findById(packageSubmissionId)
                .orElseThrow(() -> new PackageSubmissionException(ErrorStatus.PACKAGE_SUBMISSION_NOT_FOUND));

        packageSubmission.accept(packagingRequest);

        // 배달 방법에 따라서 포장재 전달 상태 변경
        if (packageSubmission.getDeliveryMethod() == DeliveryMethod.DIRECT) {
            packageSubmission.changeStatus(PackageSubmissionStatus.DELIVERING);
        } else if (packageSubmission.getDeliveryMethod() == DeliveryMethod.VIA_COURIER) {
            packageSubmission.changeStatus(PackageSubmissionStatus.PENDING);
        }

        return packageSubmissionConverter.toPackageSubmissionAcceptedResponse(packageSubmission);
    }
}
