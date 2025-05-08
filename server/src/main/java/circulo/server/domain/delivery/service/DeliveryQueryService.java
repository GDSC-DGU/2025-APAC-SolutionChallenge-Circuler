package circulo.server.domain.delivery.service;

import circulo.server.domain.delivery.dto.response.DeliveryResponse;

import java.util.List;

public interface DeliveryQueryService {
    List<DeliveryResponse.DeliveryPendingResponseDTO> getPendingCourierDeliveries();
}

