package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Concierge;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Concierge entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConciergeRepository extends JpaRepository<Concierge, Long> {}
