package circulo.server.domain.delivery.controller;

import circulo.server.domain.delivery.dto.response.DeliveryResponse;
import circulo.server.domain.delivery.service.DeliveryQueryService;
import circulo.server.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/delivery")
public class DeliveryController {

    private final DeliveryQueryService deliveryQueryService;

    @Operation(
            summary = "배달 대기 상태 리스트 조회 API | by 규리",
            description = "배달 대기 상태 리스트 조회합니다."
    )
    @GetMapping("/pending")
    public ResponseEntity<ApiResponse<List<DeliveryResponse.DeliveryPendingResponseDTO>>> getPendingCourierDeliveries() {
        List<DeliveryResponse.DeliveryPendingResponseDTO> result = deliveryQueryService.getPendingCourierDeliveries();
        return ResponseEntity.ok(ApiResponse.onSuccess(result));
    }

    @Operation(
            summary = "배달 대기 상태 상세 조회 API | by 규리",
            description = "개별 배달 대기 상태 물건 상세 조회합니다."
    )
    @GetMapping("/pending/{deliveryId}")
    public ResponseEntity<ApiResponse<DeliveryResponse.DeliveryPendingResponseDTO>> getPendingCourierDeliveryDetail(
            @PathVariable Long deliveryId
    ) {
        return ResponseEntity.ok(ApiResponse.onSuccess(deliveryQueryService.getPendingDeliveryDetail(deliveryId)));
    }

}


