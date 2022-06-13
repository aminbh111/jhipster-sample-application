package io.github.jhipster.application.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.application.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RestaurantMenuDataTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RestaurantMenuData.class);
        RestaurantMenuData restaurantMenuData1 = new RestaurantMenuData();
        restaurantMenuData1.setId(1L);
        RestaurantMenuData restaurantMenuData2 = new RestaurantMenuData();
        restaurantMenuData2.setId(restaurantMenuData1.getId());
        assertThat(restaurantMenuData1).isEqualTo(restaurantMenuData2);
        restaurantMenuData2.setId(2L);
        assertThat(restaurantMenuData1).isNotEqualTo(restaurantMenuData2);
        restaurantMenuData1.setId(null);
        assertThat(restaurantMenuData1).isNotEqualTo(restaurantMenuData2);
    }
}
