package circulo.server.domain.packageSubmission.controller;

import circulo.server.domain.packageSubmission.dto.response.PackageSubmissionResponse;
import circulo.server.domain.packageSubmission.service.PackageSubmissionCommandService;
import circulo.server.domain.packageSubmission.service.PackageSubmissionQueryService;
import circulo.server.global.apiPayload.ApiResponse;
import circulo.server.global.handler.annotation.Auth;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/packageSubmission")
public class PackageSubmissionController {

    private final PackageSubmissionQueryService packageSubmissionQueryService;
    private final PackageSubmissionCommandService packageSubmissionCommandService;

    @Operation(
            summary = "포장재 전달 신청 리스트 조회 API (자영업자 전용)| by 지희",
            description = "자영업자 등록한 포장재 요청에 대해 일반 사용자가 포장재 전달 신청한 리스트 조회합니다."
    )
    @GetMapping("/{packagingRequestId}/submissions")
    public ApiResponse<List<PackageSubmissionResponse.PackageSubmissionListItem>> getPackagingRequestList(@Auth Long userId, @PathVariable Long packagingRequestId) {
        List<PackageSubmissionResponse.PackageSubmissionListItem> response = packageSubmissionQueryService.packageSubmissions(packagingRequestId);
        return ApiResponse.onSuccess(response);
    }

    @Operation(
            summary = "포장재 전달 신청 수락 API (자영업자 전용)| by 지희",
            description = "포장재 전달 신청 목록 중 하나를 선택하여 'accept' 버튼을 누르면 수락합니다."
    )
    @GetMapping("/{packagingRequestId}/submissions/{packageSubmissionId}/accept")
    public ApiResponse<PackageSubmissionResponse.PackageSubmissionAcceptedResponse> getPackageSubmission(
            @Auth Long userId,
            @PathVariable Long packagingRequestId,
            @PathVariable Long packageSubmissionId) {
PackageSubmissionResponse.PackageSubmissionAcceptedResponse response = packageSubmissionCommandService.acceptPackageSubmission(userId, packageSubmissionId, packagingRequestId);
        return ApiResponse.onSuccess(response);
    }
}
