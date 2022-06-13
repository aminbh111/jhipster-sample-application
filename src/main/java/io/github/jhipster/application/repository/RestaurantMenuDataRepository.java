package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.RestaurantMenuData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RestaurantMenuData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RestaurantMenuDataRepository extends JpaRepository<RestaurantMenuData, Long> {}
