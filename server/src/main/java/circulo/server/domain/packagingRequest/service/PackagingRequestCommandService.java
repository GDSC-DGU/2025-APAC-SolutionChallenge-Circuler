package circulo.server.domain.packagingRequest.service;

import circulo.server.domain.packagingRequest.dto.request.PackagingRequestRequest;
import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;

public interface PackagingRequestCommandService {

    PackagingRequestResponse.PackageRequestSuccessResponse createPackagingRequest(Long userId, PackagingRequestRequest.CreatePackagingRequest request);
}
