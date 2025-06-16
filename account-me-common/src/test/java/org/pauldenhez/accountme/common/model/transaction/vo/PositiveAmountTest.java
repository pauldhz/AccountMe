package org.pauldenhez.accountme.common.model.transaction.vo;

import org.junit.jupiter.api.Test;
import org.pauldenhez.accountme.common.model.transaction.vo.PositiveAmount;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositiveAmountTest {

    @Test
    public void shouldNotAcceptNegativeAmount() {

        assertThatThrownBy(() -> {
           new PositiveAmount(-5.00);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("should be positive");
    }

}