package circulo.server.domain.packageSubmission.service;

import circulo.server.domain.packageSubmission.dto.response.PackageSubmissionResponse;

import java.util.List;

public interface PackageSubmissionQueryService {
    List<PackageSubmissionResponse.PackageSubmissionListItem> packageSubmissions(Long packagingRequestId);
}
