package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ServiceData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ServiceData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceDataRepository extends JpaRepository<ServiceData, Long> {}
