package circulo.server.domain.packagingRequest.dto.response;

import lombok.*;

public class PackagingRequestResponse {

    @Getter
    @Builder
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class packageRequestSuccess {
        // 포장재 요청 글 id
        Long id;
    }
}
