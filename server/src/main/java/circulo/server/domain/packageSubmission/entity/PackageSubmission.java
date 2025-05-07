package circulo.server.domain.packageSubmission.entity;

import circulo.server.domain.packageSubmission.entity.enums.DeliveryMethod;
import circulo.server.domain.packageSubmission.entity.enums.PackageSubmissionStatus;
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
public class PackageSubmission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private Long id;

    // 포장재 제출을 희밍하는 일반 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 포장재를 요청한 소상공인
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeman_id")
    private User storeMan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private PackagingRequest packagingRequest;

    @Enumerated(EnumType.STRING)
    private PackageSubmissionStatus status;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    private String location;

    private Boolean aiVerified;

    private Boolean selectedBySeller;

    public void accept(PackagingRequest packagingRequest) {
        this.packagingRequest = packagingRequest;
        this.storeMan = packagingRequest.getUser();
        this.selectedBySeller = true;
    }

    public void changeStatus(PackageSubmissionStatus status) {
        this.status = status;
    }
}