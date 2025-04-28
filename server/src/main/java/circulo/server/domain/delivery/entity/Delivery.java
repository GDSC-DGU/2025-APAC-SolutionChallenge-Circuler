package circulo.server.domain.delivery.entity;

import circulo.server.domain.delivery.entity.enums.DeliveryStatus;
import circulo.server.domain.user.entity.User;
import circulo.server.domain.packageSubmission.entity.PackageSubmission;
import circulo.server.global.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User deliverer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id")
    private PackageSubmission packageSubmission;


    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}