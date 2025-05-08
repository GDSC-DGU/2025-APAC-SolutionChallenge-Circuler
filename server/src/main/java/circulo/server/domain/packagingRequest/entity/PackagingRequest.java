package circulo.server.domain.packagingRequest.entity;

import circulo.server.domain.packagingRequest.entity.enums.PackagingRequestStatus;
import circulo.server.domain.packagingRequest.entity.enums.PackagingType;
import circulo.server.domain.user.entity.User;
import circulo.server.global.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PackagingRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PackagingType packagingType;

    private Integer quantity;

    private String location;

    @Enumerated(EnumType.STRING)
    private PackagingRequestStatus status;

    public void changeStatus(PackagingRequestStatus status) {
        this.status = status;
    }
}