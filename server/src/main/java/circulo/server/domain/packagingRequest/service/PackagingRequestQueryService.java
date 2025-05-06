package circulo.server.domain.packagingRequest.service;

import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;

public interface PackagingRequestQueryService {
    PackagingRequestResponse.PackagingRequestListResponse packagingRequests(Long userId);
}
