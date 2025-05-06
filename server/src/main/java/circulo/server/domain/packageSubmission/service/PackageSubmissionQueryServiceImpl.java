package circulo.server.domain.packageSubmission.service;

import circulo.server.domain.packageSubmission.converter.PackageSubmissionConverter;
import circulo.server.domain.packageSubmission.dto.response.PackageSubmissionResponse;
import circulo.server.domain.packageSubmission.entity.PackageSubmission;
import circulo.server.domain.packageSubmission.repository.PackageSubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PackageSubmissionQueryServiceImpl implements PackageSubmissionQueryService{

    private final PackageSubmissionRepository packageSubmissionRepository;
    private final PackageSubmissionConverter packageSubmissionConverter;

    @Override
    public List<PackageSubmissionResponse.PackageSubmissionListItem> packageSubmissions(Long packagingRequestId) {
        List<PackageSubmission> submissions = packageSubmissionRepository.findAllByPackagingRequestId(packagingRequestId);

        return packageSubmissionConverter.toPackageRequestList(submissions);
    }
}
