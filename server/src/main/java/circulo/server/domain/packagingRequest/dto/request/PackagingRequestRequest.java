package circulo.server.domain.packagingRequest.dto.request;

import circulo.server.domain.packagingRequest.entity.enums.PackagingType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PackagingRequestRequest {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreatePackagingRequest {
        @NotNull(message = "포장재 타입은 필수 입력 값입니다.")
        private PackagingType type;
        @NotNull(message = "포장재 수량은 필수 입력 값입니다.")
        private Integer quantity;
        @NotBlank(message = "사용자 위치는 필수 입력 값입니다.")
        private String location;
    }
}
