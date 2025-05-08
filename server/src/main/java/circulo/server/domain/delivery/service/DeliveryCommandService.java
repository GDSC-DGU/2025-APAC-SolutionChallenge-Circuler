package circulo.server.domain.delivery.service;

public interface DeliveryCommandService {
    void applyForDelivery(Long deliveryId, Long userId);
}
