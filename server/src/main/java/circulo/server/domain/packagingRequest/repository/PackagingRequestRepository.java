package circulo.server.domain.packagingRequest.repository;

import circulo.server.domain.packagingRequest.entity.PackagingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagingRequestRepository extends JpaRepository<PackagingRequest, Long> {
}
