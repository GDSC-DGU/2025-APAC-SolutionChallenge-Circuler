package circulo.server.domain.packageSubmission.controller;

import circulo.server.domain.packageSubmission.dto.response.PackageSubmissionResponse;
import circulo.server.domain.packageSubmission.service.PackageSubmissionQueryService;
import circulo.server.domain.packagingRequest.dto.response.PackagingRequestResponse;
import circulo.server.global.apiPayload.ApiResponse;
import circulo.server.global.handler.annotation.Auth;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/packageSubmission")
public class PackageSubmissionController {

    private final PackageSubmissionQueryService packageSubmissionQueryService;

    @Operation(
            summary = "포장재 전달 신청 리스트 조회 API (자영업자 전용)| by 지희",
            description = "자영업자 등록한 포장재 요청에 대해 일반 사용자가 포장재 전달 신청한 리스트 조회합니다."
    )
    @GetMapping("/{packagingRequestId}")
    public ApiResponse<List<PackageSubmissionResponse.PackageSubmissionListItem>> getPackagingRequestList(@Auth Long userId, @PathVariable Long packagingRequestId) {
        List<PackageSubmissionResponse.PackageSubmissionListItem> response = packageSubmissionQueryService.packageSubmissions(packagingRequestId);
        return ApiResponse.onSuccess(response);
    }
}
