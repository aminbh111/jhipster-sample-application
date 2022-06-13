package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.RestaurantMenu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the RestaurantMenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RestaurantMenuRepository extends JpaRepository<RestaurantMenu, Long> {}
