package circulo.server.domain.packagingRequest.converter;

import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;
import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PackagingRequestConverter {

    public PackagingRequestResponse.PackageRequestSuccess toPackageRequestSuccess(PackagingRequest packagingRequest) {
        return PackagingRequestResponse.PackageRequestSuccess.builder()
                .id(packagingRequest.getId())
                .build();
    }

    public PackagingRequestResponse.PackagingRequestListResponse toPackageRequestList(List<PackagingRequest> packagingRequestList) {

        List<PackagingRequestResponse.PackagingRequestListItem> result = packagingRequestList.stream()
                .map(r -> new PackagingRequestResponse.PackagingRequestListItem(
                        r.getId(),
                        r.getPackagingType().name(),
                        r.getQuantity(),
                        r.getLocation(),
                        "0m" // TODO: 거리 계산 필요시 여기에 적용
                ))
                .toList();

        return PackagingRequestResponse.PackagingRequestListResponse.builder()
                .results(result)
                .build();
    }

    public PackagingRequestResponse.PackagingRequestDetailResponse toPackageRequestDetailResponse(PackagingRequest packagingRequest) {
        return PackagingRequestResponse.PackagingRequestDetailResponse.builder()
                .id(packagingRequest.getId())
                .quantity(packagingRequest.getQuantity())
                .location(packagingRequest.getLocation())
                .type(packagingRequest.getPackagingType().name())
                .distance("0m")
                .build();
    }
}
