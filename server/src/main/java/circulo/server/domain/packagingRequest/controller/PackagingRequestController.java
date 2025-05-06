package circulo.server.domain.packagingRequest.controller;

import circulo.server.domain.packagingRequest.dto.request.PackagingRequestRequest;
import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;
import circulo.server.domain.packagingRequest.service.PackagingRequestCommandService;
import circulo.server.global.apiPayload.ApiResponse;
import circulo.server.global.handler.annotation.Auth;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/packagingRequest")
public class PackagingRequestController {

    private final PackagingRequestCommandService packagingRequestCommandService;

    @Operation(
            summary = "포장재 요청 등록 API | by 지희",
            description = "일반 사용자가 포장재 요청 등록을 합니다."
    )
    @PostMapping("")
    public ApiResponse<PackagingRequestResponse.packageRequestSuccess> packagingRequest(@Auth Long userId, @RequestBody @Valid PackagingRequestRequest.CreatePackagingRequest request) {
        PackagingRequestResponse.packageRequestSuccess response = packagingRequestCommandService.createPackagingRequest(userId, request);
        return ApiResponse.onSuccess(response);
    }
}
