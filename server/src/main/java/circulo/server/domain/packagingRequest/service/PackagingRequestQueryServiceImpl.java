package circulo.server.domain.packagingRequest.service;

import circulo.server.domain.packagingRequest.converter.PackagingRequestConverter;
import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;
import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import circulo.server.domain.packagingRequest.repository.PackagingRequestRepository;
import circulo.server.global.apiPayload.code.exception.custom.PackagingRequestException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackagingRequestQueryServiceImpl implements PackagingRequestQueryService {

    private final PackagingRequestRepository packagingRequestRepository;
    private final PackagingRequestConverter packagingRequestConverter;

    @Override
    @Transactional(readOnly = true)
    public List<PackagingRequestResponse.PackagingRequestListItem> packagingRequests(Long userId) {
        List<PackagingRequest> requests = packagingRequestRepository.findAll()
                .stream()
                .limit(10)
                .toList();

        return packagingRequestConverter.toPackageRequestList(requests);
    }

    @Override
    @Transactional(readOnly = true)
    public PackagingRequestResponse.PackagingRequestDetailResponse packagingRequestDetail(Long userId, Long packagingRequestId) {
        PackagingRequest request = packagingRequestRepository.findById(packagingRequestId).orElseThrow(() -> new PackagingRequestException(ErrorStatus.PACKAGING_REQUEST_NOT_FOUND));

        return packagingRequestConverter.toPackageRequestDetailResponse(request);
    }

}