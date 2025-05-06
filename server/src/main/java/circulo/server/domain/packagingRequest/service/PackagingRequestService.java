package circulo.server.domain.packagingRequest.service;

import circulo.server.domain.packagingRequest.dto.PackagingRequestResponseDto;
import circulo.server.domain.packagingRequest.repository.PackagingRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PackagingRequestService {

    private final PackagingRequestRepository packagingRequestRepository;

    public List<PackagingRequestResponseDto> getRequestsByUserId(Long userId) {
        return packagingRequestRepository.findAllByUser_Id(userId).stream()
                .map(PackagingRequestResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
