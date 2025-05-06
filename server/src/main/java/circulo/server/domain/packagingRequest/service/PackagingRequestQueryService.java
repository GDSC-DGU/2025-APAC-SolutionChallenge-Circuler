package circulo.server.domain.packagingRequest.service;

import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;

import java.util.List;

public interface PackagingRequestQueryService {
    List<PackagingRequestResponse.PackagingRequestListItem> packagingRequests(Long userId);
    PackagingRequestResponse.PackagingRequestDetailResponse packagingRequestDetail(Long userId, Long packagingRequestId);
}
