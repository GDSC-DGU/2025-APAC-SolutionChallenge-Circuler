package circulo.server.domain.delivery.service;

import circulo.server.domain.delivery.entity.Delivery;
import circulo.server.domain.delivery.entity.enums.DeliveryStatus;
import circulo.server.domain.delivery.repository.DeliveryRepository;
import circulo.server.domain.user.entity.User;
import circulo.server.domain.user.repository.UserRepository;
import circulo.server.global.apiPayload.code.exception.custom.DeliveryException;
import circulo.server.global.apiPayload.code.exception.custom.UserException;
import circulo.server.global.apiPayload.code.status.ErrorStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryCommandServiceImpl implements DeliveryCommandService {

    private final DeliveryRepository deliveryRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void applyForDelivery(Long deliveryId, Long userId) {
        Delivery delivery = deliveryRepository.findByIdAndStatus(deliveryId, DeliveryStatus.PENDING)
                .orElseThrow(() -> new DeliveryException(ErrorStatus.DELIVERY_NOT_FOUND));

        User deliveryMan = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));

        delivery.assignDeliveryMan(deliveryMan);
    }
}