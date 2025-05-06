package circulo.server.domain.delivery.dto.response;

import lombok.*;

public class DeliveryResponse {

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DeliveryPendingResponseDTO {
        private Long deliveryId;
        private Integer submissionQuantity;
        private String submissionLocation;
        private String packagingType;
        private String requestLocation;
        private String userName;
        private String storemanName;
    }
}
