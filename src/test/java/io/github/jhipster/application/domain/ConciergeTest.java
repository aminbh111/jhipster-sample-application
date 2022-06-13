package io.github.jhipster.application.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.application.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ConciergeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Concierge.class);
        Concierge concierge1 = new Concierge();
        concierge1.setId(1L);
        Concierge concierge2 = new Concierge();
        concierge2.setId(concierge1.getId());
        assertThat(concierge1).isEqualTo(concierge2);
        concierge2.setId(2L);
        assertThat(concierge1).isNotEqualTo(concierge2);
        concierge1.setId(null);
        assertThat(concierge1).isNotEqualTo(concierge2);
    }
}
