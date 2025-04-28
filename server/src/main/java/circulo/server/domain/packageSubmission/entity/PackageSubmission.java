package circulo.server.domain.packageSubmission.entity;

import circulo.server.domain.packageSubmission.entity.enums.DeliveryMethod;
import circulo.server.domain.user.entity.User;
import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import circulo.server.global.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PackageSubmission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private PackagingRequest packagingRequest;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    private String location;

    private Boolean aiVerified;

    private Boolean selectedBySeller;
}