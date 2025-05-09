package circulo.server.domain.delivery.service;

import circulo.server.domain.delivery.converter.DeliveryConverter;
import circulo.server.domain.delivery.dto.response.DeliveryResponse;
import circulo.server.domain.delivery.entity.Delivery;
import circulo.server.domain.delivery.entity.enums.DeliveryStatus;
import circulo.server.domain.delivery.repository.DeliveryRepository;
import circulo.server.domain.packageSubmission.entity.enums.DeliveryMethod;
import circulo.server.global.apiPayload.code.exception.custom.BadRequestException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryQueryServiceImpl implements DeliveryQueryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryConverter deliveryConverter;

    @Override
    public List<DeliveryResponse.DeliveryPendingResponseDTO> getPendingCourierDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findPendingCourierDeliveries(DeliveryStatus.PENDING, DeliveryMethod.VIA_COURIER);
        if (deliveries.isEmpty()) {
            throw new BadRequestException(ErrorStatus.NO_PENDING_DELIVERIES_FOUND); // Handling the error with ErrorStatus enum
        }
        return deliveries.stream()
                .map(deliveryConverter::toPendingResponseDTO)
                .toList();
    }

    @Override
    public DeliveryResponse.DeliveryPendingResponseDTO getPendingDeliveryDetail(Long deliveryId) {
        Delivery delivery = deliveryRepository.findByIdAndStatus(deliveryId, DeliveryStatus.PENDING)
                .orElseThrow(() -> new BadRequestException(ErrorStatus.DELIVERY_NOT_FOUND));
        return deliveryConverter.toPendingResponseDTO(delivery);
    }
}

