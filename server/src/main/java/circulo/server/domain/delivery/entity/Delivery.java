package circulo.server.domain.delivery.entity;

import circulo.server.domain.delivery.entity.enums.DeliveryStatus;
import circulo.server.domain.packageSubmission.entity.PackageSubmission;
import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import circulo.server.domain.user.entity.User;
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
    @JoinColumn(name = "submission_id")
    private PackageSubmission packageSubmission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private PackagingRequest packagingRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryman_id")
    private User deliveryMan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeman_id")
    private User storeMan;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public void assignDeliveryMan(User deliveryMan) {
        this.deliveryMan = deliveryMan;
        this.status = DeliveryStatus.IN_PROGRESS;
    }

    public void changeStatus(DeliveryStatus status) {
        this.status = status;
    }
}