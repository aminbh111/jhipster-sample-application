package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Playlist;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Playlist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {}
