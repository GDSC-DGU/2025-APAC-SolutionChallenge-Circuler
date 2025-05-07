package circulo.server.domain.packagingRequest.service;

import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;

import java.util.List;

public interface PackagingRequestQueryService {
    PackagingRequestResponse.PackagingRequestListResponse packagingRequests(Long userId);
    PackagingRequestResponse.PackagingRequestDetailResponse packagingRequestDetail(Long userId, Long packagingRequestId);
    List<PackagingRequestResponse.PackagingRequestResponseDto> getRequestsByUserId(Long userId);
}
