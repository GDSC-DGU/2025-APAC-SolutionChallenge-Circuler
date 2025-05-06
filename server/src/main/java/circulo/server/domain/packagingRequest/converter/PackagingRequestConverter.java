package circulo.server.domain.packagingRequest.converter;

import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;
import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PackagingRequestConverter {

    public PackagingRequestResponse.packageRequestSuccess toPackageRequestSuccess(PackagingRequest packagingRequest) {
        return PackagingRequestResponse.packageRequestSuccess.builder()
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
}
