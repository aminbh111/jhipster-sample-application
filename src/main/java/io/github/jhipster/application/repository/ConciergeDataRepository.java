package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ConciergeData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ConciergeData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConciergeDataRepository extends JpaRepository<ConciergeData, Long> {}
