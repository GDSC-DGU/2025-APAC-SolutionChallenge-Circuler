package circulo.server.domain.packagingRequest.converter;

import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;
import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PackagingRequestConverter {

    public PackagingRequestResponse.packageRequestSuccess toPackageRequestSuccess(PackagingRequest packagingRequest) {
        return PackagingRequestResponse.packageRequestSuccess.builder()
                .id(packagingRequest.getId())
                .build();
    }
}
