package circulo.server.domain.packageSubmission.converter;

import circulo.server.domain.packageSubmission.dto.response.PackageSubmissionResponse;
import circulo.server.domain.packageSubmission.entity.PackageSubmission;
import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;
import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PackageSubmissionConverter {

    public List<PackageSubmissionResponse.PackageSubmissionListItem> toPackageRequestList(List<PackageSubmission> packageSubmissionList) {

        return packageSubmissionList.stream()
                .map(r -> new PackageSubmissionResponse.PackageSubmissionListItem(
                        r.getId(),
                        r.getQuantity(),
                        r.getLocation(),
                        r.getDeliveryMethod()
                ))
                .toList();
    }
}
