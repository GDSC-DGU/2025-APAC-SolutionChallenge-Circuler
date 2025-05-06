package circulo.server.domain.packageSubmission.repository;

import circulo.server.domain.packageSubmission.entity.PackageSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageSubmissionRepository extends JpaRepository<PackageSubmission, Long> {
    List<PackageSubmission> findAllByPackagingRequestId(Long packagingRequestId);
}
