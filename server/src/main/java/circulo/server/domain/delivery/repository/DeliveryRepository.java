package circulo.server.domain.delivery.repository;

import circulo.server.domain.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("""
        SELECT d FROM Delivery d
        JOIN FETCH d.packageSubmission ps
        JOIN FETCH d.packagingRequest pr
        JOIN FETCH d.user u
        JOIN FETCH d.storeMan sm
        WHERE d.status = 'PENDING'
        AND ps.deliveryMethod = 'VIA_COURIER'
    """)
    List<Delivery> findPendingCourierDeliveries();
}
