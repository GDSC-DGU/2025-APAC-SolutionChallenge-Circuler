package circulo.server.domain.delivery.service;

import circulo.server.domain.delivery.dto.response.DeliveryResponse;
import circulo.server.domain.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public List<DeliveryResponse.DeliveryPendingResponseDTO> getPendingCourierDeliveries() {
        return deliveryRepository.findPendingCourierDeliveries().stream()
                .map(d -> DeliveryResponse.DeliveryPendingResponseDTO.builder()
                        .deliveryId(d.getId())
                        .submissionQuantity(d.getPackageSubmission().getQuantity())
                        .submissionLocation(d.getPackageSubmission().getLocation())
                        .packagingType(d.getPackagingRequest().getPackagingType().name())
                        .requestLocation(d.getPackagingRequest().getLocation())
                        .userName(d.getUser().getName())
                        .storemanName(d.getStoreMan().getName())
                        .build()
                )
                .toList();
    }
}
