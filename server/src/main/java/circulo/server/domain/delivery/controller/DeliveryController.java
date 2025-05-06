package circulo.server.domain.delivery.controller;

import circulo.server.domain.delivery.dto.response.DeliveryResponse;
import circulo.server.domain.delivery.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Operation(
            summary = "배달 대기 상태인 매칭 리스트 조회 API | by 규리",
            description = "배달 대기 상태인 매칭 리스트 조회합니다."
    )
    @GetMapping("/pending")
    public ResponseEntity<List<DeliveryResponse.DeliveryPendingResponseDTO>> getPendingCourierDeliveries() {
        return ResponseEntity.ok(deliveryService.getPendingCourierDeliveries());
    }

}

