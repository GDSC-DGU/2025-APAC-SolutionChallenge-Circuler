package circulo.server.domain.delivery.repository;

import circulo.server.domain.delivery.entity.Delivery;
import circulo.server.domain.delivery.entity.enums.DeliveryStatus;
import circulo.server.domain.packageSubmission.entity.enums.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("""
        SELECT d FROM Delivery d
        JOIN FETCH d.packageSubmission ps
        JOIN FETCH d.packagingRequest pr
        JOIN FETCH d.user u
        JOIN FETCH d.storeMan sm
        WHERE d.status = :status
        AND ps.deliveryMethod = :method
    """)
    List<Delivery> findPendingCourierDeliveries(@Param("status") DeliveryStatus status,
                                                @Param("method") DeliveryMethod method);

    @Query("""
        SELECT d FROM Delivery d
        JOIN FETCH d.packageSubmission ps
        JOIN FETCH d.packagingRequest pr
        JOIN FETCH d.user u
        JOIN FETCH d.storeMan sm
        WHERE d.id = :deliveryId
        AND d.status = :status
    """)
    Optional<Delivery> findByIdAndStatus(@Param("deliveryId") Long deliveryId, @Param("status") DeliveryStatus status);
}

