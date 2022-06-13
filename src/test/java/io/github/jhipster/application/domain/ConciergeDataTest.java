package io.github.jhipster.application.domain;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.jhipster.application.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ConciergeDataTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConciergeData.class);
        ConciergeData conciergeData1 = new ConciergeData();
        conciergeData1.setId(1L);
        ConciergeData conciergeData2 = new ConciergeData();
        conciergeData2.setId(conciergeData1.getId());
        assertThat(conciergeData1).isEqualTo(conciergeData2);
        conciergeData2.setId(2L);
        assertThat(conciergeData1).isNotEqualTo(conciergeData2);
        conciergeData1.setId(null);
        assertThat(conciergeData1).isNotEqualTo(conciergeData2);
    }
}
