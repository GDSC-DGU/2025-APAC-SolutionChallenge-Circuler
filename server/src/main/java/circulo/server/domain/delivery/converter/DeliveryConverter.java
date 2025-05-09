package circulo.server.domain.delivery.converter;

import circulo.server.domain.delivery.dto.response.DeliveryResponse;
import circulo.server.domain.delivery.entity.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryConverter {

    public DeliveryResponse.DeliveryPendingResponseDTO toPendingResponseDTO(Delivery delivery) {
        return DeliveryResponse.DeliveryPendingResponseDTO.builder()
                .deliveryId(delivery.getId())
                .submissionQuantity(delivery.getPackageSubmission().getQuantity())
                .submissionLocation(delivery.getPackageSubmission().getLocation())
                .packagingType(delivery.getPackagingRequest().getPackagingType().name())
                .requestLocation(delivery.getPackagingRequest().getLocation())
                .userName(delivery.getUser().getName())
                .storemanName(delivery.getStoreMan().getName())
                .build();
    }
}
