package circulo.server.domain.packageSubmission.controller;

import circulo.server.domain.packageSubmission.dto.request.PackageSubmissionRequest;
import circulo.server.domain.packageSubmission.dto.response.PackageSubmissionResponse;
import circulo.server.domain.packageSubmission.service.PackageSubmissionCommandService;
import circulo.server.domain.packageSubmission.service.PackageSubmissionQueryService;
import circulo.server.global.apiPayload.ApiResponse;
import circulo.server.global.handler.annotation.Auth;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/packageSubmission")
public class PackageSubmissionController {

    private final PackageSubmissionQueryService packageSubmissionQueryService;
    private final PackageSubmissionCommandService packageSubmissionCommandService;

    @Operation(
            summary = "포장재 전달 신청 등록 API | by 지희",
            description = "일반 사용자가 포장재 전달 신청을 등록합니다."
    )
    @PostMapping("/{packagingRequestId}")
    public ApiResponse<PackageSubmissionResponse.PackageSubmissionSuccessResponse> createPackageSubmission(@Auth Long userId,
            @PathVariable Long packagingRequestId, @Valid @RequestBody PackageSubmissionRequest.CreatePackageSubmissionRequest request) {

        PackageSubmissionResponse.PackageSubmissionSuccessResponse response = packageSubmissionCommandService.createPackageSubmission(userId, packagingRequestId, request);
        return ApiResponse.onSuccess(response);
    }

    @Operation(
            summary = "포장재 사진 등록 API | by 지희",
            description = "일반 사용자가 포장재 사진을 등록하고, 본인이 등록한 포장재 타입과 사진의 포장재 사진이 일치한 지 확인합니다."
    )
    @PostMapping(value = "/verify/{packageSubmissionId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<PackageSubmissionResponse.VerifyResponse> verifyPackageSubmission(
            @Auth Long userId,
            @PathVariable Long packageSubmissionId,
            @Parameter(description = "업로드할 포장재 이미지", required = true,
                    content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(type = "string", format = "binary")))
            @RequestPart("file") MultipartFile file
    ) {
        PackageSubmissionResponse.VerifyResponse response =
                packageSubmissionCommandService.verifyPackageType(userId, packageSubmissionId, file);
        return ApiResponse.onSuccess(response);
    }

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
    public ApiResponse<PackageSubmissionResponse.PackageSubmissionAcceptedResponse> acceptPackageSubmission(
            @Auth Long userId,
            @PathVariable Long packagingRequestId,
            @PathVariable Long packageSubmissionId) {
PackageSubmissionResponse.PackageSubmissionAcceptedResponse response = packageSubmissionCommandService.acceptPackageSubmission(userId, packageSubmissionId, packagingRequestId);
        return ApiResponse.onSuccess(response);
    }

    @Operation(
            summary = "배달 완료 처리 API | by 규리",
            description = "소상공인이 배달 완료 버튼을 누르면 해당 Packaging Submission을 배달 완료 처리합니다."
    )
    @PostMapping("/{packageSubmissionId}/delivered")
    public ApiResponse<Void> markAsDelivered(@PathVariable Long packageSubmissionId) {
        packageSubmissionCommandService.markAsDelivered(packageSubmissionId);
        return ApiResponse.onSuccess(null);
    }

}
