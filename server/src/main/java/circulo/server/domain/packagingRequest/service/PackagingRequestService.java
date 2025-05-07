package circulo.server.domain.packagingRequest.service;

import circulo.server.domain.packagingRequest.converter.PackagingRequestConverter;
import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;
import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import circulo.server.domain.packagingRequest.repository.PackagingRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PackagingRequestService {

    private final PackagingRequestRepository packagingRequestRepository;
    private final PackagingRequestConverter packagingRequestConverter;

    public List<PackagingRequestResponse.PackagingRequestResponseDto> getRequestsByUserId(Long userId) {
        List<PackagingRequest> requests = packagingRequestRepository.findAllByUser_Id(userId);
        return requests.stream()
                .map(packagingRequestConverter::toPackagingRequestResponseDto)
                .collect(Collectors.toList());
    }
}
