package circulo.server.domain.packagingRequest.repository;

import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackagingRequestRepository extends JpaRepository<PackagingRequest, Long> {
    List<PackagingRequest> findAllByUserId(Long userId);
}
