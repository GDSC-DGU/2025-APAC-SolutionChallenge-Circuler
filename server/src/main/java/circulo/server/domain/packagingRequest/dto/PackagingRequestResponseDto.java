package circulo.server.domain.packagingRequest.dto;

import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PackagingRequestResponseDto {
    private Long request_id;
    private String packaging_type;
    private Integer quantity;
    private String status;
    private LocalDateTime created_at;

    public static PackagingRequestResponseDto fromEntity(PackagingRequest packagingRequest) {
        return new PackagingRequestResponseDto(
                packagingRequest.getId(),
                packagingRequest.getPackagingType().name().toLowerCase(),
                packagingRequest.getQuantity(),
                packagingRequest.getStatus().name().toLowerCase(),
                packagingRequest.getCreatedAt()
        );
    }
}
