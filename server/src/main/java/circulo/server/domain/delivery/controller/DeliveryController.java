package circulo.server.domain.delivery.controller;

import circulo.server.domain.delivery.dto.response.DeliveryResponse;
import circulo.server.domain.delivery.service.DeliveryCommandService;
import circulo.server.domain.delivery.service.DeliveryQueryService;
import circulo.server.global.apiPayload.ApiResponse;
import circulo.server.global.handler.annotation.Auth;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/delivery")
public class DeliveryController {

    private final DeliveryQueryService deliveryQueryService;
    private final DeliveryCommandService deliveryCommandService;

    @Operation(
            summary = "배달 대기 상태 리스트 조회 API | by 규리",
            description = "배달 대기 상태 리스트 조회합니다."
    )
    @GetMapping("/pending")
    public ApiResponse<List<DeliveryResponse.DeliveryPendingResponseDTO>> getPendingCourierDeliveries() {
        List<DeliveryResponse.DeliveryPendingResponseDTO> result = deliveryQueryService.getPendingCourierDeliveries();
        return ApiResponse.onSuccess(result);
    }

    @Operation(
            summary = "배달 대기 상태 상세 조회 API | by 규리",
            description = "개별 배달 대기 상태 물건 상세 조회합니다."
    )
    @GetMapping("/pending/{deliveryId}")
    public ApiResponse<DeliveryResponse.DeliveryPendingResponseDTO> getPendingCourierDeliveryDetail(
            @PathVariable Long deliveryId
    ) {
        return ApiResponse.onSuccess(deliveryQueryService.getPendingDeliveryDetail(deliveryId));
    }

    @Operation(
            summary = "배달 매칭 API | by 규리",
            description = "배달 버튼 누르면 해당 사용자를 배달원으로 배달 매칭합니다."
    )
    @PostMapping("/apply/{deliveryId}")
    public ApiResponse<Void> applyForDelivery(
            @PathVariable Long deliveryId,
            @Auth Long userId
    ) {
        deliveryCommandService.applyForDelivery(deliveryId, userId);
        return ApiResponse.onSuccess(null);
    }

}


