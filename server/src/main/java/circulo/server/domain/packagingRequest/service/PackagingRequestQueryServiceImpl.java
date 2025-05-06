package circulo.server.domain.packagingRequest.service;

import circulo.server.domain.packagingRequest.converter.PackagingRequestConverter;
import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;
import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import circulo.server.domain.packagingRequest.repository.PackagingRequestRepository;
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
    public PackagingRequestResponse.PackagingRequestListResponse packagingRequests(Long userId) {
        List<PackagingRequest> requests = packagingRequestRepository.findAll();

        return packagingRequestConverter.toPackageRequestList(requests);
    }
}