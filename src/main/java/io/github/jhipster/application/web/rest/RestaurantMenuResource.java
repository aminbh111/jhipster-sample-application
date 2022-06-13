package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.RestaurantMenu;
import io.github.jhipster.application.repository.RestaurantMenuRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.RestaurantMenu}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RestaurantMenuResource {

    private final Logger log = LoggerFactory.getLogger(RestaurantMenuResource.class);

    private static final String ENTITY_NAME = "restaurantMenu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RestaurantMenuRepository restaurantMenuRepository;

    public RestaurantMenuResource(RestaurantMenuRepository restaurantMenuRepository) {
        this.restaurantMenuRepository = restaurantMenuRepository;
    }

    /**
     * {@code POST  /restaurant-menus} : Create a new restaurantMenu.
     *
     * @param restaurantMenu the restaurantMenu to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new restaurantMenu, or with status {@code 400 (Bad Request)} if the restaurantMenu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/restaurant-menus")
    public ResponseEntity<RestaurantMenu> createRestaurantMenu(@RequestBody RestaurantMenu restaurantMenu) throws URISyntaxException {
        log.debug("REST request to save RestaurantMenu : {}", restaurantMenu);
        if (restaurantMenu.getId() != null) {
            throw new BadRequestAlertException("A new restaurantMenu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RestaurantMenu result = restaurantMenuRepository.save(restaurantMenu);
        return ResponseEntity
            .created(new URI("/api/restaurant-menus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /restaurant-menus/:id} : Updates an existing restaurantMenu.
     *
     * @param id the id of the restaurantMenu to save.
     * @param restaurantMenu the restaurantMenu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantMenu,
     * or with status {@code 400 (Bad Request)} if the restaurantMenu is not valid,
     * or with status {@code 500 (Internal Server Error)} if the restaurantMenu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/restaurant-menus/{id}")
    public ResponseEntity<RestaurantMenu> updateRestaurantMenu(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantMenu restaurantMenu
    ) throws URISyntaxException {
        log.debug("REST request to update RestaurantMenu : {}, {}", id, restaurantMenu);
        if (restaurantMenu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantMenu.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantMenuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RestaurantMenu result = restaurantMenuRepository.save(restaurantMenu);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantMenu.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /restaurant-menus/:id} : Partial updates given fields of an existing restaurantMenu, field will ignore if it is null
     *
     * @param id the id of the restaurantMenu to save.
     * @param restaurantMenu the restaurantMenu to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated restaurantMenu,
     * or with status {@code 400 (Bad Request)} if the restaurantMenu is not valid,
     * or with status {@code 404 (Not Found)} if the restaurantMenu is not found,
     * or with status {@code 500 (Internal Server Error)} if the restaurantMenu couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/restaurant-menus/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RestaurantMenu> partialUpdateRestaurantMenu(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RestaurantMenu restaurantMenu
    ) throws URISyntaxException {
        log.debug("REST request to partial update RestaurantMenu partially : {}, {}", id, restaurantMenu);
        if (restaurantMenu.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, restaurantMenu.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!restaurantMenuRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RestaurantMenu> result = restaurantMenuRepository
            .findById(restaurantMenu.getId())
            .map(existingRestaurantMenu -> {
                if (restaurantMenu.getDate() != null) {
                    existingRestaurantMenu.setDate(restaurantMenu.getDate());
                }
                if (restaurantMenu.getPublish() != null) {
                    existingRestaurantMenu.setPublish(restaurantMenu.getPublish());
                }
                if (restaurantMenu.getContentPosition() != null) {
                    existingRestaurantMenu.setContentPosition(restaurantMenu.getContentPosition());
                }
                if (restaurantMenu.getImagePosition() != null) {
                    existingRestaurantMenu.setImagePosition(restaurantMenu.getImagePosition());
                }

                return existingRestaurantMenu;
            })
            .map(restaurantMenuRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, restaurantMenu.getId().toString())
        );
    }

    /**
     * {@code GET  /restaurant-menus} : get all the restaurantMenus.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of restaurantMenus in body.
     */
    @GetMapping("/restaurant-menus")
    public ResponseEntity<List<RestaurantMenu>> getAllRestaurantMenus(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of RestaurantMenus");
        Page<RestaurantMenu> page = restaurantMenuRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /restaurant-menus/:id} : get the "id" restaurantMenu.
     *
     * @param id the id of the restaurantMenu to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the restaurantMenu, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/restaurant-menus/{id}")
    public ResponseEntity<RestaurantMenu> getRestaurantMenu(@PathVariable Long id) {
        log.debug("REST request to get RestaurantMenu : {}", id);
        Optional<RestaurantMenu> restaurantMenu = restaurantMenuRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(restaurantMenu);
    }

    /**
     * {@code DELETE  /restaurant-menus/:id} : delete the "id" restaurantMenu.
     *
     * @param id the id of the restaurantMenu to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/restaurant-menus/{id}")
    public ResponseEntity<Void> deleteRestaurantMenu(@PathVariable Long id) {
        log.debug("REST request to delete RestaurantMenu : {}", id);
        restaurantMenuRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
